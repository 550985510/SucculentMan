package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.bean.staff.Department;

import java.util.List;

/**
 * 部门相关
 * @author 木叶丸
 * @date 2018/2/24
 */
public interface DepartmentMapper {

    /**
     * 查询全部部门信息
     * @return 部门列表信息
     */
    List<Department> selectAll();

    /**
     * 通过部门id修改部门信息
     * @param department 部门id及修改条件
     */
    void updateById(Department department);
}
