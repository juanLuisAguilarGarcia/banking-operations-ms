package com.pichincha.domain.port.db;

import com.pichincha.domain.entities.Account;
import com.pichincha.infra.adapter.db.entites.AccountsDto;

public interface AccountsPortRepository {
    Account save(AccountsDto account);
    Account getAccountById(Long accountId);
    Account getAccountByAccountTypeIdAndAccountNumber(Long typeId, Long number);
    void deleteAccount(Long accountId);
    Account updateAccount(AccountsDto account);
}
