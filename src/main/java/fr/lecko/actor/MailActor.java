package fr.lecko.actor;

import com.microsoft.graph.models.User;
import fr.lecko.contract.save_microsoft_graph.dto.Mail;
import fr.lecko.dao.MongoDB;
import fr.lecko.util.Authentication;
import fr.lecko.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MailActor {

    public static final String MAIL_PARAM = "mail";
    public static final String ID_PARAM = "id";
    public static final String VIEW_MAIL_PARAM = "subject, from, receivedDateTime, body";

    private Logger logger = LoggerFactory.getLogger(MailActor.class);

    @Autowired
    MongoDB mongoDB;

    public List<Mail> getMails() {
        return mongoDB.getMails();
    }

    public void save(String userMail) {
        List<Mail> mails = new ArrayList<>();
        final String filter = String.format("%s eq '%s'", MAIL_PARAM, userMail);
        Optional<User> users =
                Authentication.getGraphClient()
                        .users()
                        .buildRequest()
                        .filter(filter)
                        .select(ID_PARAM).get().getCurrentPage()
                        .stream().findFirst();

        users.ifPresent(user -> {
            Authentication.getGraphClient()
                    .users(user.id)
                    .messages().buildRequest()
                    .select(VIEW_MAIL_PARAM).get().getCurrentPage()
                    .stream().forEach(message -> mails.add(Factory.createMail(message)));

            mails.forEach(mail -> logger.info("Mail found : " + mail.getName()));
        });
    }

}
