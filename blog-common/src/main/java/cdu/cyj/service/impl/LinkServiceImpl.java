package cdu.cyj.service.impl;

import cdu.cyj.constants.SystemConstants;
import cdu.cyj.dao.LinkDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.Link;
import cdu.cyj.domain.vo.LinkVo;
import cdu.cyj.service.LinkService;
import cdu.cyj.utils.BeanCopyUtils;
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
}
