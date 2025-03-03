package com.pichincha.infra.api.router.facade;

import com.pichincha.domain.entities.Movement;
import com.pichincha.infra.api.router.controller.dto.response.movement.MovementDto;
import com.pichincha.infra.api.router.controller.error.exception.MovementException;

public interface MovementsFacade {

    public MovementDto createMovement(Movement movement) throws MovementException;

    public MovementDto getMovementById(Long movementId) throws MovementException;

    public void deleteMovement(Long movementId) throws MovementException;

    public MovementDto updateMovement(Movement movement) throws MovementException;
}
