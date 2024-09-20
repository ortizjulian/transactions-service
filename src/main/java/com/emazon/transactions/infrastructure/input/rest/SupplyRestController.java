package com.emazon.transactions.infrastructure.input.rest;

import com.emazon.transactions.application.dto.SupplyRequestDto;
import com.emazon.transactions.application.handler.ISecurityHandler;
import com.emazon.transactions.application.handler.ISupplyHandler;

import com.emazon.transactions.infrastructure.output.security.entity.SecurityUser;
import com.emazon.transactions.utils.SecurityConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("supply")
@RequiredArgsConstructor
@Validated
public class SupplyRestController {

    private final ISupplyHandler supplyHandler;
    private final ISecurityHandler securityHandler;
    @Operation(
            summary = "Add stock to an article",
            description = "This endpoint adds a specified quantity to an article in the stock service and creates a supply record for its history."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Stock added successfully"),
            @ApiResponse(responseCode = "404", description = "Not article found with given id"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Void> addQuantity(@Valid @RequestBody SupplyRequestDto supplyRequestDto,
                                            @RequestHeader(SecurityConstants.AUTHORIZATION) String token) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
            Long userId = userDetails.getId();
            securityHandler.setToken(token);
            supplyHandler.addStockToArticle(supplyRequestDto,userId);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } finally {
            securityHandler.removeToken();
        }
    }

    @Operation(summary = "Get Next Supply Date", description = "Retrieve the next supply date for a specific article based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Next supply date retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Unknown next supply date for the given ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/next/{articleId}")
    public ResponseEntity<LocalDateTime> nextSupplyDate(@PathVariable Long articleId) {
        LocalDateTime nextSupplyDate = supplyHandler.nextSupplyDate(articleId);
        return ResponseEntity.ok(nextSupplyDate);
    }
}
