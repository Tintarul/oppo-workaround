package com.color.otaassistant.filter.flterChain;

import java.util.IllegalFormatFlagsException;
import p017n.MessageBuffer;

/* loaded from: classes.dex */
public abstract class FilterChain {

    /* renamed from: a */
    private int f138a;

    /* renamed from: b */
    private FilterChain f139b;

    /* renamed from: c */
    private FilterChain f140c;

    /* loaded from: classes.dex */
    public enum ChainType {
        CHAIN_HEADER,
        CHAIN_PROTOCOL,
        CHAIN_DATA
    }

    /* loaded from: classes.dex */
    public enum FilterResult {
        HEADER,
        FORWARD,
        STOP,
        FINISH,
        ERROR
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.color.otaassistant.filter.flterChain.FilterChain$b */
    /* loaded from: classes.dex */
    public class C0401b extends FilterChain {
        public C0401b(FilterChain filterChain) {
            super();
        }

        @Override // com.color.otaassistant.filter.flterChain.FilterChain
        /* renamed from: e */
        public ChainType mo203e() {
            return ChainType.CHAIN_HEADER;
        }

        @Override // com.color.otaassistant.filter.flterChain.FilterChain
        /* renamed from: g */
        public FilterResult mo108g(MessageBuffer messageBuffer) {
            return FilterResult.HEADER;
        }
    }

    /* renamed from: b */
    private final FilterChain m210b(FilterChain filterChain) {
        synchronized (this.f140c) {
            filterChain.f140c = this.f140c;
            int i = filterChain.f138a;
            int i2 = this.f138a;
            if (i < i2) {
                filterChain.f139b = this;
            } else if (i == i2 && filterChain.mo203e() == mo203e()) {
                filterChain.f139b = this.f139b;
            } else {
                FilterChain filterChain2 = this.f139b;
                if (filterChain2 != null) {
                    this.f139b = filterChain2.m210b(filterChain);
                } else {
                    this.f139b = filterChain;
                }
                return this;
            }
            return filterChain;
        }
    }

    /* renamed from: c */
    private final FilterResult m209c(MessageBuffer messageBuffer, ChainType chainType, FilterResult filterResult) {
        FilterChain filterChain;
        synchronized (this.f140c) {
            if (mo203e() == chainType || mo203e() == ChainType.CHAIN_HEADER) {
                filterResult = mo108g(messageBuffer);
            }
            if ((filterResult == FilterResult.FORWARD || filterResult == FilterResult.HEADER) && (filterChain = this.f139b) != null) {
                filterResult = filterChain.m209c(messageBuffer, chainType, filterResult);
            }
        }
        return filterResult;
    }

    /* renamed from: f */
    private final void m207f(FilterChain filterChain) {
        if (filterChain != null) {
            if (filterChain.f138a > 0) {
                if (filterChain.mo203e() != ChainType.CHAIN_HEADER) {
                    if (this.f138a < 0) {
                        throw new IllegalFormatFlagsException("need definition this mPriority by setPriority");
                    }
                    return;
                }
                throw new IllegalFormatFlagsException("chainType can't be the CHAIN_HEADER");
            }
            throw new IllegalFormatFlagsException("need definition filter mPriority by setPriority");
        }
        throw new IllegalArgumentException("FilterChain cannot be null");
    }

    /* renamed from: h */
    private String m206h() {
        return "\n" + super.toString() + " ( mPriority : " + this.f138a + ", chainType : " + mo203e() + ", header : " + this.f140c.m204j() + ")";
    }

    /* renamed from: i */
    private String m205i() {
        String sb;
        synchronized (this.f140c) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(m206h());
            FilterChain filterChain = this.f139b;
            sb2.append(filterChain != null ? filterChain.m205i() : "");
            sb = sb2.toString();
        }
        return sb;
    }

    /* renamed from: j */
    private String m204j() {
        return super.toString();
    }

    /* renamed from: a */
    public final FilterChain m211a(FilterChain filterChain) {
        m207f(filterChain);
        return this.f140c.m210b(filterChain);
    }

    /* renamed from: d */
    public final FilterResult m208d(MessageBuffer messageBuffer, ChainType chainType) {
        FilterChain filterChain = this.f140c;
        FilterResult filterResult = FilterResult.HEADER;
        FilterResult m209c = filterChain.m209c(messageBuffer, chainType, filterResult);
        return m209c == filterResult ? FilterResult.FINISH : m209c;
    }

    /* renamed from: e */
    public abstract ChainType mo203e();

    /* renamed from: g */
    public abstract FilterResult mo108g(MessageBuffer messageBuffer);

    public String toString() {
        return super.toString() + this.f140c.m205i();
    }

    private FilterChain() {
        if (this instanceof C0401b) {
            this.f138a = 0;
            this.f140c = this;
            return;
        }
        throw new IllegalArgumentException("No parameter initialization can only be called by the FilterChainHeader");
    }

    public FilterChain(int i) {
        if (i > 0) {
            C0401b c0401b = new C0401b(this);
            this.f140c = c0401b;
            c0401b.f139b = this;
            this.f138a = i;
            return;
        }
        throw new IllegalArgumentException("mPriority must be greater than 0");
    }
}
