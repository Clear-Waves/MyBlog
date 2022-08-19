package cdu.cyj.controller;

import cdu.cyj.annotation.SystemLog;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@Api(tags = "文件上传")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping
    @SystemLog(businessName = "上传图片")
    @ApiOperation(value = "上传图片")
    public ResponseResult<?> uploadImg(MultipartFile img) {
        return uploadService.uploadImg(img);
    }

}
