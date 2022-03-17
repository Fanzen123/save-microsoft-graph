package fr.lecko.actor;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.requests.UserRequestBuilder;
import fr.lecko.contract.save_microsoft_graph.dto.Credentials;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AuthenticationActor {

    public static final String SCOPE_EMAIL = "email";

    GraphServiceClient graphClient;

    public void init(Credentials credentials) {
        final ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                .clientId(credentials.getClientId())
                .clientSecret(credentials.getClientSecret())
                .tenantId(credentials.getTenant())
                .build();

        final TokenCredentialAuthProvider tokenCredentialAuthProvider =
                new TokenCredentialAuthProvider(Arrays.asList(SCOPE_EMAIL), clientSecretCredential);

        graphClient = GraphServiceClient.builder()
                .authenticationProvider(tokenCredentialAuthProvider)
                .buildClient();
    }

    public UserRequestBuilder getUserRequestBuilder() {
        return graphClient.me();
    }
}
