package p010g;

import org.json.JSONObject;
import p009f.CipherContainer;

/* renamed from: g.b */
/* loaded from: classes.dex */
public class CipherUtil {
    /* renamed from: a */
    private static CipherContainer m157a(String str) {
        JSONObject jSONObject = new JSONObject(str);
        return new CipherContainer(jSONObject.getString("cipher"), jSONObject.getString("iv"));
    }

    /* renamed from: b */
    private static String m156b(CipherContainer cipherContainer) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cipher", cipherContainer.m190a());
        jSONObject.put("iv", cipherContainer.m189b());
        return jSONObject.toString();
    }

    /* renamed from: c */
    public static CipherContainer m155c(String str) {
        return m157a(str);
    }

    /* renamed from: d */
    public static String m154d(String str, String str2) {
        return m156b(new CipherContainer(str, str2));
    }
}
