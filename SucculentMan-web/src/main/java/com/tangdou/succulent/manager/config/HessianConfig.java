package com.tangdou.succulent.manager.config;


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

    /**
     * 用户管理调用功能
     *
     * @return
     */
    @Bean(name = "/hessian/userServiceApi")
    public HessianServiceExporter userServiceApi() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(userServiceApi);
        exporter.setServiceInterface(UserServiceApi.class);
        return exporter;
    }

}
