package com.example.mysafedisclosure;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PostHash {

    private String hashedPost;

    public PostHash(String aPost) {
        try {
            byte[] md5InBytes;
            md5InBytes = PostHash.digest(aPost.getBytes("UTF-8"));
            hashedPost=bytesToHex(md5InBytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static byte[] digest(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        return md.digest(input);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public String getHashedPost(){
        return hashedPost;
    }
}
