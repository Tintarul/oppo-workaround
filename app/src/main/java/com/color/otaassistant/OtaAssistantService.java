package com.color.otaassistant;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import p011h.MessageServer;
import p019p.LogUtils;

/* loaded from: classes.dex */
public class OtaAssistantService extends Service {

    /* renamed from: a */
    private OtaAssistantReceiver f136a;

    /* renamed from: b */
    private MessageServer f137b;

    /* renamed from: c */
    private void m213c() {
        this.f136a = new OtaAssistantReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_STATE");
        registerReceiver(this.f136a, intentFilter);
    }

    /* renamed from: d */
    private void m212d() {
        OtaAssistantReceiver otaAssistantReceiver = this.f136a;
        if (otaAssistantReceiver != null) {
            unregisterReceiver(otaAssistantReceiver);
        }
    }

    /* renamed from: a */
    public void m215a() {
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager.getNotificationChannel("OtaAssistantService_notify_new_channel_id") == null) {
            notificationManager.createNotificationChannel(new NotificationChannel("OtaAssistantService_notify_new_channel_id", getString(R.string.app_name), 3));
        }
    }

    /* renamed from: b */
    public Notification m214b() {
        return new Notification.Builder(this, "OtaAssistantService_notify_new_channel_id").setSmallIcon(R.drawable.notice_logo).setContentText(getString(R.string.notify_service_message)).setShowWhen(false).build();
    }

    @Override // android.app.Service
    protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        try {
            String str = strArr[0];
            String str2 = strArr[1];
            LogUtils.m8a("OtaAssistantService", "dumpInfo---type = " + str + "---command = " + str2);
            if ("getInfo".equals(str) && "localPort".equals(str2) && printWriter != null) {
                printWriter.println(this.f137b.m142c());
            }
        } catch (Exception e) {
            LogUtils.m8a("OtaAssistantService", "dumpInfo---error : " + e.getMessage());
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtils.m8a("OtaAssistantService", "onCreate");
        m213c();
        m215a();
        startForeground(9527, m214b());
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtils.m8a("OtaAssistantService", "onDestroy");
        MessageServer messageServer = this.f137b;
        if (messageServer != null) {
            messageServer.m138g();
        }
        m212d();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            return 2;
        }
        String action = intent.getAction();
        LogUtils.m8a("OtaAssistantService", "onStartCommand action = " + action);
        action.hashCode();
        if (action.equals("oplus.intent.action.OTA_ASSISTANT_PC_TOOL_SERVICE")) {
            if (this.f137b == null) {
                MessageServer messageServer = new MessageServer();
                this.f137b = messageServer;
                messageServer.m139f();
            } else {
                LogUtils.m8a("OtaAssistantService", "MessageServer already running");
            }
        }
        return 2;
    }
}
