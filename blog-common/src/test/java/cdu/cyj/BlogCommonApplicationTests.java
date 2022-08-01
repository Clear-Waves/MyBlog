package cdu.cyj;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.InputStream;

//@SpringBootTest
class BlogCommonApplicationTests {

//    @Autowired
//    private ArticleService service;

//    @Autowired
//    private ArticleDao articleDao;

    @Test
    void contextLoads() {
    }

    @Test
    void test2() {
//        System.out.println(articleDao.queryAll());
    }
    
    @Test
    void testQiniuYun() {

//        //构造一个带指定 Region 对象的配置类
//        Configuration cfg = new Configuration(Region.autoRegion());
//        //...其他参数参考类注释
//        UploadManager uploadManager = new UploadManager(cfg);
//        //...生成上传凭证，然后准备上传
//        String accessKey = "dhHUdry64TNgJTOxeR1uYndKoNYmZ7YlxsNiCZUd";
//        String secretKey = "wBJdWuMEeJT3rXV-F3GNTJiFPzmOyX5JbeOWOKtB";
//        String bucket = "myblogoss";
//        //默认不指定key的情况下，以文件内容的hash值作为文件名
//        String key = "test.png";
//        try {
//            // 创建文件流
//            InputStream stream = new FileInputStream("C:\\Users\\21968\\Pictures\\QQ截图20220414102236.png");
//
//            Auth auth = Auth.create(accessKey, secretKey);
//            String upToken = auth.uploadToken(bucket);
//            try {
//                Response response = uploadManager.put(stream,key,upToken,null, null);
//                //解析上传成功的结果
//                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//                System.out.println(putRet.key);
//                System.out.println(putRet.hash);
//            } catch (QiniuException ex) {
//                Response r = ex.response;
//                System.err.println(r.toString());
//                try {
//                    System.err.println(r.bodyString());
//                } catch (QiniuException ex2) {
//                    //ignore
//                }
//            }
//        } catch (Exception ex) {
//            //ignore
//        }

    }

}
