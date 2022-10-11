package com.evaluation.movement.repository;

import com.evaluation.movement.entities.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Integer> {

    List<Movement> findAllByDate(Date date);

    Movement save(Movement movement);

    void deleteMovementById(Integer id);
}