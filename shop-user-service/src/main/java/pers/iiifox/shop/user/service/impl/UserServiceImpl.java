package pers.iiifox.shop.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.iiifox.shop.exception.BizException;
import pers.iiifox.shop.result.ErrorCodeEnum;
import pers.iiifox.shop.result.R;
import pers.iiifox.shop.user.mapper.UserMapper;
import pers.iiifox.shop.user.pojo.entity.UserDO;
import pers.iiifox.shop.user.pojo.request.UserRegisterRequest;
import pers.iiifox.shop.user.service.NotifyService;
import pers.iiifox.shop.user.service.UserService;

import java.time.LocalDateTime;

/**
 * @author tzh
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2022-12-23 04:32:53
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO>
        implements UserService {

    @Autowired
    private NotifyService notifyService;

    @Override
    public R register(UserRegisterRequest userRegisterRequest) {
        if (!notifyService.checkCode(userRegisterRequest.getEmail(), userRegisterRequest.getCode())) {
            throw new BizException(ErrorCodeEnum.USER_ERROR_A0130);
        }

        UserDO user = new UserDO();
        BeanUtils.copyProperties(userRegisterRequest, user);
        user.setCreateTime(LocalDateTime.now());
        // 设置密码 TODO
        // user.setPassword();

        // 账号唯一性检查
        if (!checkUnique(user.getEmail())) {
            throw new BizException(ErrorCodeEnum.USER_ERROR_A0111);
        }

        if (save(user)) {
            // 新用户注册成功,初始化信息,发放福利等
            userRegisterInitTask(user);
        }
        return null;
    }

    /**
     * 校验用户账号唯一性
     */
    private boolean checkUnique(String email) {
        return false;
    }

    /**
     * 用户注册成功后的初始化信息
     */
    private void userRegisterInitTask(UserDO user) {

    }
}




