package androidx.core.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.List;

/* loaded from: classes.dex */
public final class DocumentsContractCompat {
    private static final String PATH_TREE = "tree";

    /* loaded from: classes.dex */
    public static final class DocumentCompat {
        public static final int FLAG_VIRTUAL_DOCUMENT = 512;

        private DocumentCompat() {
        }
    }

    @RequiresApi(19)
    /* loaded from: classes.dex */
    private static class DocumentsContractApi19Impl {
        private DocumentsContractApi19Impl() {
        }

        @DoNotInline
        public static Uri buildDocumentUri(String str, String str2) {
            return DocumentsContract.buildDocumentUri(str, str2);
        }

        @DoNotInline
        static boolean deleteDocument(ContentResolver contentResolver, Uri uri) {
            return DocumentsContract.deleteDocument(contentResolver, uri);
        }

        @DoNotInline
        static String getDocumentId(Uri uri) {
            return DocumentsContract.getDocumentId(uri);
        }

        @DoNotInline
        static boolean isDocumentUri(Context context, @Nullable Uri uri) {
            return DocumentsContract.isDocumentUri(context, uri);
        }
    }

    @RequiresApi(21)
    /* loaded from: classes.dex */
    private static class DocumentsContractApi21Impl {
        private DocumentsContractApi21Impl() {
        }

        @DoNotInline
        static Uri buildChildDocumentsUri(String str, String str2) {
            return DocumentsContract.buildChildDocumentsUri(str, str2);
        }

        @DoNotInline
        static Uri buildChildDocumentsUriUsingTree(Uri uri, String str) {
            return DocumentsContract.buildChildDocumentsUriUsingTree(uri, str);
        }

        @DoNotInline
        static Uri buildDocumentUriUsingTree(Uri uri, String str) {
            return DocumentsContract.buildDocumentUriUsingTree(uri, str);
        }

        @DoNotInline
        public static Uri buildTreeDocumentUri(String str, String str2) {
            return DocumentsContract.buildTreeDocumentUri(str, str2);
        }

        @DoNotInline
        static Uri createDocument(ContentResolver contentResolver, Uri uri, String str, String str2) {
            return DocumentsContract.createDocument(contentResolver, uri, str, str2);
        }

        @DoNotInline
        static String getTreeDocumentId(Uri uri) {
            return DocumentsContract.getTreeDocumentId(uri);
        }

        @DoNotInline
        static Uri renameDocument(@NonNull ContentResolver contentResolver, @NonNull Uri uri, @NonNull String str) {
            return DocumentsContract.renameDocument(contentResolver, uri, str);
        }
    }

    @RequiresApi(24)
    /* loaded from: classes.dex */
    private static class DocumentsContractApi24Impl {
        private DocumentsContractApi24Impl() {
        }

        @DoNotInline
        static boolean isTreeUri(@NonNull Uri uri) {
            return DocumentsContract.isTreeUri(uri);
        }

        @DoNotInline
        static boolean removeDocument(ContentResolver contentResolver, Uri uri, Uri uri2) {
            return DocumentsContract.removeDocument(contentResolver, uri, uri2);
        }
    }

    private DocumentsContractCompat() {
    }

    @Nullable
    public static Uri buildChildDocumentsUri(@NonNull String str, @Nullable String str2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return DocumentsContractApi21Impl.buildChildDocumentsUri(str, str2);
        }
        return null;
    }

    @Nullable
    public static Uri buildChildDocumentsUriUsingTree(@NonNull Uri uri, @NonNull String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            return DocumentsContractApi21Impl.buildChildDocumentsUriUsingTree(uri, str);
        }
        return null;
    }

    @Nullable
    public static Uri buildDocumentUri(@NonNull String str, @NonNull String str2) {
        if (Build.VERSION.SDK_INT >= 19) {
            return DocumentsContractApi19Impl.buildDocumentUri(str, str2);
        }
        return null;
    }

    @Nullable
    public static Uri buildDocumentUriUsingTree(@NonNull Uri uri, @NonNull String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            return DocumentsContractApi21Impl.buildDocumentUriUsingTree(uri, str);
        }
        return null;
    }

    @Nullable
    public static Uri buildTreeDocumentUri(@NonNull String str, @NonNull String str2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return DocumentsContractApi21Impl.buildTreeDocumentUri(str, str2);
        }
        return null;
    }

    @Nullable
    public static Uri createDocument(@NonNull ContentResolver contentResolver, @NonNull Uri uri, @NonNull String str, @NonNull String str2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return DocumentsContractApi21Impl.createDocument(contentResolver, uri, str, str2);
        }
        return null;
    }

    @Nullable
    public static String getDocumentId(@NonNull Uri uri) {
        if (Build.VERSION.SDK_INT >= 19) {
            return DocumentsContractApi19Impl.getDocumentId(uri);
        }
        return null;
    }

    @Nullable
    public static String getTreeDocumentId(@NonNull Uri uri) {
        if (Build.VERSION.SDK_INT >= 21) {
            return DocumentsContractApi21Impl.getTreeDocumentId(uri);
        }
        return null;
    }

    public static boolean isDocumentUri(@NonNull Context context, @Nullable Uri uri) {
        if (Build.VERSION.SDK_INT >= 19) {
            return DocumentsContractApi19Impl.isDocumentUri(context, uri);
        }
        return false;
    }

    public static boolean isTreeUri(@NonNull Uri uri) {
        int i = Build.VERSION.SDK_INT;
        if (i < 21) {
            return false;
        }
        if (i < 24) {
            List<String> pathSegments = uri.getPathSegments();
            return pathSegments.size() >= 2 && PATH_TREE.equals(pathSegments.get(0));
        }
        return DocumentsContractApi24Impl.isTreeUri(uri);
    }

    public static boolean removeDocument(@NonNull ContentResolver contentResolver, @NonNull Uri uri, @NonNull Uri uri2) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            return DocumentsContractApi24Impl.removeDocument(contentResolver, uri, uri2);
        }
        if (i >= 19) {
            return DocumentsContractApi19Impl.deleteDocument(contentResolver, uri);
        }
        return false;
    }

    @Nullable
    public static Uri renameDocument(@NonNull ContentResolver contentResolver, @NonNull Uri uri, @NonNull String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            return DocumentsContractApi21Impl.renameDocument(contentResolver, uri, str);
        }
        return null;
    }
}
