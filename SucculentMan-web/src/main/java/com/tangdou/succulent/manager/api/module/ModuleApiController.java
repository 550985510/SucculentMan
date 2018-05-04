package com.tangdou.succulent.manager.api.module;

import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.api.common.RestResultEnum;
import com.tangdou.succulent.manager.bean.module.Module;
import com.tangdou.succulent.manager.bean.staff.StaffUser;
import com.tangdou.succulent.manager.config.AdminSecurityConfig;
import com.tangdou.succulent.manager.service.module.ModuleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 论坛模块相关
 * @author 木叶丸
 * @date 2018/3/16
 */
@RestController
@RequestMapping("/api/module")
public class ModuleApiController {

    private StaffUser currentUser = new StaffUser();

    @Resource
    private ModuleService moduleService;

    /**
     * 角色权限列表
     * @return 角色权限列表信息
     */
    @PostMapping("/list")
    public ResponseResult<List<Module>> findAll(Integer type) {
        if (type == 3) {
            type = null;
        }
        List<Module> list = moduleService.findAll(type);
        return new ResponseResult<>(list);
    }

    @PostMapping("/edit")
    public ResponseResult edit(@RequestBody Module module, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        module.setModifiedBy(currentUser.getRealName());
        moduleService.updateById(module);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody Module module, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        module.setModifiedBy(currentUser.getRealName());
        moduleService.deleteById(module);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 添加模块
     * @param module 模块信息
     * @param session 登陆信息
     * @return 操作状态
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody Module module, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        module.setCreatedBy(currentUser.getRealName());
        module.setModifiedBy(currentUser.getRealName());
        moduleService.add(module);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }
}
