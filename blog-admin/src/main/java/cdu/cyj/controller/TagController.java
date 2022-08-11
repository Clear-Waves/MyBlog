package cdu.cyj.controller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.TagAddDto;
import cdu.cyj.domain.entity.Tag;
import cdu.cyj.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/tag")
public class    TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("listAllTag")
    public ResponseResult<?> tagList() {
        return tagService.tagList();
    }

    @GetMapping("/list")
    public ResponseResult<?> listTag(Tag tag, Integer pageNum, Integer pageSize) {
        return tagService.tagList(tag, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseResult<?> getTag(@PathVariable Integer id) {
        return tagService.getTag(id);
    }

    @PostMapping
    public ResponseResult<?> addTag(@RequestBody TagAddDto tagAddDto) {
        return tagService.addTag(tagAddDto);
    }

    @DeleteMapping("/{ids}")
    public ResponseResult<?> deleteTag(@PathVariable List<Integer> ids) {
        return tagService.deleteByIds(ids);
    }

}
