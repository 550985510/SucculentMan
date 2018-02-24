package com.tangdou.succulent.manager.service.staff.impl;

import com.tangdou.succulent.manager.bean.staff.StaffRole;
import com.tangdou.succulent.manager.mapper.StaffRoleMapper;
import com.tangdou.succulent.manager.service.staff.StaffRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 木叶丸
 * @date 2018/2/23
 */
@Service
public class StaffRoleServiceImpl implements StaffRoleService {

    @Resource
    private StaffRoleMapper staffRoleMapper;

    /**
     * 查询全部员工角色
     *
     * @return 员工角色列表
     */
    @Override
    public List<StaffRole> findAll() {
        List<StaffRole> list = staffRoleMapper.selectAll();
        return list;
    }

    /**
     * 通过id修改角色信息
     *
     * @param staffRole id及修改内容
     */
    @Override
    public void updateById(StaffRole staffRole) {
        staffRoleMapper.updateById(staffRole);
    }
}
