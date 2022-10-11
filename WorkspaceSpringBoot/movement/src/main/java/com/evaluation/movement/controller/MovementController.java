package com.evaluation.movement.controller;

import com.evaluation.movement.entities.Movement;
import com.evaluation.movement.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class MovementController {
    @Autowired
    private MovementService movementService;

    @GetMapping("/account/{date}")
    public List<Movement> getMovementsByDate(Date date){
        return  movementService.findMovementsByDate(date);
    }
}
