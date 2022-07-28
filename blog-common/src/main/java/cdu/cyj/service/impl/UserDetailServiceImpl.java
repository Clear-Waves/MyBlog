package cdu.cyj.service.impl;

import cdu.cyj.dao.UserDao;
import cdu.cyj.domain.entity.LoginUser;
import cdu.cyj.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.queryByUserName(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 返回用户信息
        // TODO 处理角色与权限信息

        return new LoginUser(user);
    }
}
