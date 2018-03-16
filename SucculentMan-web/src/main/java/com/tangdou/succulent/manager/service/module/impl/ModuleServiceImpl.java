package com.tangdou.succulent.manager.service.module.impl;

import com.tangdou.succulent.manager.bean.module.Module;
import com.tangdou.succulent.manager.mapper.ModuleMapper;
import com.tangdou.succulent.manager.service.module.ModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 论坛模块相关
 * @author 木叶丸
 * @date 2018/3/16
 */
@Service
public class ModuleServiceImpl implements ModuleService {

    @Resource
    private ModuleMapper moduleMapper;

    /**
     * 查询全部模块列表信息
     *
     * @return 模块列表信息
     */
    @Override
    public List<Module> findAll() {
        return moduleMapper.selectAll();
    }

    /**
     * 修改模块信息
     *
     * @param module 模块信息
     */
    @Override
    public void updateById(Module module) {
        moduleMapper.updateById(module);
    }

    /**
     * 逻辑删除模块
     *
     * @param module 模块id
     */
    @Override
    public void deleteById(Module module) {
        module.setDeleted(1);
        moduleMapper.updateById(module);
    }
}
