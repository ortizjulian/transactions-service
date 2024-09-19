package com.emazon.transactions.domain.api;

public interface ISecurityServicePort {
    void setToken(String token);
    void removeToken();
}
