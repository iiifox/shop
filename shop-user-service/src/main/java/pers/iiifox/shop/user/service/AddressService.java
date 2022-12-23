package pers.iiifox.shop.user.service;

import pers.iiifox.shop.user.pojo.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author tzh
* @description 针对表【address(收货地址表)】的数据库操作Service
* @createDate 2022-12-23 04:32:52
*/
public interface AddressService extends IService<Address> {

    /**
     * 根据收货地址表主键获取收货地址信息
     *
     * @param addressId 收货地址表主键
     * @return 收货地址信息
     */
    Object detail(long addressId);
}
