package pers.iiifox.shop.user.service;

/**
 * @author 田章
 * @description 通知服务 接口
 * @date 2022/12/25
 */
public interface NotifyService {

    /**
     * 发送注册码
     *
     * @param to 接收注册码的邮箱地址
     */
    void sendRegisterCode(String to);
}
