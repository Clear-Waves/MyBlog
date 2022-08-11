package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.LinkAddDto;
import cdu.cyj.domain.dto.LinkUpdateDto;
import cdu.cyj.domain.entity.Link;

import java.util.List;

public interface LinkService {

    /**
     * 前台获取已经审核通过的友链
     *
     * @return 统一返回对象
     */
    ResponseResult<?> getAllLink();

    /**
     * 查询友链列表
     * @param link 查询对象
     * @param pageNum 页码
     * @param pageSize 页大小
     *
     * @return 统一返回对象
     */
    ResponseResult<?> listLink(Link link, Integer pageNum, Integer pageSize);

    /**
     * 获取友链详细信息
     *
     * @param id 友链id
     * @return 统一返回对象
     */
    ResponseResult<?> getLinkDetail(Integer id);

    /**
     * 增加友链
     *
     * @param linkAddDto 友链DTO
     * @return 统一返回对象
     */
    ResponseResult<?> addLink(LinkAddDto linkAddDto);

    /**
     * 更新友链
     *
     * @param linkUpdateDto 友链DTO
     * @return 统一返回对象
     */
    ResponseResult<?> updateLink(LinkUpdateDto linkUpdateDto);

    /**
     * 删除友链
     *
     * @param ids 友链id列表
     * @return 统一返回对象
     */
    ResponseResult<?> deleteLink(List<Integer> ids);

    /**
     * 更新友链的状态（审核）
     *
     * @param id 友链id
     * @param status 新状态码
     * @return 统一返回对象
     */
    ResponseResult<?> updateLink(Integer id, Integer status);
}
