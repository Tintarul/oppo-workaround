package androidx.core.p004os;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Locale;

/* renamed from: androidx.core.os.LocaleListInterface */
/* loaded from: classes.dex */
interface LocaleListInterface {
    Locale get(int i);

    @Nullable
    Locale getFirstMatch(@NonNull String[] strArr);

    Object getLocaleList();

    @IntRange(from = -1)
    int indexOf(Locale locale);

    boolean isEmpty();

    @IntRange(from = 0)
    int size();

    String toLanguageTags();
}
