package com.emazon.transactions.infrastructure.input.rest;

import com.emazon.transactions.application.dto.SupplyRequestDto;
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

@RestController
@RequestMapping("supply")
@RequiredArgsConstructor
@Validated
public class SupplyController {

    private final ISupplyHandler supplyHandler;

    @Operation(
            summary = "Add stock to an article",
            description = "This endpoint adds a specified quantity to an article in the stock service and creates a supply record for its history."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Stock added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Void> addQuantity(@Valid @RequestBody SupplyRequestDto supplyRequestDto,
                                            @RequestHeader(SecurityConstants.AUTHORIZATION) String token) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
        Long userId = userDetails.getId();

        supplyHandler.addStockToArticle(supplyRequestDto,userId , token);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
