package pers.iiifox.shop.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.iiifox.shop.user.pojo.entity.UserDO;
import pers.iiifox.shop.user.service.UserService;
import pers.iiifox.shop.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author tzh
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2022-12-23 04:32:53
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO>
    implements UserService{

}




