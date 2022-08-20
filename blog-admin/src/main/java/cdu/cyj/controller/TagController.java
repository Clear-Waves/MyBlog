package cdu.cyj.controller;

import cdu.cyj.annotation.SystemLog;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.TagAddDto;
import cdu.cyj.domain.entity.Tag;
import cdu.cyj.service.TagService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/tag")
@Api(tags = "标签")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("listAllTag")
    @PreAuthorize("hasAuthority('content:write')")
    @SystemLog(businessName = "获取标签列表")
    public ResponseResult<?> tagList() {
        return tagService.tagList();
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('content:tag')")
    @SystemLog(businessName = "获取标签列表")
    public ResponseResult<?> listTag(Tag tag, Integer pageNum, Integer pageSize) {
        return tagService.tagList(tag, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('content:tag')")
    @SystemLog(businessName = "获取标签详情")
    public ResponseResult<?> getTag(@PathVariable Integer id) {
        return tagService.getTag(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('content:tag')")
    @SystemLog(businessName = "新增标签")
    public ResponseResult<?> addTag(@RequestBody TagAddDto tagAddDto) {
        return tagService.addTag(tagAddDto);
    }

    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAuthority('content:tag')")
    @SystemLog(businessName = "批量删除标签")
    public ResponseResult<?> deleteTag(@PathVariable List<Integer> ids) {
        return tagService.deleteByIds(ids);
    }

}
