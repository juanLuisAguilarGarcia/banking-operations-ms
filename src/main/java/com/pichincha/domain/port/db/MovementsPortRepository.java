package com.pichincha.domain.port.db;

import com.pichincha.domain.entities.Movement;
import com.pichincha.infra.adapter.db.entites.MovementsDto;

public interface MovementsPortRepository {
    Movement save(MovementsDto movement);
    Movement getMovementById(Long movementId);
    void deleteMovement(Long movementId);
    Movement updateMovement(MovementsDto movement);
}
