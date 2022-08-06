package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.User;

public interface UserService {

    /**
     * 获取前台用户的信息
     *
     * @return 统一返回
     */
    ResponseResult<?> userInfo();

    /**
     * 后台获取用户信息
     *
     * @return 统一返回
     */
    ResponseResult<?> adminUserInfo();

    /**
     * 更新用户信息
     *
     * @param user  用户
     * @return 统一返回
     */
    ResponseResult<?> updateUserInfo(User user);

    /**
     * 注册用户
     *
     * @param user 用户
     * @return 统一返回
     */
    ResponseResult<?> register(User user);
}
