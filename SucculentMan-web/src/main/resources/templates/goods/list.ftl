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
                    <span class="panel-title"> 商品列表</span>
                </div>
                <div class="panel-body">
                    <div class="well">
                        <form class="form-inline">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class="input-group-addon btn-default" for="title_input">商品名称</label>
                                    <input id="title_input" type="text" v-model="searchInfo.name"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <label class="input-group-addon btn-default" for="status_select">上架状态</label>
                                    <select id="status_select" type="text" v-model="searchInfo.status"
                                            class="form-control">
                                        <option value=null>全部</option>
                                        <option value="0">已下架</option>
                                        <option value="1">已上架</option>
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
                                <th>商品名称</th>
                                <th>商品单价</th>
                                <th>上架状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="goods in goods">
                                <td>{{goods.id}}</td>
                                <td>{{goods.name}}</td>
                                <td>{{goods.price}}</td>
                                <td>
                                    <label v-if="goods.status === 0" class="label label-danger">已下架</label>
                                    <label v-if="goods.status === 1" class="label label-success">已上架</label>
                                </td>
                                <td>
                                    <button class="btn btn-default" v-on:click="detail(goods.id)">
                                        <i class="fa fa-book"></i> 查看
                                    </button>
                                    <button v-if="goods.status === 0" class="btn btn-info" v-on:click="up(goods)">
                                        <i class="fa fa-arrow-circle-up"></i> 上架
                                    </button>
                                    <button v-if="goods.status === 1" class="btn btn-danger" v-on:click="down(goods)">
                                        <i class="fa fa-arrow-circle-down"></i> 下架
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-center" colspan="20" v-if="goods.length == 0">没有数据 ！</td>
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
                    <div class="panel-footer">
                        <button class="btn btn-success btn-lg" data-toggle='modal' data-target="#addGoods">
                            <i class="fa fa-plus-circle"></i> 添加商品
                        </button>
                    </div>
                </div>
            <#--<div class="panel-footer">-->

            <#--</div>-->
            </div>
        </div>
    </section>

    <!-- 添加商品 -->
    <div id="addGoods" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加商品</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="name" class="control-label">商品名称</label>
                        <input id="name" class="form-control" v-model="addGoods.name"/>
                    </div>
                    <div class="form-group">
                        <label for="price" class="control-label">商品单价(元)</label>
                        <input id="price" class="form-control" v-model="addGoods.price"/>
                    </div>
                    <div class="form-group">
                        <label for="input-file" class="control-label">上传商品图片</label>
                        <input id="input-file" type="file" name="myFileName" class="file-loading"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" v-on:click="add">确认</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>
<#include '../include/footer.ftl'/>
<script src="<@s.url '/js/jquery.pagination-1.2.7.js'/>"></script>
<script src="<@s.url '/js/fileinput.js'/>"></script>
<script src="<@s.url '/js/zh.js'/>"></script>
<!-- Charts JS -->
<script>
    var app = new Vue({
        el: '#main',
        data: {
            searchInfo: {
                name: '',
                status: '',
                page: 1,
                pageSize: 20
            },
            addGoods: {},
            goods: []
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
            var _self = this;
            $("#input-file").fileinput({
                uploadUrl: contentPath + "/api/upload/img", //上传的地址
                language: 'zh', //设置语言
                showCaption: false,
                overwriteInitial: false,
                showBrowse: false,
                browseOnZoneClick: true
            }).on("fileuploaded", function (e, result) {
                _self.addGoods.img = result.response.msg;
            });
        },
        methods: {
            search: function () {
                this.searchInfo.page = 1;
                $('#pageMenu').page('destroy');//销毁分页
                this.query();
            },
            query: function () {
                var url = contentPath + "/api/goods/list";
                this.$http.post(url, this.searchInfo).then(function (response) {
                    this.goods = response.data.data.list;
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
            detail: function (id) {
                window.location.href = contentPath + "/goods/detail?id=" + id;
            },
            add: function () {
                this.addGoods.content = this.editor.txt.html();
                var url = contentPath + "/api/goods/add";
                this.$http.post(url, this.addGoods).then(function (response) {
                    $("#addGoods").modal('hide');
                    swal("操作成功！", "", "success");
                    this.query();
                }, function (error) {
                    swal(error.body.msg);
                });
            },
            up: function (goods) {
                var that = this;
                swal({
                    title: "确定上架该商品吗？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定！",
                    cancelButtonText: "取消！",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        goods.status = 1;
                        var url = contentPath + "/api/goods/status";
                        that.$http.post(url, goods).then(function (response) {
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
            down: function (goods) {
                var that = this;
                swal({
                    title: "确定下架该商品吗？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定！",
                    cancelButtonText: "取消！",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        goods.status =  0;
                        var url = contentPath + "/api/goods/status";
                        that.$http.post(url, goods).then(function (response) {
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
