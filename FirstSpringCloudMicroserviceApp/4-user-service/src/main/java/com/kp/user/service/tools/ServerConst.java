package com.kp.user.service.tools;

import lombok.Getter;

public enum ServerConst {

    ORDER_NOT_FOUND("Order with given ID not found in database"),
    USER_EXISTS_ALREADY("User exists already"),
    LIST_IS_EMPTY("Order list is empty");

    @Getter
    private final String message;

    ServerConst(final String message) {
        this.message = message;
    }


}
