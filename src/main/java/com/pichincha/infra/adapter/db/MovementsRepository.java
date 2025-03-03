package com.pichincha.infra.adapter.db;

import com.pichincha.domain.entities.Movement;
import com.pichincha.domain.port.db.MovementsPortRepository;
import com.pichincha.infra.adapter.db.entites.MovementsDto;
import com.pichincha.infra.adapter.db.jpa.MovementsJpaRepository;
import com.pichincha.infra.adapter.db.mapper.MapperMovementEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovementsRepository implements MovementsPortRepository {

    @Autowired
    private MovementsJpaRepository movementsJpaRepository;

    @Transactional
    public Movement save(MovementsDto movementToSave){
        return MapperMovementEntity.toMovement(movementsJpaRepository.save(movementToSave));
    }

    public Movement getMovementById(Long movementId){
        return MapperMovementEntity.toMovement(movementsJpaRepository.findByMovementId(movementId));
    }

    @Transactional
    public void deleteMovement(Long movementId){
        movementsJpaRepository.deleteById(movementId);
        return;
    }

    @Transactional
    public Movement updateMovement(MovementsDto movementToUpdate){
        return MapperMovementEntity.toMovement(movementsJpaRepository.save(movementToUpdate));
    }
}
