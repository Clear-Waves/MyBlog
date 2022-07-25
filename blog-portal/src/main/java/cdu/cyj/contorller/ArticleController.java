package cdu.cyj.contorller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.vo.HotArticleVo;
import cdu.cyj.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult<List<HotArticleVo>> hotArticleList() {
        return articleService.getHotArticleList();
    }

}
