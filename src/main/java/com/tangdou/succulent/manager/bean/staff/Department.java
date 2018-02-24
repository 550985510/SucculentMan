package com.tangdou.succulent.manager.bean.staff;

import lombok.Data;

import java.util.Date;

/**
 * @author 木叶丸
 * @date 2018/2/24
 */
@Data
public class Department {

    private Integer id;

    private String name;

    private Integer deleted;

    private Date createdTime;

    private String createdBy;

    private Date modifiedTime;

    private String modifiedBy;

}
