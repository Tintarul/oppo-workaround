package p014k;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import com.color.otaassistant.protocol.bean.InstallBean;
import com.color.otaassistant.protocol.bean.PackageBean;
import java.io.File;
import java.util.List;
import p011h.PermissionUtil;
import p019p.LogUtils;
import p019p.VersionUtil;

/* renamed from: k.e */
/* loaded from: classes.dex */
public class VABMode extends InstallPackage {
    public VABMode(@NonNull Context context, @NonNull InstallBean installBean) {
        super(context, installBean);
    }

    @Override // p014k.InstallPackage
    /* renamed from: a */
    protected void mo96a() {
        m94e();
    }

    @Override // p014k.InstallPackage
    /* renamed from: d */
    protected boolean mo95d() {
        return !m93f();
    }

    /* renamed from: e */
    public void m94e() {
        List<PackageBean> components = this.f192b.getComponents();
        if (components != null && !components.isEmpty()) {
            String packageFullPath = components.get(0).getPackageFullPath();
            if (packageFullPath == null) {
                LogUtils.m7b("VABMode", "package full path is null");
                return;
            }
            PermissionUtil.m134d(this.f191a);
            Uri uriForFile = FileProvider.getUriForFile(this.f191a, "com.color.otaassistant.DataSharedProvider", new File(packageFullPath));
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.putExtra("local_file_uri", uriForFile.toString());
            if (VersionUtil.m2b(this.f191a, "com.oplus.ota")) {
                intent.setClassName("com.oplus.ota", "com.oplus.otaui.activity.EntryActivity");
                this.f191a.grantUriPermission("com.oplus.ota", uriForFile, 65);
                LogUtils.m8a("VABMode", "prepare to startActivity for VAB, up to 11.3");
            } else if (VersionUtil.m2b(this.f191a, "com.oppo.ota")) {
                intent.setClassName("com.oppo.ota", "com.oppo.otaui.activity.EntryActivity");
                this.f191a.grantUriPermission("com.oppo.ota", uriForFile, 65);
                LogUtils.m8a("VABMode", "prepare to startActivity for VAB, less than 11.3");
            } else {
                LogUtils.m7b("VABMode", "not supported, not has package");
                return;
            }
            this.f191a.startActivity(intent);
            return;
        }
        LogUtils.m7b("VABMode", "components is null or empty, not startActivity to VAB update");
    }

    /* renamed from: f */
    protected boolean m93f() {
        LogUtils.m8a("VABMode", "start verify ota package.");
        for (PackageBean packageBean : this.f192b.getComponents()) {
            if (!m105c(new File(packageBean.getPackageFullPath()), packageBean)) {
                return false;
            }
        }
        LogUtils.m8a("VABMode", "end verify ota package.");
        return true;
    }
}
