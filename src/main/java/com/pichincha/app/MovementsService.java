package com.pichincha.app;

import com.pichincha.app.mapper.MovementMapper;
import com.pichincha.domain.entities.Account;
import com.pichincha.domain.entities.Movement;
import com.pichincha.domain.port.db.AccountsPortRepository;
import com.pichincha.domain.port.db.MovementsPortRepository;
import com.pichincha.infra.api.router.controller.dto.response.movement.MovementDto;
import com.pichincha.app.exception.MovementException;
import com.pichincha.infra.api.router.facade.MovementsFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.pichincha.app.ServiceConsts.*;

@Slf4j
@Service
public class MovementsService implements MovementsFacade {

    @Autowired
    private MovementsPortRepository movementsPortRepository;

    @Autowired
    private AccountsPortRepository accountsPortRepository;

    public MovementDto createMovement(Movement movementToSave) throws MovementException {
        try{
            Account account = existsAccount(movementToSave.getAccountId());

            if(Objects.isNull(movementToSave.getMovementDate())){
                movementToSave.setMovementDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
            }

            if (account.getMovements().isEmpty()){
                movementToSave.setBalanceAmount(account.getInitAmount().add(movementToSave.getAmount()));
            } else {
                movementToSave.setBalanceAmount(account.getMovements().get(0).getBalanceAmount().add(movementToSave.getAmount()));
            }

            if(movementToSave.getBalanceAmount().compareTo(BigDecimal.ZERO) < 0){
                log.error(String.format(MSG_ERROR_PROCESS_SERVICE_MOVEMENT, "created",  "account_id: ", movementToSave.getAccountId(),
                        "unavailable balance"));
                throw new MovementException("422-13", "unavailable balance");
            }

            Movement movement = movementsPortRepository.save(
                    MovementMapper.toMovementEntityDto(movementToSave));

            log.info(String.format(MSG_PROCESS_SERVICE_MOVEMENT, "created", "account_id: ", movementToSave.getAccountId()));
            return MovementMapper.toMovementDto(MovementMapper.toDto(movement), MSG_MOVEMENT_CREATED);
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE_MOVEMENT, "created",  "account_id: ", movementToSave.getAccountId(),
                    ex.getMessage()));
            throw new MovementException("422-7", ex.getMessage());
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
            throw new MovementException("422-8", ex.getMessage());
        }

    }

    public void deleteMovement(Long movementId) throws MovementException {
        try{
            Movement movement = existsMovement(movementId);

            movement.setMovementId(null);
            movement.setBalanceAmount(movement.getBalanceAmount().subtract(movement.getAmount()));

            if(movement.getBalanceAmount().compareTo(BigDecimal.ZERO) < 0){
                log.error(String.format(MSG_ERROR_PROCESS_SERVICE_MOVEMENT, "created",  "movement_id: ", movementId,
                        "unavailable balance"));
                throw new MovementException("422-14", "unavailable balance");
            }

            movement.setAmount(movement.getAmount().negate());
            movement.setMovementDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
            movement.setCreateAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));
            movement.setUpdateAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));

            movementsPortRepository.save(MovementMapper.toMovementEntityDto(movement));
            log.info(String.format(MSG_PROCESS_SERVICE_MOVEMENT, "delete", "movementId: ", movementId));
            return;
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE_MOVEMENT, "delete",  "movementId: ", movementId,
                    ex.getMessage()));
            throw new MovementException("422-9", ex.getMessage());
        }
    }

    public MovementDto updateMovement(Movement movementToUpdate) throws MovementException {
        try{
            Movement mov = existsMovement(movementToUpdate.getMovementId());
            mov.setUpdateAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));
            mov.setMovementDate(movementToUpdate.getMovementDate());

            Movement movement = movementsPortRepository.updateMovement(MovementMapper.toMovementEntityDto(mov));
            log.info(String.format(MSG_PROCESS_SERVICE_MOVEMENT, "update", "movementId: ", movementToUpdate.getMovementId()));
            return MovementMapper.toMovementDto(MovementMapper.toDto(movement), MSG_MOVEMENT_UPDATE );
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE_MOVEMENT, "update",  "movementId: ", movementToUpdate.getMovementId(),
                    ex.getMessage()));
            throw new MovementException("422-10", ex.getMessage());
        }
    }

    private Movement existsMovement(Long movementId) throws MovementException {
        Movement movement = movementsPortRepository.getMovementById(movementId);

        if(Objects.isNull(movement.getMovementId())  || movement.getMovementId() < 1L) {
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE_MOVEMENT , "exists",  "movementId: ", movementId,
                    "movement not found"));
            throw new MovementException("422-11", "movement not found");
        }

        return movement;
    }

    private Account existsAccount(Long accountId) throws MovementException {
        Account account = accountsPortRepository.getAccountById(accountId);

        if(Objects.isNull(account.getAccountId())  || account.getAccountId() < 1L) {
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE_MOVEMENT , "exists",  "accountId: ", accountId,
                    "account not found"));
            throw new MovementException("422-12", "account not found");
        }

        return account;
    }
}
