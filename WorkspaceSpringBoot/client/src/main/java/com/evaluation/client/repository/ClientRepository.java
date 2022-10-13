package com.evaluation.client.repository;

import com.evaluation.client.entities.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
    Optional<Client> findById(Integer id);

    @Override
    List<Client> findAll();

    @Override
    Client save(Client client);


}
