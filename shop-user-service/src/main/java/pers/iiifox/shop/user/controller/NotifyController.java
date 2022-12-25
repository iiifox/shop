package pers.iiifox.shop.user.controller;

import com.google.code.kaptcha.Producer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.iiifox.shop.enums.ErrorCodeEnum;
import pers.iiifox.shop.exception.BizException;
import pers.iiifox.shop.result.R;
import pers.iiifox.shop.user.service.NotifyService;
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

    @Autowired
    private NotifyService notifyService;

    @Operation(summary = "获取图形验证码")
    @GetMapping("/captcha")
    public R getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        String captchaText = captchaProducer.createText();
        log.info("图形验证码: {}", captchaText);

        // 图形验证码中的文字存入 Redis
        String key = getCacheCaptchaKey(request);
        redisTemplate.opsForValue().set(key, captchaText, 1, TimeUnit.MINUTES);

        // 根据图形验证码的文字生成图形验证码，并写入 response
        BufferedImage image = captchaProducer.createImage(captchaText);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.ok();
    }

    @Operation(summary = "获取邮箱验证码(注册码)",
            description = "提交邮箱和图形验证码，邮箱用来接收注册码",
            parameters = {
                    @Parameter(name = "to", description = "收件邮箱"),
                    @Parameter(name = "captcha", description = "提交的图形验证码")
            })
    @GetMapping("/register_code")
    public R getRegisterCode(@RequestParam("to") String to,
                             @RequestParam("captcha") String captcha,
                             HttpServletRequest request) {
        // 对 IP 进行限制：一分钟内不允许重复发送邮箱获取验证码的请求
        String key = String.format("user:code:limit:%s", IpUtils.getRemoteIp(request));
        if (redisTemplate.opsForValue().get(key) != null) {
            throw new BizException(ErrorCodeEnum.USER_ERROR_A0506);
        }

        String captchaKey = getCacheCaptchaKey(request);
        String cacheCaptcha = redisTemplate.opsForValue().get(captchaKey);
        if (captcha != null && captcha.equalsIgnoreCase(cacheCaptcha)) {
            redisTemplate.delete(captchaKey);
            notifyService.sendRegisterCode(to);
            // 发送成功，激活 IP 限制，不允许重复请求
            redisTemplate.opsForValue().set(key, "", 1, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error(new BizException("图形验证码错误"));
        }
    }

    /**
     * 获取Redis缓存图形验证码的key
     */
    private String getCacheCaptchaKey(HttpServletRequest request) {
        String ip = IpUtils.getRemoteIp(request);
        String userAgent = request.getHeader("User-Agent");
        return "user:captcha:" + MD5Utils.md5(ip + userAgent);
    }

}
