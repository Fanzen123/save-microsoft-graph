package fr.lecko.util;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.requests.GraphServiceClient;
import fr.lecko.contract.save_microsoft_graph.dto.Credentials;

import java.io.IOException;
import java.util.Collections;

public class Authentication {

    public static final String MICROSOFT_COM_DEFAULT = "https://graph.microsoft.com/.default";
    private static GraphServiceClient graphClient;

    public static void init(Credentials credentials) throws IOException {
        final ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                .clientId(credentials.getClientId())
                .tenantId(credentials.getTenant())
                .clientSecret(credentials.getClientSecret())
                .build();

        final TokenCredentialAuthProvider tokenCredentialAuthProvider =
                new TokenCredentialAuthProvider(
                        Collections.singletonList(MICROSOFT_COM_DEFAULT), clientSecretCredential);

        graphClient = GraphServiceClient
                .builder()
                .authenticationProvider(tokenCredentialAuthProvider)
                .buildClient();
    }

    public static GraphServiceClient getGraphClient() {
        return graphClient;
    }

}
