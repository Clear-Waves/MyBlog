package cdu.cyj.controller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.RoleAddDto;
import cdu.cyj.domain.dto.RoleUpdateDto;
import cdu.cyj.domain.entity.Role;
import cdu.cyj.service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/role")
@Validated
@Api(tags = "角色管理")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/listAllRole")
    @PreAuthorize("hasAuthority('system:user')")
    public ResponseResult<?> listAllRole() {
        return roleService.listAllRole();
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('system:role')")
    public ResponseResult<?> listRole(Role role, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return roleService.listRole(role, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:role')")
    public ResponseResult<?> getRole(@PathVariable Integer id) {
        return roleService.getRole(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('system:role')")
    public ResponseResult<?> updateRole(@RequestBody @Valid RoleUpdateDto roleUpdateDto) {
        return roleService.updateRole(roleUpdateDto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('system:role')")
    public ResponseResult<?> addRole(@RequestBody @Valid RoleAddDto roleAddDto) {
        return roleService.addRole(roleAddDto);
    }

    @PutMapping("/changeStatus")
    @PreAuthorize("hasAuthority('system:role')")
    public ResponseResult<?> changeRoleStatus(@RequestBody Map<String, Integer> map) {
        return roleService.changeRoleStatus(map);
    }

    @DeleteMapping("/{roleIds}")
    @PreAuthorize("hasAuthority('system:role')")
    public ResponseResult<?> deleteRole(@PathVariable List<Integer> roleIds) {
        return roleService.deleteRole(roleIds);
    }

}
