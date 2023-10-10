package p015l;

import p016m.NioAbstractSelectableChannel;
import p016m.NioAbstractSocketChannel;
import p017n.MessageProtocol;

/* renamed from: l.b */
/* loaded from: classes.dex */
public interface ChannelCallback {
    /* renamed from: a */
    void mo92a(NioAbstractSocketChannel nioAbstractSocketChannel);

    /* renamed from: b */
    void mo91b(NioAbstractSelectableChannel nioAbstractSelectableChannel);

    /* renamed from: c */
    void mo90c(NioAbstractSocketChannel nioAbstractSocketChannel, MessageProtocol messageProtocol);
}
