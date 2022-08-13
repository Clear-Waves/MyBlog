package cdu.cyj.controller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/listAllRole")
    public ResponseResult<?> listAllRole() {
        return roleService.listAllRole();
    }

}
