package com.emazon.transactions.application.handler;

public interface ISecurityHandler {
    void setToken(String token);
    void removeToken();
}
