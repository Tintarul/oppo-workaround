package p015l;

import android.text.TextUtils;
import p012i.CryptoProtocol;
import p015l.ChannelAuthentication;

/* renamed from: l.c */
/* loaded from: classes.dex */
public class MessageAuthentication extends ChannelAuthentication {

    /* renamed from: a */
    private String f199a;

    /* renamed from: b */
    private boolean f200b = false;

    public MessageAuthentication(String str) {
        this.f199a = str;
    }

    @Override // p015l.ChannelAuthentication
    /* renamed from: a */
    public boolean mo89a() {
        return !TextUtils.isEmpty(this.f199a) && this.f200b;
    }

    @Override // p015l.ChannelAuthentication
    /* renamed from: b */
    public boolean mo88b(ChannelAuthentication.C0499a c0499a) {
        if (c0499a == null || TextUtils.isEmpty(c0499a.f198c) || TextUtils.isEmpty(c0499a.f196a) || TextUtils.isEmpty(c0499a.f197b)) {
            return false;
        }
        boolean m120h = CryptoProtocol.m120h(c0499a.f196a, c0499a.f197b, c0499a.f198c.getBytes());
        this.f200b = m120h;
        return m120h;
    }

    @Override // p015l.ChannelAuthentication
    /* renamed from: c */
    public String mo87c(String str) {
        return TextUtils.isEmpty(this.f199a) ? str : CryptoProtocol.m123e(str, this.f199a);
    }

    @Override // p015l.ChannelAuthentication
    /* renamed from: d */
    public String mo86d(String str) {
        return TextUtils.isEmpty(this.f199a) ? str : CryptoProtocol.m122f(str, this.f199a);
    }
}
