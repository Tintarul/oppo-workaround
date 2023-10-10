package p005b;

import com.allawn.cryptography.entity.EncryptEnum;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p010g.Base64Utils;

/* renamed from: b.a */
/* loaded from: classes.dex */
public class AesUtil {
    /* renamed from: a */
    public static String m226a(String str, String str2, String str3) {
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(2, new SecretKeySpec(Base64Utils.m159a(str2), EncryptEnum.AES.name()), new IvParameterSpec(Base64Utils.m159a(str3)));
        return new String(cipher.doFinal(Base64Utils.m159a(str)));
    }

    /* renamed from: b */
    public static String m225b(String str, String str2, String str3) {
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(1, new SecretKeySpec(Base64Utils.m159a(str2), EncryptEnum.AES.name()), new IvParameterSpec(Base64Utils.m159a(str3)));
        return Base64Utils.m158b(cipher.doFinal(str.getBytes()));
    }

    /* renamed from: c */
    public static String m224c() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return Base64Utils.m158b(bArr);
    }

    /* renamed from: d */
    public static String m223d() {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return Base64Utils.m158b(keyGenerator.generateKey().getEncoded());
    }
}
