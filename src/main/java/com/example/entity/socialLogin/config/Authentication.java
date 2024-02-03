package com.example.entity.socialLogin.config;

public record Authentication(
        long userId
) {

    public static Authentication createEmptyAuthentication() {
        return new Authentication(-1L);
    }

}
