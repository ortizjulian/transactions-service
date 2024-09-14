package com.emazon.transactions.infrastructure.configuration.feign;

import com.emazon.transactions.infrastructure.output.security.adapter.SecurityAdapter;
import com.emazon.transactions.utils.SecurityConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeignClientInterceptor implements RequestInterceptor {

    private final SecurityAdapter securityAdapter;

    @Override
    public void apply(RequestTemplate template) {

        String token = securityAdapter.getToken();

        if (token != null && !token.isEmpty()) {
            template.header(SecurityConstants.AUTHORIZATION, token);
        }

    }
}
