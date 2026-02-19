package com.dnfm.game.test.util;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.dnfm.mina.protobuf.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MessageCodec {

    private static final int HEADER_SIZE = 8;

    public static byte[] encodeMessage(Message message, byte seq) throws Exception {
        byte[] body;
        try {
            Codec<Message> codec = (Codec<Message>) ProtobufProxy.create(message.getClass());
            body = codec.encode(message);
        } catch (IllegalArgumentException e) {
            body = new byte[0];
        }
        
        byte[] encBody = encrypt(seq, body);
        
        int totalLen = (encBody != null && encBody.length > 0 ? encBody.length : 0) + HEADER_SIZE;
        
        ByteBuffer buffer = ByteBuffer.allocate(totalLen);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        
        buffer.putShort((short) totalLen);
        buffer.putShort((short) message.getModule());
        buffer.put(seq);
        
        Integer transId = message.transId;
        if (transId != null) {
            buffer.put(transId.byteValue());
        } else {
            buffer.put((byte) 0);
        }
        
        buffer.putShort((short) body.length);
        
        if (encBody != null && encBody.length > 0) {
            buffer.put(encBody);
        }
        
        return buffer.array();
    }

    public static byte[] encrypt(byte seq, byte[] in) {
        if (in == null || in.length == 0) {
            return new byte[0];
        }
        
        if (seq == 0) {
            seq = 10;
        }
        
        byte[] compressed = lz4Compress(in);
        byte[] encrypted = xor(compressed, seq);
        
        return encrypted;
    }

    public static byte[] decrypt(byte seq, byte[] in, int originalLen) {
        if (seq == 0) {
            seq = 10;
        }
        
        if (in == null || in.length == 0 || originalLen == 0) {
            return new byte[0];
        }
        
        byte[] decrypted = xor(in, seq);
        byte[] decompressed = lz4Decompress(decrypted, originalLen);
        
        return decompressed;
    }

    private static byte[] xor(byte[] in, byte xorByte) {
        byte[] res = new byte[in.length];
        for (int i = 0; i < in.length; i++) {
            res[i] = (byte) (in[i] ^ xorByte);
        }
        return res;
    }

    private static byte[] lz4Compress(byte[] in) {
        try {
            net.jpountz.lz4.LZ4Factory factory = net.jpountz.lz4.LZ4Factory.fastestInstance();
            net.jpountz.lz4.LZ4Compressor compressor = factory.fastCompressor();
            
            int maxCompressedLength = compressor.maxCompressedLength(in.length);
            byte[] compressed = new byte[maxCompressedLength];
            
            int compressedLength = compressor.compress(in, 0, in.length, compressed, 0, maxCompressedLength);
            
            byte[] result = new byte[compressedLength];
            System.arraycopy(compressed, 0, result, 0, compressedLength);
            
            return result;
        } catch (Exception e) {
            throw new RuntimeException("LZ4压缩失败", e);
        }
    }

    private static byte[] lz4Decompress(byte[] in, int originalLen) {
        try {
            net.jpountz.lz4.LZ4Factory factory = net.jpountz.lz4.LZ4Factory.fastestInstance();
            net.jpountz.lz4.LZ4FastDecompressor decompressor = factory.fastDecompressor();
            
            byte[] decompressed = new byte[originalLen];
            decompressor.decompress(in, 0, decompressed, 0, originalLen);
            
            return decompressed;
        } catch (Exception e) {
            throw new RuntimeException("LZ4解压失败", e);
        }
    }

    public static Message decodeMessage(byte[] data) throws Exception {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        
        short totalLen = buffer.getShort();
        short module = buffer.getShort();
        byte seq = buffer.get();
        byte transId = buffer.get();
        short bodyLen = buffer.getShort();
        
        int encBodyLen = totalLen - HEADER_SIZE;
        byte[] encBody = new byte[encBodyLen];
        buffer.get(encBody);
        
        byte[] body = decrypt(seq, encBody, bodyLen);
        
        return decodeBody(module, body);
    }

    private static Message decodeBody(short module, byte[] body) throws Exception {
        Class<? extends Message> messageClass = getMessageClass(module);
        if (messageClass == null) {
            throw new RuntimeException("未找到模块ID对应的消息类: " + module);
        }
        
        Codec<Message> codec = (Codec<Message>) ProtobufProxy.create(messageClass);
        return codec.decode(body);
    }

    private static Class<? extends Message> getMessageClass(short module) {
        switch (module) {
            case 10000:
                return com.dnfm.mina.protobuf.RES_LOGIN.class;
            case 10001:
                return com.dnfm.mina.protobuf.RES_CHARACTER_INFO.class;
            case 10002:
                return com.dnfm.mina.protobuf.RES_CHARAC_LIST.class;
            case 10006:
                return com.dnfm.mina.protobuf.RES_PING.class;
            case 10103:
                return com.dnfm.mina.protobuf.RES_CHARACTER_INFO.class;
            default:
                return null;
        }
    }
}
