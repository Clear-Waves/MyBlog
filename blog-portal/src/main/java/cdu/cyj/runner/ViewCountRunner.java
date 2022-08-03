package cdu.cyj.runner;

import cdu.cyj.constants.SystemConstants;
import cdu.cyj.dao.ArticleDao;
import cdu.cyj.domain.entity.Article;
import cdu.cyj.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {

        // 查询各个文章的浏览量
        List<Article> articleList = articleDao.queryAll();

        Map<String, Integer> map = articleList
                .stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), Article::getViewCount));
        // 存入redis （articleId: viewCount）
        redisCache.setCacheMap(SystemConstants.ARTICLE_VIEWCOUNT_KEY, map);
    }
}
