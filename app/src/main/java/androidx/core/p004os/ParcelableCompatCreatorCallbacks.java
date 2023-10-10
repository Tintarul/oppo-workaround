package androidx.core.p004os;

import android.os.Parcel;

@Deprecated
/* renamed from: androidx.core.os.ParcelableCompatCreatorCallbacks */
/* loaded from: classes.dex */
public interface ParcelableCompatCreatorCallbacks<T> {
    T createFromParcel(Parcel parcel, ClassLoader classLoader);

    T[] newArray(int i);
}
