package p018o;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import p000a.Crypto;
import p000a.Session;
import p012i.CryptoServer;
import p012i.CryptolUtil;
import p019p.LogUtils;

/* renamed from: o.a */
/* loaded from: classes.dex */
public class RequestBuilder {
    /* renamed from: a */
    private static String m36a(Context context) {
        JSONObject jSONObject = new JSONObject();
        String[] split = SystemProperties.get("ro.oplus.components.list").split(",");
        JSONArray jSONArray = new JSONArray();
        for (String str : split) {
            JSONObject jSONObject2 = new JSONObject();
            if (!TextUtils.isEmpty(str)) {
                String str2 = SystemProperties.get("ro.oplus.image." + str + ".version");
                if (TextUtils.isEmpty(str2)) {
                    str2 = SystemProperties.get("ro.oplus.version." + str);
                    LogUtils.m8a("RequestBuilder", "have no image version, manifest componentVersion: " + str2);
                }
                if (str.equalsIgnoreCase("base")) {
                    str = "system_vendor";
                }
                jSONObject2.put("componentName", str);
                jSONObject2.put("componentVersion", str2);
                jSONArray.put(jSONObject2);
            }
        }
        if (jSONArray.length() > 0) {
            jSONObject.put("components", jSONArray);
        }
        jSONObject.put("mode", "0");
        jSONObject.put("time", System.currentTimeMillis());
        boolean z = RequestUtil.m18p() == 101;
        jSONObject.put("isRooted", z ? "1" : "0");
        if (RequestUtil.m15s()) {
            jSONObject.put("type", "1");
        } else {
            jSONObject.put("type", z ? "1" : "0");
        }
        jSONObject.put("registrationId", "");
        jSONObject.put("securityPatch", RequestUtil.m21m());
        jSONObject.put("securityPatchVendor", RequestUtil.m20n());
        jSONObject.put("deviceId", CryptolUtil.m113c(RequestUtil.m32b(context), "SHA256"));
        LogUtils.m8a("RequestBuilder", "buildRequestBody origin json string = " + jSONObject.toString());
        return jSONObject.toString();
    }

    /* renamed from: b */
    private static String m35b(Context context, String str) {
        String m31c = RequestUtil.m31c();
        String m22l = RequestUtil.m22l();
        String m33a = RequestUtil.m33a();
        String m28f = RequestUtil.m28f();
        String m29e = RequestUtil.m29e();
        String m27g = RequestUtil.m27g(context);
        String m23k = RequestUtil.m23k();
        String m19o = RequestUtil.m19o();
        String m24j = RequestUtil.m24j();
        String m30d = RequestUtil.m30d();
        String m25i = RequestUtil.m25i();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("language", m31c);
        jSONObject.put("romVersion", m22l);
        jSONObject.put("androidVersion", m33a);
        jSONObject.put("colorOSVersion", m28f);
        jSONObject.put("infVersion", "1");
        jSONObject.put("operator", m29e);
        jSONObject.put("trackRegion", m23k);
        jSONObject.put("uRegion", m19o);
        jSONObject.put("model", m24j);
        jSONObject.put("mode", "manual");
        jSONObject.put("nvCarrier", m30d);
        jSONObject.put("pipelineKey", m25i);
        jSONObject.put("version", "2");
        jSONObject.put("deviceId", CryptolUtil.m113c(RequestUtil.m32b(context), "SHA256"));
        jSONObject.put("channel", "pc");
        LogUtils.m8a("RequestBuilder", "buildRequestHeader origin json string = " + jSONObject.toString());
        jSONObject.put("otaVersion", m27g);
        jSONObject.put("protectedKey", str);
        return jSONObject.toString();
    }

    /* renamed from: c */
    public static String m34c(Context context) {
        JSONObject jSONObject = new JSONObject();
        Session m286a = Crypto.m286a();
        try {
            try {
                jSONObject.put("requestBody", new JSONObject().put("params", CryptoServer.m118b(m36a(context), m286a)).toString());
                jSONObject.put("requestHeader", m35b(context, m286a.m274h("SCENE_1")));
                Crypto.m282e(m286a);
                return jSONObject.toString();
            } catch (Exception e) {
                LogUtils.m7b("RequestBuilder", "buildUpdateRequestInfo Exception : " + Log.getStackTraceString(e));
                Crypto.m282e(m286a);
                return "";
            }
        } catch (Throwable th) {
            Crypto.m282e(m286a);
            throw th;
        }
    }
}
