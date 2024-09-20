package com.emazon.transactions.domain.usecase;

import com.emazon.transactions.domain.api.ISecurityServicePort;
import com.emazon.transactions.domain.spi.ISecurityPersistencePort;

public class SecurityUseCase implements ISecurityServicePort {

    private ISecurityPersistencePort securityPersistencePort;

    public SecurityUseCase(ISecurityPersistencePort securityPersistencePort) {
        this.securityPersistencePort = securityPersistencePort;
    }

    @Override
    public void setToken(String token) {
        securityPersistencePort.setToken(token);
    }

    @Override
    public void removeToken() {
        securityPersistencePort.removeToken();
    }
}
