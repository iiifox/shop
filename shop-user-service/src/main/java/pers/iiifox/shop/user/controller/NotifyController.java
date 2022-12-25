package pers.iiifox.shop.user.controller;

import com.google.code.kaptcha.Producer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.iiifox.shop.result.R;
import pers.iiifox.shop.util.IpUtils;
import pers.iiifox.shop.util.MD5Utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Operation(summary = "获取图形验证码")
    @GetMapping("/captcha")
    public R getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        String captchaText = captchaProducer.createText();
        log.info("图形验证码: {}", captchaText);
        cacheCaptcha(request, captchaText);
        BufferedImage image = captchaProducer.createImage(captchaText);
        try(ServletOutputStream outputStream = response.getOutputStream()) {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.ok();
    }

    /**
     * 将图形验证码文字缓存到 Redis，有效期一分钟
     */
    private void cacheCaptcha(HttpServletRequest request, String captchaText) {
        String ip = IpUtils.getRemoteIp(request);
        String userAgent = request.getHeader("User-Agent");
        String key = "user:captcha:" + MD5Utils.md5(ip + userAgent);
        redisTemplate.opsForValue().set(key, captchaText, 1, TimeUnit.MINUTES);
    }

}
