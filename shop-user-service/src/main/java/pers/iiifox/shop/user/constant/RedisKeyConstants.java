package pers.iiifox.shop.user.constant;

/**
 * @author 田章
 * @description Redis缓存键值对中的键 统一管理常量类
 * @date 2022/12/29
 */
public class RedisKeyConstants {

    /**
     * 用户注册图形验证码
     */
    public static final String USER_REGISTER_CAPTCHA = "user:register:captcha:%s";

    /**
     * 用户请求邮箱接收验证码（注册码）时 ip 一分钟限制
     */
    public static final String USER_REGISTER_LIMIT = "user:register:limit:%s";

    /**
     * 用户验证码（注册码）
     */
    public static final String USER_REGISTER_CODE = "user:register:code:%s";
}
