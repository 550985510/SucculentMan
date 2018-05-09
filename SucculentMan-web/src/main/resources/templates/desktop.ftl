<#import "spring.ftl" as s>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <title>多肉达人后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<#include 'include/baselink.ftl'>
</head>
<body class="dashboard-page">
<div id="main">
    <section id="content" class="table-layout">
        <div class="tray tray-center">
            <div class="panel">
                <div class="panel-heading">
                        <span class="panel-icon">
                            <i class="fa fa-bar-chart-o"></i>
                        </span>
                    <span class="panel-title"> 按天统计用户量</span>
                </div>
                <div class="panel-body">
                    <div id="userDaysChart" style="height:400px;"></div>
                </div>
            </div>

            <div class="panel">
                <div class="panel-heading">
                        <span class="panel-icon">
                            <i class="fa fa-bar-chart-o"></i>
                        </span>
                    <span class="panel-title"> 按月统计用户量</span>
                </div>
                <div class="panel-body">
                    <div id="userMonthsChart" style="height:400px;"></div>
                </div>
            </div>
        </div>
    </section>
</div>
<#include 'include/footer.ftl'/>
<!-- Charts JS -->
<script src="<@s.url '/plugins/echarts/echarts-all.js'/>"></script>
<script>
    var app = new Vue({
        el: '#main',
        data: {
            days: {
                xAxis: [],
                series: []
            },
            months: {
                xAxis: [],
                series: []
            }
        },
        created: function () {
            this.query();
        },
        mounted: function () {

        },
        methods: {
            query: function () {
                var url = contentPath + "/api/statistics/user";
                this.$http.post(url).then(function (response) {
                    this.days = response.data.data.days;
                    this.months = response.data.data.months;
                    this.$nextTick(function () {
                        this.init();
                    })
                }, function (error) {
                    swal(error.body.msg);
                });
            },
            init: function () {

                var user_days_option = {
                    title: {
                        text: '注册用户数',
                        subtext: '按天统计用户增长数'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['新用户注册数']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            magicType: {show: true, type: ['line', 'bar']},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: this.days.xAxis,
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        }
                    ],
                    series: [
                        {
                            name: '新用户注册数',
                            type: 'line',
                            data: this.days.series,
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        }
                    ]
                };

                var user_months_option = {
                    title: {
                        text: '注册用户数',
                        subtext: '按月统计用户增长数'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['新用户注册数']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            magicType: {show: true, type: ['line', 'bar']},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: this.months.xAxis,
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}'
                            }
                        }
                    ],
                    series: [
                        {
                            name: '新用户注册数',
                            type: 'line',
                            data: this.months.series,
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        }
                    ]
                };
                // 为echarts对象加载数据
                var userDaysChart = echarts.init(document.getElementById('userDaysChart'));
                userDaysChart.setOption(user_days_option);

                var userMonthsChart = echarts.init(document.getElementById('userMonthsChart'));
                userMonthsChart.setOption(user_months_option);
            }
        }
    });
</script>
</body>
</html>
