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
                    <span class="panel-title"> 我的个人中心</span>
                </div>
                <div class="panel-body">
                    <div class="panel-body">
                        <div class="panel">
                            <div class="panel-body">
                                <div class="col-md-2 col-sm-2">
                                    <img class="img-thumbnail" v-bind:src="staff.avatar">
                                </div>
                                <div class="col-md-10 col-sm-10">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th>真实姓名</th>
                                            <td>{{staff.realName}}</td>
                                            <th>性别</th>
                                            <td>
                                                <span v-if="staff.gender === 0">男</span>
                                                <span v-if="staff.gender === 1">女</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>手机号码</th>
                                            <td>{{staff.mobile}}</td>
                                            <th>昵称</th>
                                            <td>{{staff.nickName}}</td>
                                        </tr>
                                        <tr>
                                            <th>拥有角色</th>
                                            <td>{{staff.roleName}}</td>
                                            <th>所属部门</th>
                                            <td>{{staff.deptName}}</td>
                                        </tr>
                                        <tr>
                                            <th>入职时间</th>
                                            <td>{{staff.createdTime}}</td>
                                            <th>完善信息</th>
                                            <td>
                                                <button class="btn btn-info" data-toggle='modal' data-target="#edit">
                                                    <i class="fa fa-pencil"></i> 编辑
                                                </button>
                                                <button class="btn btn-info">
                                                    <i class="fa fa-pencil-square-o"></i> 完善个人信息
                                                </button>
                                            </td>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <div class="panel">
                            <div class="panel-heading">
                                <span class="panel-title"> 我发布的文章</span>
                            </div>
                            <div class="panel-body">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th>期望贷款金额</th>
                                        <th>提交贷款申请时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                    </tr>
                                    <tr>
                                        <td class="text-center" colspan="20" v-if="articles.length == 0">暂无文章信息 ！</td>
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
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- 修改个人信息 -->
    <div id="edit" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">修改个人信息</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="input-file" class="control-label col-lg-3" style="text-align: end">头像</label>
                            <div class="col-lg-8">
                                <input id="input-file" type="file" name="myFileName" class="file-loading"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_modal_name_input" class="control-label col-lg-3">姓名</label>
                            <div class="bs-component col-lg-8">
                                <input id="add_modal_name_input" class="form-control" v-model="staff.realName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-lg-3">性别</label>
                            <div class="radio col-lg-8">
                                <label><input type="radio" name="gender" value="0" v-model="staff.gender">男</label>
                                <label><input type="radio" name="gender" value="1" v-model="staff.gender">女</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_modal_mobile_input" class="control-label col-lg-3">手机号</label>
                            <div class="bs-component col-lg-8">
                                <input id="add_modal_mobile_input" class="form-control" v-model="staff.mobile">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_modal_nickName_input" class="control-label col-lg-3">昵称</label>
                            <div class="bs-component col-lg-8">
                                <input id="add_modal_nickName_input" class="form-control" v-model="staff.nickName">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" v-on:click="edit">确认</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>
<#include '../include/footer.ftl'/>
<script src="<@s.url '/js/jquery.pagination-1.2.7.js'/>"></script>
<script src="<@s.url '/js/fileinput.js'/>"></script>
<script src="<@s.url '/js/zh.js'/>"></script>
<script src="<@s.url '/plugins/holder/holder.min.js'/>"></script>
<script src="<@s.url '/plugins/typeahead/typeahead.bundle.min.js'/>"></script>
<!-- Charts JS -->
<script>
    var app = new Vue({
        el: '#main',
        data: {
            id: ${Session.user.id},
            staff: {},
            searchInfo: {
                staffId: ${Session.user.id},
                page: 1,
                pageSize: 20
            },
            articles: []
        },
        created: function () {
            this.searchInfo.page = 1;
            $('#pageMenu').page('destroy');
            this.findArticle();
            this.query();
        },
        watch: {
            "searchInfo.page": function () {
                this.findArticle();
            }
        },
        methods: {
            query: function () {
                var url = contentPath + "/api/staff/personal?id=" + this.id;
                this.$http.get(url).then(function (response) {
                    this.staff = response.data.data;
                    var _self = this;
                    var imageAvatar = this.staff.avatar;
                    this.$nextTick(function () {
                        $("#input-file").fileinput({
                            uploadUrl: contentPath + "/api/upload/img", //上传的地址
                            overwriteInitial: true,
                            language: 'zh', //设置语言
                            showClose: false,
                            showCaption: false,
                            showBrowse: false,
                            browseOnZoneClick: true,
                            removeLabel: '',
                            removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
                            removeTitle: 'Cancel or reset changes',
                            msgErrorClass: 'alert alert-block alert-danger',
                            defaultPreviewContent: '<img src="' + imageAvatar + '" class="thumb" width="200">'
                        }).on("fileuploaded", function (e, result) {
                            _self.staff.avatar = result.response.msg;
                        });
                    });
                }, function (error) {
                    swal(error.body.msg);
                });
            },
            findArticle: function () {
                var url = contentPath + "/api/article/findByAuthor";
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
            edit: function () {
                var that = this;
                swal({
                    title: "确定修改个人信息？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定！",
                    cancelButtonText: "取消！",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        var url = contentPath + "/api/staff/editUser";
                        that.$http.post(url, that.staff).then(function (response) {
                            $("#edit").modal('hide');
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
