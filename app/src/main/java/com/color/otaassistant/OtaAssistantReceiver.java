package com.color.otaassistant;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import p011h.PermissionUtil;
import p019p.InstallUtil;
import p019p.LogUtils;

/* loaded from: classes.dex */
public class OtaAssistantReceiver extends BroadcastReceiver {

    /* renamed from: a */
    private static boolean f135a = false;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle extras;
        String action = intent.getAction();
        LogUtils.m8a("OtaAssistantReceiver", "onReceive action : " + action);
        if ((TextUtils.equals(action, "oplus.intent.action.BOOT_COMPLETED") || TextUtils.equals(action, "oppo.intent.action.BOOT_COMPLETED")) && !f135a) {
            if (PermissionUtil.m135c(context)) {
                LogUtils.m8a("OtaAssistantReceiver", "device has restarted, uninstall");
                InstallUtil.m11b();
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    packageManager.deletePackage(context.getPackageName(), null, 0);
                }
            }
        } else if (TextUtils.equals(action, "oplus.intent.action.OTA_ASSISTANT_PC_TOOL_RECEIVER")) {
            Intent intent2 = new Intent("oplus.intent.action.OTA_ASSISTANT_PC_TOOL_SERVICE");
            intent2.setPackage(context.getPackageName());
            context.startForegroundService(intent2);
            f135a = true;
        } else if (!TextUtils.equals(action, "android.hardware.usb.action.USB_STATE") || (extras = intent.getExtras()) == null || extras.getBoolean("connected", true)) {
        } else {
            LogUtils.m8a("OtaAssistantReceiver", "usb disconnected kill myself process");
            Process.killProcess(Process.myPid());
        }
    }
}
