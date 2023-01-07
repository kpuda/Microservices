package com.kp.user.service;

public enum ServerConst {
    USER_EXISTS_ALREADY("User exists already");
    private final String name;
    ServerConst(String name) {
        this.name = name;
    }
}
