package com.emazon.transactions.infrastructure.input.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import com.emazon.transactions.application.dto.SupplyRequestDto;
import com.emazon.transactions.application.handler.ISupplyHandler;
import com.emazon.transactions.configuration.TestSecurityConfig;
import com.emazon.transactions.infrastructure.output.security.jwt.JwtTokenManager;
import com.emazon.transactions.utils.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = SupplyRestController.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {SupplyRestController.class, TestSecurityConfig.class})
class SupplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ISupplyHandler supplyHandler;

    @MockBean
    private JwtTokenManager jwtTokenManager;

    @Test
    void SupplyRestController_AddQuantity_WhenQuantityIsNegative_ShouldReturnBadRequest() throws Exception {

        SupplyRequestDto invalidSupplyRequestDto = new SupplyRequestDto(1L, -10L);

        ResultActions response = mockMvc.perform(post("/supply")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidSupplyRequestDto)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void SupplyRestController_AddQuantity_WhenArticleIdIsNull_ShouldReturnBadRequest() throws Exception {
        SupplyRequestDto invalidSupplyRequestDto = new SupplyRequestDto(null, 10L);

        ResultActions response = mockMvc.perform(post("/supply")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidSupplyRequestDto)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}