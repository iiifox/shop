package pers.iiifox.shop.user.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author 田章
 * @description 邮箱组件，可被 service 调用。
 * @date 2022/12/24
 */
@Component
public class EmailComponent {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送电子邮件
     *
     * @param from    发件人邮箱
     * @param subject 邮件主题
     * @param content 邮件正文内容
     * @param to      收件人邮箱地址
     * @throws MailParseException          邮箱解析失败
     * @throws MailAuthenticationException 发件人身份验证失败
     * @throws MailSendException           邮件发送失败
     */
    public void sendEmail(String from, String subject, String content, String... to) {
        javaMailSender.send(new SimpleMailMessage(){{
            setFrom(from);
            setSubject(subject);
            setText(content);
            setTo(to);
        }});
    }
}
