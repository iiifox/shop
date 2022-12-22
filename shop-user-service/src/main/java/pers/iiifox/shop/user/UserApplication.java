package pers.iiifox.shop.user;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author 田章
 * @description 用户服务启动类
 * @createDate 2022/12/23 4:47
 */
@Slf4j
@SpringBootApplication
@MapperScan("pers.iiifox.shop.user.mapper")
public class UserApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext run = SpringApplication.run(UserApplication.class, args);
        // 获取本机ip
        String ip = InetAddress.getLocalHost().getHostAddress();
        Environment env = run.getEnvironment();
        String port = env.getProperty("server.port");
        // 未配置默认8080
        if (port == null) {
            port = "8080";
        }
        String path = env.getProperty("server.servlet.context-path");
        // 未配置默认空白
        if (path == null) {
            path = "";
        }

        log.info("\n----------------------------------------------------------\n\t" +
                "shop-user-service 服务启动成功，访问路径如下:\n\t" +
                "本地路径: \thttp://localhost:" + port + path + "/\n\t" +
                "网络地址: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "----------------------------------------------------------");
    }

}
