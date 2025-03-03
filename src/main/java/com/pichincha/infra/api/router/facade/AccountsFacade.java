package com.pichincha.infra.api.router.facade;

import com.pichincha.domain.entities.Account;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountDto;
import com.pichincha.app.exception.AccountException;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountStateDto;

import java.sql.Date;
import java.util.List;

public interface AccountsFacade {

    public AccountDto createAccount(Account account) throws AccountException;

    public AccountDto getAccountById(Long accountId) throws AccountException;

    public void deleteAccount(Long accountId) throws AccountException;

    public AccountDto updateAccount(Account account) throws AccountException;

    public AccountStateDto accountState(Long clientId, Date initDate, Date endDate) throws AccountException;
}
