package com.pichincha.app.exception;

import com.pichincha.infra.api.router.controller.error.exception.BankingOperationsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class AccountException extends BankingOperationsException {
    public AccountException(String code, String message){ super(code, message); }
}
