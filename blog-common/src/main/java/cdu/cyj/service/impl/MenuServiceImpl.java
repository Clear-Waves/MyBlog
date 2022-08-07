package cdu.cyj.service.impl;

import cdu.cyj.dao.MenuDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.Menu;
import cdu.cyj.domain.vo.RouterMetaVo;
import cdu.cyj.domain.vo.RouterVo;
import cdu.cyj.service.MenuService;
import cdu.cyj.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public ResponseResult<?> getRouters() {
        // 查询根菜单
        List<Menu> rootMenu = menuDao.queryAllByStatusAndVisibleAndParent(0, 1, -1);
        // 封装到vo
        List<RouterVo> routerVoList = BeanCopyUtils.copyBeanList(rootMenu, RouterVo.class);

        // 使用stream流装入children
        routerVoList = routerVoList.stream()
                .map(routerVo -> routerVo.setChildren(getChildren(routerVo.getId())))
                .map(routerVo -> routerVo.setMeta(BeanCopyUtils.copyBean(routerVo, RouterMetaVo.class)))
                .collect(Collectors.toList());

        // 使用map进行封装
        Map<String, Object> map = new HashMap<>();
        map.put("routers", routerVoList);

        return ResponseResult.okResult(map);
    }

    private List<RouterVo> getChildren(Integer rootId) {
        List<Menu> childrenMenu = menuDao.queryAllByStatusAndVisibleAndParent(0, 1, rootId);
        List<RouterVo> routerVos = BeanCopyUtils.copyBeanList(childrenMenu, RouterVo.class);
        return routerVos.stream()
                .map(routerVo -> routerVo.setMeta(BeanCopyUtils.copyBean(routerVo, RouterMetaVo.class)))
                .collect(Collectors.toList());
    }
}
