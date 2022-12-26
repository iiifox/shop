package pers.iiifox.shop.user.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 田章
 * @description 文件操作 Service 接口
 * @date 2022/12/26
 */
public interface FileService {

    /**
     * 文件上传
     *
     * @param file 上传的文件对象
     * @return 上传后的文件路径
     */
    String upload(MultipartFile file);

}
