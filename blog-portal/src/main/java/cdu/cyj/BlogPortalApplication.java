package cdu.cyj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "cdu.cyj.dao")
public class BlogPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogPortalApplication.class, args);
    }

}
