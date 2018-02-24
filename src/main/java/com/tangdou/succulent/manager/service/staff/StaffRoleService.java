package com.tangdou.succulent.manager.service.staff;

import com.tangdou.succulent.manager.bean.staff.StaffRole;

import java.util.List;

/**
 * 员工角色相关操作
 * @author 木叶丸
 * @date 2018/2/23
 */
public interface StaffRoleService {

    /**
     * 查询全部员工角色
     * @return 员工角色列表
     */
    List<StaffRole> findAll();

    /**
     * 通过id修改角色信息
     * @param staffRole id及修改内容
     */
    void updateById(StaffRole staffRole);
}
