package p019p;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.io.File;

/* renamed from: p.b */
/* loaded from: classes.dex */
public class InstallUtil {
    /* renamed from: a */
    public static void m12a(File file) {
        if (!file.exists()) {
            LogUtils.m7b("InstallUtil", file.getName() + " not exist");
        } else if (file.isFile()) {
            if (file.delete()) {
                return;
            }
            LogUtils.m7b("InstallUtil", file.getName() + " delete failed");
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file2 : listFiles) {
                    m12a(file2);
                }
                if (file.delete()) {
                    return;
                }
                LogUtils.m7b("InstallUtil", file.getName() + " delete failed");
            } else if (!file.delete()) {
                LogUtils.m7b("InstallUtil", file.getName() + " delete failed");
            }
        }
    }

    /* renamed from: b */
    public static void m11b() {
        m12a(new File("/data/ota_package/otaAssistant"));
        m12a(new File("/data/oplus_ota_package/otaAssistant"));
    }

    /* renamed from: c */
    public static int m10c(Context context) {
        Intent registerReceiver = context.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver != null) {
            return registerReceiver.getIntExtra("level", -1);
        }
        return 0;
    }

    /* renamed from: d */
    public static boolean m9d(Context context) {
        return m10c(context) < 20;
    }
}
