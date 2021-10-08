package com.hist.teachermanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Yangluxin
 * @date 2020/7/22 21:53
 */
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.hist.teachermanage.api.mapper")
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }
}
