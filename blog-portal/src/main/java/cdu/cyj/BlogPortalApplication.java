package cdu.cyj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
@EnableTransactionManagement
@MapperScan(basePackages = "cdu.cyj.dao")
public class BlogPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogPortalApplication.class, args);
    }

}
