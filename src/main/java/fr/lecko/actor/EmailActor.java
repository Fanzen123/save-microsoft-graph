package fr.lecko.actor;

import fr.lecko.contract.save_microsoft_graph.dto.Credentials;
import fr.lecko.contract.save_microsoft_graph.dto.Email;
import fr.lecko.dao.MongoDB;
import fr.lecko.util.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmailActor {

    @Autowired
    MongoDB mongoDB;

    @Autowired
    AuthenticationActor authenticationActor;

    public List<Email> getEmails() {
        return mongoDB.getEmails();
    }

    public void save() {
        List<Email> emails = new ArrayList<>();
        authenticationActor.getUserRequestBuilder()
                .messages().buildRequest().get()
                .getCurrentPage().forEach(message -> emails.add(Factory.createEmail(message)));
    }

}
