package androidx.startup;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class StartupLogger {
    static final boolean DEBUG = false;
    private static final String TAG = "StartupLogger";

    private StartupLogger() {
    }

    /* renamed from: e */
    public static void m228e(@NonNull String str, @Nullable Throwable th) {
        Log.e(TAG, str, th);
    }

    /* renamed from: i */
    public static void m227i(@NonNull String str) {
        Log.i(TAG, str);
    }
}
