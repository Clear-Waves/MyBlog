package cdu.cyj.controller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouterController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/getRouters")
    public ResponseResult<?> getRouters() {
        return menuService.getRouters();
    }

}
