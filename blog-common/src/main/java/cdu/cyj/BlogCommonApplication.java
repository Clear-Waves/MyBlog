package cdu.cyj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cdu.cyj.dao")
public class BlogCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogCommonApplication.class, args);
    }

}
