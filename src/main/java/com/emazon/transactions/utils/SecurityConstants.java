package com.emazon.transactions.utils;

import static com.emazon.transactions.utils.Constants.UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED;

public class SecurityConstants {
    private SecurityConstants () {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    //Token
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER ="Bearer ";
    //Jwt Claims
    public static final String CLAIM_ROLE = "role";
    public static final String CLAIM_ID = "id";
    //Roles names
    public static final String ROLE_WAREHOUSE_ASSISTANT = "Aux_bodega";
    public static final String ROLE_ADMIN = "Administrador";
    public static final String ROLE_CLIENT = "Cliente";

}

