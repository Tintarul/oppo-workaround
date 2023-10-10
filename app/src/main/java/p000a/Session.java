package p000a;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.allawn.cryptography.EncryptException;
import com.allawn.cryptography.entity.EncryptEnum;
import com.allawn.cryptography.exception.InvalidAlgorithmException;
import com.allawn.cryptography.exception.SceneNotFoundException;
import com.allawn.cryptography.exception.SessionExpiredException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.json.JSONException;
import p005b.AesUtil;
import p006c.CryptoCore;
import p007d.SessionSceneDataRepository;
import p009f.CipherContainer;
import p009f.CryptoConfig;
import p009f.SceneConfig;
import p009f.SceneData;
import p010g.CipherUtil;
import p010g.CryptoConfigUtil;
import p010g.SceneUtil;

/* renamed from: a.c */
/* loaded from: classes.dex */
public class Session {

    /* renamed from: a */
    private final CryptoCore f5a;

    /* renamed from: b */
    private final SessionSceneDataRepository f6b = new SessionSceneDataRepository();

    /* renamed from: c */
    private final Map<String, CryptoConfig> f7c = new HashMap();

    /* renamed from: d */
    private Handler f8d;

    /* renamed from: e */
    private Runnable f9e;

    /* renamed from: f */
    private boolean f10f;

    /* renamed from: g */
    private int f11g;

    public Session(CryptoCore cryptoCore, int i) {
        this.f11g = 60;
        this.f5a = cryptoCore;
        this.f11g = i;
    }

    /* renamed from: c */
    private void m279c() {
        this.f6b.m196b();
        this.f7c.clear();
    }

    /* renamed from: d */
    private SceneData m278d(String str) {
        return SceneUtil.m148c(this.f5a.m219c(str));
    }

    /* renamed from: i */
    private SceneConfig m273i(String str) {
        return this.f5a.m219c(str);
    }

    @NonNull
    /* renamed from: j */
    private SceneData m272j(@NonNull String str) {
        if (m273i(str).m179g()) {
            return this.f5a.m218d(str);
        }
        return m271k(str);
    }

    @NonNull
    /* renamed from: k */
    private SceneData m271k(@NonNull String str) {
        SceneData m195c = this.f6b.m195c(str);
        if (m195c == null) {
            SceneData m278d = m278d(str);
            this.f6b.m197a(m278d);
            return m278d;
        }
        return m195c;
    }

    /* renamed from: l */
    public /* synthetic */ void m270l() {
        this.f10f = true;
        m279c();
    }

    /* renamed from: b */
    public void m280b() {
        Runnable runnable;
        this.f10f = false;
        Handler handler = this.f8d;
        if (handler != null && (runnable = this.f9e) != null) {
            handler.removeCallbacks(runnable);
        }
        if (this.f8d == null) {
            this.f8d = new Handler(Looper.getMainLooper());
        }
        if (this.f9e == null) {
            this.f9e = new Runnable() { // from class: a.b
                @Override // java.lang.Runnable
                public final void run() {
                    Session.this.m270l();
                }
            };
        }
        this.f8d.postDelayed(this.f9e, this.f11g * 1000);
    }

    @NonNull
    /* renamed from: e */
    public String m277e(String str, String str2) {
        try {
            if (!this.f10f) {
                CipherContainer m155c = CipherUtil.m155c(str);
                String m190a = m155c.m190a();
                String m189b = m155c.m189b();
                SceneData m272j = m272j(str2);
                String m170b = m272j.m170b();
                String m171a = m272j.m171a();
                if (EncryptEnum.AES.name().equals(m171a)) {
                    return AesUtil.m226a(m190a, m170b, m189b);
                }
                throw new InvalidAlgorithmException(m171a);
            }
            throw new SessionExpiredException();
        } catch (InvalidAlgorithmException | SceneNotFoundException | SessionExpiredException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | JSONException e) {
            throw new EncryptException(e);
        }
    }

    @NonNull
    /* renamed from: f */
    public String m276f(String str, String str2) {
        try {
            if (!this.f10f) {
                SceneConfig m273i = m273i(str2);
                SceneData m272j = m272j(str2);
                String m170b = m272j.m170b();
                String m224c = AesUtil.m224c();
                String m171a = m272j.m171a();
                if (EncryptEnum.AES.name().equals(m171a)) {
                    String m154d = CipherUtil.m154d(AesUtil.m225b(str, m170b, m224c), m224c);
                    this.f7c.put(str2, new CryptoConfig(SceneUtil.m146e(m272j.m170b(), m273i.m183c(), m273i.m181e()), m272j.m167e(), m273i.m182d()));
                    return m154d;
                }
                throw new InvalidAlgorithmException(m171a);
            }
            throw new SessionExpiredException();
        } catch (InvalidAlgorithmException | SceneNotFoundException | SessionExpiredException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException | JSONException e) {
            throw new EncryptException(e);
        }
    }

    @NonNull
    /* renamed from: g */
    public String m275g(@NonNull Collection<String> collection) {
        try {
            if (!this.f10f) {
                HashMap hashMap = new HashMap();
                for (String str : collection) {
                    CryptoConfig cryptoConfig = this.f7c.get(str);
                    if (cryptoConfig != null) {
                        hashMap.put(str, cryptoConfig);
                    } else {
                        throw new NullPointerException("scene(" + str + ") not found in cryptoConfigs.");
                    }
                }
                return CryptoConfigUtil.m153a(hashMap);
            }
            throw new SessionExpiredException();
        } catch (SessionExpiredException | NullPointerException | JSONException e) {
            throw new EncryptException(e);
        }
    }

    @NonNull
    /* renamed from: h */
    public String m274h(@NonNull String... strArr) {
        return m275g(Arrays.asList(strArr));
    }

    /* renamed from: m */
    public boolean m269m() {
        if (this.f10f) {
            return false;
        }
        this.f8d.removeCallbacks(this.f9e);
        m279c();
        return true;
    }
}
