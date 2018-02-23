package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.bean.staff.StaffUser;

import java.util.List;

/**
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
}
