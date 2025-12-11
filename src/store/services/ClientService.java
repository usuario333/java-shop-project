package store.services;

import java.util.HashMap;

import store.models.Client;

public class ClientService {
    HashMap<char[], Client> clients;
    
    public ClientService() {
        clients = new HashMap<>();
    }

    public HashMap<char[], Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        clients.put(client.getId(), client);
    }
}
