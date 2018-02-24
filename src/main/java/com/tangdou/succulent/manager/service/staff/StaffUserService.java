package com.tangdou.succulent.manager.service.staff;

import com.github.pagehelper.PageInfo;
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

    /**
     * 分页查询员工信息
     * @param staffUser 员工查询条件
     * @return 员工分页信息
     */
    PageInfo<StaffUser> findList(StaffUser staffUser);

    /**
     * 通过id修改员工角色
     * @param staffUser id及员工角色信息
     */
    void updateRoleById(StaffUser staffUser);
}
