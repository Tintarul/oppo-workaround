package p006c;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import com.allawn.cryptography.exception.SceneNotFoundException;
import java.util.List;
import java.util.Map;
import p000a.Session;
import p007d.CryptoSceneDataRepository;
import p009f.SceneConfig;
import p009f.SceneData;
import p010g.SceneUtil;

/* renamed from: c.a */
/* loaded from: classes.dex */
public class CryptoCore {

    /* renamed from: c */
    private final Map<String, SceneConfig> f126c;

    /* renamed from: e */
    private final int f128e;

    /* renamed from: a */
    private final Pools.Pool<Session> f124a = new Pools.SynchronizedPool(5);

    /* renamed from: b */
    private final Object f125b = new Object();

    /* renamed from: d */
    private final CryptoSceneDataRepository f127d = new CryptoSceneDataRepository();

    public CryptoCore(Context context, List<SceneConfig> list, int i) {
        this.f126c = SceneUtil.m149b(list);
        this.f128e = i;
    }

    /* renamed from: b */
    private SceneData m220b(String str) {
        SceneConfig sceneConfig = this.f126c.get(str);
        if (sceneConfig != null) {
            return SceneUtil.m148c(sceneConfig);
        }
        throw new SceneNotFoundException(str);
    }

    /* renamed from: a */
    public Session m221a() {
        Session acquire = this.f124a.acquire();
        if (acquire == null) {
            acquire = new Session(this, this.f128e);
        }
        acquire.m280b();
        return acquire;
    }

    @NonNull
    /* renamed from: c */
    public SceneConfig m219c(@NonNull String str) {
        SceneConfig sceneConfig = this.f126c.get(str);
        if (sceneConfig != null) {
            return sceneConfig;
        }
        throw new SceneNotFoundException(str);
    }

    @NonNull
    /* renamed from: d */
    public SceneData m218d(@NonNull String str) {
        SceneData m200c;
        synchronized (this.f125b) {
            m219c(str);
            m200c = this.f127d.m200c(str);
            if (m200c == null || m200c.m166f()) {
                m200c = m220b(str);
                this.f127d.m201b(m200c);
            }
        }
        return m200c;
    }

    /* renamed from: e */
    public boolean m217e(Session session) {
        if (session.m269m()) {
            return this.f124a.release(session);
        }
        return false;
    }
}
