package com.tangdou.succulent.manager.service.statistics.impl;

import com.tangdou.succulent.manager.service.statistics.StatisticsService;
import com.tangdou.succulent.manager.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Months;

import java.util.*;

/**
 * 主页统计相关操作
 *
 * @author 木叶丸
 * @date 2018/1/9
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private UserService userService;


    /**
     * 统计本月的用户注册增长曲线
     * <p>
     * 默认缓存10分钟
     *
     * @return
     */
    @Override
    public Map<String, List> countUserRegisterDays() {
        Map<String, List> result = new HashMap();
        //取今年第一个月
        LocalDate start = LocalDate.now().withDayOfMonth(1);
        LocalDate end = LocalDate.now();
        int days = Days.daysBetween(start, end).getDays();
        List<String> xAxis = new LinkedList<>();
        List<Integer> series = new LinkedList<>();
        for (int i = 0; i <= days; i++) {
            LocalDate localDate = start.plusDays(i);
            Integer count = userService.countByDateRange(localDate.toDate(), localDate.plusDays(1).toDate());
            xAxis.add(localDate.toString("yyyy-MM-dd"));
            series.add(count);
        }
        result.put("xAxis", xAxis);
        result.put("series", series);
        return result;
    }

    /**
     * 统计按月的用户注册增长曲线
     * <p>
     * 默认缓存10分钟
     *
     * @return
     */
    @Override
    public Map<String, List> countUserRegisterMonths() {
        Map<String, List> result = new HashMap();
        //取今年第一个月的第一天
        LocalDate minLocalDate = LocalDate.now().withMonthOfYear(1).dayOfMonth().withMinimumValue();
        //获取本月最后一天
        LocalDate lastLocalDate = LocalDate.now().dayOfMonth().withMaximumValue();
        //计算月份数
        int months = Months.monthsBetween(minLocalDate, lastLocalDate).getMonths();
        //Echart参数值
        List<String> xAxis = new LinkedList<>();
        List<Integer> series = new LinkedList<>();
        for (int i = 0; i <= months; i++) {
            LocalDate localDate = minLocalDate.plusMonths(i);
            Integer count = userService.countByDateRange(localDate.toDate(), localDate.plusMonths(1).toDate());
            xAxis.add(localDate.toString("yyyy-MM"));
            series.add(count);
        }
        result.put("xAxis", xAxis);
        result.put("series", series);
        return result;
    }
}
