package cdu.cyj.controller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.RoleAddDto;
import cdu.cyj.domain.dto.RoleUpdateDto;
import cdu.cyj.domain.entity.Role;
import cdu.cyj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/listAllRole")
    public ResponseResult<?> listAllRole() {
        return roleService.listAllRole();
    }

    @GetMapping("/list")
    public ResponseResult<?> listRole(Role role, Integer pageNum, Integer pageSize) {
        return roleService.listRole(role, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseResult<?> getRole(@PathVariable Integer id) {
        return roleService.getRole(id);
    }

    @PutMapping
    public ResponseResult<?> updateRole(@RequestBody RoleUpdateDto roleUpdateDto) {
        return roleService.updateRole(roleUpdateDto);
    }

    @PostMapping
    public ResponseResult<?> addRole(@RequestBody RoleAddDto roleAddDto) {
        return roleService.addRole(roleAddDto);
    }

    @PutMapping("/changeStatus")
    public ResponseResult<?> changeRoleStatus(@RequestBody Map<String, Integer> map) {
        return roleService.changeRoleStatus(map);
    }

    @DeleteMapping("/{roleIds}")
    public ResponseResult<?> deleteRole(@PathVariable List<Integer> roleIds) {
        return roleService.deleteRole(roleIds);
    }

}
