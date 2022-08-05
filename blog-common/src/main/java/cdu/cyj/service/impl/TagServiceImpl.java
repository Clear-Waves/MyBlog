package cdu.cyj.service.impl;

import cdu.cyj.dao.TagDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public ResponseResult<?> tageList() {
        return ResponseResult.okResult(tagDao.queryAllByStatus(0));
    }
}
