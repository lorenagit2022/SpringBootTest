package com.evaluation.client.service;

import com.evaluation.client.entities.Client;
import com.evaluation.client.entities.Person;
import com.evaluation.client.exception.ClientException;
import com.evaluation.client.exception.ClientNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Optional<Client> findByIdentify(String identify) throws ClientNotFoundException;

    Optional<Client> findByID(Integer id) throws ClientNotFoundException;

    List<Client> findAllClients();

    Client createClient(Client client) throws ClientException;

    Client updateClient(Client client) throws ClientNotFoundException;

    Client updateClientSatus(String identify, Boolean status) throws ClientNotFoundException;

    Person deleteClient(String identify);
}
