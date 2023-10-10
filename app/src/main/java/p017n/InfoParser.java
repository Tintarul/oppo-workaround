package p017n;

import com.color.otaassistant.protocol.bean.InstallBean;
import org.json.JSONObject;
import p015l.ChannelAuthentication;
import p019p.GsonUtils;

/* renamed from: n.b */
/* loaded from: classes.dex */
public class InfoParser {
    /* renamed from: a */
    public static String m56a(String str) {
        return new JSONObject(str).getString("keyPair_pub");
    }

    /* renamed from: b */
    public static InstallBean m55b(String str) {
        return (InstallBean) GsonUtils.m14a(str, InstallBean.class);
    }

    /* renamed from: c */
    public static ChannelAuthentication.C0499a m54c(String str) {
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.getBoolean("verifyPass")) {
            return new ChannelAuthentication.C0499a(jSONObject.getString("signData"), jSONObject.getString("signValue"), jSONObject.getString("pubKey"));
        }
        throw new IllegalArgumentException("pc rsa Verify fail");
    }
}
