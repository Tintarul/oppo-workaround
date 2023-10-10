package p010g;

import androidx.annotation.NonNull;
import com.allawn.cryptography.entity.EncryptEnum;
import com.allawn.cryptography.entity.NegotiationEnum;
import com.allawn.cryptography.exception.InvalidAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import p005b.AesUtil;
import p005b.RsaUtil;
import p009f.SceneConfig;
import p009f.SceneData;

/* renamed from: g.e */
/* loaded from: classes.dex */
public class SceneUtil {

    /* renamed from: a */
    public static AtomicLong f169a = new AtomicLong();

    /* renamed from: a */
    private static String m150a(String str) {
        String name = EncryptEnum.AES.name();
        if (name.equals(str)) {
            return name;
        }
        throw new InvalidAlgorithmException(str);
    }

    @NonNull
    /* renamed from: b */
    public static Map<String, SceneConfig> m149b(List<SceneConfig> list) {
        HashMap hashMap = new HashMap();
        Objects.requireNonNull(list, "sceneConfigs should not be null.");
        for (SceneConfig sceneConfig : list) {
            if (sceneConfig != null) {
                hashMap.put(sceneConfig.m180f(), sceneConfig);
            }
        }
        return hashMap;
    }

    @NonNull
    /* renamed from: c */
    public static SceneData m148c(SceneConfig sceneConfig) {
        SceneData sceneData = new SceneData();
        sceneData.m161k(sceneConfig.m180f());
        sceneData.m165g(m150a(sceneConfig.m185a()));
        sceneData.m163i(sceneConfig.m184b());
        sceneData.m164h(m147d(sceneConfig.m185a()));
        long m151a = DateUtil.m151a() + (sceneData.m169c() * 1000);
        if (sceneConfig.m179g()) {
            sceneData.m160l(String.valueOf(m151a));
        } else {
            sceneData.m160l(String.valueOf(f169a.getAndIncrement()));
        }
        sceneData.m162j(m151a);
        return sceneData;
    }

    /* renamed from: d */
    private static String m147d(String str) {
        if (EncryptEnum.AES.name().equals(str)) {
            return AesUtil.m223d();
        }
        throw new InvalidAlgorithmException(str);
    }

    /* renamed from: e */
    public static String m146e(String str, String str2, String str3) {
        String name = NegotiationEnum.RSA.name();
        String name2 = NegotiationEnum.EC.name();
        if (name.equals(str2)) {
            return RsaUtil.m222a(str, str3);
        }
        if (name2.equals(str2)) {
            throw new RuntimeException("还没实现ec");
        }
        throw new InvalidAlgorithmException(str2);
    }
}
