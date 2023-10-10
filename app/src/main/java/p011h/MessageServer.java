package p011h;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.color.otaassistant.OtaAssistantApplication;
import com.color.otaassistant.protocol.bean.InstallBean;
import com.color.otaassistant.protocol.bean.MessageBean;
import java.io.IOException;
import java.net.InetSocketAddress;
import p012i.CryptoProtocol;
import p013j.FilterDataDecrypt;
import p013j.FilterProtocolContent;
import p013j.FilterProtocolHeader;
import p015l.ChannelCallback;
import p015l.MessageAuthentication;
import p015l.NioMessageServerSocketChannel;
import p016m.NioAbstractSelectableChannel;
import p016m.NioAbstractServerSocketChannel;
import p016m.NioAbstractSocketChannel;
import p017n.InfoBuilder;
import p017n.InfoParser;
import p017n.MessageProtocol;
import p017n.MessageUtils;
import p019p.LogUtils;

/* renamed from: h.b */
/* loaded from: classes.dex */
public class MessageServer {

    /* renamed from: a */
    private NioAbstractServerSocketChannel f170a;

    /* renamed from: b */
    private Handler f171b;

    /* renamed from: c */
    private Context f172c = OtaAssistantApplication.m216a();

    /* compiled from: MessageServer.java */
    /* renamed from: h.b$b */
    /* loaded from: classes.dex */
    public class C0492b implements ChannelCallback {
        private C0492b() {
            MessageServer.this = r1;
        }

        @Override // p015l.ChannelCallback
        /* renamed from: a */
        public void mo92a(NioAbstractSocketChannel nioAbstractSocketChannel) {
            LogUtils.m6c("MessageServer", "MessageChannelCallback channelConnect : " + nioAbstractSocketChannel);
            try {
                SocketManager.m130b().m129c(nioAbstractSocketChannel, 1);
                MessageProtocol m46a = MessageUtils.m46a(30000, InfoBuilder.m60d(MessageServer.this.f172c));
                LogUtils.m6c("MessageServer", "MessageChannelCallback write messageProtocol: " + m46a);
                nioAbstractSocketChannel.m64p(m46a.m42b());
            } catch (Exception e) {
                LogUtils.m6c("MessageServer", "MessageChannelCallback channelConnect Exception: " + Log.getStackTraceString(e));
            }
        }

        @Override // p015l.ChannelCallback
        /* renamed from: b */
        public void mo91b(NioAbstractSelectableChannel nioAbstractSelectableChannel) {
            LogUtils.m6c("MessageServer", "MessageChannelCallback channelClosed : " + nioAbstractSelectableChannel);
        }

        @Override // p015l.ChannelCallback
        /* renamed from: c */
        public void mo90c(NioAbstractSocketChannel nioAbstractSocketChannel, MessageProtocol messageProtocol) {
            LogUtils.m6c("MessageServer", "MessageChannelCallback channelRead socketChannel: " + nioAbstractSocketChannel);
            try {
                MessageProtocol m141d = MessageServer.this.m141d(messageProtocol, nioAbstractSocketChannel);
                LogUtils.m6c("MessageServer", "MessageChannelCallback write messageProtocol: " + m141d);
                if (m141d != null) {
                    nioAbstractSocketChannel.m64p(m141d.m42b());
                }
            } catch (Exception e) {
                LogUtils.m6c("MessageServer", "MessageChannelCallback channelRead Exception: " + Log.getStackTraceString(e));
                try {
                    nioAbstractSocketChannel.m82d();
                } catch (IOException e2) {
                    LogUtils.m6c("MessageServer", "socketChannel close IOException: " + Log.getStackTraceString(e2));
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: d */
    public MessageProtocol m141d(MessageProtocol messageProtocol, NioAbstractSocketChannel nioAbstractSocketChannel) {
        MessageBean m40d = messageProtocol.m40d();
        LogUtils.m6c("MessageServer", "handleMessage msg: " + messageProtocol);
        int cmdId = m40d.getCmdId();
        if (MessageUtils.C0505a.f220a.contains(Integer.valueOf(cmdId)) && !nioAbstractSocketChannel.m67m().mo89a()) {
            throw new IllegalArgumentException("the opposite end of the socket has no identity authentication and cannot reply to the " + cmdId + "command, close the session");
        } else if (MessageUtils.C0505a.f221b.contains(Integer.valueOf(cmdId)) && nioAbstractSocketChannel.m67m().mo89a()) {
            throw new IllegalArgumentException("the opposite end of the socket has been authenticated and cannot reply to the " + cmdId + "command, close the session");
        } else {
            switch (cmdId) {
                case 40000:
                    nioAbstractSocketChannel.m65o(new MessageAuthentication(CryptoProtocol.m127a(InfoParser.m56a(m40d.getExtraInfo()))));
                    nioAbstractSocketChannel.m84a(new FilterDataDecrypt(nioAbstractSocketChannel.m67m()));
                    return MessageUtils.m45b(30001, InfoBuilder.m63a(), nioAbstractSocketChannel.m67m());
                case 40001:
                    return MessageUtils.m45b(30002, InfoBuilder.m62b(nioAbstractSocketChannel.m67m().mo88b(InfoParser.m54c(m40d.getExtraInfo()))), nioAbstractSocketChannel.m67m());
                case 40003:
                    return MessageUtils.m45b(30003, InfoBuilder.m59e(this.f172c), nioAbstractSocketChannel.m67m());
                case 40004:
                    return MessageUtils.m45b(30004, InfoBuilder.m61c(m40d.getExtraInfo()), nioAbstractSocketChannel.m67m());
                case 40005:
                    InstallBean m55b = InfoParser.m55b(m40d.getExtraInfo());
                    if (m55b != null) {
                        return MessageUtils.m45b(30005, InfoBuilder.m58f(this.f172c, m55b), nioAbstractSocketChannel.m67m());
                    }
                    break;
            }
            return null;
        }
    }

    /* renamed from: e */
    private void m140e() {
        try {
            NioMessageServerSocketChannel nioMessageServerSocketChannel = new NioMessageServerSocketChannel();
            this.f170a = nioMessageServerSocketChannel;
            try {
                nioMessageServerSocketChannel.m75l(new InetSocketAddress("127.0.0.1", 9527));
            } catch (IOException e) {
                LogUtils.m6c("MessageServer", "mNioServerSocketChannel bind Exception: " + e.getMessage());
                this.f170a.m75l(new InetSocketAddress("127.0.0.1", 0));
            }
            LogUtils.m6c("MessageServer", "initServerSocket localPort : " + this.f170a.m73n());
            this.f170a.m84a(new FilterProtocolHeader()).m84a(new FilterProtocolContent()).m77j(24).m78i(new C0492b(), this.f171b).m81e(false);
            SocketManager.m130b().m129c(this.f170a, 16);
        } catch (Exception e2) {
            LogUtils.m6c("MessageServer", "initServerSocket Exception: " + Log.getStackTraceString(e2));
        }
    }

    /* renamed from: c */
    public int m142c() {
        NioAbstractServerSocketChannel nioAbstractServerSocketChannel = this.f170a;
        if (nioAbstractServerSocketChannel != null) {
            return nioAbstractServerSocketChannel.m73n();
        }
        return 0;
    }

    /* renamed from: f */
    public void m139f() {
        LogUtils.m6c("MessageServer", "startServer");
        HandlerThread handlerThread = new HandlerThread("MessageServer");
        handlerThread.start();
        if (handlerThread.getLooper() != null) {
            this.f171b = new Handler(handlerThread.getLooper());
        }
        m140e();
    }

    /* renamed from: g */
    public void m138g() {
        LogUtils.m6c("MessageServer", "stopServer");
        try {
            this.f170a.m82d();
        } catch (IOException e) {
            LogUtils.m6c("MessageServer", "NioServerSocketChannel close Exception: " + Log.getStackTraceString(e));
        }
        Handler handler = this.f171b;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f171b.getLooper().quit();
            this.f171b = null;
        }
    }
}
