package pers.iiifox.shop.user;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 田章
 * @description 用户服务启动类
 * @createDate 2022/12/23 4:47
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"pers.iiifox.shop.user", "pers.iiifox.shop.config"})
@MapperScan("pers.iiifox.shop.user.mapper")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
