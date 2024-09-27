package com.emazon.transactions.infrastructure.input.rest;

import com.emazon.transactions.application.dto.SaleDtoRequest;
import com.emazon.transactions.application.handler.ISaleHandler;
import com.emazon.transactions.application.handler.ISecurityHandler;
import com.emazon.transactions.infrastructure.output.security.entity.SecurityUser;
import com.emazon.transactions.utils.SecurityConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sale")
@RequiredArgsConstructor
@Validated
public class SaleRestController {

    private final ISecurityHandler securityHandler;
    private final ISaleHandler saleHandler;
    @PostMapping
    public ResponseEntity<Void> createSale(
            @Valid @RequestBody SaleDtoRequest saleRequest,
            @RequestHeader(SecurityConstants.AUTHORIZATION) String token
    ) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
            Long userId = userDetails.getId();
            securityHandler.setToken(token);
            saleHandler.createSale(saleRequest, userId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } finally {
            securityHandler.removeToken();
        }
    }
}
