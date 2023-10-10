package p013j;

import com.color.otaassistant.filter.flterChain.FilterChain;
import com.color.otaassistant.filter.flterChain.FilterChainData;
import p015l.ChannelAuthentication;
import p017n.MessageBuffer;

/* renamed from: j.a */
/* loaded from: classes.dex */
public class FilterDataDecrypt extends FilterChainData {

    /* renamed from: d */
    private ChannelAuthentication f184d;

    public FilterDataDecrypt(ChannelAuthentication channelAuthentication) {
        super(1);
        this.f184d = null;
        this.f184d = channelAuthentication;
    }

    @Override // com.color.otaassistant.filter.flterChain.FilterChain
    /* renamed from: g */
    public FilterChain.FilterResult mo108g(MessageBuffer messageBuffer) {
        if (this.f184d != null) {
            messageBuffer.m51c().m40d().setExtraInfo(this.f184d.mo87c(messageBuffer.m51c().m40d().getExtraInfo()));
        }
        return FilterChain.FilterResult.FINISH;
    }
}
