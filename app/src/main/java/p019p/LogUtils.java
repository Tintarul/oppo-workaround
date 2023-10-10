package p019p;

import android.os.SystemProperties;
import android.util.Log;

/* renamed from: p.c */
/* loaded from: classes.dex */
public class LogUtils {

    /* renamed from: a */
    public static final boolean f227a = SystemProperties.getBoolean("persist.sys.assert.panic", false);

    /* renamed from: a */
    public static void m8a(String str, String str2) {
        if (f227a) {
            if (str2.length() > 3072) {
                m5d(3, str + ": " + str2);
                return;
            }
            Log.d("OtaAssistant", str + ": " + str2);
        }
    }

    /* renamed from: b */
    public static void m7b(String str, String str2) {
        if (f227a) {
            if (str2.length() > 3072) {
                m5d(6, str + ": " + str2);
                return;
            }
            Log.e("OtaAssistant", str + ": " + str2);
        }
    }

    /* renamed from: c */
    public static void m6c(String str, String str2) {
        if (f227a) {
            if (str2.length() > 3072) {
                m5d(4, str + ": " + str2);
                return;
            }
            Log.i("OtaAssistant", str + ": " + str2);
        }
    }

    /* renamed from: d */
    public static void m5d(int i, String str) {
        String substring;
        int i2 = 0;
        while (i2 < str.length()) {
            int i3 = i2 + 3072;
            if (str.length() <= i3) {
                substring = str.substring(i2);
            } else {
                substring = str.substring(i2, i3);
            }
            if (i == 2) {
                Log.v("OtaAssistant", substring);
            } else if (i == 3) {
                Log.d("OtaAssistant", substring);
            } else if (i == 4) {
                Log.i("OtaAssistant", substring);
            } else if (i == 5) {
                Log.w("OtaAssistant", substring);
            } else if (i == 6) {
                Log.e("OtaAssistant", substring);
            }
            i2 = i3;
        }
    }

    /* renamed from: e */
    public static void m4e(String str, String str2) {
        if (f227a) {
            if (str2.length() > 3072) {
                m5d(5, str + ": " + str2);
                return;
            }
            Log.w("OtaAssistant", str + ": " + str2);
        }
    }
}
