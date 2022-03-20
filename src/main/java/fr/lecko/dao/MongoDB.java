package fr.lecko.dao;

import fr.lecko.contract.save_microsoft_graph.dto.Mail;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class MongoDB {

    private List<Mail> mails;

    public MongoDB() {
        mails = Arrays.asList(createMail());
    }

    private Mail createMail() {
        Mail mail = new Mail();
        mail.setName("Example");
        mail.setDate(OffsetDateTime.parse("2022-03-17T19:18:00.064+01:00"));
        mail.setFrom("Dupont");
        mail.setContent("Bonjour, comment allez vous ? Cordialement");
        return mail;
    }

    public List<Mail> getMails() {
        return mails;
    }

    public void setMails(List<Mail> mails) {
        this.mails = mails;
    }
}
