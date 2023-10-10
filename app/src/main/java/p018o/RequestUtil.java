package p018o;

import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import java.util.Locale;
import p019p.LogUtils;

/* renamed from: o.b */
/* loaded from: classes.dex */
public class RequestUtil {
    /* renamed from: a */
    public static String m33a() {
        String str = Build.VERSION.RELEASE;
        return "Android" + str;
    }

    /* renamed from: b */
    public static String m32b(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getImei();
    }

    /* renamed from: c */
    public static String m31c() {
        return Locale.getDefault().toLanguageTag();
    }

    /* renamed from: d */
    public static String m30d() {
        return SystemProperties.get("ro.build.oplus_nv_id", "");
    }

    /* renamed from: e */
    public static String m29e() {
        String str = SystemProperties.get("ro.vendor.oplus.operator", "");
        return TextUtils.isEmpty(str) ? SystemProperties.get("ro.vendor.oppo.operator", "") : str;
    }

    /* renamed from: f */
    public static String m28f() {
        String m17q = m17q();
        if (TextUtils.isEmpty(m17q)) {
            m17q = SystemProperties.get("ro.build.version.opporom");
        }
        LogUtils.m8a("RequestUtil", "invokeVersionRelease " + m17q);
        m17q = (m17q == null || m17q.length() == 0) ? "V1.0.0" : "V1.0.0";
        String lowerCase = m17q.toLowerCase(Locale.getDefault());
        String str = "alpha";
        if (!lowerCase.endsWith("alpha")) {
            str = lowerCase.endsWith("beta") ? "beta" : "";
        }
        int indexOf = m17q.indexOf("V") + 1;
        if (-1 == indexOf) {
            indexOf = 0;
        }
        int indexOf2 = m17q.indexOf("_");
        if (-1 == indexOf2) {
            indexOf2 = m17q.length();
        }
        String replaceAll = m17q.substring(indexOf, indexOf2).replaceAll("\n", " ");
        return "ColorOS" + replaceAll + str;
    }

    /* renamed from: g */
    public static String m27g(Context context) {
        String m26h = m26h();
        if (SystemProperties.getInt("oplus.ota.debug.root", 0) == 1 && m26h.contains("PDTTest")) {
            LogUtils.m8a("RequestUtil", "debug for root test!");
            m26h = m26h.replace("PDTTest", "");
        }
        if (TextUtils.isEmpty(m26h)) {
            LogUtils.m8a("RequestUtil", "oh, the ota version is error!");
            return "";
        }
        return m26h;
    }

    /* renamed from: h */
    public static String m26h() {
        return SystemProperties.get("ro.build.version.ota");
    }

    /* renamed from: i */
    public static String m25i() {
        return SystemProperties.get("ro.oplus.pipeline_key", "");
    }

    /* renamed from: j */
    public static String m24j() {
        return SystemProperties.get("ro.product.name", "");
    }

    /* renamed from: k */
    public static String m23k() {
        String str = SystemProperties.get("ro.oplus.pipeline.region", "");
        if (TextUtils.isEmpty(str)) {
            str = SystemProperties.get("ro.vendor.oplus.regionmark", "");
        }
        return TextUtils.isEmpty(str) ? SystemProperties.get("ro.vendor.oppo.regionmark", "UNKNOWN") : str;
    }

    /* renamed from: l */
    public static String m22l() {
        String str = SystemProperties.get("ro.build.display.id");
        return ((str == null || str.length() == 0) ? "V1.0.0" : "V1.0.0").replaceAll("\n", " ");
    }

    /* renamed from: m */
    public static String m21m() {
        return SystemProperties.get("ro.build.version.security_patch", "");
    }

    /* renamed from: n */
    public static String m20n() {
        return SystemProperties.get("ro.vendor.build.security_patch", "");
    }

    /* renamed from: o */
    public static String m19o() {
        String str = SystemProperties.get("persist.sys.oplus.region", "");
        return TextUtils.isEmpty(str) ? SystemProperties.get("persist.sys.oppo.region", "UNKNOWN") : str;
    }

    /* renamed from: p */
    public static int m18p() {
        return (!m16r() && m15s()) ? 101 : 100;
    }

    /* renamed from: q */
    public static String m17q() {
        try {
            return (String) Class.forName("com.oplus.os.OplusBuild$VERSION").getField("RELEASE").get(null);
        } catch (Exception e) {
            LogUtils.m8a("RequestUtil", "invokeVersionRelease " + Log.getStackTraceString(e));
            return "";
        }
    }

    /* renamed from: r */
    public static boolean m16r() {
        return 1 == SystemProperties.getInt("oplus.ota.debug.mode", 0);
    }

    /* renamed from: s */
    public static boolean m15s() {
        String str = SystemProperties.get("ro.boot.veritymode", "");
        LogUtils.m8a("RequestUtil", "verity state=" + str);
        return !"enforcing".equals(str);
    }
}
