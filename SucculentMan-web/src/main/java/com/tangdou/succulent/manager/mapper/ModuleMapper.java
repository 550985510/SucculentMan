package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.bean.module.Module;

import java.util.List;

/**
 * 论坛模块相关
 * @author 木叶丸
 * @date 2018/3/16
 */
public interface ModuleMapper {

    /**
     * 查询全部模块信息
     * @return 模块列表信息
     */
    List<Module> selectAll();

    /**
     * 通过id修改
     * @param module 修改内容
     */
    void updateById(Module module);

    /**
     * 插入一条模块信息
     * @param module 模块信息
     */
    void insert(Module module);
}