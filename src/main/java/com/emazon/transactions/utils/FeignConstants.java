package com.emazon.transactions.utils;

import static com.emazon.transactions.utils.Constants.UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED;

public class FeignConstants {
    private FeignConstants () {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    //Article
    public static final String FEIGN_ARTICLE_NAME = "ARTICLE-API";
    public static final String PATH_ARTICLE_URL = "${stock.base-url}";
    public static final String PATH_ARTICLE_UPDATE_QUANTITY = "/article/updateQuantity";
    public static final String PATH_ARTICLE_BY_ID = "/article/{articleId}";
    public static final String ARTICLE_ID = "articleId";
    //cart
    public static final String FEIGN_CART_NAME = "CART-API";
    public static final String PATH_CART_URL = "${cart.base-url}";
    public static final String PATH_DELETE_CART_BY_ID = "/cart/items/{articleId}";
    //Exceptions
    public static final String BAD_REQUEST = "Bad Request";
    public static final String NOT_FOUND = "Not Found";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error from remote service";

    public static final int BAD_REQUEST_CODE = 400;
    public static final int NOT_FOUND_CODE = 404;
}
