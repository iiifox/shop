package pers.iiifox.shop.user.service;

/**
 * @author 田章
 * @description 邮箱 Service
 * @date 2022/12/24
 */
public interface MailService {

    /**
     * 发送电子邮件
     *
     * @param subject 邮件主题
     * @param content 邮件正文内容
     * @param to      收件人邮箱地址
     */
    void sendEmail(String subject, String content, String... to);
}
