package androidx.resourceinspection.annotation;

import androidx.annotation.NonNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes.dex */
public @interface Attribute {

    @Target({})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface IntMap {
        int mask();

        @NonNull
        String name();

        int value();
    }

    @NonNull
    IntMap[] intMapping();

    @NonNull
    String value();
}
