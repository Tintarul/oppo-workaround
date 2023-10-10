package p000a;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import p006c.CryptoCore;
import p009f.SceneConfig;

/* renamed from: a.a */
/* loaded from: classes.dex */
public class Crypto {

    /* renamed from: b */
    private static final Object f0b = new Object();

    /* renamed from: c */
    private static final AtomicBoolean f1c = new AtomicBoolean(false);
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: d */
    private static Crypto f2d;

    /* renamed from: a */
    private CryptoCore f3a;

    private Crypto() {
    }

    /* renamed from: a */
    public static Session m286a() {
        return m284c().f3a.m221a();
    }

    /* renamed from: b */
    private void m285b(Context context, List<SceneConfig> list) {
        this.f3a = new CryptoCore(context, list, 60);
    }

    /* renamed from: c */
    private static Crypto m284c() {
        synchronized (f0b) {
            if (f2d == null) {
                f2d = new Crypto();
            }
        }
        return f2d;
    }

    /* renamed from: d */
    public static void m283d(@NonNull Context context, @NonNull List<SceneConfig> list) {
        if (f1c.getAndSet(true)) {
            return;
        }
        m284c().m285b(context, list);
    }

    /* renamed from: e */
    public static boolean m282e(Session session) {
        return m284c().f3a.m217e(session);
    }
}
