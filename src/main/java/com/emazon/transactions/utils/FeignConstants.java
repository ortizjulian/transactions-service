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
    public static final String EXCEPTION_ARTICLE_NOT_FOUND_BY_ID = "Article not found with ID: ";
    public static final String EXCEPTION_FEIGN_UNEXPECTED_RESPONSE = "Feign client returned an unexpected status: ";
}
