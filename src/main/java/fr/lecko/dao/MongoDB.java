package fr.lecko.dao;

import fr.lecko.contract.save_microsoft_graph.dto.Email;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class MongoDB {

    private List<Email> emails;

    public MongoDB() {
        emails = Arrays.asList(createEmail());
    }

    private Email createEmail() {
        Email email = new Email();
        email.setName("Example");
        email.setDate(OffsetDateTime.parse("2022-03-17T19:18:00.064+01:00"));
        email.setFrom("Dupont");
        email.setContent("Bonjour, comment allez vous ? Cordialement");
        return email;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }
}
