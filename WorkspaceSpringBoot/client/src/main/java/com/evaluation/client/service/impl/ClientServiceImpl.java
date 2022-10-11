package com.evaluation.client.service.impl;

import com.evaluation.client.entities.Client;
import com.evaluation.client.entities.Person;
import com.evaluation.client.exception.ClientException;
import com.evaluation.client.exception.ClientNotFoundException;
import com.evaluation.client.repository.ClientRepository;
import com.evaluation.client.repository.PersonRepository;
import com.evaluation.client.service.ClientService;
import net.bytebuddy.asm.Advice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.boot.model.naming.ImplicitEntityNameSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Optional<Client> findByIdentify(Integer identify) throws ClientNotFoundException {
        logger.info("valor inicialid:" + identify);
        Optional<Person> person = personRepository.findByIdentify(identify);
        if (Objects.isNull(person) || person.isEmpty()) {
            throw new ClientNotFoundException(identify.toString());
        }
        return clientRepository.findById(person.get().getId());

    }


    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) throws ClientException {

       try{
            return clientRepository.save(client);
        }catch(Exception e){
            throw new ClientException("No se puede guardar", e);
        }
    }

    @Override
    public Client updateClient(Client client) throws ClientNotFoundException {
        Optional<Client> clientResult = clientRepository.findById(client.getIdentify());
        if (clientResult.isPresent()) {
            return clientRepository.save(clientResult.get());
        }else{
            throw new ClientNotFoundException(clientResult.get().getIdentify().toString());
        }
    }

    @Override
    public Client updateClientSatus(Integer identify, Boolean status) throws ClientNotFoundException {
        Optional<Client> client = this.findByIdentify(identify);
        if (client.isPresent()) {
            client.get().setStatus(status);
            return clientRepository.save(client.get());
        }else{
            throw new ClientNotFoundException(client.get().getIdentify().toString());
        }
    }


    public Person deleteClient(Integer identify)  {
        return personRepository.deleteByIdentify(identify);
    }


}
