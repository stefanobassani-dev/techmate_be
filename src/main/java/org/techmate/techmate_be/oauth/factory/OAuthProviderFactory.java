package org.techmate.techmate_be.oauth.factory;

import org.springframework.stereotype.Component;
import org.techmate.techmate_be.exception.OAuthProviderNotFoundException;
import org.techmate.techmate_be.oauth.client.IOAuthProviderClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OAuthProviderFactory {
    final Map<String, IOAuthProviderClient> clients;

    public OAuthProviderFactory(List<IOAuthProviderClient> clientsImpl) {
        clients = new HashMap<>();

        for (IOAuthProviderClient client : clientsImpl) {
            clients.put(client.getClass().getSimpleName()
                    .replace("ProviderClient", "").toLowerCase(), client);
        }
    }

    public IOAuthProviderClient getClient(String provider) {
        IOAuthProviderClient client = clients.get(provider.toLowerCase());

        if (client == null) {
            throw new OAuthProviderNotFoundException(provider);
        }

        return client;
    }
}