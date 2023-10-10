package p014k;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.color.otaassistant.protocol.bean.InstallBean;
import com.color.otaassistant.protocol.bean.PackageBean;
import java.io.File;
import java.util.Comparator;
import java.util.function.Function;
import p012i.CryptolUtil;
import p019p.InstallUtil;
import p019p.LogUtils;

/* renamed from: k.c */
/* loaded from: classes.dex */
public abstract class InstallPackage {

    /* renamed from: a */
    protected final Context f191a;

    /* renamed from: b */
    protected final InstallBean f192b;

    public InstallPackage(Context context, InstallBean installBean) {
        this.f191a = context;
        this.f192b = installBean;
    }

    /* renamed from: a */
    protected abstract void mo96a();

    /* renamed from: b */
    public int m106b(Context context, InstallBean installBean) {
        if (InstallUtil.m9d(context)) {
            LogUtils.m6c("InstallPackage", "ota low power, skip install");
            return 1;
        }
        try {
            installBean.getComponents().sort(Comparator.comparing(new Function() { // from class: k.b
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Long.valueOf(((PackageBean) obj).getPacketSize());
                }
            }));
            LogUtils.m6c("InstallPackage", "reorder InstallBean : " + installBean);
            if (mo95d()) {
                LogUtils.m8a("InstallPackage", "verify package failed");
                return 2;
            }
            mo96a();
            return 0;
        } catch (Exception e) {
            InstallUtil.m11b();
            LogUtils.m7b("InstallPackage", "isAllowInstall Exception: " + Log.getStackTraceString(e));
            return 4;
        }
    }

    /* renamed from: c */
    public boolean m105c(File file, PackageBean packageBean) {
        return file.exists() && file.isFile() && file.length() == packageBean.getPacketSize() && TextUtils.equals(packageBean.getPacketMd5(), CryptolUtil.m109g(file));
    }

    /* renamed from: d */
    protected abstract boolean mo95d();
}
