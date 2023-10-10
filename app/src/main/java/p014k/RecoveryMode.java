package p014k;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.color.otaassistant.protocol.bean.InstallBean;
import com.color.otaassistant.protocol.bean.PackageBean;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p011h.Constants;
import p011h.PermissionUtil;
import p014k.InstallDialog;
import p019p.InstallUtil;
import p019p.LogUtils;

/* renamed from: k.d */
/* loaded from: classes.dex */
public class RecoveryMode extends InstallPackage {

    /* renamed from: c */
    private static final PosixFilePermission[] f193c = {PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE, PosixFilePermission.GROUP_READ, PosixFilePermission.GROUP_WRITE, PosixFilePermission.GROUP_EXECUTE};

    /* renamed from: d */
    private static InstallDialog f194d;

    /* compiled from: RecoveryMode.java */
    /* renamed from: k.d$a */
    /* loaded from: classes.dex */
    public class C0498a implements InstallDialog.InterfaceC0496a {
        C0498a() {
            RecoveryMode.this = r1;
        }

        @Override // p014k.InstallDialog.InterfaceC0496a
        /* renamed from: a */
        public void mo97a() {
            RecoveryMode recoveryMode = RecoveryMode.this;
            recoveryMode.m103f(recoveryMode.f191a);
        }

        @Override // p014k.InstallDialog.InterfaceC0496a
        public void onCancel() {
        }
    }

    public RecoveryMode(@NonNull Context context, @NonNull InstallBean installBean) {
        super(context, installBean);
    }

    /* renamed from: g */
    private void m102g(File file) {
        if (file.isDirectory()) {
            PermissionUtil.m137a(file.getAbsolutePath(), "", "cache");
            PermissionUtil.m136b(file.getAbsolutePath(), f193c);
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                LogUtils.m7b("RecoveryMode", "child files is null");
                return;
            }
            for (File file2 : listFiles) {
                m102g(file2);
            }
        }
    }

    /* renamed from: h */
    private void m101h(String str, String str2) {
        LogUtils.m6c("RecoveryMode", "moveFile source: " + str);
        File file = new File(str);
        Path path = file.toPath();
        String str3 = str2 + File.separator + path.getFileName();
        LogUtils.m6c("RecoveryMode", "move path: " + str3);
        Files.copy(path, new File(str3).toPath(), StandardCopyOption.COPY_ATTRIBUTES);
        InstallUtil.m12a(file);
    }

    /* renamed from: i */
    private void m100i() {
        String versionName = this.f192b.getVersionName();
        LogUtils.m6c("RecoveryMode", "showInstallDialog versionId: " + versionName);
        PermissionUtil.m134d(this.f191a);
        InstallDialog installDialog = f194d;
        if (installDialog != null && installDialog.isShowing()) {
            f194d.dismiss();
        }
        InstallDialog installDialog2 = new InstallDialog(this.f191a, versionName);
        f194d = installDialog2;
        installDialog2.getWindow().setType(2038);
        f194d.setCancelable(false);
        f194d.m107a(new C0498a());
        f194d.show();
    }

    @Override // p014k.InstallPackage
    /* renamed from: a */
    protected void mo96a() {
        LogUtils.m8a("RecoveryMode", "start recovery update");
        m100i();
    }

    @Override // p014k.InstallPackage
    /* renamed from: d */
    protected boolean mo95d() {
        return (m99j() || m98k()) ? false : true;
    }

    /* renamed from: e */
    public String m104e() {
        String m145a = Constants.m145a();
        File file = new File(m145a + File.separator + ".otaPackage");
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            m102g(new File(m145a));
            if (!mkdirs) {
                LogUtils.m7b("RecoveryMode", "download dir mkdirs failed!");
            }
        }
        return file.getAbsolutePath();
    }

    /* renamed from: f */
    public void m103f(Context context) {
        try {
            ArrayList arrayList = new ArrayList();
            File[] listFiles = new File(m104e()).listFiles();
            if (listFiles == null) {
                LogUtils.m7b("RecoveryMode", "files is null");
                return;
            }
            File file = null;
            for (File file2 : listFiles) {
                String name = file2.getName();
                if (name.contains("system_vendor")) {
                    arrayList.add(0, file2);
                } else if (name.contains("my_manifest") && file == null) {
                    file = file2;
                } else {
                    arrayList.add(file2);
                }
            }
            if (file != null) {
                arrayList.add(file);
            }
            if (arrayList.size() <= 0) {
                LogUtils.m7b("RecoveryMode", "size <= 0, return");
                return;
            }
            LogUtils.m8a("RecoveryMode", "installPackageList orderedList: " + arrayList);
            PermissionUtil.m133e(context);
            Class.forName("com.oplus.ota.OplusRecoverySystem").getMethod("oplusInstallPackageList", Context.class, ArrayList.class).invoke(null, context, arrayList);
        } catch (Exception e) {
            LogUtils.m8a("RecoveryMode", "installPackageList Exception: " + Log.getStackTraceString(e));
        }
    }

    /* renamed from: j */
    public boolean m99j() {
        boolean z;
        File[] listFiles = new File(m104e()).listFiles();
        List<PackageBean> components = this.f192b.getComponents();
        if (listFiles != null && components != null && listFiles.length == components.size()) {
            Iterator<PackageBean> it = components.iterator();
            do {
                z = true;
                if (!it.hasNext()) {
                    return true;
                }
                PackageBean next = it.next();
                String lastPathSegment = Uri.parse(next.getPackageFullPath()).getLastPathSegment();
                int length = listFiles.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z = false;
                        continue;
                        break;
                    }
                    File file = listFiles[i];
                    if (!TextUtils.equals(file.getName(), lastPathSegment) || !m105c(file, next)) {
                        i++;
                    }
                }
            } while (z);
            LogUtils.m8a("RecoveryMode", "verify result false");
            return false;
        }
        LogUtils.m8a("RecoveryMode", "file length is null");
        return false;
    }

    /* renamed from: k */
    protected boolean m98k() {
        InstallUtil.m11b();
        for (PackageBean packageBean : this.f192b.getComponents()) {
            if (!m105c(new File(packageBean.getPackageFullPath()), packageBean)) {
                return false;
            }
            m101h(packageBean.getPackageFullPath(), m104e());
        }
        return true;
    }
}
