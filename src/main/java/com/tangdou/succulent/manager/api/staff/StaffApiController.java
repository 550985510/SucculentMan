package com.tangdou.succulent.manager.api.staff;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.bean.ResponseResult;
import com.tangdou.succulent.manager.bean.RestResultEnum;
import com.tangdou.succulent.manager.bean.staff.Department;
import com.tangdou.succulent.manager.bean.staff.StaffRole;
import com.tangdou.succulent.manager.bean.staff.StaffUser;
import com.tangdou.succulent.manager.config.AdminSecurityConfig;
import com.tangdou.succulent.manager.service.staff.DepartmentService;
import com.tangdou.succulent.manager.service.staff.StaffRoleService;
import com.tangdou.succulent.manager.service.staff.StaffUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author cuihang
 * Created by 木叶丸 on 2018/2/13
 */
@RestController
@RequestMapping("/api/staff")
public class StaffApiController {

    final Logger logger = LoggerFactory.getLogger(getClass());

    private StaffUser currentUser = new StaffUser();

    @Resource
    private StaffUserService staffUserService;

    @Resource
    private StaffRoleService staffRoleService;

    @Resource
    private DepartmentService departmentService;

    @PostMapping("/login")
    public ResponseResult login(@RequestParam("username")String username, @RequestParam("password")String password, HttpSession session) {
        StaffUser staffUser = new StaffUser();
        staffUser.setUserName(username);
        staffUser.setPassWord(password);
        StaffUser info = staffUserService.findForLogin(staffUser);
        if (info != null && info.getStatus() != 3 && info.getRoleId() != 0) {
            info.setPassWord(null);
            session.setAttribute(AdminSecurityConfig.SESSION_KEY,info);
            return new ResponseResult(RestResultEnum.SUCCESS);
        }
        return new ResponseResult(RestResultEnum.ERROR);
    }

    @PostMapping("/userList")
    public ResponseResult<PageInfo<StaffUser>> findUserList(@RequestBody StaffUser staffUser) {
        PageInfo<StaffUser> pageInfo = staffUserService.findList(staffUser);
        return new ResponseResult<>(pageInfo);
    }

    @PostMapping("/roleList")
    public ResponseResult<List<StaffRole>> findAllRole() {
        List<StaffRole> list = staffRoleService.findAll();
        return new ResponseResult<>(list);
    }

    @PostMapping("/deptList")
    public ResponseResult<List<Department>> findAllDept() {
        List<Department> list = departmentService.findAll();
        return new ResponseResult<>(list);
    }

    @PostMapping("/addUser")
    public ResponseResult addUser(@RequestBody StaffUser staffUser, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        staffUser.setModifiedBy(currentUser.getRealName());
        staffUser.setCreatedBy(currentUser.getRealName());
        staffUserService.add(staffUser);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    @PostMapping("/setRole")
    public ResponseResult setRole(@RequestBody StaffUser staffUser, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        staffUser.setModifiedBy(currentUser.getRealName());
        staffUserService.updateRoleById(staffUser);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    @PostMapping("/editRole")
    public ResponseResult editRole(@RequestBody StaffRole staffRole, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        staffRole.setModifiedBy(currentUser.getRealName());
        staffRoleService.updateById(staffRole);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

}
