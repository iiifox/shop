package pers.iiifox.shop.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pers.iiifox.shop.exception.BizException;
import pers.iiifox.shop.result.ErrorCodeEnum;
import pers.iiifox.shop.user.service.MailService;

/**
 * @author 田章
 * @description 邮箱 Service 实现类
 * @date 2022/12/24
 */
@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String subject, String content, String... to) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailMessage.setTo(to);
        try {
            javaMailSender.send(mailMessage);
        } catch (MailParseException e) {
            throw new BizException(ErrorCodeEnum.USER_ERROR_A0153);
        } catch (MailAuthenticationException e) {
        //
        } catch (MailSendException e) {
            throw new BizException(ErrorCodeEnum.SERVICE_ERROR_C0500);
        }
    }
}
