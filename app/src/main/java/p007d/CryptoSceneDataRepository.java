package p007d;

import android.os.Handler;
import android.os.Looper;
import p008e.SceneDataMemoryDataSource;
import p009f.SceneData;

/* renamed from: d.b */
/* loaded from: classes.dex */
public class CryptoSceneDataRepository {

    /* renamed from: a */
    private final SceneDataMemoryDataSource f146a = new SceneDataMemoryDataSource();

    /* renamed from: b */
    private final Handler f147b = new Handler(Looper.getMainLooper());

    /* renamed from: b */
    public void m201b(final SceneData sceneData) {
        this.f146a.m194a(sceneData);
        this.f147b.postDelayed(new Runnable() { // from class: d.a
            @Override // java.lang.Runnable
            public final void run() {
                CryptoSceneDataRepository.this.m199d(sceneData);
            }
        }, sceneData.m169c() * 1000);
    }

    /* renamed from: c */
    public SceneData m200c(String str) {
        return this.f146a.m192c(str);
    }

    /* renamed from: e */
    public void m199d(SceneData sceneData) {
        this.f146a.m191d(sceneData);
    }
}
