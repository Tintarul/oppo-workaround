package p015l;

import p016m.NioAbstractSelectableChannel;
import p016m.NioAbstractServerSocketChannel;
import p016m.NioAbstractSocketChannel;

/* renamed from: l.d */
/* loaded from: classes.dex */
public class NioMessageServerSocketChannel extends NioAbstractServerSocketChannel {
    @Override // p016m.NioAbstractServerSocketChannel
    /* renamed from: o */
    protected int mo72o() {
        NioAbstractSocketChannel m85p = m85p();
        m85p.m81e(false).m84a(this.f204b).m78i(this.f205c, this.f206d).m77j(this.f203a);
        m80f(new NioAbstractSelectableChannel.RunnableC0502b(m85p));
        return 0;
    }

    /* renamed from: p */
    public NioAbstractSocketChannel m85p() {
        return new NioMessageSocketChannel(mo71b().accept());
    }
}
