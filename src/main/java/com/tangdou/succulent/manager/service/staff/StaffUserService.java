package com.tangdou.succulent.manager.service.staff;

import com.tangdou.succulent.manager.bean.staff.StaffUser;

/**
 * 员工相关操作
 * @author 木叶丸
 * Created by 木叶丸 on 2018/2/13
 */
public interface StaffUserService {

    /**
     * 登陆
     * @param staffUser 员工对象
     * @return 员工对象
     */
    StaffUser findForLogin(StaffUser staffUser);
}
