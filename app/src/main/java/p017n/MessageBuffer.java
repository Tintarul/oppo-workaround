package p017n;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/* renamed from: n.c */
/* loaded from: classes.dex */
public class MessageBuffer {

    /* renamed from: a */
    private ByteBuffer f218a;

    /* renamed from: b */
    private MessageProtocol f219b;

    public MessageBuffer(int i) {
        this.f218a = ByteBuffer.allocate(i);
    }

    /* renamed from: a */
    public ByteBuffer m53a() {
        return this.f218a;
    }

    /* renamed from: b */
    public void m52b(int i) {
        if (this.f218a.capacity() < i) {
            ByteBuffer allocate = ByteBuffer.allocate(i);
            this.f218a.flip();
            this.f218a = allocate.put(this.f218a);
            return;
        }
        throw new IllegalArgumentException("expansion size cannot be smaller than the existing capacity");
    }

    /* renamed from: c */
    public MessageProtocol m51c() {
        return this.f219b;
    }

    /* renamed from: d */
    public MessageProtocol m50d() {
        MessageProtocol messageProtocol = this.f219b;
        this.f219b = null;
        return messageProtocol;
    }

    /* renamed from: e */
    public int m49e(SocketChannel socketChannel) {
        int read;
        do {
            read = socketChannel.read(this.f218a);
        } while (read > 0);
        return read;
    }

    /* renamed from: f */
    public void m48f(ByteBuffer byteBuffer) {
        this.f218a = byteBuffer;
    }

    /* renamed from: g */
    public void m47g(MessageProtocol messageProtocol) {
        this.f219b = messageProtocol;
    }

    public MessageBuffer(MessageProtocol messageProtocol) {
        this.f219b = messageProtocol;
        m47g(messageProtocol);
    }
}
