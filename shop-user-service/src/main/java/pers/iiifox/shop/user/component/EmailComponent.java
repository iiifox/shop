package pers.iiifox.shop.user.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author 田章
 * @description 邮件组件
 * @date 2022/12/25
 */
@Component
public class EmailComponent {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送邮件
     *
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param to      邮件接收地址
     * @throws MailParseException          邮箱地址解析失败
     * @throws MailAuthenticationException 发件人身份验证失败
     * @throws MailSendException           邮件发送失败
     */
    public void sendEmail(String subject, String content, String... to) {
        javaMailSender.send(new SimpleMailMessage() {{
            setFrom(from);
            setSubject(subject);
            setText(content);
            setTo(to);
        }});
    }
}
