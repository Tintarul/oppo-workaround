package com.color.otaassistant.filter.flterChain;

import com.color.otaassistant.filter.flterChain.FilterChain;

/* renamed from: com.color.otaassistant.filter.flterChain.b */
/* loaded from: classes.dex */
public abstract class FilterChainProtocol extends FilterChain {
    public FilterChainProtocol(int i) {
        super(i);
    }

    @Override // com.color.otaassistant.filter.flterChain.FilterChain
    /* renamed from: e */
    public FilterChain.ChainType mo203e() {
        return FilterChain.ChainType.CHAIN_PROTOCOL;
    }
}
