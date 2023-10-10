package p011h;

import android.util.Log;
import java.lang.Thread;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.IllegalFormatFlagsException;
import java.util.Iterator;
import p016m.NioAbstractSelectableChannel;
import p019p.LogUtils;

/* renamed from: h.d */
/* loaded from: classes.dex */
public class SocketManager {

    /* renamed from: a */
    private Selector f177a;

    /* renamed from: b */
    private Thread f178b;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SocketManager.java */
    /* renamed from: h.d$b */
    /* loaded from: classes.dex */
    public class C0494b extends Thread {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0494b(String str) {
            super(str);
            SocketManager.this = r1;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            while (true) {
                try {
                    synchronized (SocketManager.m130b()) {
                    }
                    if (SocketManager.this.f177a.select() != 0) {
                        Iterator<SelectionKey> it = SocketManager.this.f177a.selectedKeys().iterator();
                        while (it.hasNext()) {
                            SelectionKey next = it.next();
                            NioAbstractSelectableChannel nioAbstractSelectableChannel = (NioAbstractSelectableChannel) next.attachment();
                            LogUtils.m6c("SocketManager", "handleEvent readyOps : " + next.readyOps());
                            if (nioAbstractSelectableChannel.mo70g(next) == -1) {
                                nioAbstractSelectableChannel.m82d();
                            }
                            it.remove();
                        }
                    }
                } catch (Exception e) {
                    LogUtils.m6c("SocketManager", "SocketThread run Exception: " + Log.getStackTraceString(e));
                    return;
                }
            }
        }
    }

    /* compiled from: SocketManager.java */
    /* renamed from: h.d$c */
    /* loaded from: classes.dex */
    public static class C0495c {

        /* renamed from: a */
        private static final SocketManager f180a = new SocketManager();
    }

    /* renamed from: b */
    public static SocketManager m130b() {
        return C0495c.f180a;
    }

    /* renamed from: c */
    public void m129c(NioAbstractSelectableChannel nioAbstractSelectableChannel, int i) {
        if (nioAbstractSelectableChannel.m83c(i)) {
            synchronized (this) {
                if (this.f177a == null) {
                    this.f177a = Selector.open();
                }
                Thread thread = this.f178b;
                if (thread == null || thread.getState() == Thread.State.TERMINATED) {
                    C0494b c0494b = new C0494b(C0494b.class.getName());
                    this.f178b = c0494b;
                    c0494b.start();
                }
                this.f177a.wakeup();
                nioAbstractSelectableChannel.m79h(this.f177a, i);
            }
            return;
        }
        throw new IllegalFormatFlagsException("opsflag parameter abnormal");
    }

    private SocketManager() {
    }
}
