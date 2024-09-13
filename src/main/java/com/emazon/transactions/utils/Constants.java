package com.emazon.transactions.utils;

public class Constants {
    private Constants() {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    public static final String UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED = "Utility class should not be instantiated";

    public static final String RESPONSE_MESSAGE_KEY = "Message";

    public static final String EXCEPTION_ARTICLE_ID_NULL = "Article ID cannot be null";
    public static final String EXCEPTION_ARTICLE_ID_POSITIVE = "Article ID must be a positive number";
    public static final String EXCEPTION_ARTICLE_QUANTITY_NULL = "Quantity cannot be null";
    public static final String EXCEPTION_ARTICLE_QUANTITY_POSITIVE = "Quantity must be a positive number";

}
