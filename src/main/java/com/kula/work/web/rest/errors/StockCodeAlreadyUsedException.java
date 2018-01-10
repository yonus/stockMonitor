package com.kula.work.web.rest.errors;

public class StockCodeAlreadyUsedException extends BadRequestAlertException{
    public StockCodeAlreadyUsedException() {
        super("Stock is already used", "stockManagement","codeAvailable");
    }
}
