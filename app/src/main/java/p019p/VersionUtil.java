package p019p;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.SystemProperties;

/* renamed from: p.d */
/* loaded from: classes.dex */
public class VersionUtil {
    /* renamed from: a */
    public static boolean m3a() {
        return Build.VERSION.SDK_INT >= 30;
    }

    /* renamed from: b */
    public static boolean m2b(Context context, String str) {
        String str2;
        if (context == null) {
            LogUtils.m7b("VersionUtil", "context is null");
            return false;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo != null && (str2 = applicationInfo.packageName) != null) {
                if (str2.equals(str)) {
                    return true;
                }
                LogUtils.m7b("VersionUtil", "packageName not match.");
            } else {
                LogUtils.m7b("VersionUtil", "has not packageName or get application info null.");
            }
        } catch (Exception e) {
            LogUtils.m7b("VersionUtil", "get package failed:" + e.toString());
        }
        return false;
    }

    /* renamed from: c */
    public static boolean m1c() {
        try {
            return SystemProperties.getBoolean("ro.virtual_ab.enabled", false);
        } catch (Exception e) {
            LogUtils.m7b("VersionUtil", "get ab.enabled failed:" + e.toString());
            return false;
        }
    }

    /* renamed from: d */
    public static boolean m0d() {
        return Build.VERSION.SDK_INT >= 31;
    }
}
