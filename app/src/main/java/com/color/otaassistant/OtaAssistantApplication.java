package com.color.otaassistant;

import android.app.Application;
import android.content.Context;
import p011h.PermissionUtil;
import p012i.CryptoServer;

/* loaded from: classes.dex */
public class OtaAssistantApplication extends Application {

    /* renamed from: a */
    private static Context f134a;

    /* renamed from: a */
    public static Context m216a() {
        return f134a;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        f134a = this;
        CryptoServer.m117c(this);
        PermissionUtil.m133e(f134a);
    }
}
