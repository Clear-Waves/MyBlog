package cdu.cyj.service.impl;

import cdu.cyj.dao.MenuDao;
import cdu.cyj.dao.RoleDao;
import cdu.cyj.dao.UserDao;
import cdu.cyj.domain.entity.LoginUser;
import cdu.cyj.domain.entity.Menu;
import cdu.cyj.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MenuDao menuDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.queryByUserName(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        List<Menu> menus;

        // 超级管理员特殊处理
        if (user.getId() == 1) {
            menus = menuDao.queryAllByStatus(0);
        } else {
            // 返回用户信息
            // 处理角色与权限信息
            List<Integer> roleIdList = roleDao.queryIdsByUserId(user.getId());
            List<Integer> menuIds = menuDao.queryIdByRoleIds(roleIdList);
            menus = menuDao.queryByIdBatch(menuIds);
        }


        // stream流处理权限字符
        List<String> permissions = menus.stream()
                .map(Menu::getPerms)
                .distinct()
                .collect(Collectors.toList());


        return new LoginUser(user, permissions);
    }
}
