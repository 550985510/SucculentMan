package com.tangdou.succulent.manager.service.module;

import com.tangdou.succulent.manager.bean.module.Module;

import java.util.List;

/**
 * 论坛模块相关
 * @author 木叶丸
 * @date 2018/3/16
 */
public interface ModuleService {

    /**
     * 查询全部模块列表信息
     * @return 模块列表信息
     */
    List<Module> findAll();

    /**
     * 修改模块信息
     * @param module 模块信息
     */
    void updateById(Module module);

    /**
     * 逻辑删除模块
     * @param module 模块id
     */
    void deleteById(Module module);

    /**
     * 添加模块
     * @param module 模块信息
     */
    void add(Module module);
}
