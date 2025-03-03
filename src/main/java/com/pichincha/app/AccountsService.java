package com.pichincha.app;

import com.pichincha.app.mapper.AccountMapper;
import com.pichincha.domain.entities.Account;
import com.pichincha.domain.port.db.AccountsPortRepository;
import com.pichincha.infra.adapter.db.entites.AccountsDto;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountDto;
import com.pichincha.app.exception.AccountException;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountStateDto;
import com.pichincha.infra.api.router.controller.dto.response.movement.MovementDto;
import com.pichincha.infra.api.router.facade.AccountsFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import static com.pichincha.app.ServiceConsts.*;

@Slf4j
@Service
public class AccountsService implements AccountsFacade {

    @Autowired
    private AccountsPortRepository accountsPortRepository;

    public AccountDto createAccount(Account accountToSave) throws AccountException {
        try{
            Account account = accountsPortRepository.save(
                    AccountMapper.toAccountEntityDto(
                            validAccountBeforeCreate(accountToSave)));

            log.info(String.format(MSG_PROCESS_SERVICE, "created", "account_number: ", accountToSave.getAccountNumber()));
            return AccountMapper.toAccountDto(AccountMapper.toDto(account), MSG_ACCOUNT_CREATED);
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "created",  "account_number: ", accountToSave.getAccountNumber(),
                    ex.getMessage()));
            throw new AccountException("422-1", ex.getMessage());
        }
    }

    public AccountDto getAccountById(Long accountId) throws AccountException {
        try{
            Account account = existsAccount(accountId);
            log.info(String.format(MSG_PROCESS_SERVICE, "getById", "accountId: ", accountId));
            return AccountMapper.toAccountDto(AccountMapper.toDto(account), MSG_ACCOUNT_GET);
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "getById",  "accountId: ", accountId,
                    ex.getMessage()));
            throw new AccountException("422-2", ex.getMessage());
        }

    }

    public void deleteAccount(Long accountId) throws AccountException {
        try{
            Account account = existsAccount(accountId);

            AccountsDto accountEntity = AccountMapper.toAccountEntityDto(account);
            accountEntity.setIsActive(false);

            accountsPortRepository.save(accountEntity);
            log.info(String.format(MSG_PROCESS_SERVICE, "delete", "accountId: ", accountId));
            return;
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "delete",  "accountId: ", accountId,
                    ex.getMessage()));
            throw new AccountException("422-3", ex.getMessage());
        }
    }

    public AccountDto updateAccount(Account accountToUpdate) throws AccountException {
        try{
            existsAccount(accountToUpdate.getAccountId());

            Account account = accountsPortRepository.updateAccount(AccountMapper.toAccountEntityDto(accountToUpdate));
            log.info(String.format(MSG_PROCESS_SERVICE, "update", "accountId: ", accountToUpdate.getAccountId()));
            return AccountMapper.toAccountDto(AccountMapper.toDto(account), MSG_ACCOUNT_UPDATE );
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "update",  "accountId: ", accountToUpdate.getAccountId(),
                    ex.getMessage()));
            throw new AccountException("422-4", ex.getMessage());
        }
    }

    public AccountStateDto accountState(Long clientId, Date initDate, Date endDate) throws AccountException {
        try{
            List<Account> accounts = accountsPortRepository.getAccountsByClientIdAndDates(clientId,
                    new java.sql.Timestamp(initDate.getTime()),
                    new java.sql.Timestamp(endDate.getTime()));

            if(accounts.isEmpty()){
                log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "exists",  "clientId: ", clientId,
                        "accounts not found"));
                throw new AccountException("422-16", "accounts not found");
            }

            log.info(String.format(MSG_PROCESS_SERVICE, "account state", "clientId: ", clientId));
            return AccountMapper.toAccountStateDto(accounts, MSG_ACCOUNT_STATE );
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "account state",  "clientId: ", clientId,
                    ex.getMessage()));
            throw new AccountException("422-15", ex.getMessage());
        }
    }

    private Account existsAccount(Long accountId) throws AccountException {
        Account account = accountsPortRepository.getAccountById(accountId);

        if(Objects.isNull(account.getAccountId())  || account.getAccountId() < 1L) {
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "exists",  "accountId: ", accountId,
                    "account not found"));
            throw new AccountException("422-5", "account not found");
        }

        return account;
    }

    private Account validAccountBeforeCreate(Account accountToCreate) throws AccountException {
        Account account = accountsPortRepository.getAccountByAccountTypeIdAndAccountNumber(
                accountToCreate.getAccountType().getAccountTypeId(), accountToCreate.getAccountNumber() );

        if(!Objects.isNull(account.getAccountId()) && account.getAccountId() >= 1L ) {
            if(account.getIsActive()) {
                log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "exists",  "account_number: ", accountToCreate.getAccountNumber(),
                        "Account exists in system."));
                throw new AccountException("422-6", "Account exists in system.");
            } else {
                log.info(String.format(MSG_PROCESS_SERVICE, "exists so the information will be updated and actived", "accountId: ", account.getAccountId()));
                accountToCreate.setAccountId(account.getAccountId());
                accountToCreate.setClientId(account.getClientId());
                accountToCreate.getAccountType().setAccountTypeId(account.getAccountType().getAccountTypeId());
                accountToCreate.getAccountType().setDescription(account.getAccountType().getDescription());
                accountToCreate.setIsActive(true);
                return accountToCreate;
            }
        }else {
            return accountToCreate;
        }

    }
}
