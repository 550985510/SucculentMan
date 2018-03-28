package com.tangdou.succulent.manager.api.staff;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.bean.common.RestResultEnum;
import com.tangdou.succulent.manager.bean.staff.ChangePassWord;
import com.tangdou.succulent.manager.bean.staff.Department;
import com.tangdou.succulent.manager.bean.staff.StaffRole;
import com.tangdou.succulent.manager.bean.staff.StaffUser;
import com.tangdou.succulent.manager.bean.staff.enums.OnTheJobStatus;
import com.tangdou.succulent.manager.config.AdminSecurityConfig;
import com.tangdou.succulent.manager.service.staff.StaffRoleService;
import com.tangdou.succulent.manager.service.staff.StaffUserService;
import com.tangdou.succulent.manager.util.SecurityPasswordUtils;
import com.tangdou.succulent.manager.bean.common.ResponseResult;
import com.tangdou.succulent.manager.service.staff.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 员工相关
 * @author cuihang
 * Created by 木叶丸 on 2018/2/13
 */
@RestController
@RequestMapping("/api/staff")
public class StaffApiController {

    private StaffUser currentUser = new StaffUser();

    @Resource
    private StaffUserService staffUserService;

    @Resource
    private StaffRoleService staffRoleService;

    @Resource
    private DepartmentService departmentService;

    /**
     * 登陆判断
     * @param username 用户名
     * @param password 密码
     * @param session  登陆信息保存
     * @return 登陆操作状态
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestParam("username")String username, @RequestParam("password")String password, HttpSession session) {
        StaffUser staffUser = new StaffUser();
        staffUser.setUserName(username);
        //通过账号拿到混合加密盐值
        StaffUser user = staffUserService.findByUserName(staffUser);
        if (user == null) {
            return new ResponseResult(RestResultEnum.LOGIN_ERROR);
        }
        String salt = user.getSalt();
        //根据盐值和用户输入密码加密
        String passphrase = SecurityPasswordUtils.getPassphrase(salt, password);
        staffUser.setSalt(salt);
        staffUser.setPassWord(passphrase);
        //判断登陆
        StaffUser info = staffUserService.findForLogin(staffUser);
        if (info != null && !info.getStatus().equals(OnTheJobStatus.QUIT_JOB_STATUS.getKey()) && info.getRoleId() != 0) {
            session.setAttribute(AdminSecurityConfig.SESSION_KEY,info);
            return new ResponseResult(RestResultEnum.SUCCESS);
        }
        return new ResponseResult(RestResultEnum.LOGIN_ERROR);
    }

    @PostMapping("/logout")
    public ResponseResult logout(HttpSession session) {
        session.removeAttribute(AdminSecurityConfig.SESSION_KEY);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 员工列表
     * @param staffUser 员工信息
     * @return 员工列表信息
     */
    @PostMapping("/userList")
    public ResponseResult<PageInfo<StaffUser>> findUserList(@RequestBody StaffUser staffUser) {
        PageInfo<StaffUser> pageInfo = staffUserService.findList(staffUser);
        return new ResponseResult<>(pageInfo);
    }

    @GetMapping("/personal")
    public ResponseResult<StaffUser> detail(Integer id) {
        StaffUser user = staffUserService.findById(id);
        return new ResponseResult<>(user);
    }

    @PostMapping("/editUser")
    public ResponseResult editUser(@RequestBody StaffUser staffUser, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        staffUser.setModifiedBy(currentUser.getRealName());
        staffUserService.update(staffUser);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    @PostMapping("/changePassWord")
    public ResponseResult changePassWord(@RequestBody ChangePassWord changePassWord, HttpSession session) {
        StaffUser staffUser = new StaffUser();
        StaffUser user = staffUserService.findById(changePassWord.getId());
        String salt = user.getSalt();
        //根据盐值和用户输入密码加密
        String passphrase = SecurityPasswordUtils.getPassphrase(salt, changePassWord.getPassWord());
        staffUser.setSalt(salt);
        staffUser.setPassWord(passphrase);
        staffUser.setUserName(user.getUserName());
        //判断登陆
        StaffUser info = staffUserService.findForLogin(staffUser);
        if (info == null) {
            return new ResponseResult(RestResultEnum.WRONG_PASSWORD);
        } else {
            currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
            passphrase = SecurityPasswordUtils.getPassphrase(salt, changePassWord.getNewPassWord());
            staffUser.setPassWord(passphrase);
            staffUser.setId(changePassWord.getId());
            staffUser.setModifiedBy(currentUser.getRealName());
            staffUserService.update(staffUser);
            return new ResponseResult(RestResultEnum.SUCCESS);
        }
    }

    /**
     * 小编列表
     * @return 小编列表信息
     */
    @PostMapping("/authorList")
    public ResponseResult<List<StaffUser>> findUserList() {
        List<StaffUser> list = staffUserService.findAuthorList();
        return new ResponseResult<>(list);
    }

    /**
     * 角色权限列表
     * @return 角色权限列表信息
     */
    @PostMapping("/roleList")
    public ResponseResult<List<StaffRole>> findAllRole() {
        List<StaffRole> list = staffRoleService.findAll();
        return new ResponseResult<>(list);
    }

    /**
     * 部门列表
     * @return 部门列表信息
     */
    @PostMapping("/deptList")
    public ResponseResult<List<Department>> findAllDept() {
        List<Department> list = departmentService.findAll();
        return new ResponseResult<>(list);
    }

    /**
     * 逻辑删除部门
     * @param department 部门信息
     * @param session 登陆状态
     * @return 操作状态
     */
    @PostMapping("/deleteDept")
    public ResponseResult deleteDept(@RequestBody Department department, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        department.setModifiedBy(currentUser.getRealName());
        departmentService.deleteById(department);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 修改部门
     * @param department 修改信息
     * @param session 登陆信息
     * @return 操作状态
     */
    @PostMapping("/editDept")
    public ResponseResult editDept(@RequestBody  Department department, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        department.setModifiedBy(currentUser.getRealName());
        departmentService.updateById(department);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 添加部门
     * @param department 部门信息
     * @param session 登陆信息
     * @return 操作状态
     */
    @PostMapping("/addDept")
    public ResponseResult addDept(@RequestBody Department department, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        department.setModifiedBy(currentUser.getRealName());
        department.setCreatedBy(currentUser.getRealName());
        departmentService.add(department);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 添加员工
     * @param staffUser 员工信息
     * @param session 登陆状态
     * @return 操作状态
     */
    @PostMapping("/addUser")
    public ResponseResult addUser(@RequestBody StaffUser staffUser, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        staffUser.setModifiedBy(currentUser.getRealName());
        staffUser.setCreatedBy(currentUser.getRealName());
        staffUser.setUserName(staffUser.getMobile());
        //密码随机设置
        String salt = SecurityPasswordUtils.getSalt();
        String password = "admin";
        String passphrase = SecurityPasswordUtils.getPassphrase(salt, password);
        staffUser.setSalt(salt);
        staffUser.setPassWord(passphrase);
        staffUserService.add(staffUser);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 设置员工权限角色
     * @param staffUser 员工信息
     * @param session 登陆信息
     * @return 操作状态
     */
    @PostMapping("/setRole")
    public ResponseResult setRole(@RequestBody StaffUser staffUser, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        staffUser.setModifiedBy(currentUser.getRealName());
        staffUserService.updateRoleById(staffUser);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 修改权限角色
     * @param staffRole 修改信息
     * @param session 登陆信息
     * @return 操作状态
     */
    @PostMapping("/editRole")
    public ResponseResult editRole(@RequestBody StaffRole staffRole, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        staffRole.setModifiedBy(currentUser.getRealName());
        staffRoleService.updateById(staffRole);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 逻辑删除权限角色
     * @param staffRole 权限角色信息
     * @param session 登陆信息
     * @return 操作状态
     */
    @PostMapping("/deleteRole")
    public ResponseResult deleteRole(@RequestBody StaffRole staffRole, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        staffRole.setModifiedBy(currentUser.getRealName());
        staffRoleService.deleteById(staffRole);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

    /**
     * 添加权限角色
     * @param staffRole 权限角色信息
     * @param session 登陆信息
     * @return 操作状态
     */
    @PostMapping("/addRole")
    public ResponseResult addRole(@RequestBody StaffRole staffRole, HttpSession session) {
        currentUser = (StaffUser) session.getAttribute(AdminSecurityConfig.SESSION_KEY);
        staffRole.setCreatedBy(currentUser.getRealName());
        staffRole.setModifiedBy(currentUser.getRealName());
        staffRoleService.add(staffRole);
        return new ResponseResult(RestResultEnum.SUCCESS);
    }

}
