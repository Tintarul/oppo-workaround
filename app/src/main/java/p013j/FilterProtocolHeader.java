package p013j;

import com.color.otaassistant.filter.flterChain.FilterChain;
import com.color.otaassistant.filter.flterChain.FilterChainProtocol;
import java.nio.ByteBuffer;
import p017n.MessageBuffer;
import p017n.MessageProtocol;

/* renamed from: j.c */
/* loaded from: classes.dex */
public class FilterProtocolHeader extends FilterChainProtocol {
    public FilterProtocolHeader() {
        super(1);
    }

    @Override // com.color.otaassistant.filter.flterChain.FilterChain
    /* renamed from: g */
    public FilterChain.FilterResult mo108g(MessageBuffer messageBuffer) {
        if (messageBuffer == null) {
            return FilterChain.FilterResult.ERROR;
        }
        if (messageBuffer.m51c() != null) {
            return FilterChain.FilterResult.FORWARD;
        }
        ByteBuffer m53a = messageBuffer.m53a();
        if (m53a.capacity() < 24) {
            messageBuffer.m52b(24);
            return FilterChain.FilterResult.STOP;
        } else if (m53a.position() < 24) {
            return FilterChain.FilterResult.STOP;
        } else {
            m53a.flip();
            byte[] bArr = new byte[24];
            m53a.get(bArr);
            m53a.compact();
            messageBuffer.m47g(new MessageProtocol(ByteBuffer.wrap(bArr)));
            return FilterChain.FilterResult.FORWARD;
        }
    }
}
