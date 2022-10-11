package com.evaluation.movement.service.impl;

import com.evaluation.movement.entities.Movement;
import com.evaluation.movement.repository.MovementRepository;
import com.evaluation.movement.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    private MovementRepository movementRepository;

    @Override
    public List<Movement> findMovementsByDate(Date date) {
        return movementRepository.findAllByDate(date);
    }

    @Override
    public Movement createMovement(Movement movement) {
        return null;
    }

    @Override
    public Movement updateMovement(Movement movement) {
        return null;
    }

    @Override
    public void deleteMovementById(Integer id) {

    }
}
