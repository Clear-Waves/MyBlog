package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.User;

public interface UserService {

    ResponseResult<?> userInfo();

    ResponseResult<?> updateUserInfo(User user);

    ResponseResult<?> register(User user);
}
