package com.emazon.transactions.utils;

public class Constants {
    private Constants() {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    public static final String UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED = "Utility class should not be instantiated";

    public static final String MAPPER_SPRING = "spring";
    public static final String MAPPER_SALE_ENTITY_IGNORE_ITEMS = "items";

    public static final String RESPONSE_MESSAGE_KEY = "Message";

    public static final Double DOUBLE_ZERO = 0D;

    public static final Long ONE_MONTH = 1L;

    public static final String EXCEPTION_SALE_ITEMS_NULL = "The list of sale items cannot be null.";
    public static final String EXCEPTION_SALE_ITEMS_EMPTY = "The list of sale items cannot be empty.";

    public static final String EXCEPTION_SERVICE_UNAVAILABLE = "The service is currently unavailable. Please try again later.";
    public static final String EXCEPTION_ARTICLE_ID_NULL = "Article ID cannot be null";
    public static final String EXCEPTION_ARTICLE_ID_POSITIVE = "Article ID must be a positive number";
    public static final String EXCEPTION_ARTICLE_QUANTITY_NULL = "Quantity cannot be null";
    public static final String EXCEPTION_ARTICLE_QUANTITY_POSITIVE = "Quantity must be a positive number";

    //Sale-Status
    public static final String SALE_STATUS_PENDING = "PENDING";
    public static final String SALE_STATUS_COMPLETED = "COMPLETED";
    public static final String SALE_STATUS_FAILED = "FAILED";
    //Sale-Exceptions
    public static final String EXCEPTION_OUT_OF_STOCK = "The following articles are out of stock: ";
}
