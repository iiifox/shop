package pers.iiifox.shop.util;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class MD5UtilsTest {

    @Test
    void test() throws NoSuchAlgorithmException {
        MessageDigest algorithm = MessageDigest.getInstance("MD5");
        algorithm.reset();
        for (byte b : algorithm.digest("123456".getBytes(StandardCharsets.UTF_8))) {
            System.out.println(b + "\t" + (b & 0xff));
        }
    }

    @Test
    void testMd5() {
        System.out.println(MD5Utils.md5("123456"));
        System.out.println(MD5Utils.md5("123456"));
        System.out.println(MD5Utils.md5("123456"));
    }

}