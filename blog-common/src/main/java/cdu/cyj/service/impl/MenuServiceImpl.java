package cdu.cyj.service.impl;

import cdu.cyj.dao.MenuDao;
import cdu.cyj.dao.RoleDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.Menu;
import cdu.cyj.domain.entity.User;
import cdu.cyj.domain.vo.MenuTreeVo;
import cdu.cyj.domain.vo.RouterMetaVo;
import cdu.cyj.domain.vo.RouterVo;
import cdu.cyj.service.MenuService;
import cdu.cyj.utils.BeanCopyUtils;
import cdu.cyj.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RoleDao roleDao;

    @Transactional
    @Override
    public ResponseResult<?> getRouters() {
        // 查询根菜单
        List<Menu> rootMenu = menuDao.queryAllByStatusAndVisibleAndParent(0, 1, -1);
        // 封装到vo
        List<RouterVo> routerVoList = BeanCopyUtils.copyBeanList(rootMenu, RouterVo.class);

        // 查询对应用户角色拥有的菜单权限
        // 1.查询当前用户对应的角色
        User user = SecurityUtils.getLoginUser().getUser();
        if (user.getId() == 1) {
            // id == 1: 超级管理员不受限制
            routerVoList = routerVoList.stream()
                    .map(routerVo -> routerVo.setChildren(getChildren(routerVo.getId())))
                    .map(routerVo -> routerVo.setMeta(BeanCopyUtils.copyBean(routerVo, RouterMetaVo.class)))
                    .filter(routerVo -> routerVo.getChildren().size() != 0)
                    .collect(Collectors.toList());
        } else {
            // 2.查询角色对应的menuId
            List<Integer> roleIdList = roleDao.queryIdsByUserId(user.getId());
            List<Integer> menuIds = menuDao.queryIdByRoleIds(roleIdList);
            // 3.过滤
            // 只返回用户有权限的菜单

            // 使用stream流装入children
            routerVoList = routerVoList.stream()
                    .map(routerVo -> routerVo.setChildren(getChildren(routerVo.getId(), menuIds)))
                    .map(routerVo -> routerVo.setMeta(BeanCopyUtils.copyBean(routerVo, RouterMetaVo.class)))
                    .filter(routerVo -> routerVo.getChildren().size() != 0)
                    .collect(Collectors.toList());
        }

        // 使用map进行封装
        Map<String, Object> map = new HashMap<>();
        map.put("routers", routerVoList);

        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult<?> getTree() {
        // 查询根菜单
        List<Menu> rootMenu = menuDao.queryAllByStatusAndVisibleAndParent(0, 1, -1);
        // 封装到vo
        List<MenuTreeVo> menuTreeVos = BeanCopyUtils.copyBeanList(rootMenu, MenuTreeVo.class);

        for (int i = 0; i < rootMenu.size(); i++) {
            menuTreeVos.get(i).setLabel(rootMenu.get(i).getTitle());
        }

        // 使用stream流装入children
        menuTreeVos = menuTreeVos.stream()
                .map(menuTreeVo -> menuTreeVo.setChildren(getMenuChildren(menuTreeVo.getId())))
                .collect(Collectors.toList());

        return ResponseResult.okResult(menuTreeVos);
    }

    @Override
    public ResponseResult<?> getTreeByRoleId(Integer rid) {

        // 获取菜单树结构
        ResponseResult<?> tree = getTree();
        Object data = tree.getData();
        // 获取当前角色的可用菜单
        List<Integer> menuIds = menuDao.queryAllByRoleId(rid);

        // 封装返回
        HashMap<String, Object> map = new HashMap<>();
        map.put("menus", data);
        map.put("checkedKeys", menuIds);
        return ResponseResult.okResult(map);

    }

    private List<RouterVo> getChildren(Integer rootId, List<Integer> menuIdList) {
        List<Menu> childrenMenu = menuDao.queryAllByStatusAndVisibleAndParent(0, 1, rootId);
        List<RouterVo> routerVos = BeanCopyUtils.copyBeanList(childrenMenu, RouterVo.class);
        return routerVos.stream()
                .filter(routerVo -> menuIdList.contains(routerVo.getId()))
                .map(routerVo -> routerVo.setMeta(BeanCopyUtils.copyBean(routerVo, RouterMetaVo.class)))
                .collect(Collectors.toList());
    }

    private List<RouterVo> getChildren(Integer rootId) {
        List<Menu> childrenMenu = menuDao.queryAllByStatusAndVisibleAndParent(0, 1, rootId);
        List<RouterVo> routerVos = BeanCopyUtils.copyBeanList(childrenMenu, RouterVo.class);
        return routerVos.stream()
                .map(routerVo -> routerVo.setMeta(BeanCopyUtils.copyBean(routerVo, RouterMetaVo.class)))
                .collect(Collectors.toList());
    }

    private List<MenuTreeVo> getMenuChildren(Integer rootId) {
        List<Menu> childrenMenu = menuDao.queryAllByStatusAndVisibleAndParent(0, 1, rootId);
        // 封装到vo
        List<MenuTreeVo> menuTreeVos = BeanCopyUtils.copyBeanList(childrenMenu, MenuTreeVo.class);

        for (int i = 0; i < childrenMenu.size(); i++) {
            menuTreeVos.get(i).setLabel(childrenMenu.get(i).getTitle());
        }

        return menuTreeVos;
    }
}
