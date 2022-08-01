package cdu.cyj.service.impl;

import cdu.cyj.dao.UserDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.User;
import cdu.cyj.domain.vo.UserInfoVo;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.service.UserService;
import cdu.cyj.utils.BeanCopyUtils;
import cdu.cyj.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseResult<?> userInfo() {

        // 获取、查询
        Integer userId = SecurityUtils.getUserId();
        User user = userDao.queryById(userId);

        // 封装
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult<?> updateUserInfo(User user) {

        // 调用dao更新
        int count = userDao.update(user);
        // 返回结果
        if (count == 1) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }
}
