package com.pichincha.infra.adapter.http.exception;

import com.pichincha.infra.api.router.controller.error.exception.BankingOperationsException;

public class ClientFeingException extends BankingOperationsException {
    public ClientFeingException(String code, String message){ super(code, message); }
}
