package cdu.cyj.contorller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    LinkService linkService;

    @GetMapping("/getAllLink")
    public ResponseResult<?> getAllLink() {

        return linkService.getAllLink();

    }

}
