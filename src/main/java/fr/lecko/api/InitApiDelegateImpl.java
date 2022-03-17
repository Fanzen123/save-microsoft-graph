package fr.lecko.api;

import fr.lecko.actor.AuthenticationActor;
import fr.lecko.actor.EmailActor;
import fr.lecko.contract.save_microsoft_graph.api.InitApiDelegate;
import fr.lecko.contract.save_microsoft_graph.api.SaveApiDelegate;
import fr.lecko.contract.save_microsoft_graph.dto.Credentials;
import fr.lecko.contract.save_microsoft_graph.dto.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitApiDelegateImpl implements InitApiDelegate {

    @Autowired
    AuthenticationActor authenticationActor;

    @Override
    public ResponseEntity<Void> init(Credentials credentials) {
        authenticationActor.init(credentials);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
