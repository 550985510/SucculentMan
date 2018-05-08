<#import "/spring.ftl" as s>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <title>多肉达人后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<@s.url '/css/jquery.pagination.css'/>">
<#include '../include/baselink.ftl'>
    <link href="<@s.url '/css/fileinput.css'/>" rel="stylesheet" type="text/css">
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
                    <span class="panel-title"> 订单列表</span>
                </div>
                <div class="panel-body">
                    <div class="well">
                        <form class="form-inline">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class="input-group-addon btn-default" for="title_input">用户真实姓名</label>
                                    <input id="title_input" type="text" v-model="searchInfo.userName"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <label class="input-group-addon btn-default" for="status_select">订单状态</label>
                                    <select id="status_select" type="text" v-model="searchInfo.status"
                                            class="form-control">
                                        <option value=null>全部</option>
                                        <option value="0">待处理</option>
                                        <option value="1">已发货</option>
                                        <option value="2">已退回</option>
                                        <option value="3">交易完成</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group input-group">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-default" v-on:click="search">
                                        <span class="fa fa-search"></span> 查询
                                    </button>
                                </span>
                            </div>
                        </form>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>用户真实姓名</th>
                                <th>收货地址</th>
                                <th>商品名称</th>
                                <th>商品数量</th>
                                <th>订单金额</th>
                                <th>订单状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="order in orders">
                                <td>{{order.id}}</td>
                                <td>{{order.userName}}</td>
                                <td>{{order.location}}</td>
                                <td>{{order.goodsName}}</td>
                                <td>{{order.goodsNum}}</td>
                                <td>{{order.amount}}</td>
                                <td>
                                    <label v-if="order.status === 0" class="label label-warning">待处理</label>
                                    <label v-if="order.status === 1" class="label label-info">已发货</label>
                                    <label v-if="order.status === 2" class="label label-danger">已退货</label>
                                    <label v-if="order.status === 3" class="label label-success">交易完成</label>
                                </td>
                                <td>
                                    <button v-if="order.status === 0" class="btn btn-info" v-on:click="up(order)">
                                        确认发货
                                    </button>
                                    <button v-if="order.status === 2" class="btn btn-danger" v-on:click="down(order)">
                                        确认退货
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-center" colspan="20" v-if="orders.length == 0">没有数据 ！</td>
                            </tr>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="20">
                                    <div class="table-responsive">
                                        <div id="pageMenu"></div>
                                    </div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            <#--<div class="panel-footer">-->

            <#--</div>-->
            </div>
        </div>
    </section>
</div>
<#include '../include/footer.ftl'/>
<script src="<@s.url '/js/jquery.pagination-1.2.7.js'/>"></script>
<!-- Charts JS -->
<script>
    var app = new Vue({
        el: '#main',
        data: {
            searchInfo: {
                userName: '',
                status: '',
                page: 1,
                pageSize: 20
            },
            orders: []
        },
        created: function () {
            this.searchInfo.page = 1;
            $('#pageMenu').page('destroy');
            this.query();
        },
        watch: {
            "searchInfo.page": function () {
                this.query();
            }
        },
        mounted: function () {

        },
        methods: {
            search: function () {
                this.searchInfo.page = 1;
                $('#pageMenu').page('destroy');//销毁分页
                this.query();
            },
            query: function () {
                var url = contentPath + "/api/order/list";
                this.$http.post(url, this.searchInfo).then(function (response) {
                    this.orders = response.data.data.list;
                    var temp = this;
                    $("#pageMenu").page({//加载分页
                        total: response.data.data.total,
                        pageSize: response.data.data.pageSize,
                        firstBtnText: '首页',
                        lastBtnText: '尾页',
                        prevBtnText: '上一页',
                        nextBtnText: '下一页',
                        showInfo: true,
                        showJump: true,
                        jumpBtnText: '跳转',
                        infoFormat: '{start} ~ {end}条，共{total}条'
                    }, response.data.data.page)//传入请求参数
                            .on("pageClicked", function (event, pageIndex) {
                                temp.searchInfo.page = pageIndex + 1;
                            }).on('jumpClicked', function (event, pageIndex) {
                        temp.searchInfo.page = pageIndex + 1;
                    });
                }, function (error) {
                    swal(error.body.msg);
                });
            },
            up: function (order) {
                var that = this;
                swal({
                    title: "是否确认发货？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定！",
                    cancelButtonText: "取消！",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        order.status = 1;
                        var url = contentPath + "/api/order/edit";
                        that.$http.post(url, order).then(function (response) {
                            swal("操作成功！", "", "success");
                            that.query();
                        }, function (error) {
                            swal(error.body.msg);
                        });
                    } else {
                        swal("取消！", "", "error");
                    }
                });
            },
            down: function (order) {
                var that = this;
                swal({
                    title: "是否确认退货？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定！",
                    cancelButtonText: "取消！",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        order.status =  3;
                        var url = contentPath + "/api/order/edit";
                        that.$http.post(url, order).then(function (response) {
                            swal("操作成功！", "", "success");
                            that.query();
                        }, function (error) {
                            swal(error.body.msg);
                        });
                    } else {
                        swal("取消！", "", "error");
                    }
                });
            }
        }
    });
</script>
</body>
</html>
