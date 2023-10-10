package p005b;

import androidx.annotation.NonNull;
import com.allawn.cryptography.entity.NegotiationEnum;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import p010g.Base64Utils;

/* renamed from: b.b */
/* loaded from: classes.dex */
public class RsaUtil {
    @NonNull
    /* renamed from: a */
    public static String m222a(@NonNull String str, @NonNull String str2) {
        Cipher cipher = Cipher.getInstance("RSA/None/OAEPPadding");
        cipher.init(1, KeyFactory.getInstance(NegotiationEnum.RSA.name()).generatePublic(new X509EncodedKeySpec(Base64Utils.m159a(str2))));
        return Base64Utils.m158b(cipher.doFinal(str.getBytes()));
    }
}
