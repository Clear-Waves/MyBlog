package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.UserAddDto;
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

    /**
     * 查询用户列表
     *
     * @param user user对象
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return 统一返回对象
     */
    ResponseResult<?> listUser(User user, Integer pageNum, Integer pageSize);

    /**
     * 通过id查询用户详情
     *
     *
     * @param id 用户id
     * @return 统一返回对象
     */
    ResponseResult<?> userDetail(Integer id);

    /**
     * 添加用户
     *
     * @param userAddDto 添加用户dto
     * @return 统一返回对象
     */
    ResponseResult<?> addUser(UserAddDto userAddDto);
}
