package com.tangdou.succulent.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 后台管理系统入口
 * @author 木叶丸
 */
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@MapperScan("com.tangdou.succulent.manager.mapper")
public class SucculentManManagerStarter extends SpringBootServletInitializer {

    /**
     * war包发布到tomcat需要增加SpringBootServletInitializer子类，并覆盖configure方法或直接继承后覆写
     * @param application application
     * @return application
     */
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(SucculentManManagerStarter.class);
    }

    public static void main(String[] args){
        SpringApplication.run(SucculentManManagerStarter.class,args);
    }
}
