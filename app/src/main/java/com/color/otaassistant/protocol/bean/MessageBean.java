package com.color.otaassistant.protocol.bean;

import com.google.gson.annotations.Expose;
import p019p.GsonUtils;

/* loaded from: classes.dex */
public class MessageBean {
    @Expose(deserialize = true, serialize = true)
    private int cmdId;
    @Expose(deserialize = true, serialize = true)
    private long createTime;
    @Expose(deserialize = true, serialize = true)
    private String extraInfo;
    @Expose(deserialize = true, serialize = true)
    private Boolean needAck;
    @Expose(deserialize = true, serialize = true)
    private String uuid;

    public MessageBean() {
    }

    public int getCmdId() {
        return this.cmdId;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public String getExtraInfo() {
        return this.extraInfo;
    }

    public String getUuid() {
        return this.uuid;
    }

    public boolean needAck() {
        Boolean bool = this.needAck;
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public void setCmdId(int i) {
        this.cmdId = i;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public void setExtraInfo(String str) {
        this.extraInfo = str;
    }

    public void setNeedAck(boolean z) {
        this.needAck = Boolean.valueOf(z);
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public String toJson() {
        return GsonUtils.m13b(this);
    }

    public String toString() {
        return "[" + this.uuid + ", " + this.cmdId + ", " + this.createTime + ", " + this.needAck + "]";
    }

    public MessageBean(int i) {
        this(i, null);
    }

    public MessageBean(int i, String str) {
        this.cmdId = i;
        this.extraInfo = str;
        this.uuid = "0";
        this.createTime = System.currentTimeMillis();
    }
}
