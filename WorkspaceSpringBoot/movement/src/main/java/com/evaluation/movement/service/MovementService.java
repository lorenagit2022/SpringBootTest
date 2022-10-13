package com.evaluation.movement.service;

import com.evaluation.movement.entities.Movement;
import com.evaluation.movement.exception.MovementException;
import com.evaluation.movement.exception.MovementNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovementService {

    Optional<Movement> findMovementById(Integer id);


    List<Movement> findMovementsByIdAccount(Integer idAccount);

    public List<Movement> findMovementsByIdAccountAndDates(Integer idAccount, Date start, Date end) throws MovementNotFoundException;


    Optional<Movement> findLastMovementsByIdAccount(Integer idAccount) throws MovementNotFoundException, JsonProcessingException;

    Movement createMovement(Movement movement) throws MovementException, MovementNotFoundException, JsonProcessingException;

    Movement updateMovement(Movement movement) throws MovementException;

}

