package cdu.cyj.controller;

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
    public ResponseResult<?> listLink(Link link, Integer pageNum, Integer pageSize) {
        return linkService.listLink(link, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseResult<?> getLink(@PathVariable Integer id) {
        return linkService.getLinkDetail(id);
    }

    @PostMapping
    public ResponseResult<?> addLink(@RequestBody @Valid LinkAddDto linkAddDto) {
        return linkService.addLink(linkAddDto);
    }

    @PutMapping
    public ResponseResult<?> updateLink(@RequestBody @Valid LinkUpdateDto linkUpdateDto) {
        return linkService.updateLink(linkUpdateDto);
    }

    @DeleteMapping("/{ids}")
    public ResponseResult<?> deleteLink(@PathVariable List<Integer> ids) {
        return linkService.deleteLink(ids);
    }

    @PutMapping("/changeLinkStatus")
    public ResponseResult<?> changeLinkStatus(@RequestBody Map<String, Integer> map) {
        return linkService.updateLink(map.get("id"), map.get("status"));
    }

}
