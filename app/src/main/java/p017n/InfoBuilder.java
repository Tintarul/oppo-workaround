package p017n;

import android.content.Context;
import com.color.otaassistant.protocol.bean.InstallBean;
import org.json.JSONObject;
import p011h.PermissionUtil;
import p012i.CryptoProtocol;
import p012i.CryptoServer;
import p014k.InstallPackage;
import p014k.RecoveryMode;
import p014k.VABMode;
import p018o.RequestBuilder;
import p019p.LogUtils;
import p019p.VersionUtil;

/* renamed from: n.a */
/* loaded from: classes.dex */
public class InfoBuilder {
    /* renamed from: a */
    public static String m63a() {
        JSONObject jSONObject = new JSONObject();
        CryptoProtocol.m125c(jSONObject);
        return jSONObject.toString();
    }

    /* renamed from: b */
    public static String m62b(boolean z) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("verifyPass", z);
        CryptoProtocol.m125c(jSONObject);
        return jSONObject.toString();
    }

    /* renamed from: c */
    public static String m61c(String str) {
        return CryptoServer.m119a(new JSONObject(str).getString("body"));
    }

    /* renamed from: d */
    public static String m60d(Context context) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("isSupportUpgrade", m57g(context));
        CryptoProtocol.m124d(jSONObject);
        return jSONObject.toString();
    }

    /* renamed from: e */
    public static String m59e(Context context) {
        return RequestBuilder.m34c(context);
    }

    /* renamed from: f */
    public static String m58f(Context context, InstallBean installBean) {
        InstallPackage recoveryMode;
        LogUtils.m8a("InfoBuilder", "verify install");
        JSONObject jSONObject = new JSONObject();
        if (VersionUtil.m1c()) {
            recoveryMode = new VABMode(context, installBean);
        } else {
            recoveryMode = new RecoveryMode(context, installBean);
        }
        jSONObject.put("verifyResult", recoveryMode.m106b(context, installBean));
        return jSONObject.toString();
    }

    /* renamed from: g */
    public static boolean m57g(Context context) {
        if (!VersionUtil.m1c() || VersionUtil.m2b(context, "com.oplus.ota") || VersionUtil.m2b(context, "com.oppo.ota")) {
            return VersionUtil.m3a() && PermissionUtil.m135c(context);
        }
        LogUtils.m7b("InfoBuilder", "vab mode not has ota package, not support upgrade");
        return false;
    }
}
