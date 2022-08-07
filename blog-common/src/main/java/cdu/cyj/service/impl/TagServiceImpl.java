package cdu.cyj.service.impl;

import cdu.cyj.dao.TagDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.Tag;
import cdu.cyj.domain.vo.TagVo;
import cdu.cyj.service.TagService;
import cdu.cyj.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public ResponseResult<?> tagList() {
        List<Tag> tagList = tagDao.queryAllByStatus(0);
        List<TagVo> tagVoList = BeanCopyUtils.copyBeanList(tagList, TagVo.class);

        return ResponseResult.okResult(tagVoList);
    }
}
