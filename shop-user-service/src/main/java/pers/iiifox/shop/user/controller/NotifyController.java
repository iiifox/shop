package pers.iiifox.shop.user.controller;

import com.google.code.kaptcha.Producer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.iiifox.shop.result.R;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author 田章
 * @description 用户通知 前端控制器
 * @date 2022/12/23
 */
@Slf4j
@Tag(name = "NotifyController", description = "用户通知")
@RestController
@RequestMapping("/api/user/v1")
public class NotifyController {

    @Autowired
    private Producer captchaProducer;

    @Operation(summary = "获取图形验证码")
    @GetMapping("/captcha")
    public R getCaptcha(HttpServletResponse response) {
        String captchaText = captchaProducer.createText();
        log.info("图形验证码: {}", captchaText);
        BufferedImage image = captchaProducer.createImage(captchaText);
        try(ServletOutputStream outputStream = response.getOutputStream()) {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.ok();
    }
}
