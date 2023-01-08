package pers.iiifox.shop.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.iiifox.shop.result.R;
import pers.iiifox.shop.user.pojo.request.UserRegisterRequest;
import pers.iiifox.shop.user.service.FileService;
import pers.iiifox.shop.user.service.UserService;

/**
 * @author 田章
 * @description 用户接口 前端控制器
 * @date 2022/12/26
 */
@Tag(name = "UserController", description = "用户模块")
@RestController
@RequestMapping("/api/user/v1")
public class UserController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @Operation(
            summary = "用户头像上传",
            requestBody = @RequestBody(content = @Content(
                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                    schemaProperties = @SchemaProperty(
                            schema = @Schema(name = "avatar", description = "头像", type = "file")
                    )
            )),
            responses = @ApiResponse(description = "头像路径")
    )
    @PostMapping(value = "/upload")
    public R uploadHeaderImg(@RequestPart("avatar") MultipartFile avatar) {
        return R.ok(fileService.upload(avatar));
    }

    @Operation(
            summary = "用户注册",
            requestBody = @RequestBody(description = "用户注册请求实体类")
    )
    @PostMapping("/register")
    public R register(@RequestBody UserRegisterRequest userRegisterRequest) {
//        if (StringUtils.isBlank(userRegisterRequest.getCode())) {
//
//        }
        return R.ok(userService.register(userRegisterRequest));
    }

}
