package pers.iiifox.shop.user.service;

/**
 * @author 田章
 * @description 通知服务 接口
 * @date 2022/12/25
 */
public interface NotifyService {

    /**
     * 发送验证码
     *
     * @param to 接收验证码的邮箱地址
     */
    void sendCode(String to);

    /**
     * 校验验证码
     *
     * @param to   接收验证码的邮箱地址
     * @param code 验证码
     * @return 校验成功返回true，校验失败返回false
     */
    boolean checkCode(String to, String code);

}
