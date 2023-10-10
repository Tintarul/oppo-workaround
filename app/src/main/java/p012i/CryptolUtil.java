package p012i;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Locale;
import javax.crypto.Cipher;
import p019p.LogUtils;

/* renamed from: i.c */
/* loaded from: classes.dex */
public class CryptolUtil {
    /* renamed from: a */
    public static String m115a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append("0" + hexString);
            } else {
                sb.append(hexString);
            }
        }
        LogUtils.m8a("CryptolUtil", "bytes end");
        return sb.toString().toLowerCase(Locale.getDefault());
    }

    /* renamed from: b */
    public static KeyStore m114b(String str, int i) {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(str);
        keyPairGenerator.initialize(i);
        KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
        return new KeyStore(generateKeyPair.getPublic(), generateKeyPair.getPrivate());
    }

    /* renamed from: c */
    public static String m113c(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return m115a(MessageDigest.getInstance(str2).digest(str.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: d */
    public static String m112d(String str, String str2, Key key, AlgorithmParameterSpec algorithmParameterSpec) {
        if (TextUtils.isEmpty(str2)) {
            return str2;
        }
        try {
            Cipher cipher = Cipher.getInstance(str);
            cipher.init(2, key, algorithmParameterSpec);
            return new String(cipher.doFinal(Base64.decode(str2, 0)));
        } catch (Exception e) {
            LogUtils.m4e("CryptolUtil", "decrypt Exception: " + Log.getStackTraceString(e));
            return str2;
        }
    }

    /* renamed from: e */
    public static String m111e(String str, String str2, Key key, AlgorithmParameterSpec algorithmParameterSpec) {
        if (TextUtils.isEmpty(str2)) {
            return str2;
        }
        try {
            Cipher cipher = Cipher.getInstance(str);
            cipher.init(1, key, algorithmParameterSpec);
            return Base64.encodeToString(cipher.doFinal(str2.getBytes()), 0);
        } catch (Exception e) {
            LogUtils.m4e("CryptolUtil", "encrypt Exception: " + Log.getStackTraceString(e));
            return str2;
        }
    }

    /* renamed from: f */
    public static String m110f(int i) {
        byte[] bArr = new byte[i];
        new SecureRandom().nextBytes(bArr);
        return Base64.encodeToString(bArr, 0).substring(0, i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x0037, code lost:
        if (r3 == null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0039, code lost:
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x003e, code lost:
        if (r3 == null) goto L22;
     */
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m109g(File file) {
        FileInputStream fileInputStream;
        byte[] bArr = new byte[1024];
        FileInputStream fileInputStream2 = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            try {
                fileInputStream = new FileInputStream(file);
                while (true) {
                    try {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        byte[] bArr2 = new byte[read];
                        System.arraycopy(bArr, 0, bArr2, 0, read);
                        messageDigest.update(bArr2);
                    } catch (FileNotFoundException unused) {
                    } catch (IOException unused2) {
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException unused3) {
                            }
                        }
                        throw th;
                    }
                }
                String m115a = m115a(messageDigest.digest());
                try {
                    fileInputStream.close();
                } catch (IOException unused4) {
                }
                return m115a;
            } catch (FileNotFoundException unused5) {
                fileInputStream = null;
            } catch (IOException unused6) {
                fileInputStream = null;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException | NoSuchAlgorithmException unused7) {
        }
        return null;
    }
}
