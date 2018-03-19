package com.tangdou.succulent.manager.service.staff.impl;

import com.tangdou.succulent.manager.bean.staff.StaffRole;
import com.tangdou.succulent.manager.mapper.StaffUserMapper;
import com.tangdou.succulent.manager.service.staff.StaffRoleService;
import com.tangdou.succulent.manager.mapper.StaffRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private StaffUserMapper staffUserMapper;

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
     * 修改角色表的同时修改员工表关联的角色信息（保持数据一致性）
     * @param staffRole id及修改内容
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(StaffRole staffRole) {
        staffRoleMapper.updateById(staffRole);
        staffUserMapper.updateRoleByRoleId(staffRole.getId(), staffRole.getName());
    }

    /**
     * 逻辑删除角色
     *
     * @param staffRole id及逻辑删除状态
     */
    @Override
    public void deleteById(StaffRole staffRole) {
        staffRole.setDeleted(1);
        staffRoleMapper.updateById(staffRole);
    }

    /**
     * 新增权限角色
     *
     * @param staffRole 权限角色信息
     */
    @Override
    public void add(StaffRole staffRole) {
        staffRoleMapper.insert(staffRole);
    }
}
