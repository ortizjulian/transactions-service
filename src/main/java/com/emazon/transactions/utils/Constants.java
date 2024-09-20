package com.emazon.transactions.utils;

public class Constants {
    private Constants() {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    public static final String UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED = "Utility class should not be instantiated";

    public static final String MAPPER_SPRING = "spring";
    public static final String RESPONSE_MESSAGE_KEY = "Message";

    public static final Long ONE_MONTH = 1L;

    public static final String EXCEPTION_SERVICE_UNAVAILABLE = "The service is currently unavailable. Please try again later.";
    public static final String EXCEPTION_NOT_KNOW_NEXT_SUPPLY = "UnKnown supply date for article ID: ";
    public static final String EXCEPTION_ARTICLE_ID_NULL = "Article ID cannot be null";
    public static final String EXCEPTION_ARTICLE_ID_POSITIVE = "Article ID must be a positive number";
    public static final String EXCEPTION_ARTICLE_QUANTITY_NULL = "Quantity cannot be null";
    public static final String EXCEPTION_ARTICLE_QUANTITY_POSITIVE = "Quantity must be a positive number";
}
