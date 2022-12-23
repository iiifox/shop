package pers.iiifox.shop.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.iiifox.shop.user.service.AddressService;

/**
 * @author 田章
 * @description 收货地址 前端控制器
 * @createDate 2022/12/23 8:18
 */
@Tag(name = "AddressController", description = "用户地址模块")
@RestController
@RequestMapping("/api/address/v1")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Operation(summary = "根据地址id获取地址信息")
    @GetMapping("/find/{address_id}")
    public Object getOneAddress(@PathVariable("address_id") long addressId) {
        return addressService.getOneAddress(addressId);
    }

}
