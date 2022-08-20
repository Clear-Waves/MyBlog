package cdu.cyj.controller;

import cdu.cyj.annotation.SystemLog;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.service.MenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "路由")
public class RouterController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/getRouters")
    @SystemLog(businessName = "获取路由")
    public ResponseResult<?> getRouters() {
        return menuService.getRouters();
    }

}
