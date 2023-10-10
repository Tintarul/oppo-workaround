package p016m;

import android.util.Log;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import p015l.ChannelAuthentication;
import p016m.NioAbstractSelectableChannel;
import p019p.LogUtils;

/* renamed from: m.c */
/* loaded from: classes.dex */
public abstract class NioAbstractSocketChannel extends NioAbstractSelectableChannel {

    /* renamed from: e */
    private SocketChannel f216e;

    /* renamed from: f */
    private ChannelAuthentication f217f = new ChannelAuthentication();

    public NioAbstractSocketChannel(SocketChannel socketChannel) {
        if (socketChannel != null) {
            this.f216e = socketChannel;
            return;
        }
        throw new IllegalArgumentException("socketChannel");
    }

    @Override // p016m.NioAbstractSelectableChannel
    /* renamed from: g */
    public int mo70g(SelectionKey selectionKey) {
        if (m83c(selectionKey.readyOps()) && selectionKey.isValid()) {
            try {
                if (selectionKey.isConnectable()) {
                    m80f(new NioAbstractSelectableChannel.RunnableC0502b(this));
                }
                if (selectionKey.isReadable()) {
                    return mo66n();
                }
                return 0;
            } catch (IOException e) {
                LogUtils.m6c("NioAbstractSocketChannel", "handleEvent IOException : " + Log.getStackTraceString(e));
            } catch (Exception e2) {
                LogUtils.m6c("NioAbstractSocketChannel", "handleEvent Exception : " + Log.getStackTraceString(e2));
                return -1;
            }
        }
        return -1;
    }

    @Override // p016m.NioAbstractSelectableChannel
    /* renamed from: k */
    public int mo69k() {
        return 13;
    }

    @Override // p016m.NioAbstractSelectableChannel
    /* renamed from: l */
    public SocketChannel mo71b() {
        return this.f216e;
    }

    /* renamed from: m */
    public final ChannelAuthentication m67m() {
        return this.f217f;
    }

    /* renamed from: n */
    protected abstract int mo66n();

    /* renamed from: o */
    public final void m65o(ChannelAuthentication channelAuthentication) {
        this.f217f = channelAuthentication;
    }

    /* renamed from: p */
    public final void m64p(ByteBuffer byteBuffer) {
        if (byteBuffer == null || !byteBuffer.hasRemaining()) {
            return;
        }
        synchronized (this) {
            while (byteBuffer.hasRemaining()) {
                mo71b().write(byteBuffer);
            }
        }
    }
}
