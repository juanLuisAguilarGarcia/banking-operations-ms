package com.pichincha.infra.adapter.db;

import com.pichincha.domain.entities.Account;
import com.pichincha.domain.port.db.AccountsPortRepository;
import com.pichincha.infra.adapter.db.entites.AccountsDto;
import com.pichincha.infra.adapter.db.jpa.AccountsJpaRepository;
import com.pichincha.infra.adapter.db.mapper.MapperAccountEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class AccountsRepository implements AccountsPortRepository {

    @Autowired
    private AccountsJpaRepository accountsJpaRepository;

    @Transactional
    public Account save(AccountsDto accountToSave){
        return MapperAccountEntity.toAccount(accountsJpaRepository.save(accountToSave));
    }

    public Account getAccountById(Long accountId){
        return MapperAccountEntity.toAccount(accountsJpaRepository.findByAccountIdAndIsActive(accountId, true));
    }

    @Transactional
    public void deleteAccount(Long accountId){
        accountsJpaRepository.deleteById(accountId);
        return;
    }

    @Transactional
    public Account updateAccount(AccountsDto accontToUpdate){
        return MapperAccountEntity.toAccount(accountsJpaRepository.save(accontToUpdate));
    }

    public Account getAccountByAccountTypeIdAndAccountNumber(Long typeId, Long number){
        return MapperAccountEntity.toAccount(accountsJpaRepository.findByIdentificationTypeIdAndNumber(typeId, number));
    }

    public List<Account> getAccountsByClientIdAndDates(Long clientId, Timestamp initDate, Timestamp endDate){
        return MapperAccountEntity.toAccountLis(accountsJpaRepository.getAccountsByClientIdAndDates(clientId, initDate, endDate));
    }
}
