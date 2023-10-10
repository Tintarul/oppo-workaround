package p007d;

import p008e.SceneDataMemoryDataSource;
import p009f.SceneData;

/* renamed from: d.c */
/* loaded from: classes.dex */
public class SessionSceneDataRepository {

    /* renamed from: a */
    private final SceneDataMemoryDataSource f148a = new SceneDataMemoryDataSource();

    /* renamed from: a */
    public void m197a(SceneData sceneData) {
        this.f148a.m194a(sceneData);
    }

    /* renamed from: b */
    public void m196b() {
        this.f148a.m193b();
    }

    /* renamed from: c */
    public SceneData m195c(String str) {
        return this.f148a.m192c(str);
    }
}
