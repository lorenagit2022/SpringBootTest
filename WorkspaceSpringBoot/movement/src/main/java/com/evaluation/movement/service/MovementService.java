package com.evaluation.movement.service;

import com.evaluation.movement.entities.Movement;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovementService {

    List<Movement> findMovementsByDate(Date date);

    Movement createMovement(Movement movement);

    Movement updateMovement(Movement movement);

    void deleteMovementById(Integer id);
}
