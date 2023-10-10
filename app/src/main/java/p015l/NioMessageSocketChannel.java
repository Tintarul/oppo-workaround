package p015l;

import com.color.otaassistant.filter.flterChain.FilterChain;
import java.nio.channels.SocketChannel;
import p016m.NioAbstractSelectableChannel;
import p016m.NioAbstractSocketChannel;
import p017n.MessageBuffer;
import p019p.LogUtils;

/* renamed from: l.e */
/* loaded from: classes.dex */
public class NioMessageSocketChannel extends NioAbstractSocketChannel {

    /* renamed from: g */
    protected MessageBuffer f201g;

    /* compiled from: NioMessageSocketChannel.java */
    /* renamed from: l.e$a */
    /* loaded from: classes.dex */
    static /* synthetic */ class C0500a {

        /* renamed from: a */
        static final /* synthetic */ int[] f202a;

        static {
            int[] iArr = new int[FilterChain.FilterResult.values().length];
            f202a = iArr;
            try {
                iArr[FilterChain.FilterResult.FINISH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f202a[FilterChain.FilterResult.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public NioMessageSocketChannel(SocketChannel socketChannel) {
        super(socketChannel);
    }

    @Override // p016m.NioAbstractSocketChannel
    /* renamed from: n */
    protected int mo66n() {
        if (this.f201g == null) {
            this.f201g = new MessageBuffer(this.f203a);
        }
        if (this.f201g.m49e(mo71b()) == -1) {
            LogUtils.m6c("NioMessageSocketChannel", "peer socket connection is broken");
            m82d();
            return 0;
        }
        FilterChain filterChain = this.f204b;
        if (filterChain != null) {
            int i = C0500a.f202a[filterChain.m208d(this.f201g, FilterChain.ChainType.CHAIN_PROTOCOL).ordinal()];
            if (i != 1) {
                return i != 2 ? 0 : -1;
            }
            m80f(new NioAbstractSelectableChannel.RunnableC0503c(this, new MessageBuffer(this.f201g.m50d())));
            return 0;
        }
        return 0;
    }
}
