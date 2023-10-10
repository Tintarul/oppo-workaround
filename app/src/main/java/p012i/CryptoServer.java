package p012i;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import p000a.Crypto;
import p000a.Session;
import p009f.SceneConfig;
import p018o.RequestUtil;
import p019p.LogUtils;

/* renamed from: i.b */
/* loaded from: classes.dex */
public class CryptoServer {
    /* renamed from: a */
    public static String m119a(String str) {
        Session m286a = Crypto.m286a();
        try {
            try {
                return m286a.m277e(str, "SCENE_1");
            } catch (Exception e) {
                LogUtils.m8a("CryptoOtaServerUtil", "decrypt Exception: " + Log.getStackTraceString(e));
                Crypto.m282e(m286a);
                return "";
            }
        } finally {
            Crypto.m282e(m286a);
        }
    }

    /* renamed from: b */
    public static String m118b(String str, Session session) {
        try {
            return session.m276f(str, "SCENE_1");
        } catch (Exception e) {
            LogUtils.m8a("CryptoOtaServerUtil", "encrypt Exception: " + Log.getStackTraceString(e));
            return null;
        }
    }

    /* renamed from: c */
    public static void m117c(Context context) {
        Crypto.m283d(context, m116d());
        LogUtils.m8a("CryptoOtaServerUtil", "init Crypto SDK");
    }

    /* renamed from: d */
    public static ArrayList<SceneConfig> m116d() {
        ArrayList<SceneConfig> arrayList = new ArrayList<>();
        SceneConfig sceneConfig = new SceneConfig();
        sceneConfig.m178h("AES");
        sceneConfig.m175k("RSA");
        String m23k = RequestUtil.m23k();
        if (TextUtils.equals(m23k, "CN")) {
            sceneConfig.m173m("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApXYGXQpNL7gmMzzvajHaoZIHQQvBc2cOEhJc7/tsaO4sT0unoQnwQKfNQCuv7qC1Nu32eCLuewe9LSYhDXr9KSBWjOcCFXVXteLO9WCaAh5hwnUoP/5/Wz0jJwBA+yqs3AaGLA9wJ0+B2lB1vLE4FZNE7exUfwUc03fJxHG9nCLKjIZlrnAAHjRCd8mpnADwfkCEIPIGhnwq7pdkbamZcoZfZud1+fPsELviB9u447C6bKnTU4AaMcR9Y2/uI6TJUTcgyCp+ilgU0JxemrSIPFk3jbCbzamQ6Shkw/jDRzYoXpBRg/2QDkbq+j3ljInu0RHDfOeXf3VBfHSnQ66HCwIDAQAB");
            sceneConfig.m174l("1615879139745");
        } else if (TextUtils.equals(m23k, "IN")) {
            sceneConfig.m173m("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwYtghkzeStC9YvAwOQmWylbp74Tj8hhi3f9IlK7A/CWrGbLgzz/BeKxNb45zBN8pgaaEOwAJ1qZQV5G4nProWCPOP1ro1PkemFJvw/vzOOT5uN0ADnHDzZkZXCU/knxqUSfLcwQlHXsYhNsAm7uOKjY9YXF4zWzYN0eFPkML3Pj/zg7hl/ov9clB2VeyI1/blMHFfcNA/fvqDTENXcNBIhgJvXiCpLcZqp+aLZPC5AwY/sCb3j5jTWer0Rk0ZjQBZE1AncwYvUx4mA65U59cWpTyl4c47J29MsQ66hqWv6eBHlDNZSEsQpHePUqgsf7lmO5Wd7teB8ugQki2oz1Y5QIDAQAB");
            sceneConfig.m174l("1615896309308");
        } else if (m23k.startsWith("EU")) {
            sceneConfig.m173m("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh8/EThsK3f0WyyPgrtXb/D0Xni6UZNppaQHUqHWo976cybl92VxmehE0ISObnxERaOtrlYmTPIxkVC9MMueDvTwZ1l0KxevZVKU0sJRxNR9AFcw6D7k9fPzzpNJmhSlhpNbt3BEepdgibdRZbacF3NWy3ejOYWHgxC+I/Vj1v7QU5gD+1OhgWeRDcwuV4nGY1ln2lvkRj8EiJYXfkSq/wUI5AvPdNXdEqwou4FBcf6mD84G8pKDyNTQwwuk9lvFlcq4mRqgYaFg9DAgpDgqVK4NTJWM7tQS1GZuRA6PhupfDqnQExyBFhzCefHkEhcFywNyxlPe953NWLFWwbGvFKwIDAQAB");
            sceneConfig.m174l("1615897067573");
        } else {
            sceneConfig.m173m("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkA980wxi+eTGcFDiw2I6RrUeO4jL/Aj3Yw4dNuW7tYt+O1sRTHgrzxPD9SrOqzz7G0KgoSfdFHe3JVLPN+U1waK+T0HfLusVJshDaMrMiQFDUiKajb+QKr+bXQhVofH74fjat+oRJ8vjXARSpFk4/41x5j1Bt/2bHoqtdGPcUizZ4whMwzap+hzVlZgs7BNfepo24PWPRujsN3uopl+8u4HFpQDlQl7GdqDYDj2zNOHdFQI2UpSf0aIeKCKOpSKF72KDEESpJVQsqO4nxMwEi2jMujQeCHyTCjBZ+W35RzwT9+0pyZv8FB3c7FYY9FdF/+lvfax5mvFEBd9jO+dpMQIDAQAB");
            sceneConfig.m174l("1615895993238");
        }
        sceneConfig.m177i(86400);
        sceneConfig.m176j(true);
        sceneConfig.m172n("SCENE_1");
        arrayList.add(sceneConfig);
        return arrayList;
    }
}
