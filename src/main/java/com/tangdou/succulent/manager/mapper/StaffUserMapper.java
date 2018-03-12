package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.bean.staff.StaffUser;

import java.util.List;

/**
 * 员工相关
 * @author cuihang
 * @date 2018/2/13
 */
public interface StaffUserMapper {

    /**
     * 登陆
     * @param staffUser 账号密码
     * @return 员工信息
     */
    StaffUser selectForLogin(StaffUser staffUser);

    /**
     * 员工列表
     * @param staffUser 查询条件
     * @return 员工列表信息
     */
    List<StaffUser> selectList(StaffUser staffUser);

    /**
     * 通过id修改员工角色
     * @param staffUser id及员工角色信息
     */
    void updateRoleById(StaffUser staffUser);

    /**
     * 添加员工
     * @param staffUser 员工信息
     */
    void insert(StaffUser staffUser);

    /**
     * 通过账号查找用户（拿到混合加密盐值判断登陆）
     *
     * @param staffUser 账号
     * @return 用户信息
     */
    StaffUser selectByUserName(StaffUser staffUser);
}
