package cdu.cyj.service.impl;

import cdu.cyj.constants.SystemConstants;
import cdu.cyj.dao.LinkDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.LinkAddDto;
import cdu.cyj.domain.dto.LinkUpdateDto;
import cdu.cyj.domain.entity.Link;
import cdu.cyj.domain.vo.AdminLinkVo;
import cdu.cyj.domain.vo.LinkVo;
import cdu.cyj.domain.vo.PageVo;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.service.LinkService;
import cdu.cyj.utils.AutoFilledUtils;
import cdu.cyj.utils.BeanCopyUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Autowired
    LinkDao linkDao;

    @Override
    public ResponseResult<?> getAllLink() {

        // 查询所有审核通过的友联
        List<Link> linkList = linkDao.queryAllByStatus(SystemConstants.LINK_APPROVED_STATUS);
        // 封装Vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(linkList, LinkVo.class);
        // 封装Response
        return ResponseResult.okResult(linkVos);

    }

    @Override
    public ResponseResult<?> listLink(Link link, Integer pageNum, Integer pageSize) {

        // 分页
        PageHelper.startPage(pageNum, pageSize);
        // 调用dao查询相关数据
        List<Link> linkList = linkDao.queryAllByObject(link);
        // 封装返回
        PageInfo<Link> linkPageInfo = new PageInfo<>(linkList);
        List<AdminLinkVo> adminLinkVos = BeanCopyUtils.copyBeanList(linkList, AdminLinkVo.class);
        PageVo pageVo = new PageVo(adminLinkVos, linkPageInfo.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<?> getLinkDetail(Integer id) {
        // 调用dao查询
        Link link = linkDao.queryById(id);
        // 封装返回
        AdminLinkVo linkVo = BeanCopyUtils.copyBean(link, AdminLinkVo.class);
        return ResponseResult.okResult(linkVo);
    }

    @Override
    public ResponseResult<?> addLink(LinkAddDto linkAddDto) {
        // 转换类
        Link link = BeanCopyUtils.copyBean(linkAddDto, Link.class);
        // 自动填充
        AutoFilledUtils.autoFillOnInsert(link);
        // 调用dao添加
        int insert = linkDao.insert(link);
        // 封装返回
        if (insert == 1) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResponseResult<?> updateLink(LinkUpdateDto linkUpdateDto) {
        // 转换类
        Link link = BeanCopyUtils.copyBean(linkUpdateDto, Link.class);
        // 自动填充
        AutoFilledUtils.autoFillOnUpdate(link);
        // 调用dao更新
        int update = linkDao.update(link);
        // 封装返回
        if (update == 1) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResponseResult<?> deleteLink(List<Integer> ids) {
        // 调用dao批量删除
        int delete = linkDao.deleteByIdBatch(ids);
        // 封装返回
        if (delete == ids.size()) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResponseResult<?> updateLink(Integer id, Integer status) {
        Link link = new Link();
        link.setId(id);
        link.setStatus(status);
        // 自动填充
        AutoFilledUtils.autoFillOnUpdate(link);
        // 调用dao更新
        int update = linkDao.update(link);
        // 封装返回
        if (update == 1) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }


}
