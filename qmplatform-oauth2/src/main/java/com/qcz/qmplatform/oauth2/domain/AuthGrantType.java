package com.qcz.qmplatform.oauth2.domain;

public enum AuthGrantType {

    PASSWORD("password"),
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token"),
    AUTHORIZATION_CODE("authorization_code");

    private final String type;

    AuthGrantType(String type) {
        this.type = type;
    }

    public String value() {
        return type;
    }
}
