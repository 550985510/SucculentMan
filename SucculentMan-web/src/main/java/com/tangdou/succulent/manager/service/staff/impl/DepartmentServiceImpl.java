package com.tangdou.succulent.manager.service.staff.impl;

import com.tangdou.succulent.manager.bean.staff.Department;
import com.tangdou.succulent.manager.mapper.DepartmentMapper;
import com.tangdou.succulent.manager.mapper.StaffUserMapper;
import com.tangdou.succulent.manager.service.staff.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门相关
 * @author 木叶丸
 * @date 2018/2/24
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private StaffUserMapper staffUserMapper;

    /**
     * 查询全部部门信息
     *
     * @return 部门列表信息
     */
    @Override
    public List<Department> findAll() {
        return departmentMapper.selectAll();
    }

    /**
     * 逻辑删除部门
     *
     * @param department 部门id
     */
    @Override
    public void deleteById(Department department) {
        department.setDeleted(1);
        departmentMapper.updateById(department);
    }

    /**
     * 修改部门信息
     * 修改部门表的同时修改员工表关联的角色信息（保持数据一致性）
     * @param department 部门信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(Department department) {
        departmentMapper.updateById(department);
        staffUserMapper.updateDeptByDeptId(department.getId(), department.getName());
    }

    /**
     * 添加部门
     *
     * @param department 部门信息
     */
    @Override
    public void add(Department department) {
        departmentMapper.insert(department);
    }
}
