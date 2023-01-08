package pers.iiifox.shop.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.iiifox.shop.user.pojo.entity.AddressDO;
import pers.iiifox.shop.user.service.AddressService;
import pers.iiifox.shop.user.mapper.AddressMapper;
import org.springframework.stereotype.Service;

/**
 * @author 田章
 * @description 针对表【address(收货地址表)】的数据库操作Service实现
 * @createDate 2022-12-23 04:32:52
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, AddressDO>
        implements AddressService {

    @Override
    public AddressDO getAddress(long addressId) {
        return getById(addressId);
    }
}




