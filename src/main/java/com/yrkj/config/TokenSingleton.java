package com.yrkj.config;

/**
 * Created by xuenianxiang on 2017/5/19.
 */
public class TokenSingleton {

    private static TokenSingleton instance;
    public static synchronized TokenSingleton getInstance() {
        if (instance == null) {
            instance = new TokenSingleton();
        }
        return instance;
    }

    private String wxToken;
    private long tokenTime;

    private  String ticket;
    private long ticketTime;

    private String api_ticket;
    private long api_ticketTime;

    private TokenSingleton(){}


    public static void setInstance(TokenSingleton instance) {
        TokenSingleton.instance = instance;
    }

    public String getWxToken() {
        return wxToken;
    }

    public void setWxToken(String wxToken) {
        this.wxToken = wxToken;
    }

    public long getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(long tokenTime) {
        this.tokenTime = tokenTime;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public long getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(long ticketTime) {
        this.ticketTime = ticketTime;
    }

    public String getApi_ticket() {
        return api_ticket;
    }

    public void setApi_ticket(String api_ticket) {
        this.api_ticket = api_ticket;
    }

    public long getApi_ticketTime() {
        return api_ticketTime;
    }

    public void setApi_ticketTime(long api_ticketTime) {
        this.api_ticketTime = api_ticketTime;
    }
}
