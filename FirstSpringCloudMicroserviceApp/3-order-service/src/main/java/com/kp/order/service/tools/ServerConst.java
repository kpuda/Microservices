package com.kp.order.service.tools;

import lombok.Getter;

public enum ServerConst {

    ORDER_NOT_FOUND("Order with given ID not found in database"),
    LIST_IS_EMPTY("Order list is empty");

    @Getter
    private final String message;

    ServerConst(final String message) {
        this.message = message;
    }


}
