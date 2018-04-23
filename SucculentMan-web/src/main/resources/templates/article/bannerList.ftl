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
    <link rel="stylesheet" type="text/css" href="<@s.url '/plugins/tagmanager/tagmanager.css'/>">
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
                    <span class="panel-title"> 模块列表</span>
                </div>
                <div class="panel-body">
                    <div class="panel-body">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>标题</th>
                                <th>作者</th>
                                <th>关键词</th>
                                <th>所属模块</th>
                                <th>首页显示状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="article in articles">
                                <td>{{article.id}}</td>
                                <td>{{article.title}}</td>
                                <td>{{article.author}}</td>
                                <td>
                                    <label class="label label-success" v-for="keyword in article.keywordList"
                                           style="margin-right: 5px">{{keyword}}</label>
                                </td>
                                <td>{{article.moduleName}}</td>
                                <td>
                                    <label v-if="article.bannerStatus === 0" class="label label-warning">首页不显示</label>
                                    <label v-if="article.bannerStatus === 1" class="label label-success">首页显示</label>
                                </td>
                                <td>
                                    <button class="btn btn-default" v-on:click="detail(article.id)">
                                        <i class="fa fa-book"></i> 查看
                                    </button>
                                    <button v-if="article.bannerStatus === 0" class="btn btn-success" v-on:click="bannerShow(article)">
                                        首页显示
                                    </button>
                                    <button v-if="article.bannerStatus === 1" class="btn btn-warning" v-on:click="bannerHide(article)">
                                        首页不显示
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-center" colspan="20" v-if="articles.length == 0">没有数据 ！</td>
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
                status: 2,
                page: 1,
                pageSize: 20
            },
            articles: []
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
        methods: {
            search: function () {
                this.searchInfo.page = 1;
                $('#pageMenu').page('destroy');//销毁分页
                this.query();
            },
            query: function () {
                var url = contentPath + "/api/article/list";
                this.$http.post(url, this.searchInfo).then(function (response) {
                    this.articles = response.data.data.list;
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
                window.location.href = contentPath + "/article/detail?id=" + id;
            },
            bannerShow: function (article) {
                article.bannerStatus = 1;
                var url = contentPath + "/api/article/updateBanner";
                that.$http.post(url, article).then(function (response) {

                    }, function (error) {
                    swal(error.body.msg);
                });
            },
            bannerHide: function (article) {
                article.bannerStatus = 0;
                var url = contentPath + "/api/article/updateBanner";
                that.$http.post(url, article).then(function (response) {

                }, function (error) {
                    swal(error.body.msg);
                });
            }
        }
    });
</script>
</body>
</html>
