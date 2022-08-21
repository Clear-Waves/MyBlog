package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.TagAddDto;
import cdu.cyj.domain.dto.TagUpdateDto;
import cdu.cyj.domain.entity.Tag;

import java.util.List;

public interface TagService {

    /**
     * 获取所有tag对象列表
     *
     * @return 统一返回结果
     */
    ResponseResult<?> tagList();

    /**
     * 使用对象查询所有列表
     *
     * @param tag 查询对象
     * @return 统一返回结果
     */
    ResponseResult<?> tagList(Tag tag, Integer pagNum, Integer pageSize);

    /**
     * 获取tag详情
     *
     * @param id tagID
     * @return 统一返回对象
     */
    ResponseResult<?> getTag(Integer id);

    /**
     * 添加tag
     *
     * @param tagAddDto 标签DTO
     * @return 统一返回对象
     */
    ResponseResult<?> addTag(TagAddDto tagAddDto);

    /**
     * 通过id列表批量删除
     *
     * @param ids id列表
     * @return 统一返回对象
     */
    ResponseResult<?> deleteByIds(List<Integer> ids);

    /**
     * 更新标签
     *
     * @param tagUpdateDto 标签跟新dto
     * @return 统一返回对象
     */
    ResponseResult<?> updateTag(TagUpdateDto tagUpdateDto);
}
