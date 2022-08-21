package cdu.cyj.service.impl;

import cdu.cyj.dao.TagDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.TagAddDto;
import cdu.cyj.domain.dto.TagUpdateDto;
import cdu.cyj.domain.entity.Tag;
import cdu.cyj.domain.vo.AdminTagVo;
import cdu.cyj.domain.vo.PageVo;
import cdu.cyj.domain.vo.TagVo;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.service.TagService;
import cdu.cyj.utils.AutoFilledUtils;
import cdu.cyj.utils.BeanCopyUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public ResponseResult<?> tagList(Tag tag, Integer pagNum, Integer pageSize) {
        // 分页
        PageHelper.startPage(pagNum, pageSize);
        // 调用dao查询
        List<Tag> tags = tagDao.queryAllByObject(tag);
        // 封装返回
        PageInfo<Tag> tagPageInfo = new PageInfo<>(tags);
        List<AdminTagVo> adminTagVos = BeanCopyUtils.copyBeanList(tags, AdminTagVo.class);
        PageVo pageVo = new PageVo(adminTagVos, tagPageInfo.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<?> getTag(Integer id) {
        // 调用dao查询
        Tag tag = tagDao.queryById(id);
        // 封装返回
        AdminTagVo tagVo = BeanCopyUtils.copyBean(tag, AdminTagVo.class);
        return ResponseResult.okResult(tagVo);
    }

    @Override
    public ResponseResult<?> addTag(TagAddDto tagAddDto) {
        // BeanCopy
        Tag tag = BeanCopyUtils.copyBean(tagAddDto, Tag.class);
        // 自动填充
        AutoFilledUtils.autoFillOnInsert(tag);
        // 调用dao进行写入
        int insert = tagDao.insert(tag);
        // 封装返回
        if (insert == 1) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResponseResult<?> deleteByIds(List<Integer> ids) {
        // 调用dao批量删除
        int delete = tagDao.deleteByIdBatch(ids);
        // 封装返回
        if (delete == ids.size()) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResponseResult<?> updateTag(TagUpdateDto tagUpdateDto) {
        // beanCopy
        Tag tag = BeanCopyUtils.copyBean(tagUpdateDto, Tag.class);
        // 自动填充
        AutoFilledUtils.autoFillOnUpdate(tag);
        // 调用dao进行更新
        int update = tagDao.update(tag);
        if (update == 1) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }
}
