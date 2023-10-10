package com.color.otaassistant;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

/* loaded from: classes.dex */
public class DataSharedProvider extends FileProvider {
    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public void attachInfo(@NonNull Context context, @NonNull ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return super.delete(uri, str, strArr);
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public String getType(@NonNull Uri uri) {
        return super.getType(uri);
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        return super.insert(uri, contentValues);
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public boolean onCreate() {
        return super.onCreate();
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String str) {
        return super.openFile(uri, str);
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        return super.query(uri, strArr, str, strArr2, str2);
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public int update(@NonNull Uri uri, ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        return super.update(uri, contentValues, str, strArr);
    }
}
