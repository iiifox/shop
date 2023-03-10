package pers.iiifox.shop.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.iiifox.shop.result.R;
import pers.iiifox.shop.user.pojo.entity.UserDO;
import pers.iiifox.shop.user.pojo.request.UserRegisterRequest;

/**
* @author tzh
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2022-12-23 04:32:53
*/
public interface UserService extends IService<UserDO> {

    R register(UserRegisterRequest userRegisterRequest);
}
