package com.pichincha.app;

import com.pichincha.app.mapper.MovementMapper;
import com.pichincha.domain.entities.Movement;
import com.pichincha.domain.port.db.MovementsPortRepository;
import com.pichincha.infra.api.router.controller.dto.response.movement.MovementDto;
import com.pichincha.infra.api.router.controller.error.exception.MovementException;
import com.pichincha.infra.api.router.facade.MovementsFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.pichincha.app.ServiceConsts.*;

@Slf4j
@Service
public class MovementsService implements MovementsFacade {

    @Autowired
    private MovementsPortRepository movementsPortRepository;

    public MovementDto createMovement(Movement movementToSave) throws MovementException {
        try{
            Movement movement = movementsPortRepository.save(
                    MovementMapper.toMovementEntityDto(movementToSave));

            log.info(String.format(MSG_PROCESS_SERVICE_MOVEMENT, "created", "account_id: ", movementToSave.getAccountId()));
            return MovementMapper.toMovementDto(MovementMapper.toDto(movement), MSG_MOVEMENT_CREATED);
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE_MOVEMENT, "created",  "account_id: ", movementToSave.getAccountId(),
                    ex.getMessage()));
            throw new MovementException("422-1", ex.getMessage());
        }
    }

    public MovementDto getMovementById(Long movementId) throws MovementException {
        try{
            Movement movement = existsMovement(movementId);
            log.info(String.format(MSG_PROCESS_SERVICE_MOVEMENT, "getById", "movementId: ", movementId));
            return MovementMapper.toMovementDto(MovementMapper.toDto(movement), MSG_MOVEMENT_GET);
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE_MOVEMENT, "getById",  "movementId: ", movementId,
                    ex.getMessage()));
            throw new MovementException("422-2", ex.getMessage());
        }

    }

    public void deleteMovement(Long movementId) throws MovementException {
        try{
            Movement movement = existsMovement(movementId);

            movementsPortRepository.save(MovementMapper.toMovementEntityDto(movement));
            log.info(String.format(MSG_PROCESS_SERVICE_MOVEMENT, "delete", "movementId: ", movementId));
            return;
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE_MOVEMENT, "delete",  "movementId: ", movementId,
                    ex.getMessage()));
            throw new MovementException("422-3", ex.getMessage());
        }
    }

    public MovementDto updateMovement(Movement movementToUpdate) throws MovementException {
        try{
            existsMovement(movementToUpdate.getMovementId());

            Movement movement = movementsPortRepository.updateMovement(MovementMapper.toMovementEntityDto(movementToUpdate));
            log.info(String.format(MSG_PROCESS_SERVICE_MOVEMENT, "update", "movementId: ", movementToUpdate.getMovementId()));
            return MovementMapper.toMovementDto(MovementMapper.toDto(movement), MSG_MOVEMENT_UPDATE );
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE_MOVEMENT, "update",  "movementId: ", movementToUpdate.getMovementId(),
                    ex.getMessage()));
            throw new MovementException("422-4", ex.getMessage());
        }
    }

    private Movement existsMovement(Long movementId) throws MovementException {
        Movement movement = movementsPortRepository.getMovementById(movementId);

        if(Objects.isNull(movement.getMovementId())  || movement.getMovementId() < 1L) {
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE_MOVEMENT , "exists",  "movementId: ", movementId,
                    "movement not found"));
            throw new MovementException("422-5", "movement not found");
        }

        return movement;
    }

}
