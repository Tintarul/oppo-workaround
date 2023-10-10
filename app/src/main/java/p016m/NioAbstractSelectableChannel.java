package p016m;

import android.os.Handler;
import com.color.otaassistant.filter.flterChain.FilterChain;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import p015l.ChannelCallback;
import p017n.MessageBuffer;

/* renamed from: m.a */
/* loaded from: classes.dex */
public abstract class NioAbstractSelectableChannel {

    /* renamed from: a */
    protected int f203a = 4;

    /* renamed from: b */
    protected FilterChain f204b;

    /* renamed from: c */
    protected ChannelCallback f205c;

    /* renamed from: d */
    protected Handler f206d;

    /* JADX INFO: Access modifiers changed from: protected */
    /* compiled from: NioAbstractSelectableChannel.java */
    /* renamed from: m.a$a */
    /* loaded from: classes.dex */
    public class RunnableC0501a implements Runnable {

        /* renamed from: a */
        private NioAbstractSelectableChannel f207a;

        public RunnableC0501a(NioAbstractSelectableChannel nioAbstractSelectableChannel) {
            NioAbstractSelectableChannel.this = r1;
            this.f207a = nioAbstractSelectableChannel;
        }

        @Override // java.lang.Runnable
        public void run() {
            NioAbstractSelectableChannel.this.f205c.mo91b(this.f207a);
        }
    }

    /* compiled from: NioAbstractSelectableChannel.java */
    /* renamed from: m.a$b */
    /* loaded from: classes.dex */
    protected class RunnableC0502b implements Runnable {

        /* renamed from: a */
        private NioAbstractSocketChannel f209a;

        public RunnableC0502b(NioAbstractSocketChannel nioAbstractSocketChannel) {
            NioAbstractSelectableChannel.this = r1;
            this.f209a = nioAbstractSocketChannel;
        }

        @Override // java.lang.Runnable
        public void run() {
            NioAbstractSelectableChannel.this.f205c.mo92a(this.f209a);
        }
    }

    /* compiled from: NioAbstractSelectableChannel.java */
    /* renamed from: m.a$c */
    /* loaded from: classes.dex */
    protected class RunnableC0503c implements Runnable {

        /* renamed from: a */
        private NioAbstractSocketChannel f211a;

        /* renamed from: b */
        private MessageBuffer f212b;

        public RunnableC0503c(NioAbstractSocketChannel nioAbstractSocketChannel, MessageBuffer messageBuffer) {
            NioAbstractSelectableChannel.this = r1;
            this.f211a = nioAbstractSocketChannel;
            this.f212b = messageBuffer;
        }

        @Override // java.lang.Runnable
        public void run() {
            ChannelCallback channelCallback;
            if (NioAbstractSelectableChannel.this.f204b.m208d(this.f212b, FilterChain.ChainType.CHAIN_DATA) == FilterChain.FilterResult.ERROR || (channelCallback = NioAbstractSelectableChannel.this.f205c) == null) {
                return;
            }
            channelCallback.mo90c(this.f211a, this.f212b.m51c());
        }
    }

    /* compiled from: NioAbstractSelectableChannel.java */
    /* renamed from: m.a$d */
    /* loaded from: classes.dex */
    public static class C0504d {

        /* renamed from: a */
        private static final ExecutorService f214a = Executors.newSingleThreadExecutor();
    }

    /* renamed from: a */
    public final NioAbstractSelectableChannel m84a(FilterChain filterChain) {
        FilterChain filterChain2 = this.f204b;
        if (filterChain2 != null) {
            filterChain2.m211a(filterChain);
        } else {
            this.f204b = filterChain;
        }
        return this;
    }

    /* renamed from: b */
    public abstract AbstractSelectableChannel mo71b();

    /* renamed from: c */
    public boolean m83c(int i) {
        int mo69k = mo69k();
        return ((~mo69k) & i) == 0 && (mo69k & i) != 0;
    }

    /* renamed from: d */
    public void m82d() {
        m80f(new RunnableC0501a(this));
        mo71b().close();
    }

    /* renamed from: e */
    public final NioAbstractSelectableChannel m81e(boolean z) {
        mo71b().configureBlocking(z);
        return this;
    }

    /* renamed from: f */
    public void m80f(Runnable runnable) {
        Handler handler = this.f206d;
        if (handler == null) {
            C0504d.f214a.execute(runnable);
        } else {
            handler.post(runnable);
        }
    }

    /* renamed from: g */
    public abstract int mo70g(SelectionKey selectionKey);

    /* renamed from: h */
    public final SelectionKey m79h(Selector selector, int i) {
        return mo71b().register(selector, i, this);
    }

    /* renamed from: i */
    public final NioAbstractSelectableChannel m78i(ChannelCallback channelCallback, Handler handler) {
        if (channelCallback != null) {
            this.f205c = channelCallback;
            this.f206d = handler;
            return this;
        }
        throw new IllegalArgumentException("channelCallback cannot be null");
    }

    /* renamed from: j */
    public final NioAbstractSelectableChannel m77j(int i) {
        this.f203a = i;
        return this;
    }

    /* renamed from: k */
    public abstract int mo69k();
}
