package p017n;

import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.color.otaassistant.protocol.bean.MessageBean;
import java.nio.ByteBuffer;

/* renamed from: n.d */
/* loaded from: classes.dex */
public class MessageProtocol extends Protocol<MessageBean> {
    public MessageProtocol(ByteBuffer byteBuffer) {
        super(byteBuffer);
    }

    public MessageProtocol(MessageBean messageBean) {
        super(2, PathInterpolatorCompat.MAX_NUM_POINTS, messageBean);
    }
}
