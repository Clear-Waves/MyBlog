package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.User;

public interface AdminLoginService {

    /**
     *  登录
     *
     * @param user user参数
     * @return 统一返回对象
     */
    ResponseResult<?> login(User user);

    /**
     * 退出登录
     *
     * @return 统一返回对象
     */
    ResponseResult<?> logout();

}
