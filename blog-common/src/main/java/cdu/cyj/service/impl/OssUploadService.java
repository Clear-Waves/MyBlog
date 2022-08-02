package cdu.cyj.service.impl;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.service.UploadService;
import cdu.cyj.utils.PathUtils;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service("ossUploadService")
@Data
@ConfigurationProperties(prefix = "oss")
public class OssUploadService implements UploadService {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String domain;

    @Override
    public ResponseResult<?> uploadImg(MultipartFile img) {

        String originalFilename = img.getOriginalFilename();

        assert originalFilename != null;
        if (!originalFilename.endsWith(".png") && !originalFilename.endsWith(".jpg")) {
            return ResponseResult.errorResult(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }

        // 生成path
        String path = PathUtils.generateFilePath(originalFilename);
        // oss储存
        String url = ossUpload(img, path);

        return ResponseResult.okResult(url);
    }

    private String ossUpload(MultipartFile imgFile, String filePath) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = filePath;
        try {
            // 创建文件流
            InputStream stream = imgFile.getInputStream();

            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(stream, key, upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return domain + filePath;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }

        return null;
    }
}
