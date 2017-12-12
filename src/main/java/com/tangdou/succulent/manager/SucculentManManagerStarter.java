package com.tangdou.succulent.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@MapperScan(basePackages = "com.tangdou.succulent.manager.mapper")
@EnableAutoConfiguration
public class SucculentManManagerStarter extends SpringBootServletInitializer {

    //war包发布到tomcat需要增加SpringBootServletInitializer子类，并覆盖configure方法或直接继承后覆写
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(SucculentManManagerStarter.class);
    }

    public static void main(String[] args){
        SpringApplication.run(com.tangdou.succulent.manager.SucculentManManagerStarter.class,args);
    }
}
