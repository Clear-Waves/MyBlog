package cdu.cyj.controller;

import cdu.cyj.annotation.SystemLog;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
@Api(tags = "友链", description = "友链相关接口")
public class LinkController {

    @Autowired
    LinkService linkService;

    @GetMapping("/getAllLink")
    @SystemLog(businessName = "友链列表")
    @ApiOperation(value = "友链列表", notes = "查询所有友链")
    public ResponseResult<?> getAllLink() {

        return linkService.getAllLink();

    }

}
