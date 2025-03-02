package com.pichincha.infra.api.router.facade;

import com.pichincha.domain.entities.Account;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountDto;
import com.pichincha.infra.api.router.controller.error.exception.AccountException;

public interface AccountsFacade {

    public AccountDto createAccount(Account account) throws AccountException;

    public AccountDto getAccountById(Long clientId) throws AccountException;

    public void deleteAccount(Long clientId) throws AccountException;

    public AccountDto updateAccount(Account account) throws AccountException;
}
