package androidx.core.p004os;

import android.os.Parcel;
import androidx.annotation.NonNull;

/* renamed from: androidx.core.os.ParcelCompat */
/* loaded from: classes.dex */
public final class ParcelCompat {
    private ParcelCompat() {
    }

    public static boolean readBoolean(@NonNull Parcel parcel) {
        return parcel.readInt() != 0;
    }

    public static void writeBoolean(@NonNull Parcel parcel, boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }
}
