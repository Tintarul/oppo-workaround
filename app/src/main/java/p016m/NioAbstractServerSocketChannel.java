package p016m;

import android.util.Log;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import p019p.LogUtils;

/* renamed from: m.b */
/* loaded from: classes.dex */
public abstract class NioAbstractServerSocketChannel extends NioAbstractSelectableChannel {

    /* renamed from: e */
    private ServerSocketChannel f215e = ServerSocketChannel.open();

    @Override // p016m.NioAbstractSelectableChannel
    /* renamed from: g */
    public int mo70g(SelectionKey selectionKey) {
        if (m83c(selectionKey.readyOps())) {
            if (selectionKey.isAcceptable()) {
                try {
                    mo72o();
                    return 0;
                } catch (IOException e) {
                    LogUtils.m6c("NioAbstractServerSocketChannel", "handleEvent IOException : " + Log.getStackTraceString(e));
                    return -1;
                } catch (NullPointerException e2) {
                    LogUtils.m6c("NioAbstractServerSocketChannel", "handleEvent NullPointerException : " + e2.getMessage());
                    return -1;
                }
            }
            return 0;
        }
        return -1;
    }

    @Override // p016m.NioAbstractSelectableChannel
    /* renamed from: k */
    public int mo69k() {
        return 16;
    }

    /* renamed from: l */
    public final NioAbstractServerSocketChannel m75l(SocketAddress socketAddress) {
        mo71b().bind(socketAddress);
        return this;
    }

    @Override // p016m.NioAbstractSelectableChannel
    /* renamed from: m */
    public ServerSocketChannel mo71b() {
        return this.f215e;
    }

    /* renamed from: n */
    public final int m73n() {
        return mo71b().socket().getLocalPort();
    }

    /* renamed from: o */
    protected abstract int mo72o();
}
