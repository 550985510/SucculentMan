package com.tangdou.succulent.manager.service.statistics;

import java.util.List;
import java.util.Map;

/**
 * 主页统计相关操作
 * @author 木叶丸
 * @date 2018/1/9
 */
public interface StatisticsService {

    /**
     * 统计本月按天的用户注册增长曲线
     *
     * @return
     */
    Map<String, List> countUserRegisterDays();


    /**
     * 统计按月的用户注册增长曲线
     *
     * @return
     */
    Map<String, List> countUserRegisterMonths();

}
