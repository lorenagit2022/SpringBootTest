package com.evaluation.client.repository;

import com.evaluation.client.entities.Client;
import com.evaluation.client.exception.ClientNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client>  findById(Integer id);

    @Override
    List<Client> findAll();

    @Override
    Client save(Client client);

    //Client deleteByIdentify(Integer identify);

    Client save(Optional<Client> client);
}
