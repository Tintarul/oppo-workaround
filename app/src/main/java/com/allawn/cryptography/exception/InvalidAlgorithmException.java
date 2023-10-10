package com.allawn.cryptography.exception;

/* loaded from: classes.dex */
public class InvalidAlgorithmException extends Exception {
    public InvalidAlgorithmException(String str) {
        super("Algorithm(" + str + ") not supported in current scene.");
    }
}
