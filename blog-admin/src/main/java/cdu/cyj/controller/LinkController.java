package cdu.cyj.controller;

import cdu.cyj.annotation.SystemLog;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.LinkAddDto;
import cdu.cyj.domain.dto.LinkUpdateDto;
import cdu.cyj.domain.entity.Link;
import cdu.cyj.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/content/link")
@PreAuthorize("hasAuthority('content:link')")
@Validated
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    @SystemLog(businessName = "获取友链列表")
    public ResponseResult<?> listLink(Link link, Integer pageNum, Integer pageSize) {
        return linkService.listLink(link, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    @SystemLog(businessName = "获取友链详情")
    public ResponseResult<?> getLink(@PathVariable Integer id) {
        return linkService.getLinkDetail(id);
    }

    @PostMapping
    @SystemLog(businessName = "新增友链")
    public ResponseResult<?> addLink(@RequestBody @Valid LinkAddDto linkAddDto) {
        return linkService.addLink(linkAddDto);
    }

    @PutMapping
    @SystemLog(businessName = "更新友链")
    public ResponseResult<?> updateLink(@RequestBody @Valid LinkUpdateDto linkUpdateDto) {
        return linkService.updateLink(linkUpdateDto);
    }

    @DeleteMapping("/{ids}")
    @SystemLog(businessName = "批量删除友链")
    public ResponseResult<?> deleteLink(@PathVariable List<Integer> ids) {
        return linkService.deleteLink(ids);
    }

    @PutMapping("/changeLinkStatus")
    @SystemLog(businessName = "更改友链信息")
    public ResponseResult<?> changeLinkStatus(@RequestBody Map<String, Integer> map) {
        return linkService.updateLink(map.get("id"), map.get("status"));
    }

}
