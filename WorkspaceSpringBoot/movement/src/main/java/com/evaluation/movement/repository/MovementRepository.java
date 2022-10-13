package com.evaluation.movement.repository;

import com.evaluation.movement.entities.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Integer> {

    Optional<Movement> findById(Integer id);

    List<Movement> findMovementsByIdAccount(Integer idAccount);

    @Query(value= "SELECT * FROM movimiento WHERE ID_CUENTA=:idAccount and FECHA_MOVIMIENTO BETWEEN :start and :end", nativeQuery = true)
    List<Movement> findAllByIdAccountAndDates(@Param("idAccount") Integer idAccount,@Param("start") Date start, @Param("end") Date end);

    Optional<Movement> findMovementByIdAccountAndIsLast(Integer idAccount, Boolean isLast);

    Movement save(Movement movement);

    void deleteMovementById(Integer id);
}