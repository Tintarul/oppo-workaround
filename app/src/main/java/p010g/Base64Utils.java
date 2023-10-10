package p010g;

import android.util.Base64;

/* renamed from: g.a */
/* loaded from: classes.dex */
public class Base64Utils {
    /* renamed from: a */
    public static byte[] m159a(String str) {
        return Base64.decode(str, 2);
    }

    /* renamed from: b */
    public static String m158b(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }
}
