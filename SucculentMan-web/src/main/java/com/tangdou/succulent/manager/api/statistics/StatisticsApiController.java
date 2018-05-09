package com.tangdou.succulent.manager.api.statistics;

import com.tangdou.succulent.manager.api.common.ResponseResult;
import com.tangdou.succulent.manager.service.statistics.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主页统计相关操作
 * @author 木叶丸
 * @date 2018/1/7
 */
@RestController
@RequestMapping("/api/statistics")
public class StatisticsApiController {

    @Resource
    private StatisticsService statisticsService;

    /**
     * 主页订单统计信息
     */
    @PostMapping("/user")
    public ResponseResult findUserStatistics() {
        Map data = new HashMap();
        Map<String, List> days = statisticsService.countUserRegisterDays();
        //用户增长统计 按月统计
        Map<String, List> months = statisticsService.countUserRegisterMonths();
        data.put("days", days);
        data.put("months", months);
        return new ResponseResult(data);
    }

}
