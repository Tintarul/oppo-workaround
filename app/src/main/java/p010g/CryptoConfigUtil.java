package p010g;

import java.util.Map;
import org.json.JSONObject;
import p009f.CryptoConfig;

/* renamed from: g.c */
/* loaded from: classes.dex */
public class CryptoConfigUtil {
    /* renamed from: a */
    public static String m153a(Map<String, CryptoConfig> map) {
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            CryptoConfig cryptoConfig = map.get(str);
            if (cryptoConfig != null) {
                jSONObject.put(str, m152b(cryptoConfig));
            } else {
                throw new NullPointerException("scene(" + str + ") not found in cryptoConfigs.");
            }
        }
        return jSONObject.toString();
    }

    /* renamed from: b */
    private static JSONObject m152b(CryptoConfig cryptoConfig) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("protectedKey", cryptoConfig.m187b());
        jSONObject.put("version", cryptoConfig.m186c());
        jSONObject.put("negotiationVersion", cryptoConfig.m188a());
        return jSONObject;
    }
}
