package cdu.cyj;

import cdu.cyj.dao.ArticleDao;
import cdu.cyj.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogCommonApplicationTests {

    @Autowired
    private ArticleService service;

    @Autowired
    private ArticleDao articleDao;

    @Test
    void contextLoads() {
        System.out.println(service.queryById(1));
    }

    @Test
    void test2() {
        System.out.println(articleDao.queryAll());
    }

}
