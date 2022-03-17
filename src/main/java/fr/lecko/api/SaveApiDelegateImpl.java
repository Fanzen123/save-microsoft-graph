package fr.lecko.api;

import fr.lecko.actor.EmailActor;
import fr.lecko.contract.save_microsoft_graph.api.SaveApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SaveApiDelegateImpl implements SaveApiDelegate {

    @Autowired
    EmailActor emailActor;

    @Override
    public ResponseEntity<Void> save() {
        emailActor.save();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
