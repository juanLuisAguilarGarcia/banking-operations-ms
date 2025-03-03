package com.pichincha.domain.port.db;

import com.pichincha.domain.entities.Account;
import com.pichincha.infra.adapter.db.entites.AccountsDto;

import java.sql.Timestamp;
import java.util.List;

public interface AccountsPortRepository {
    Account save(AccountsDto account);
    Account getAccountById(Long accountId);
    Account getAccountByAccountTypeIdAndAccountNumber(Long typeId, Long number);
    void deleteAccount(Long accountId);
    Account updateAccount(AccountsDto account);
    List<Account> getAccountsByClientIdAndDates(Long clientId, Timestamp initDate, Timestamp endDate);
}
