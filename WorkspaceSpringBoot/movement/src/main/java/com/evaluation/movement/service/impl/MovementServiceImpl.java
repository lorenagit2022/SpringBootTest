package com.evaluation.movement.service.impl;

import com.evaluation.movement.controller.MovementController;
import com.evaluation.movement.entities.Movement;
import com.evaluation.movement.entities.dto.AccountDto;
import com.evaluation.movement.exception.MovementException;
import com.evaluation.movement.exception.MovementNotFoundException;
import com.evaluation.movement.repository.MovementRepository;
import com.evaluation.movement.service.MovementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovementServiceImpl implements MovementService {
    private static final Logger logger = LogManager.getLogger(MovementController.class);
    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private RestServiceImpl restService;

    @Override
    public Optional<Movement> findMovementById(Integer id) {
        return movementRepository.findById(id);
    }

    @Override
    public List<Movement> findMovementsByIdAccount(Integer idAccount) {
        return movementRepository.findMovementsByIdAccount(idAccount);

    }

    @Override
    public List<Movement> findMovementsByIdAccountAndDates(Integer idAccount, Date start, Date end) throws MovementNotFoundException {
        List<Movement> listMovements = movementRepository.findAllByIdAccountAndDates(idAccount, start, end);
        if (listMovements.size() == 0) {
            throw new MovementNotFoundException(idAccount.toString());
        }
        return listMovements;
    }

    @Override
    public Optional<Movement> findLastMovementsByIdAccount(Integer idAccount) throws MovementNotFoundException, JsonProcessingException {
        Optional<Movement> movement = movementRepository.findMovementByIdAccountAndIsLast(idAccount, true);

        if (Objects.isNull(movement) || movement.isEmpty()) {
            throw new MovementNotFoundException(idAccount.toString());
        }
        logger.info("movimeinto ultimo" + movement.get().getId());
        logger.info("movimeinto ultimo" + movement.get().getAvailableBalance());
        AccountDto accountDto = restService.getAccountByIDJSON(idAccount);
        logger.info("name accountDto: " + accountDto);
        movement.get().setNumberAccount(accountDto.getAccountNumber());
        movement.get().setNameClient(accountDto.getNameClient());
        return movement;
    }

    @Override
    public Movement createMovement(Movement movement) throws MovementException, JsonProcessingException {
        try {
            Optional<Movement> movementLast = this.findLastMovementsByIdAccount(movement.getIdAccount());
            Double balance = movementLast.get().getAvailableBalance();
            logger.info("balance inicial anitguo" + balance);
            logger.info("balance inicial nuevo" + movement.getValue());
            if (movement.getMovementType().equals("C")) {
                balance = balance + movement.getValue();
            } else {
                balance = balance - movement.getValue();
            }
            logger.info("balance final" + balance);
            if (balance < 0) {
                logger.info("valor negativos...");
                throw new MovementException("Saldo NO disponible", null);
            }
            logger.info("balance final" + balance);
            movement.setAvailableBalance(balance);
            movement.setIsLast(true);
            movement.setDate(new Date());
            movement.setNameClient(movementLast.get().getNameClient());
            movement.setNumberAccount(movementLast.get().getNumberAccount());
            movementLast.get().setIsLast(false);
            Movement movementPrevious = this.updateMovement(movementLast.get());
        } catch (MovementNotFoundException e) {
            if (movement.getMovementType().equals("C")) {
                movement.setAvailableBalance(movement.getValue());
                movement.setIsLast(true);
                movement.setDate(new Date());
                AccountDto accountDto = restService.getAccountByIDJSON(movement.getIdAccount());
                logger.info("name accountDto: " + accountDto);
                movement.setNumberAccount(accountDto.getAccountNumber());
                movement.setNameClient(accountDto.getNameClient());
            } else {
                throw new MovementException("Saldo NO disponible", null);
            }
        } catch (MovementException e) {
            throw new MovementException(e.getMessage(), e);
        } catch (Exception e) {
            throw new MovementException("No se puede guardar", e);
        }
        AccountDto accountDto = restService.updateAccountByIDJSON(movement.getIdAccount(), movement.getAvailableBalance());
        logger.info("finalizo");
        return movementRepository.save(movement);
    }

    @Override
    public Movement updateMovement(Movement movement) throws MovementException {
        try {
            return movementRepository.save(movement);
        } catch (Exception e) {
            throw new MovementException("No se puede actualizar", e);
        }
    }


}
