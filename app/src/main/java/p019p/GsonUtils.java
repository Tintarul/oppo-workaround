package p019p;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/* renamed from: p.a */
/* loaded from: classes.dex */
public class GsonUtils {

    /* renamed from: a */
    public static final Gson f226a = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    /* renamed from: a */
    public static <T> T m14a(String str, Class<T> cls) {
        try {
            return (T) new Gson().fromJson(str, (Class<Object>) cls);
        } catch (Exception e) {
            LogUtils.m7b("GsonUtils", "JsonDeserializer: " + e.getMessage());
            return null;
        }
    }

    /* renamed from: b */
    public static String m13b(Object obj) {
        return f226a.toJson(obj);
    }
}
