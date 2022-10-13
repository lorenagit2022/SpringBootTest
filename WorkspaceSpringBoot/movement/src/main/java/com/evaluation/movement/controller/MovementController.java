package com.evaluation.movement.controller;

import com.evaluation.movement.entities.Movement;
import com.evaluation.movement.exception.MovementException;
import com.evaluation.movement.exception.MovementNotFoundException;
import com.evaluation.movement.service.MovementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class MovementController {


    @Autowired
    private MovementService movementService;

    @GetMapping("/movement/{idAccount}")
    public List<Movement> getMovementsByIdAccount(@PathVariable Integer idAccount) {
        return movementService.findMovementsByIdAccount(idAccount);
    }

    @GetMapping("/movement/dateBetween/{idAccount}/{start}/{end}")
    public ResponseEntity<?> getMovementsByIdAccountAndDates(@PathVariable Integer idAccount, @PathVariable String start, @PathVariable String end) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Date dateStart = df.parse(start);
            Date dateEnd = df.parse(end);

            return new ResponseEntity(movementService.findMovementsByIdAccountAndDates(idAccount, dateStart, dateEnd), HttpStatus.OK);
        } catch (MovementNotFoundException e) {
            return null;
        } catch (ParseException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/movement/ID/{id}")
    public ResponseEntity<?> getMovementsById(@PathVariable Integer id) {
        return new ResponseEntity(movementService.findMovementById(id), HttpStatus.OK);
    }

    @GetMapping("/movement/last/{idAccount}")
    public ResponseEntity<?> getLastMovementsByIdAccount(@PathVariable Integer idAccount) {
        try {
            return new ResponseEntity(movementService.findLastMovementsByIdAccount(idAccount), HttpStatus.OK);
        } catch (MovementNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (JsonProcessingException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/movement/create")
    public ResponseEntity<?> createMovement(@RequestBody Movement movement) {
        try {
            return new ResponseEntity(movementService.createMovement(movement), HttpStatus.OK);
        } catch (MovementException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (MovementNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (JsonProcessingException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
