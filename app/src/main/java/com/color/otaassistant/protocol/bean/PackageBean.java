package com.color.otaassistant.protocol.bean;

/* loaded from: classes.dex */
public class PackageBean {
    private String componentId;
    private String componentName;
    private String componentVersion;
    private String packageFullPath;
    private String packetMd5;
    private long packetSize;

    public String getComponentId() {
        return this.componentId;
    }

    public String getComponentName() {
        return this.componentName;
    }

    public String getComponentVersion() {
        return this.componentVersion;
    }

    public String getPackageFullPath() {
        return this.packageFullPath;
    }

    public String getPacketMd5() {
        return this.packetMd5;
    }

    public long getPacketSize() {
        return this.packetSize;
    }

    public String toString() {
        return "PackageBean{componentId='" + this.componentId + "', componentName='" + this.componentName + "', componentVersion='" + this.componentVersion + "', packetMd5='" + this.packetMd5 + "', packetSize=" + this.packetSize + ", packageFullPath='" + this.packageFullPath + "'}";
    }
}
