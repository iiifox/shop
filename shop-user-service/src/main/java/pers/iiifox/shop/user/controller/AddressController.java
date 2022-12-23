package pers.iiifox.shop.user.controller;

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
@RestController
@RequestMapping("/api/address/v1")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/find/{address_id}")
    public Object detail(@PathVariable("address_id") long addressId) {
        return addressService.detail(addressId);
    }

}
