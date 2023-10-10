package p008e;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p009f.SceneData;

/* renamed from: e.a */
/* loaded from: classes.dex */
public class SceneDataMemoryDataSource {

    /* renamed from: a */
    private final Object f149a = new Object();

    /* renamed from: b */
    private final Map<String, SceneData> f150b = new ConcurrentHashMap();

    /* renamed from: a */
    public void m194a(SceneData sceneData) {
        synchronized (this.f149a) {
            this.f150b.put(sceneData.m168d(), sceneData);
        }
    }

    /* renamed from: b */
    public void m193b() {
        synchronized (this.f149a) {
            this.f150b.clear();
        }
    }

    /* renamed from: c */
    public SceneData m192c(String str) {
        SceneData sceneData;
        synchronized (this.f149a) {
            sceneData = this.f150b.get(str);
        }
        return sceneData;
    }

    /* renamed from: d */
    public void m191d(SceneData sceneData) {
        synchronized (this.f149a) {
            if (this.f150b.get(sceneData.m168d()) == sceneData) {
                this.f150b.remove(sceneData.m168d());
            }
        }
    }
}
