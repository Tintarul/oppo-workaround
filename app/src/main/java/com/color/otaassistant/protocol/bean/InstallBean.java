package com.color.otaassistant.protocol.bean;

import java.util.List;

/* loaded from: classes.dex */
public class InstallBean {
    private List<PackageBean> components;
    private long needDataSpace;
    private String versionName;

    public List<PackageBean> getComponents() {
        return this.components;
    }

    public long getNeedDataSpace() {
        return this.needDataSpace;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public String toString() {
        return "InstallBean{versionName='" + this.versionName + "', needDataSpace=" + this.needDataSpace + ", components=" + this.components + '}';
    }
}
