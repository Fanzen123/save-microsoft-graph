package fr.lecko.util;

import com.microsoft.graph.models.Message;
import fr.lecko.contract.save_microsoft_graph.dto.Email;

public class Factory {

    public static Email createEmail(Message message) {
        Email email = new Email();
        email.setFrom(message.from.emailAddress.address);
        email.setName(message.subject);
        email.setDate(message.receivedDateTime);
        email.setContent(message.body.content);
        return email;
    }
}
