package com.tangdou.succulent.manager.config;


import com.tangdou.succulent.manager.api.article.ArticleCollectionServiceApi;
import com.tangdou.succulent.manager.api.article.ArticleCommentServiceApi;
import com.tangdou.succulent.manager.api.article.ArticleServiceApi;
import com.tangdou.succulent.manager.api.post.PostCollectionServiceApi;
import com.tangdou.succulent.manager.api.post.PostCommentServiceApi;
import com.tangdou.succulent.manager.api.post.PostServiceApi;
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

    @Resource
    private ArticleCollectionServiceApi articleCollectionServiceApi;

    @Resource
    private ArticleCommentServiceApi articleCommentServiceApi;

    @Resource
    private PostServiceApi postServiceApi;

    @Resource
    private PostCollectionServiceApi postCollectionServiceApi;

    @Resource
    private PostCommentServiceApi postCommentServiceApi;

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

    /**
     * 文章收藏调用功能
     *
     * @return exporter
     */
    @Bean(name = "/hessian/articleCollectionServiceApi")
    public HessianServiceExporter articleCollectionServiceApi() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(articleCollectionServiceApi);
        exporter.setServiceInterface(ArticleCollectionServiceApi.class);
        return exporter;
    }

    /**
     * 文章评论调用功能
     *
     * @return exporter
     */
    @Bean(name = "/hessian/articleCommentServiceApi")
    public HessianServiceExporter articleCommentServiceApi() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(articleCommentServiceApi);
        exporter.setServiceInterface(ArticleCommentServiceApi.class);
        return exporter;
    }

    /**
     * 帖子管理调用功能
     *
     * @return exporter
     */
    @Bean(name = "/hessian/postServiceApi")
    public HessianServiceExporter postServiceApi() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(postServiceApi);
        exporter.setServiceInterface(PostServiceApi.class);
        return exporter;
    }

    /**
     * 帖子收藏调用功能
     *
     * @return exporter
     */
    @Bean(name = "/hessian/postCollectionServiceApi")
    public HessianServiceExporter postCollectionServiceApi() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(postCollectionServiceApi);
        exporter.setServiceInterface(PostCollectionServiceApi.class);
        return exporter;
    }

    /**
     * 帖子回帖调用功能
     *
     * @return exporter
     */
    @Bean(name = "/hessian/postCommentServiceApi")
    public HessianServiceExporter postCommentServiceApi() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(postCommentServiceApi);
        exporter.setServiceInterface(PostCommentServiceApi.class);
        return exporter;
    }

}
