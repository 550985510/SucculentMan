package com.tangdou.succulent.manager.mapper;

import com.tangdou.succulent.manager.bean.staff.StaffUser;

/**
 * @author cuihang
 * @date 2018/2/13
 */
public interface StaffUserMapper {

    StaffUser selectForLogin(StaffUser staffUser);
}
