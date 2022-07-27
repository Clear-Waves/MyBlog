package cdu.cyj.contorller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult<?> hotArticleList() {
        return articleService.getHotArticleList();
    }

    @GetMapping("/articleList")
    public ResponseResult<?> articleList(Integer pageNum, Integer pageSize, Integer categoryId) {

        return articleService.articleList(pageNum, pageSize, categoryId);

    }

    @GetMapping("/{id}")
    public ResponseResult<?> article(@PathVariable Integer id) {
        return articleService.getArticle(id);
    }

}
