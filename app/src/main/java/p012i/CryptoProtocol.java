package p012i;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;
import p019p.LogUtils;

/* renamed from: i.a */
/* loaded from: classes.dex */
public class CryptoProtocol {

    /* renamed from: a */
    private static KeyStore<ECPublicKey, ECPrivateKey> f181a;

    /* renamed from: a */
    public static String m127a(String str) {
        return CryptolUtil.m113c(Base64.encodeToString(m126b((ECPublicKey) KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0))), f181a.f183b, "ECDH").getEncoded(), 2), "SHA256");
    }

    /* renamed from: b */
    public static SecretKey m126b(PublicKey publicKey, PrivateKey privateKey, String str) {
        KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH");
        keyAgreement.init(privateKey);
        keyAgreement.doPhase(publicKey, true);
        return keyAgreement.generateSecret(str);
    }

    /* renamed from: c */
    public static void m125c(JSONObject jSONObject) {
        try {
            KeyStore m114b = CryptolUtil.m114b("EC", 256);
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initSign(m114b.f183b);
            String str = System.currentTimeMillis() + CryptolUtil.m110f(16);
            signature.update(str.getBytes());
            jSONObject.put("signData", str);
            jSONObject.put("signValue", Base64.encodeToString(signature.sign(), 0));
            jSONObject.put("pubKey", Base64.encodeToString(((ECPublicKey) m114b.f182a).getEncoded(), 0));
        } catch (Exception e) {
            LogUtils.m4e("CryptoProtocol", "buildAuthenticationData Exception: " + Log.getStackTraceString(e));
        }
    }

    /* renamed from: d */
    public static void m124d(JSONObject jSONObject) {
        try {
            jSONObject.put("keyPair_pub", Base64.encodeToString(m121g().f182a.getEncoded(), 0));
        } catch (Exception e) {
            LogUtils.m6c("CryptoProtocol", "buildOvalDigitalSignature Exception: " + Log.getStackTraceString(e));
        }
    }

    /* renamed from: e */
    public static String m123e(String str, String str2) {
        return (TextUtils.isEmpty(str2) || str2.length() < 32) ? str : CryptolUtil.m112d("AES/CTR/NoPadding", str, new SecretKeySpec(str2.substring(0, 32).getBytes(), "AES"), new IvParameterSpec(str2.substring(str2.length() - 16).getBytes()));
    }

    /* renamed from: f */
    public static String m122f(String str, String str2) {
        return (TextUtils.isEmpty(str2) || str2.length() < 32) ? str : CryptolUtil.m111e("AES/CTR/NoPadding", str, new SecretKeySpec(str2.substring(0, 32).getBytes(), "AES"), new IvParameterSpec(str2.substring(str2.length() - 16).getBytes()));
    }

    /* renamed from: g */
    public static KeyStore<ECPublicKey, ECPrivateKey> m121g() {
        synchronized (CryptoProtocol.class) {
            if (f181a == null) {
                f181a = CryptolUtil.m114b("EC", 256);
            }
        }
        return f181a;
    }

    /* renamed from: h */
    public static boolean m120h(String str, String str2, byte[] bArr) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(Base64.decode(bArr, 0)));
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initVerify(generatePublic);
            signature.update(str.getBytes());
            return signature.verify(Base64.decode(str2, 0));
        } catch (Exception e) {
            LogUtils.m7b("CryptoProtocol", "verifySignature exception: " + Log.getStackTraceString(e));
            return false;
        }
    }
}
