package androidx.versionedparcelable;

import androidx.annotation.RestrictTo;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public @interface VersionedParcelize {
    boolean allowSerialization();

    int[] deprecatedIds();

    Class factory();

    boolean ignoreParcelables();

    boolean isCustom();

    String jetifyAs();
}
