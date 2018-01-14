package com.kula.work.service.dto.stock;


public class StockCommandDTO {

    private String login;
    private StockCommandType command;
    private String code;
    private Integer count;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public StockCommandType getCommand() {
        return command;
    }

    public void setCommand(StockCommandType command) {
        this.command = command;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
