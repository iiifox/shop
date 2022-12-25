package pers.iiifox.shop.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;
import pers.iiifox.shop.exception.BizException;
import pers.iiifox.shop.user.component.EmailComponent;
import pers.iiifox.shop.user.service.NotifyService;

import java.util.Random;

/**
 * @author 田章
 * @description 通知服务 实现类
 * @date 2022/12/25
 */
@Service
public class NotifyServiceImpl implements NotifyService {

    @Autowired
    private EmailComponent emailComponent;

    @Value("${spring.mail.username}")
    private String from;

    private static final String CONTENT = "您的验证码为：%d，有效时间三分钟，请勿泄露于他人！";

    private static final Random RANDOM = new Random();

    @Override
    public int sendRegisterCode(String to) {
        int code = RANDOM.nextInt(100000, 1000000);
        String data = String.format(CONTENT, code);
        try {
            emailComponent.sendEmail(from, "Shop Register Code", data, to);
        } catch (MailParseException e) {
            throw new BizException("邮箱解析失败", e);
        } catch (MailAuthenticationException e) {
            throw new BizException("发件人身份验证失败", e);
        } catch (MailSendException e) {
            throw new BizException("邮件发送失败", e);
        }
        return code;
    }
}
