package pers.iiifox.shop.user.component;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * @author 田章
 * @description 腾讯云 COS 属性配置类
 * @date 2022/12/26
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
@PropertySource("classpath:qcloud.properties")
@Setter
@ConfigurationProperties(prefix = "cos")
public class COSComponent {

    private String secretId;
    private String secretKey;
    /**
     * 存储桶对应的的地域名
     */
    private String regionName;
    /**
     * 存储桶名。格式为 BucketName-APPID
     */
    private String bucketName;

    /**
     * 文件上传到 Qcloud COS
     *
     * @param file 待上传的文件
     * @param key  要上传的到存储桶中的唯一标识
     * @throws IOException          获取文件流失败
     * @throws CosClientException   如果客户端在发出请求或处理响应时遇到任何错误。
     * @throws CosServiceException  如果 Qcloud COS 在处理请求时出现任何错误。
     * @throws InterruptedException 如果此线程在等待上传完成时被中断。
     */
    public String upload(MultipartFile file, String key) throws IOException, InterruptedException {
        // 创建 TransferManager 实例，这个实例用来后续调用高级接口
        TransferManager transferManager = new TransferManager(
                // 创建 COSClient 实例，这个实例用来后续调用请求
                new COSClient(
                        // 密钥
                        new BasicCOSCredentials(secretId, secretKey),
                        // 其他配置
                        new ClientConfig(new Region(regionName)) {{
                            // 设置请求协议, http 或者 https
                            // 5.6.53 及更低的版本，建议设置使用 https 协议；5.6.54 及更高版本，默认使用了 https。
                            setHttpProtocol(HttpProtocol.https);
                        }}),
                // 若不传入线程池，默认 TransferManager 中会生成一个单线程的线程池。
                Executors.newFixedThreadPool(32)
        );

        UploadResult uploadResult = transferManager
                // 返回一个异步结果Upload
                .upload(new PutObjectRequest(bucketName, key, file.getInputStream(), new ObjectMetadata() {{
                    // 上传的流如果能够获取准确的流长度，则推荐一定填写 content-length
                    setContentLength(file.getSize());
                }}))
                // 可同步地调用 waitForUploadResult 方法等待上传完成，成功返回UploadResult, 失败抛出异常
                .waitForUploadResult();

        // 确定本进程不再使用 transferManager 实例之后，关闭之
        transferManager.shutdownNow(true);

        return new StringBuilder("https://")
                .append(uploadResult.getBucketName())
                .append(".cos.")
                .append(regionName)
                .append(".myqcloud.com/")
                .append(uploadResult.getKey()).toString();
    }
}
