package pers.iiifox.shop.util;

import lombok.extern.slf4j.Slf4j;
import pers.iiifox.shop.exception.BizException;
import pers.iiifox.shop.result.ErrorCodeEnum;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 田章
 * @description MD5 加密工具类（封装JDK自带MessageDigest）
 * @date 2022/12/24
 */
@Slf4j
public final class MD5Utils {

    private MD5Utils() {
        throw new AssertionError("pers.iiifox.shop.util.MD5Utils instances for you!");
    }

    public static String md5(String s) {
        // 将字符串转为 128 bit 的二进制位（长度为 16 的 byte 数组）
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            log.error("MD5算法实例化异常 --> {}", e.getMessage());
            throw new BizException(ErrorCodeEnum.SYSTEM_ERROR_B0001);
        }
        byte[] bytes = algorithm.digest(s.getBytes(StandardCharsets.UTF_8));

        // 将长度为 16 的 byte 数组，转为长度为 32 的 16 进制字符串
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            if ((b & 0xff) < 0x10) {
                sb.append('0');
            }
            // 先转为 int，再转 16 进制（toHexString方法会自动去除前导零）
            sb.append(Integer.toHexString(b & 0xff));
        }
        return sb.toString();
    }

}

