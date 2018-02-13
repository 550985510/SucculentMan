package com.tangdou.succulent.manager.service.staff.impl;

import com.tangdou.succulent.manager.bean.staff.StaffUser;
import com.tangdou.succulent.manager.mapper.StaffUserMapper;
import com.tangdou.succulent.manager.service.staff.StaffUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        StaffUser info = staffUserMapper.selectForLogin(staffUser);
        return info;
    }
}
