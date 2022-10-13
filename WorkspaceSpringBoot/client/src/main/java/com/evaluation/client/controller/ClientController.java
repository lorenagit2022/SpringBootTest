package com.evaluation.client.controller;

import com.evaluation.client.entities.Client;
import com.evaluation.client.exception.ClientException;
import com.evaluation.client.exception.ClientNotFoundException;
import com.evaluation.client.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ClientController {
    private static final Logger logger = LogManager.getLogger(ClientController.class);
    @Autowired
    private ClientService clientService;


    @GetMapping("/client/{identify}")
    public ResponseEntity<?> getClientByIdentification(@PathVariable String identify) {
        try {
            return new ResponseEntity(clientService.findByIdentify(identify), HttpStatus.OK);
        } catch (ClientNotFoundException mens) {
            return new ResponseEntity(mens.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/client/")
    public List<Client> getAllClient() {
        List<Client> listClient = clientService.findAllClients();
        return listClient;
    }

    @GetMapping("/client/id/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Integer id) {
        try {
            return new ResponseEntity(clientService.findByID(id), HttpStatus.OK);
        } catch (ClientNotFoundException mens) {
            return new ResponseEntity(mens.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/client/create")
    public ResponseEntity createClient(@Validated @RequestBody Client client) {
        try {
            logger.info("el di del cliente es: "+client.getId());
            return new ResponseEntity(clientService.createClient(client), HttpStatus.OK);
        }catch (ClientException mens){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/client/update/")
    public ResponseEntity<?>  updateClient(@RequestBody Client client) {
        try {
            return new ResponseEntity(clientService.updateClient(client), HttpStatus.OK);
        }catch (ClientNotFoundException mens){
            return new ResponseEntity(mens.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/client/activateClient/{identify}")
    public ResponseEntity activateClient(@PathVariable String identify) {
        try {
            return new ResponseEntity(clientService.updateClientSatus(identify, true), HttpStatus.OK);
        } catch (ClientNotFoundException mens) {
            return new ResponseEntity(mens.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/client/deactivateClient/{identify}")
    public ResponseEntity deactivateClient(@PathVariable String identify) {
        try {
            return new ResponseEntity(clientService.updateClientSatus(identify, false), HttpStatus.OK);
        } catch (ClientNotFoundException mens) {
            return new ResponseEntity(mens.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/client/delete/{identify}")
    public ResponseEntity deleteClient(@PathVariable String identify) {
        return new ResponseEntity(clientService.deleteClient(identify), HttpStatus.OK);
    }
}
