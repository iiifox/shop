package pers.iiifox.shop.user.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.iiifox.shop.user.service.MailService;

@SpringBootTest
class MailServiceImplTest {

    @Autowired
    private MailService mailService;

    @Test
    void testSentEmail() {
        mailService.sendEmail("测试", "hello world", "3111628591@qq.com");
    }

}