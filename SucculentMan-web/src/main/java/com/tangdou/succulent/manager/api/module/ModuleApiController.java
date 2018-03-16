package com.tangdou.succulent.manager.api.module;

import com.tangdou.succulent.manager.bean.common.ResponseResult;
import com.tangdou.succulent.manager.bean.common.RestResultEnum;
import com.tangdou.succulent.manager.bean.module.Module;
import com.tangdou.succulent.manager.bean.staff.StaffUser;
import com.tangdou.succulent.manager.config.AdminSecurityConfig;
import com.tangdou.succulent.manager.service.module.ModuleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseResult<List<Module>> findAll() {
        List<Module> list = moduleService.findAll();
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
}
