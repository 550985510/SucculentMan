package com.tangdou.succulent.manager.bean.staff;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工
 * @author cuihang
 * @date 2018/2/13
 */

@Data
public class StaffUser implements Serializable {

    /**
     * 员工id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 混合加密盐值
     */
    private String salt;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 1:在职,2:请假,3:休假,4:离职
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 最后修改时间
     */
    private Date modifiedTime;

    /**
     * 最后修改人
     */
    private String modifiedBy;

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer pageSize;

}
