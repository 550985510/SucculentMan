package com.tangdou.succulent.manager.service.staff.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdou.succulent.manager.bean.staff.StaffUser;
import com.tangdou.succulent.manager.mapper.StaffUserMapper;
import com.tangdou.succulent.manager.service.staff.StaffUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工相关操作
 * @author cuihang
 * Created by 木叶丸 on 2018/2/13
 */
@Service
public class StaffUserServiceImpl implements StaffUserService {

    @Resource
    private StaffUserMapper staffUserMapper;

    /**
     * 登陆
     *
     * @param staffUser 员工对象(账号密码)
     * @return 员工对象
     */
    @Override
    public StaffUser findForLogin(StaffUser staffUser) {
        return staffUserMapper.selectForLogin(staffUser);
    }

    /**
     * 分页查询员工信息
     *
     * @param staffUser 员工查询条件
     * @return 员工分页信息
     */
    @Override
    public PageInfo<StaffUser> findList(StaffUser staffUser) {
        PageHelper.startPage(staffUser.getPage(), staffUser.getPageSize());
        List<StaffUser> list = staffUserMapper.selectList(staffUser);
        return new PageInfo<>(list);
    }

    /**
     * 通过id修改员工角色
     *
     * @param staffUser id及员工角色信息
     */
    @Override
    public void updateRoleById(StaffUser staffUser) {
        staffUserMapper.updateRoleById(staffUser);
    }

    /**
     * 添加员工
     *
     * @param staffUser 员工信息
     */
    @Override
    public void add(StaffUser staffUser) {
        staffUserMapper.insert(staffUser);
    }

    /**
     * 通过账号查找用户（拿到混合加密盐值判断登陆）
     *
     * @param staffUser 账号
     * @return 用户信息
     */
    @Override
    public StaffUser findByUserName(StaffUser staffUser) {
        return staffUserMapper.selectByUserName(staffUser);
    }
}
