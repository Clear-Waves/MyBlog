package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.User;

public interface LoginService {

    ResponseResult<?> login(User user);

}
