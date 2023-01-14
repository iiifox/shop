package pers.iiifox.shop.user.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 *
 * @TableName user
 */
@TableName(value = "user")
@Data
public class UserDO implements Serializable {
    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * BCrypt加密后的密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * False表示女，True表示男
     */
    private Boolean gender;

    /**
     * 积分
     */
    private Integer points;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}