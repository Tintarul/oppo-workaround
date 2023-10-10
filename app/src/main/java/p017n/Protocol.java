package p017n;

import android.text.TextUtils;
import java.nio.ByteBuffer;
import p019p.GsonUtils;

/* renamed from: n.f */
/* loaded from: classes.dex */
public class Protocol<T> {

    /* renamed from: a */
    private int f222a;

    /* renamed from: b */
    private int f223b;

    /* renamed from: c */
    private long f224c;

    /* renamed from: d */
    private T f225d;

    public Protocol(int i, int i2, T t) {
        this.f222a = i;
        this.f223b = i2;
        this.f225d = t;
    }

    /* renamed from: a */
    private void m43a(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() >= 24) {
            String m44c = MessageUtils.m44c(byteBuffer, 8L);
            if (TextUtils.equals(m44c, "GhostPID")) {
                this.f222a = byteBuffer.getInt();
                this.f223b = byteBuffer.getInt();
                this.f224c = byteBuffer.getLong();
                return;
            }
            throw new IllegalArgumentException("The protocol header should be GhostPID, may have lost data magicString: " + m44c);
        }
        throw new IllegalArgumentException("byte size is " + byteBuffer.remaining() + ", less than protocol header length 24");
    }

    /* renamed from: g */
    public static int m37g(String str) {
        return str.indexOf("GhostPID");
    }

    /* renamed from: b */
    public ByteBuffer m42b() {
        if (this.f225d == null) {
            return null;
        }
        byte[] bytes = m39e().getBytes();
        ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 24);
        allocate.put("GhostPID".getBytes());
        allocate.putInt(this.f222a);
        allocate.putInt(this.f223b);
        allocate.putLong(bytes.length);
        allocate.put(bytes);
        allocate.flip();
        return allocate;
    }

    /* renamed from: c */
    public long m41c() {
        if (this.f224c <= 0 && this.f225d != null) {
            this.f224c = m39e().length();
        }
        return this.f224c;
    }

    /* renamed from: d */
    public T m40d() {
        return this.f225d;
    }

    /* renamed from: e */
    public String m39e() {
        T t = this.f225d;
        if (t != null) {
            return GsonUtils.m13b(t);
        }
        return null;
    }

    /* renamed from: f */
    public void m38f(T t) {
        this.f225d = t;
        this.f224c = 0L;
    }

    public String toString() {
        return "version = " + this.f222a + ", type = " + this.f223b + "; buffer = " + this.f225d;
    }

    public Protocol(ByteBuffer byteBuffer) {
        m43a(byteBuffer);
    }
}
