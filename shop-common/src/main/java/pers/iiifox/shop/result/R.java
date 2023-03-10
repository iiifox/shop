package pers.iiifox.shop.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.iiifox.shop.exception.BizException;

/**
 * @author 田章
 * @description 统一返回结果集类
 * @date 2022/12/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "R", description = "统一返回结果集类")
public class R {

    /**
     * 错误码：错误产生来源 + 四位数字编号。
     * <p>
     * 说明：错误产生来源分为 A/B/C，<br/>
     * A 表示错误来源于用户，比如参数错误，用户安装版本过低，用户支付超时等问题；<br/>
     * B 表示错误来源于当前系统，往往是业务逻辑出错，或程序健壮性差等问题；<br/>
     * C 表示错误来源于第三方服务，比如 CDN 服务出错，消息投递超时等问题；<br/>
     * 四位数字编号从 0001 到 9999，大类之间的步长间距预留 100，
     * </p>
     */
    @Schema(name = "code", description = "错误码")
    private String code;

    @Schema(name = "message", description = "错误信息描述")
    private String message;

    @Schema(name = "data", description = "响应数据")
    private Object data;

    /**
     * 成功，仅返回数据
     */
    public static R ok(Object data) {
        return new R("00000", null, data);
    }

    /**
     * 成功，不返回数据
     */
    public static R ok() {
        return R.ok(null);
    }

    /**
     * 传入枚举，返回信息
     */
    public static R error(BizException bizException) {
        return R.custorError(bizException.getCode(), bizException.getMessage());
    }

    public static R custorError(String errorCode, String message) {
        return new R(errorCode, message, null);
    }
}
