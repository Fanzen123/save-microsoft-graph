package fr.lecko.api;

import fr.lecko.actor.MailActor;
import fr.lecko.contract.save_microsoft_graph.api.MailsApiDelegate;
import fr.lecko.contract.save_microsoft_graph.dto.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailApiDelegateImpl implements MailsApiDelegate {

    @Autowired
    MailActor mailActor;

    @Override
    public ResponseEntity<List<Mail>> getMails() {
        return new ResponseEntity<>(mailActor.getMails(), HttpStatus.OK);
    }
}
