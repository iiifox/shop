package pers.iiifox.shop.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pers.iiifox.shop.exception.BizException;
import pers.iiifox.shop.result.ErrorCodeEnum;
import pers.iiifox.shop.user.component.COSComponent;
import pers.iiifox.shop.user.service.FileService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author 田章
 * @description 文件操作 Service 实现类
 * @date 2022/12/26
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private COSComponent cos;

    @Value("${qcloud.cos.bucket-name}")
    private String bucketName;

    @Override
    public String upload(MultipartFile file) {
        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new BizException("请先选择要上传的文件");
        }
        // 文件后缀名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String key = new StringBuilder("user/")
                .append(DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now()))
                .append('/')
                .append(UUID.randomUUID().toString().replaceAll("-", ""))
                .append(suffix).toString();
        try {
            return cos.upload(file, key);
        } catch (Exception e) {
            throw new BizException(ErrorCodeEnum.USER_ERROR_A0700);
        }
    }
}
