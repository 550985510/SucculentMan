package com.tangdou.succulent.manager.service.staff.impl;

import com.tangdou.succulent.manager.bean.staff.Department;
import com.tangdou.succulent.manager.mapper.DepartmentMapper;
import com.tangdou.succulent.manager.service.staff.DepartmentService;
import org.springframework.stereotype.Service;

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

    /**
     * 查询全部部门信息
     *
     * @return 部门列表信息
     */
    @Override
    public List<Department> findAll() {
        List<Department> list = departmentMapper.selectAll();
        return list;
    }
}
