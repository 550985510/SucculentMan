package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.bean.staff.StaffRole;

import java.util.List;

/**
 * 员工角色相关
 * @author 木叶丸
 * @date 2018/2/23
 */
public interface StaffRoleMapper {

    /**
     * 查询全部员工角色
     * @return 员工角色列表
     */
    List<StaffRole> selectAll();

    /**
     * 通过id修改角色信息
     * @param staffRole id及修改内容
     */
    void updateById(StaffRole staffRole);
}
