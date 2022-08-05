package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;

public interface TagService {

    /**
     * 获取所有tag对象列表
     *
     * @return 统一返回结果
     */
    ResponseResult<?> tageList();
}
