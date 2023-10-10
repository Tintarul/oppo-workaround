package p011h;

import android.app.AppOpsManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import p019p.LogUtils;

/* renamed from: h.c */
/* loaded from: classes.dex */
public class PermissionUtil {

    /* renamed from: a */
    private static final Uri f174a = Uri.parse("content://com.oplus.safecenter.security.InterfaceProvider");

    /* renamed from: b */
    private static final Uri f175b = Uri.parse("content://com.coloros.safecenter.security.InterfaceProvider");

    /* renamed from: c */
    private static FileSystem f176c;

    /* renamed from: a */
    public static boolean m137a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            LogUtils.m8a("PermissionUtil", "pathStr is empty!");
            return false;
        } else if (!new File(str).exists()) {
            LogUtils.m8a("PermissionUtil", "file " + str + " not exists");
            return false;
        } else {
            try {
                Path path = Paths.get(str, new String[0]);
                if (f176c == null) {
                    f176c = FileSystems.getDefault();
                }
                UserPrincipalLookupService userPrincipalLookupService = f176c.getUserPrincipalLookupService();
                PosixFileAttributeView posixFileAttributeView = (PosixFileAttributeView) Files.getFileAttributeView(path, PosixFileAttributeView.class, new LinkOption[0]);
                if (!TextUtils.isEmpty(str3)) {
                    posixFileAttributeView.setGroup(userPrincipalLookupService.lookupPrincipalByGroupName(str3));
                }
                if (TextUtils.isEmpty(str2)) {
                    return true;
                }
                posixFileAttributeView.setOwner(userPrincipalLookupService.lookupPrincipalByName(str2));
                return true;
            } catch (Exception e) {
                if (e instanceof UserPrincipalNotFoundException) {
                    LogUtils.m8a("PermissionUtil", "group " + str3 + "or owner " + str2 + "not exists!");
                } else {
                    LogUtils.m8a("PermissionUtil", "change owner and group failed ,exception is " + e.getMessage());
                }
                return false;
            }
        }
    }

    /* renamed from: b */
    public static boolean m136b(String str, PosixFilePermission... posixFilePermissionArr) {
        if (TextUtils.isEmpty(str)) {
            LogUtils.m8a("PermissionUtil", "dir is empty!");
            return false;
        } else if (!new File(str).exists()) {
            LogUtils.m8a("PermissionUtil", "file " + str + " not exists");
            return false;
        } else {
            try {
                HashSet hashSet = new HashSet();
                Path path = Paths.get(str, new String[0]);
                for (PosixFilePermission posixFilePermission : posixFilePermissionArr) {
                    hashSet.add(posixFilePermission);
                }
                Files.setPosixFilePermissions(path, hashSet);
                LogUtils.m8a("PermissionUtil", "change permission success :" + str);
                return true;
            } catch (IOException e) {
                LogUtils.m8a("PermissionUtil", "change permission failed !");
                e.printStackTrace();
                return false;
            }
        }
    }

    /* renamed from: c */
    public static boolean m135c(Context context) {
        return context.checkSelfPermission("android.permission.MANAGE_PROFILE_AND_DEVICE_OWNERS") == 0;
    }

    /* renamed from: d */
    public static void m134d(Context context) {
        ((AppOpsManager) context.getSystemService("appops")).setMode(24, Process.myUid(), context.getPackageName(), 0);
    }

    /* renamed from: e */
    public static void m133e(Context context) {
        if (m135c(context)) {
            String m132f = m132f("setStartupState", context.getPackageName(), true, context);
            LogUtils.m8a("PermissionUtil", "setStartUp: " + m132f);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:84:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f2  */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String m132f(String str, String str2, boolean z, Context context) {
        Bundle bundle;
        Bundle bundle2;
        String str3 = "false";
        Uri uri = f174a;
        Bundle bundle3 = null;
        try {
            try {
                Bundle acquireUnstableProvider = context.getContentResolver().acquireUnstableProvider(uri);
                if (acquireUnstableProvider == null) {
                    try {
                        uri = f175b;
                        acquireUnstableProvider = context.getContentResolver().acquireUnstableProvider(uri);
                    } catch (Exception e) {
                        e = e;
                        bundle3 = bundle2;
                        LogUtils.m7b("PermissionUtil", "setStartUpList " + Log.getStackTraceString(e));
                        if (bundle3 != null) {
                        }
                        return str3;
                    } catch (Throwable th) {
                        th = th;
                        bundle3 = bundle;
                        if (bundle3 != null) {
                        }
                        throw th;
                    }
                }
                Bundle bundle4 = acquireUnstableProvider;
                if (bundle4 != null) {
                    try {
                        try {
                            Bundle bundle5 = new Bundle();
                            ArrayList<String> arrayList = new ArrayList<>();
                            arrayList.add(str2);
                            bundle5.putStringArrayList("packageList", arrayList);
                            bundle5.putBoolean(str2, z);
                            if (Build.VERSION.SDK_INT >= 31) {
                                bundle3 = bundle4.call(context.getAttributionSource(), uri.getAuthority(), str, (String) null, bundle5);
                            } else {
                                try {
                                    bundle3 = (Bundle) bundle4.getClass().getDeclaredMethod(NotificationCompat.CATEGORY_CALL, String.class, String.class, String.class, String.class, String.class, Bundle.class).invoke(bundle4, context.getPackageName(), null, uri.getAuthority(), str, null, bundle5);
                                } catch (Exception e2) {
                                    LogUtils.m7b("PermissionUtil", "OplusPackageManager call Exception: " + Log.getStackTraceString(e2));
                                }
                            }
                            if (bundle3 != null) {
                                str3 = bundle3.getString("returnValue");
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            bundle3 = bundle4;
                            if (bundle3 != null) {
                                context.getContentResolver().releaseUnstableProvider(bundle3);
                            }
                            throw th;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        bundle3 = bundle4;
                        LogUtils.m7b("PermissionUtil", "setStartUpList " + Log.getStackTraceString(e));
                        if (bundle3 != null) {
                            context.getContentResolver().releaseUnstableProvider(bundle3);
                        }
                        return str3;
                    }
                }
                if (bundle4 != null) {
                    context.getContentResolver().releaseUnstableProvider(bundle4);
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e4) {
            e = e4;
        }
        return str3;
    }
}
