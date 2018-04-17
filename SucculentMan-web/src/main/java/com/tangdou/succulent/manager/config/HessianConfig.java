package com.tangdou.succulent.manager.config;


import com.tangdou.succulent.manager.api.article.ArticleServiceApi;
import com.tangdou.succulent.manager.api.user.UserFollowServiceApi;
import com.tangdou.succulent.manager.api.user.UserServiceApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

import javax.annotation.Resource;

/**
 * 配置Hessian的服务接口
 *
 * @author 木叶丸
 */
@Configuration
public class HessianConfig {

    @Resource
    private UserServiceApi userServiceApi;

    @Resource
    private ArticleServiceApi articleServiceApi;

    @Resource
    private UserFollowServiceApi userFollowServiceApi;

    /**
     * 用户管理调用功能
     *
     * @return exporter
     */
    @Bean(name = "/hessian/userServiceApi")
    public HessianServiceExporter userServiceApi() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(userServiceApi);
        exporter.setServiceInterface(UserServiceApi.class);
        return exporter;
    }

    /**
     * 文章管理调用功能
     *
     * @return exporter
     */
    @Bean(name = "/hessian/articleServiceApi")
    public HessianServiceExporter articleServiceApi() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(articleServiceApi);
        exporter.setServiceInterface(ArticleServiceApi.class);
        return exporter;
    }

    /**
     * 用户关注调用功能
     *
     * @return exporter
     */
    @Bean(name = "/hessian/userFollowServiceApi")
    public HessianServiceExporter userFollowServiceApi() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(userFollowServiceApi);
        exporter.setServiceInterface(UserFollowServiceApi.class);
        return exporter;
    }

}
