package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/* loaded from: classes.dex */
public class FlexBuffers {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final ReadBuf EMPTY_BB = new ArrayReadWriteBuf(new byte[]{0}, 1);
    public static final int FBT_BLOB = 25;
    public static final int FBT_BOOL = 26;
    public static final int FBT_FLOAT = 3;
    public static final int FBT_INDIRECT_FLOAT = 8;
    public static final int FBT_INDIRECT_INT = 6;
    public static final int FBT_INDIRECT_UINT = 7;
    public static final int FBT_INT = 1;
    public static final int FBT_KEY = 4;
    public static final int FBT_MAP = 9;
    public static final int FBT_NULL = 0;
    public static final int FBT_STRING = 5;
    public static final int FBT_UINT = 2;
    public static final int FBT_VECTOR = 10;
    public static final int FBT_VECTOR_BOOL = 36;
    public static final int FBT_VECTOR_FLOAT = 13;
    public static final int FBT_VECTOR_FLOAT2 = 18;
    public static final int FBT_VECTOR_FLOAT3 = 21;
    public static final int FBT_VECTOR_FLOAT4 = 24;
    public static final int FBT_VECTOR_INT = 11;
    public static final int FBT_VECTOR_INT2 = 16;
    public static final int FBT_VECTOR_INT3 = 19;
    public static final int FBT_VECTOR_INT4 = 22;
    public static final int FBT_VECTOR_KEY = 14;
    public static final int FBT_VECTOR_STRING_DEPRECATED = 15;
    public static final int FBT_VECTOR_UINT = 12;
    public static final int FBT_VECTOR_UINT2 = 17;
    public static final int FBT_VECTOR_UINT3 = 20;
    public static final int FBT_VECTOR_UINT4 = 23;

    /* loaded from: classes.dex */
    public static class Blob extends Sized {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final Blob EMPTY = new Blob(FlexBuffers.EMPTY_BB, 1, 1);

        Blob(ReadBuf readBuf, int i, int i2) {
            super(readBuf, i, i2);
        }

        public static Blob empty() {
            return EMPTY;
        }

        public ByteBuffer data() {
            ByteBuffer wrap = ByteBuffer.wrap(this.f113bb.data());
            wrap.position(this.end);
            wrap.limit(this.end + size());
            return wrap.asReadOnlyBuffer().slice();
        }

        public byte get(int i) {
            return this.f113bb.get(this.end + i);
        }

        public byte[] getBytes() {
            int size = size();
            byte[] bArr = new byte[size];
            for (int i = 0; i < size; i++) {
                bArr[i] = this.f113bb.get(this.end + i);
            }
            return bArr;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Sized
        public /* bridge */ /* synthetic */ int size() {
            return super.size();
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public String toString() {
            return this.f113bb.getString(this.end, size());
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public StringBuilder toString(StringBuilder sb) {
            sb.append('\"');
            sb.append(this.f113bb.getString(this.end, size()));
            sb.append('\"');
            return sb;
        }
    }

    /* loaded from: classes.dex */
    public static class FlexBufferException extends RuntimeException {
        public FlexBufferException(String str) {
            super(str);
        }
    }

    /* loaded from: classes.dex */
    public static class Key extends Object {
        private static final Key EMPTY = new Key(FlexBuffers.EMPTY_BB, 0, 0);

        Key(ReadBuf readBuf, int i, int i2) {
            super(readBuf, i, i2);
        }

        public static Key empty() {
            return EMPTY;
        }

        int compareTo(byte[] bArr) {
            byte b;
            byte b2;
            int i = this.end;
            int i2 = 0;
            do {
                b = this.f113bb.get(i);
                b2 = bArr[i2];
                if (b == 0) {
                    return b - b2;
                }
                i++;
                i2++;
                if (i2 == bArr.length) {
                    return b - b2;
                }
            } while (b == b2);
            return b - b2;
        }

        public boolean equals(java.lang.Object obj) {
            if (obj instanceof Key) {
                Key key = (Key) obj;
                if (key.end == this.end && key.byteWidth == this.byteWidth) {
                    return true;
                }
                return FlexBuffers.$assertionsDisabled;
            }
            return FlexBuffers.$assertionsDisabled;
        }

        public int hashCode() {
            return this.byteWidth ^ this.end;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public StringBuilder toString(StringBuilder sb) {
            sb.append(toString());
            return sb;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public String toString() {
            int i = this.end;
            while (this.f113bb.get(i) != 0) {
                i++;
            }
            int i2 = this.end;
            return this.f113bb.getString(i2, i - i2);
        }
    }

    /* loaded from: classes.dex */
    public static class KeyVector {
        private final TypedVector vec;

        KeyVector(TypedVector typedVector) {
            this.vec = typedVector;
        }

        public Key get(int i) {
            if (i >= size()) {
                return Key.EMPTY;
            }
            TypedVector typedVector = this.vec;
            TypedVector typedVector2 = this.vec;
            ReadBuf readBuf = typedVector2.f113bb;
            return new Key(readBuf, FlexBuffers.indirect(readBuf, typedVector.end + (i * typedVector.byteWidth), typedVector2.byteWidth), 1);
        }

        public int size() {
            return this.vec.size();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (int i = 0; i < this.vec.size(); i++) {
                this.vec.get(i).toString(sb);
                if (i != this.vec.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }

    /* loaded from: classes.dex */
    public static class Map extends Vector {
        private static final Map EMPTY_MAP = new Map(FlexBuffers.EMPTY_BB, 1, 1);

        Map(ReadBuf readBuf, int i, int i2) {
            super(readBuf, i, i2);
        }

        private int binarySearch(KeyVector keyVector, byte[] bArr) {
            int size = keyVector.size() - 1;
            int i = 0;
            while (i <= size) {
                int i2 = (i + size) >>> 1;
                int compareTo = keyVector.get(i2).compareTo(bArr);
                if (compareTo < 0) {
                    i = i2 + 1;
                } else if (compareTo <= 0) {
                    return i2;
                } else {
                    size = i2 - 1;
                }
            }
            return -(i + 1);
        }

        public static Map empty() {
            return EMPTY_MAP;
        }

        public Reference get(String str) {
            return get(str.getBytes(StandardCharsets.UTF_8));
        }

        public KeyVector keys() {
            int i = this.end - (this.byteWidth * 3);
            ReadBuf readBuf = this.f113bb;
            int indirect = FlexBuffers.indirect(readBuf, i, this.byteWidth);
            ReadBuf readBuf2 = this.f113bb;
            int i2 = this.byteWidth;
            return new KeyVector(new TypedVector(readBuf, indirect, FlexBuffers.readInt(readBuf2, i + i2, i2), 4));
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Vector, androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public StringBuilder toString(StringBuilder sb) {
            sb.append("{ ");
            KeyVector keys = keys();
            int size = size();
            Vector values = values();
            for (int i = 0; i < size; i++) {
                sb.append('\"');
                sb.append(keys.get(i).toString());
                sb.append("\" : ");
                sb.append(values.get(i).toString());
                if (i != size - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" }");
            return sb;
        }

        public Vector values() {
            return new Vector(this.f113bb, this.end, this.byteWidth);
        }

        public Reference get(byte[] bArr) {
            KeyVector keys = keys();
            int size = keys.size();
            int binarySearch = binarySearch(keys, bArr);
            if (binarySearch < 0 || binarySearch >= size) {
                return Reference.NULL_REFERENCE;
            }
            return get(binarySearch);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Object {

        /* renamed from: bb */
        ReadBuf f113bb;
        int byteWidth;
        int end;

        Object(ReadBuf readBuf, int i, int i2) {
            this.f113bb = readBuf;
            this.end = i;
            this.byteWidth = i2;
        }

        public String toString() {
            return toString(new StringBuilder(128)).toString();
        }

        public abstract StringBuilder toString(StringBuilder sb);
    }

    /* loaded from: classes.dex */
    public static class Reference {
        private static final Reference NULL_REFERENCE = new Reference(FlexBuffers.EMPTY_BB, 0, 1, 0);

        /* renamed from: bb */
        private ReadBuf f114bb;
        private int byteWidth;
        private int end;
        private int parentWidth;
        private int type;

        Reference(ReadBuf readBuf, int i, int i2, int i3) {
            this(readBuf, i, i2, 1 << (i3 & 3), i3 >> 2);
        }

        public Blob asBlob() {
            if (!isBlob() && !isString()) {
                return Blob.empty();
            }
            ReadBuf readBuf = this.f114bb;
            return new Blob(readBuf, FlexBuffers.indirect(readBuf, this.end, this.parentWidth), this.byteWidth);
        }

        public boolean asBoolean() {
            if (isBoolean()) {
                if (this.f114bb.get(this.end) != 0) {
                    return true;
                }
                return FlexBuffers.$assertionsDisabled;
            } else if (asUInt() != 0) {
                return true;
            } else {
                return FlexBuffers.$assertionsDisabled;
            }
        }

        public double asFloat() {
            int i = this.type;
            if (i == 3) {
                return FlexBuffers.readDouble(this.f114bb, this.end, this.parentWidth);
            }
            if (i != 1) {
                if (i != 2) {
                    if (i == 5) {
                        return Double.parseDouble(asString());
                    }
                    if (i == 6) {
                        ReadBuf readBuf = this.f114bb;
                        return FlexBuffers.readInt(readBuf, FlexBuffers.indirect(readBuf, this.end, this.parentWidth), this.byteWidth);
                    } else if (i == 7) {
                        ReadBuf readBuf2 = this.f114bb;
                        return FlexBuffers.readUInt(readBuf2, FlexBuffers.indirect(readBuf2, this.end, this.parentWidth), this.byteWidth);
                    } else if (i == 8) {
                        ReadBuf readBuf3 = this.f114bb;
                        return FlexBuffers.readDouble(readBuf3, FlexBuffers.indirect(readBuf3, this.end, this.parentWidth), this.byteWidth);
                    } else if (i == 10) {
                        return asVector().size();
                    } else {
                        if (i != 26) {
                            return 0.0d;
                        }
                    }
                }
                return FlexBuffers.readUInt(this.f114bb, this.end, this.parentWidth);
            }
            return FlexBuffers.readInt(this.f114bb, this.end, this.parentWidth);
        }

        public int asInt() {
            int i = this.type;
            if (i == 1) {
                return FlexBuffers.readInt(this.f114bb, this.end, this.parentWidth);
            }
            if (i != 2) {
                if (i != 3) {
                    if (i != 5) {
                        if (i == 6) {
                            ReadBuf readBuf = this.f114bb;
                            return FlexBuffers.readInt(readBuf, FlexBuffers.indirect(readBuf, this.end, this.parentWidth), this.byteWidth);
                        } else if (i == 7) {
                            ReadBuf readBuf2 = this.f114bb;
                            return (int) FlexBuffers.readUInt(readBuf2, FlexBuffers.indirect(readBuf2, this.end, this.parentWidth), this.parentWidth);
                        } else if (i == 8) {
                            ReadBuf readBuf3 = this.f114bb;
                            return (int) FlexBuffers.readDouble(readBuf3, FlexBuffers.indirect(readBuf3, this.end, this.parentWidth), this.byteWidth);
                        } else if (i != 10) {
                            if (i != 26) {
                                return 0;
                            }
                            return FlexBuffers.readInt(this.f114bb, this.end, this.parentWidth);
                        } else {
                            return asVector().size();
                        }
                    }
                    return Integer.parseInt(asString());
                }
                return (int) FlexBuffers.readDouble(this.f114bb, this.end, this.parentWidth);
            }
            return (int) FlexBuffers.readUInt(this.f114bb, this.end, this.parentWidth);
        }

        public Key asKey() {
            if (isKey()) {
                ReadBuf readBuf = this.f114bb;
                return new Key(readBuf, FlexBuffers.indirect(readBuf, this.end, this.parentWidth), this.byteWidth);
            }
            return Key.empty();
        }

        public long asLong() {
            int i = this.type;
            if (i == 1) {
                return FlexBuffers.readLong(this.f114bb, this.end, this.parentWidth);
            }
            if (i != 2) {
                if (i != 3) {
                    if (i == 5) {
                        try {
                            return Long.parseLong(asString());
                        } catch (NumberFormatException unused) {
                            return 0L;
                        }
                    } else if (i == 6) {
                        ReadBuf readBuf = this.f114bb;
                        return FlexBuffers.readLong(readBuf, FlexBuffers.indirect(readBuf, this.end, this.parentWidth), this.byteWidth);
                    } else if (i == 7) {
                        ReadBuf readBuf2 = this.f114bb;
                        return FlexBuffers.readUInt(readBuf2, FlexBuffers.indirect(readBuf2, this.end, this.parentWidth), this.parentWidth);
                    } else if (i == 8) {
                        ReadBuf readBuf3 = this.f114bb;
                        return (long) FlexBuffers.readDouble(readBuf3, FlexBuffers.indirect(readBuf3, this.end, this.parentWidth), this.byteWidth);
                    } else if (i != 10) {
                        if (i != 26) {
                            return 0L;
                        }
                        return FlexBuffers.readInt(this.f114bb, this.end, this.parentWidth);
                    } else {
                        return asVector().size();
                    }
                }
                return (long) FlexBuffers.readDouble(this.f114bb, this.end, this.parentWidth);
            }
            return FlexBuffers.readUInt(this.f114bb, this.end, this.parentWidth);
        }

        public Map asMap() {
            if (isMap()) {
                ReadBuf readBuf = this.f114bb;
                return new Map(readBuf, FlexBuffers.indirect(readBuf, this.end, this.parentWidth), this.byteWidth);
            }
            return Map.empty();
        }

        public String asString() {
            if (isString()) {
                int indirect = FlexBuffers.indirect(this.f114bb, this.end, this.parentWidth);
                ReadBuf readBuf = this.f114bb;
                int i = this.byteWidth;
                return this.f114bb.getString(indirect, (int) FlexBuffers.readUInt(readBuf, indirect - i, i));
            } else if (isKey()) {
                int indirect2 = FlexBuffers.indirect(this.f114bb, this.end, this.byteWidth);
                int i2 = indirect2;
                while (this.f114bb.get(i2) != 0) {
                    i2++;
                }
                return this.f114bb.getString(indirect2, i2 - indirect2);
            } else {
                return "";
            }
        }

        public long asUInt() {
            int i = this.type;
            if (i == 2) {
                return FlexBuffers.readUInt(this.f114bb, this.end, this.parentWidth);
            }
            if (i != 1) {
                if (i != 3) {
                    if (i != 10) {
                        if (i != 26) {
                            if (i != 5) {
                                if (i == 6) {
                                    ReadBuf readBuf = this.f114bb;
                                    return FlexBuffers.readLong(readBuf, FlexBuffers.indirect(readBuf, this.end, this.parentWidth), this.byteWidth);
                                } else if (i == 7) {
                                    ReadBuf readBuf2 = this.f114bb;
                                    return FlexBuffers.readUInt(readBuf2, FlexBuffers.indirect(readBuf2, this.end, this.parentWidth), this.byteWidth);
                                } else if (i != 8) {
                                    return 0L;
                                } else {
                                    ReadBuf readBuf3 = this.f114bb;
                                    return (long) FlexBuffers.readDouble(readBuf3, FlexBuffers.indirect(readBuf3, this.end, this.parentWidth), this.parentWidth);
                                }
                            }
                            return Long.parseLong(asString());
                        }
                        return FlexBuffers.readInt(this.f114bb, this.end, this.parentWidth);
                    }
                    return asVector().size();
                }
                return (long) FlexBuffers.readDouble(this.f114bb, this.end, this.parentWidth);
            }
            return FlexBuffers.readLong(this.f114bb, this.end, this.parentWidth);
        }

        public Vector asVector() {
            if (isVector()) {
                ReadBuf readBuf = this.f114bb;
                return new Vector(readBuf, FlexBuffers.indirect(readBuf, this.end, this.parentWidth), this.byteWidth);
            }
            int i = this.type;
            if (i == 15) {
                ReadBuf readBuf2 = this.f114bb;
                return new TypedVector(readBuf2, FlexBuffers.indirect(readBuf2, this.end, this.parentWidth), this.byteWidth, 4);
            } else if (FlexBuffers.isTypedVector(i)) {
                ReadBuf readBuf3 = this.f114bb;
                return new TypedVector(readBuf3, FlexBuffers.indirect(readBuf3, this.end, this.parentWidth), this.byteWidth, FlexBuffers.toTypedVectorElementType(this.type));
            } else {
                return Vector.empty();
            }
        }

        public int getType() {
            return this.type;
        }

        public boolean isBlob() {
            if (this.type == 25) {
                return true;
            }
            return FlexBuffers.$assertionsDisabled;
        }

        public boolean isBoolean() {
            if (this.type == 26) {
                return true;
            }
            return FlexBuffers.$assertionsDisabled;
        }

        public boolean isFloat() {
            int i = this.type;
            if (i == 3 || i == 8) {
                return true;
            }
            return FlexBuffers.$assertionsDisabled;
        }

        public boolean isInt() {
            int i = this.type;
            if (i == 1 || i == 6) {
                return true;
            }
            return FlexBuffers.$assertionsDisabled;
        }

        public boolean isIntOrUInt() {
            if (isInt() || isUInt()) {
                return true;
            }
            return FlexBuffers.$assertionsDisabled;
        }

        public boolean isKey() {
            if (this.type == 4) {
                return true;
            }
            return FlexBuffers.$assertionsDisabled;
        }

        public boolean isMap() {
            if (this.type == 9) {
                return true;
            }
            return FlexBuffers.$assertionsDisabled;
        }

        public boolean isNull() {
            if (this.type == 0) {
                return true;
            }
            return FlexBuffers.$assertionsDisabled;
        }

        public boolean isNumeric() {
            if (isIntOrUInt() || isFloat()) {
                return true;
            }
            return FlexBuffers.$assertionsDisabled;
        }

        public boolean isString() {
            if (this.type == 5) {
                return true;
            }
            return FlexBuffers.$assertionsDisabled;
        }

        public boolean isTypedVector() {
            return FlexBuffers.isTypedVector(this.type);
        }

        public boolean isUInt() {
            int i = this.type;
            if (i == 2 || i == 7) {
                return true;
            }
            return FlexBuffers.$assertionsDisabled;
        }

        public boolean isVector() {
            int i = this.type;
            if (i == 10 || i == 9) {
                return true;
            }
            return FlexBuffers.$assertionsDisabled;
        }

        public String toString() {
            return toString(new StringBuilder(128)).toString();
        }

        Reference(ReadBuf readBuf, int i, int i2, int i3, int i4) {
            this.f114bb = readBuf;
            this.end = i;
            this.parentWidth = i2;
            this.byteWidth = i3;
            this.type = i4;
        }

        StringBuilder toString(StringBuilder sb) {
            int i = this.type;
            if (i != 36) {
                switch (i) {
                    case 0:
                        sb.append("null");
                        return sb;
                    case 1:
                    case 6:
                        sb.append(asLong());
                        return sb;
                    case 2:
                    case 7:
                        sb.append(asUInt());
                        return sb;
                    case 3:
                    case 8:
                        sb.append(asFloat());
                        return sb;
                    case 4:
                        Key asKey = asKey();
                        sb.append('\"');
                        StringBuilder key = asKey.toString(sb);
                        key.append('\"');
                        return key;
                    case 5:
                        sb.append('\"');
                        sb.append(asString());
                        sb.append('\"');
                        return sb;
                    case 9:
                        return asMap().toString(sb);
                    case 10:
                        return asVector().toString(sb);
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        break;
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                        throw new FlexBufferException("not_implemented:" + this.type);
                    case 25:
                        return asBlob().toString(sb);
                    case 26:
                        sb.append(asBoolean());
                        return sb;
                    default:
                        return sb;
                }
            }
            sb.append(asVector());
            return sb;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Sized extends Object {
        protected final int size;

        Sized(ReadBuf readBuf, int i, int i2) {
            super(readBuf, i, i2);
            this.size = FlexBuffers.readInt(this.f113bb, i - i2, i2);
        }

        public int size() {
            return this.size;
        }
    }

    /* loaded from: classes.dex */
    public static class TypedVector extends Vector {
        private static final TypedVector EMPTY_VECTOR = new TypedVector(FlexBuffers.EMPTY_BB, 1, 1, 1);
        private final int elemType;

        TypedVector(ReadBuf readBuf, int i, int i2, int i3) {
            super(readBuf, i, i2);
            this.elemType = i3;
        }

        public static TypedVector empty() {
            return EMPTY_VECTOR;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Vector
        public Reference get(int i) {
            if (i >= size()) {
                return Reference.NULL_REFERENCE;
            }
            return new Reference(this.f113bb, this.end + (i * this.byteWidth), this.byteWidth, 1, this.elemType);
        }

        public int getElemType() {
            return this.elemType;
        }

        public boolean isEmptyVector() {
            if (this == EMPTY_VECTOR) {
                return true;
            }
            return FlexBuffers.$assertionsDisabled;
        }
    }

    /* loaded from: classes.dex */
    public static class Unsigned {
        Unsigned() {
        }

        public static int byteToUnsignedInt(byte b) {
            return b & 255;
        }

        public static long intToUnsignedLong(int i) {
            return i & 4294967295L;
        }

        public static int shortToUnsignedInt(short s) {
            return s & 65535;
        }
    }

    /* loaded from: classes.dex */
    public static class Vector extends Sized {
        private static final Vector EMPTY_VECTOR = new Vector(FlexBuffers.EMPTY_BB, 1, 1);

        Vector(ReadBuf readBuf, int i, int i2) {
            super(readBuf, i, i2);
        }

        public static Vector empty() {
            return EMPTY_VECTOR;
        }

        public Reference get(int i) {
            long size = size();
            long j = i;
            if (j >= size) {
                return Reference.NULL_REFERENCE;
            }
            return new Reference(this.f113bb, this.end + (i * this.byteWidth), this.byteWidth, Unsigned.byteToUnsignedInt(this.f113bb.get((int) (this.end + (size * this.byteWidth) + j))));
        }

        public boolean isEmpty() {
            if (this == EMPTY_VECTOR) {
                return true;
            }
            return FlexBuffers.$assertionsDisabled;
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Sized
        public /* bridge */ /* synthetic */ int size() {
            return super.size();
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        @Override // androidx.emoji2.text.flatbuffer.FlexBuffers.Object
        public StringBuilder toString(StringBuilder sb) {
            sb.append("[ ");
            int size = size();
            for (int i = 0; i < size; i++) {
                get(i).toString(sb);
                if (i != size - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" ]");
            return sb;
        }
    }

    @Deprecated
    public static Reference getRoot(ByteBuffer byteBuffer) {
        return getRoot(byteBuffer.hasArray() ? new ArrayReadWriteBuf(byteBuffer.array(), byteBuffer.limit()) : new ByteBufferReadWriteBuf(byteBuffer));
    }

    public static int indirect(ReadBuf readBuf, int i, int i2) {
        return (int) (i - readUInt(readBuf, i, i2));
    }

    public static boolean isTypeInline(int i) {
        if (i <= 3 || i == 26) {
            return true;
        }
        return $assertionsDisabled;
    }

    static boolean isTypedVector(int i) {
        if ((i < 11 || i > 15) && i != 36) {
            return $assertionsDisabled;
        }
        return true;
    }

    public static boolean isTypedVectorElementType(int i) {
        if ((i < 1 || i > 4) && i != 26) {
            return $assertionsDisabled;
        }
        return true;
    }

    public static double readDouble(ReadBuf readBuf, int i, int i2) {
        if (i2 != 4) {
            if (i2 != 8) {
                return -1.0d;
            }
            return readBuf.getDouble(i);
        }
        return readBuf.getFloat(i);
    }

    public static int readInt(ReadBuf readBuf, int i, int i2) {
        return (int) readLong(readBuf, i, i2);
    }

    public static long readLong(ReadBuf readBuf, int i, int i2) {
        int i3;
        if (i2 == 1) {
            i3 = readBuf.get(i);
        } else if (i2 == 2) {
            i3 = readBuf.getShort(i);
        } else if (i2 != 4) {
            if (i2 != 8) {
                return -1L;
            }
            return readBuf.getLong(i);
        } else {
            i3 = readBuf.getInt(i);
        }
        return i3;
    }

    public static long readUInt(ReadBuf readBuf, int i, int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 4) {
                    if (i2 != 8) {
                        return -1L;
                    }
                    return readBuf.getLong(i);
                }
                return Unsigned.intToUnsignedLong(readBuf.getInt(i));
            }
            return Unsigned.shortToUnsignedInt(readBuf.getShort(i));
        }
        return Unsigned.byteToUnsignedInt(readBuf.get(i));
    }

    public static int toTypedVector(int i, int i2) {
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        return 0;
                    }
                    return (i - 1) + 22;
                }
                return (i - 1) + 19;
            }
            return (i - 1) + 16;
        }
        return (i - 1) + 11;
    }

    static int toTypedVectorElementType(int i) {
        return (i - 11) + 1;
    }

    public static Reference getRoot(ReadBuf readBuf) {
        int limit = readBuf.limit() - 1;
        byte b = readBuf.get(limit);
        int i = limit - 1;
        return new Reference(readBuf, i - b, b, Unsigned.byteToUnsignedInt(readBuf.get(i)));
    }
}
