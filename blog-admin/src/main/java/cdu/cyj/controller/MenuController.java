package cdu.cyj.controller;

import cdu.cyj.annotation.SystemLog;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/menu")
@PreAuthorize("hasAuthority('system:role')")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/treeselect")
    @SystemLog(businessName = "获取树形菜单信息")
    public ResponseResult<?> treeSelect() {
        return menuService.getTree();
    }

    @GetMapping("/roleMenuTreeselect/{rid}")
    @SystemLog(businessName = "获取树形菜单信息")
    public ResponseResult<?> roleMenuTreeSelect(@PathVariable Integer rid) {
        return menuService.getTreeByRoleId(rid);
    }




}
