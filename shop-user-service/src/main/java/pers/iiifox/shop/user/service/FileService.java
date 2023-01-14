package pers.iiifox.shop.user.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 田章
 * @description 文件操作 Service 接口
 * @date 2022/12/26
 */
public interface FileService {

    /**
     * 上传图片
     *
     * @param file 上传的文件(图片)对象
     * @return 上传后的图片路径
     */
    String uploadImage(MultipartFile file);

}