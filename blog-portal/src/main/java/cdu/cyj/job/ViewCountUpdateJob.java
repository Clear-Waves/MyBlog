package cdu.cyj.job;

import cdu.cyj.constants.SystemConstants;
import cdu.cyj.dao.ArticleDao;
import cdu.cyj.domain.entity.Article;
import cdu.cyj.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewCountUpdateJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleDao articleDao;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void updateViewCount() {
        // 读取redis最新数据
        Map<String, Integer> viewCountMap = redisCache.getCacheMap(SystemConstants.ARTICLE_VIEWCOUNT_KEY);
        // 写入数据库
        List<Article> articleList = viewCountMap
                .entrySet()
                .stream()
                .map(entry -> new Article().setId(Integer.valueOf(entry.getKey())).setViewCount(entry.getValue()))
                .collect(Collectors.toList());
        articleDao.updateViewCountBatch(articleList);
    }

}
