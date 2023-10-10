package com.allawn.cryptography.exception;

/* loaded from: classes.dex */
public class CryptoExpiredException extends Exception {
    public CryptoExpiredException(String str) {
        super("key for Scene(" + str + ") is expired.");
    }
}
