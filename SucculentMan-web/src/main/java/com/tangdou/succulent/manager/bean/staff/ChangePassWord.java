package com.tangdou.succulent.manager.bean.staff;

import lombok.Data;

/**
 * 修改密码
 * @author 木叶丸
 * @date 2018/3/28
 */
@Data
public class ChangePassWord {

    /**
     * 员工编号
     */
    private Integer id;

    /**
     * 原密码
     */
    private String passWord;

    /**
     * 新密码
     */
    private String newPassWord;
}
