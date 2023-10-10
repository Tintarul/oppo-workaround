package androidx.appcompat.resources;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class Compatibility {

    @RequiresApi(15)
    /* loaded from: classes.dex */
    public static class Api15Impl {
        private Api15Impl() {
        }

        @DoNotInline
        public static void getValueForDensity(@NonNull Resources resources, int i, int i2, @NonNull TypedValue typedValue, boolean z) {
            resources.getValueForDensity(i, i2, typedValue, z);
        }
    }

    @RequiresApi(18)
    /* loaded from: classes.dex */
    public static class Api18Impl {
        private Api18Impl() {
        }

        @DoNotInline
        public static void setAutoCancel(@NonNull ObjectAnimator objectAnimator, boolean z) {
            objectAnimator.setAutoCancel(z);
        }
    }

    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static class Api21Impl {
        private Api21Impl() {
        }

        @NonNull
        @DoNotInline
        public static Drawable createFromXmlInner(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) {
            return Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme);
        }

        @DoNotInline
        public static int getChangingConfigurations(@NonNull TypedArray typedArray) {
            return typedArray.getChangingConfigurations();
        }

        @DoNotInline
        public static void inflate(@NonNull Drawable drawable, @NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) {
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
        }
    }

    private Compatibility() {
    }
}
