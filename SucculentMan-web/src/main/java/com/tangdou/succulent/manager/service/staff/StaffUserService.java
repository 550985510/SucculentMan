package com.tangdou.succulent.manager.service.staff;

import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.bean.staff.StaffUser;

import java.util.List;

/**
 * 员工相关操作
 * @author 木叶丸
 * Created by 木叶丸 on 2018/2/13
 */
public interface StaffUserService {

    /**
     * 登陆
     * @param staffUser 员工对象
     * @return 员工对象
     */
    StaffUser findForLogin(StaffUser staffUser);

    /**
     * 分页查询员工信息
     * @param staffUser 员工查询条件
     * @return 员工分页信息
     */
    PageInfo<StaffUser> findList(StaffUser staffUser);

    /**
     * 通过id修改员工角色
     * @param staffUser id及员工角色信息
     */
    void updateRoleById(StaffUser staffUser);

    /**
     * 添加员工
     * @param staffUser 员工信息
     */
    void add(StaffUser staffUser);

    /**
     * 通过账号查找用户（拿到混合加密盐值判断登陆）
     * @param staffUser 账号
     * @return 用户信息
     */
    StaffUser findByUserName(StaffUser staffUser);

    /**
     * 查询小编列表
     * @return 小编列表信息
     */
    List<StaffUser> findAuthorList();

    /**
     * 通过id查询员工信息
     * @param id 员工编号
     * @return 员工信息
     */
    StaffUser findById(Integer id);

    /**
     * 修改员工信息
     * @param staffUser 修改内容
     */
    void update(StaffUser staffUser);
}
