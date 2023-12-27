package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author Sean
 * @email wanghaitao21@aisino.com
 * @create_time 2023-08-16 01:06
 */

@Service
public class OssServiceImpl implements OssService {

    /**
     *
     * @param file
     * @return
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
//        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        String keyid = ConstantPropertiesUtils.KEY_ID;
        String keysecret = ConstantPropertiesUtils.KEY_SECRET;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
//        String objectName = "exampledir/exampleobject.txt";
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
//        String filePath= "D:\\localpath\\examplefile.txt";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);

        try {
            // 获取上传文件的输入流
            InputStream inputStream = file.getInputStream();

            // 获取文件的名称
            String originalFilename = file.getOriginalFilename();
            // 给文件配上随机数，防止文件名重复导致图片被覆盖
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            // 把文件进行分类存储 2023/08/15/01.jpg
            // 获取当前日期  将日期变为路径格式
            String datePath = new DateTime().toString("yyyy/MM/dd");
            originalFilename = datePath + "/" + uuid + "_" + originalFilename;

            // 创建PutObjectRequest对象。
            /**
             * bucketName bucket的名称
             * originalFilename 文件路径+文件名称
             * inputStream   输入流
             */
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, originalFilename, inputStream);
            // 创建PutObject请求。
            ossClient.putObject(putObjectRequest);


            // 把上传的到阿里云oss的路径需要自己拼接出来然后返回
            String url = "https://" + bucketName + "." + endpoint + "/" + originalFilename;


//            https://edu-sean.oss-cn-beijing.aliyuncs.com/Bn3HML_V4Xg.jpg
//            https://edu-sean.oss-cn-beijing.aliyuncs.com/Bn3HML_V4Xg.jpg
//            System.out.println(url);
            return url;
        } catch (Exception e){
            e.printStackTrace();
        }
//        catch (OSSException oe) {
//            System.out.println("Caught an OSSException, which means your request made it to OSS, "
//                    + "but was rejected with an error response for some reason.");
//            System.out.println("Error Message:" + oe.getErrorMessage());
//            System.out.println("Error Code:" + oe.getErrorCode());
//            System.out.println("Request ID:" + oe.getRequestId());
//            System.out.println("Host ID:" + oe.getHostId());
//        } catch ( ClientException  ce) { //
//            System.out.println("Caught an ClientException, which means the client encountered "
//                    + "a serious internal problem while trying to communicate with OSS, "
//                    + "such as not being able to access the network.");
//            System.out.println("Error Message:" + ce.getMessage());
//        }
        finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }
}
