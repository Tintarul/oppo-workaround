package p013j;

import com.color.otaassistant.filter.flterChain.FilterChain;
import com.color.otaassistant.filter.flterChain.FilterChainProtocol;
import com.color.otaassistant.protocol.bean.MessageBean;
import java.nio.ByteBuffer;
import p017n.MessageBuffer;
import p017n.MessageProtocol;
import p017n.MessageUtils;
import p017n.Protocol;
import p019p.GsonUtils;
import p019p.LogUtils;

/* renamed from: j.b */
/* loaded from: classes.dex */
public class FilterProtocolContent extends FilterChainProtocol {
    public FilterProtocolContent() {
        super(2);
    }

    @Override // com.color.otaassistant.filter.flterChain.FilterChain
    /* renamed from: g */
    public FilterChain.FilterResult mo108g(MessageBuffer messageBuffer) {
        if (messageBuffer == null) {
            return FilterChain.FilterResult.ERROR;
        }
        MessageProtocol m51c = messageBuffer.m51c();
        if (m51c == null) {
            return FilterChain.FilterResult.ERROR;
        }
        ByteBuffer m53a = messageBuffer.m53a();
        long m41c = m51c.m41c();
        if (m53a.capacity() < m41c) {
            messageBuffer.m52b((int) m41c);
            return FilterChain.FilterResult.STOP;
        } else if (m53a.position() < m41c) {
            return FilterChain.FilterResult.STOP;
        } else {
            m53a.flip();
            String m44c = MessageUtils.m44c(m53a, m41c);
            m53a.compact();
            int m37g = Protocol.m37g(m44c);
            if (m37g != -1) {
                LogUtils.m4e("FilterProtocolContent", "packet loss, delete this message content, keep next message header");
                String substring = m44c.substring(m37g);
                ByteBuffer allocate = ByteBuffer.allocate(substring.length() + m53a.position());
                allocate.put(substring.getBytes());
                m53a.flip();
                allocate.put(m53a);
                messageBuffer.m48f(allocate);
                messageBuffer.m50d();
                return FilterChain.FilterResult.STOP;
            }
            m51c.m38f((MessageBean) GsonUtils.m14a(m44c, MessageBean.class));
            return FilterChain.FilterResult.FINISH;
        }
    }
}
