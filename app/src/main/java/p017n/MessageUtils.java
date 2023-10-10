package p017n;

import com.color.otaassistant.protocol.bean.MessageBean;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import p015l.ChannelAuthentication;
import p019p.LogUtils;

/* renamed from: n.e */
/* loaded from: classes.dex */
public class MessageUtils {

    /* compiled from: MessageUtils.java */
    /* renamed from: n.e$a */
    /* loaded from: classes.dex */
    public static class C0505a {

        /* renamed from: a */
        public static final List<Integer> f220a = Arrays.asList(40003, 40004, 40005);

        /* renamed from: b */
        public static final List<Integer> f221b = Arrays.asList(40000);
    }

    /* renamed from: a */
    public static MessageProtocol m46a(int i, String str) {
        return new MessageProtocol(new MessageBean(i, str));
    }

    /* renamed from: b */
    public static MessageProtocol m45b(int i, String str, ChannelAuthentication channelAuthentication) {
        if (channelAuthentication != null) {
            str = channelAuthentication.mo86d(str);
        }
        return new MessageProtocol(new MessageBean(i, str));
    }

    /* renamed from: c */
    public static String m44c(ByteBuffer byteBuffer, long j) {
        byte[] bArr = new byte[(int) j];
        byteBuffer.get(bArr);
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogUtils.m4e("MessageUtils", "getStringData UnsupportedEncodingException : " + e.getMessage());
            return null;
        }
    }
}
