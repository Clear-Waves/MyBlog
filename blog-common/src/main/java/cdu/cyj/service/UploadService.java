package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    /**
     * 上传图片
     *
     * @param img 图片文件
     * @return 统一返回
     */
    ResponseResult<?> uploadImg(MultipartFile img);

}
