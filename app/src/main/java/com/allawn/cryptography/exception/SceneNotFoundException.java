package com.allawn.cryptography.exception;

/* loaded from: classes.dex */
public class SceneNotFoundException extends Exception {
    public SceneNotFoundException(String str) {
        super("Scene(" + str + ") not found.");
    }

    public SceneNotFoundException(Throwable th) {
        super(th);
    }
}
