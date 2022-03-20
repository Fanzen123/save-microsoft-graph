package fr.lecko.util;

import com.microsoft.graph.models.Message;
import fr.lecko.contract.save_microsoft_graph.dto.Mail;

public class Factory {

    public static Mail createMail(Message message) {
        Mail mail = new Mail();
        mail.setFrom(message.from.emailAddress.address);
        mail.setName(message.subject);
        mail.setDate(message.receivedDateTime);
        mail.setContent(message.body.content);
        return mail;
    }

}
