package pers.iiifox.shop.user.pojo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 田章
 * @description 用户注册请求 实体类
 * @date 2022/12/29
 */
@Data
@Schema(name = "UserRegisterRequest", description = "用户注册请求实体类")
public class UserRegisterRequest {

    @Schema(name = "nickname", description = "昵称", example = "iiifox")
    private String nickname;

    @Schema(name = "password", description = "密码", example = "iiifox")
    private String password;

    @Schema(name = "avatar", description = "头像网址",
            example = "https://shop-1314926265.cos.ap-shanghai.myqcloud.com/user/2022/12/27/108838edd43e4d09aa21799fb0329efe.png")
    private String avatar;

    @Schema(name = "signature", description = "个性签名", example = "没有签名就是最好的签名")
    private String signature;

    @Schema(name = "gender", description = "性别。False表示女，True表示男", example = "True")
    private Boolean gender;

    @Schema(name = "email", description = "电子邮箱", example = "3111628591@qq.com")
    private String email;

    @Schema(name = "code", description = "验证码", example = "123456")
    private String code;

}
