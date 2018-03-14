package com.tangdou.succulent.manager.bean.staff.enums;

import lombok.Getter;

/**
 * 员工在职状态
 * @author 木叶丸
 * @date 2018/3/14
 */
@Getter
public enum OnTheJobStatus {

    /**
     * 员工在职状态
     */
    ON_THE_JOB_STATUS(1, "在职"),
    LEAVE_JOB_STATUS(2, "请假"),
    VACATION_JOB_STATUS(3, "休假"),
    QUIT_JOB_STATUS(4, "离职"),

    OTHER(0, "other");

    private Integer key;
    private String value;

    OnTheJobStatus(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static OnTheJobStatus getEnumByKey(Integer key) {
        for (OnTheJobStatus item : OnTheJobStatus.values()) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return OTHER;
    }

    public static OnTheJobStatus getEnumByName(String name) {
        for (OnTheJobStatus item : OnTheJobStatus.values()) {
            if (item.toString().equals(name)) {
                return item;
            }
        }
        return OTHER;
    }
}
