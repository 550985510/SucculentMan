<#import "/spring.ftl" as s>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <title>多肉达人后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<@s.url '/css/jquery.pagination.css'/>">
<#include '../include/baselink.ftl'>
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
                    <span class="panel-title"> 帖子列表</span>
                </div>
                <div class="panel-body">
                    <div class="well">
                        <form class="form-inline">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class="input-group-addon btn-default" for="title_input">文章标题</label>
                                    <input id="title_input" type="text" v-model="searchInfo.title"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <label class="input-group-addon btn-default" for="module_select">所属模块</label>
                                    <select id="module_select" type="text" v-model="searchInfo.moduleId"
                                            class="form-control">
                                        <option value=null>全部</option>
                                        <option :value="module.id" v-for="module in modules">{{module.name}}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <label class="input-group-addon btn-default" for="status_select">删除状态</label>
                                    <select id="status_select" type="text" v-model="searchInfo.deleted"
                                            class="form-control">
                                        <option value=null>全部</option>
                                        <option value="0">未删除</option>
                                        <option value="1">已删除</option>
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
                                <th>贴子主题</th>
                                <th>作者</th>
                                <th>所属模块</th>
                                <th>是否删除</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="post in posts">
                                <td>{{post.id}}</td>
                                <td>{{post.title}}</td>
                                <td>{{post.userNickName}}</td>
                                <td>{{post.moduleName}}</td>
                                <td>
                                    <label v-if="post.deleted === 0" class="label label-success">未删除</label>
                                    <label v-if="post.deleted === 1" class="label label-danger">已删除</label>
                                </td>
                                <td>
                                    <button class="btn btn-default" v-on:click="detail(post.id)">
                                        <i class="fa fa-book"></i> 查看
                                    </button>
                                    <button class="btn btn-info" v-if="post.deleted === 0" v-on:click="deleteBtn(post)">
                                        <i class="fa fa-trash"></i> 删除
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-center" colspan="20" v-if="posts.length == 0">没有数据 ！</td>
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
<#include '../include/footer.ftl'/>
<script src="<@s.url '/js/jquery.pagination-1.2.7.js'/>"></script>
<!-- Charts JS -->
<script>
    var app = new Vue({
        el: '#main',
        data: {
            searchInfo: {
                title: '',
                moduleId: '',
                deleted: '',
                page: 1,
                pageSize: 20
            },
            modules: [],
            posts: []
        },
        created: function () {
            this.searchInfo.page = 1;
            $('#pageMenu').page('destroy');
            this.query();
            this.findModule();
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
                var url = contentPath + "/api/post/list";
                this.$http.post(url, this.searchInfo).then(function (response) {
                    this.posts = response.data.data.list;
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
            findModule: function () {
                var url = contentPath + "/api/module/list?type=1";
                this.$http.post(url).then(function (response) {
                    this.modules = response.data.data;
                }, function (error) {
                    swal(error.body.msg);
                });
            },
            detail: function (id) {
                window.location.href = contentPath + "/post/detail?id=" + id;
            },
            deleteBtn: function (post) {
                var that = this;
                swal({
                    title: "确定删除该主题帖吗？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定！",
                    cancelButtonText: "取消！",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        post.deleted = 1;
                        var url = contentPath + "/api/post/delete";
                        that.$http.post(url, post).then(function (response) {
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
