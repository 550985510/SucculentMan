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

    /**
     * 逻辑删除角色
     * @param staffRole id及逻辑删除状态
     */
    void deleteById(StaffRole staffRole);

    /**
     * 新增权限角色
     * @param staffRole 权限角色信息
     */
    void add(StaffRole staffRole);
}
