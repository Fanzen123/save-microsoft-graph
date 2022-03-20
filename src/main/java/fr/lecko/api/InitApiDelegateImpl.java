package fr.lecko.api;

import fr.lecko.util.Authentication;
import fr.lecko.contract.save_microsoft_graph.api.InitApiDelegate;
import fr.lecko.contract.save_microsoft_graph.dto.Credentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class InitApiDelegateImpl implements InitApiDelegate {

    @Override
    public ResponseEntity<Void> init(Credentials credentials) {
        try {
            Authentication.init(credentials);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
