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
                    <span class="panel-title"> 员工列表</span>
                </div>
                <div class="panel-body">
                    <div class="well">
                        <form class="form-inline">
                            <div class="form-group">
                                <div class="input-group">
                                    <label class="input-group-addon btn-default" for="realName_input">员工姓名</label>
                                    <input id="realName_input" type="text" v-model="searchInfo.realName"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <label class="input-group-addon btn-default" for="mobile_input">手机号</label>
                                    <input id="mobile_input" type="text" v-model="searchInfo.mobile"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <label class="input-group-addon btn-default" for="role_select">部门</label>
                                    <select id="role_select" type="text" v-model="searchInfo.deptId"
                                            class="form-control">
                                        <option value=null>全部</option>
                                        <option :value="dept.id" v-for="dept in depts">{{dept.name}}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <label class="input-group-addon btn-default" for="role_select">角色</label>
                                    <select id="role_select" type="text" v-model="searchInfo.roleId"
                                            class="form-control">
                                        <option value=null>全部</option>
                                        <option value="0">无角色</option>
                                        <option :value="role.id" v-for="role in roles">{{role.name}}</option>
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
                            <div class="form-group input-group">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-default" data-toggle='modal'
                                            data-target="#addStaff">
                                        <span class="fa fa-user"></span> 添加员工
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
                                <th>姓名</th>
                                <th>性别</th>
                                <th>手机号</th>
                                <th>昵称</th>
                                <th>所属部门</th>
                                <th>在职状态</th>
                                <th>拥有角色</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="user in users">
                                <td>{{user.id}}</td>
                                <td>{{user.realName}}</td>
                                <td>
                                    <span v-if="user.gender === 0">男</span>
                                    <span v-if="user.gender === 1">女</span>
                                </td>
                                <td>{{user.mobile}}</td>
                                <td>{{user.nickName}}</td>
                                <td>{{user.deptName}}</td>
                                <td>
                                    <span class="label label-success" v-if="user.status === 0">在职</span>
                                    <span class="label label-warning" v-if="user.status === 1">请假</span>
                                    <span class="label label-info" v-if="user.status === 2">休假</span>
                                    <span class="label label-danger" v-if="user.status === 3">离职</span>
                                </td>
                                <td>{{user.roleName}}</td>
                                <td>
                                    <span class="input-group-btn">
                                        <button class="btn btn-default btn-primary" data-toggle='modal'
                                                data-target="#setRole" v-on:click="setRoleBtn(user)">设置角色
                                        </button>
                                    </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-center" colspan="20" v-if="users.length == 0">没有数据 ！</td>
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
    <!-- 角色设置 -->
    <div id="setRole" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">角色设置</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group input-group">
                        <label for="modal_role_select" class="input-group-addon">选择角色</label>
                        <select id="modal_role_select" class="form-control" v-model="user.roleId">
                            <option value="0">无角色</option>
                            <option :value="role.id" v-for="role in roles">{{role.name}}</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" v-on:click="setRole">确认</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <!-- 添加员工 -->
    <div id="addStaff" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加员工</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="add_modal_name_input" class="control-label col-lg-3">员工姓名</label>
                            <div class="bs-component col-lg-8">
                                <input id="add_modal_name_input" class="form-control" v-model="staff.realName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-lg-3">员工性别</label>
                            <div class="radio col-lg-8">
                                <label><input type="radio" name="gender" value="0" v-model="staff.gender">男</label>
                                <label><input type="radio" name="gender" value="1" v-model="staff.gender">女</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_modal_mobile_input" class="control-label col-lg-3">手机号码</label>
                            <div class="bs-component col-lg-8">
                                <input id="add_modal_mobile_input" class="form-control" v-model="staff.mobile">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_modal_nickName_input" class="control-label col-lg-3">员工昵称</label>
                            <div class="bs-component col-lg-8">
                                <input id="add_modal_nickName_input" class="form-control" v-model="staff.nickName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_modal_role_select" class="control-label col-lg-3">选择角色</label>
                            <div class="bs-component col-lg-8">
                                <select id="add_modal_role_select" class="form-control" v-model="staff.roleId">
                                    <option value="0">无角色</option>
                                    <option :value="role.id" v-for="role in roles">{{role.name}}</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add_modal_dept_select" class="control-label col-lg-3">所属部门</label>
                            <div class="bs-component col-lg-8">
                                <select id="add_modal_dept_select" class="form-control" v-model="staff.deptId">
                                    <option :value="dept.id" v-for="dept in depts" :disabled="dept.id == 1 ? true : false">{{dept.name}}</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" v-on:click="addStaff">确认</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>
<#include '../include/footer.ftl'/>
<script src="<@s.url '/js/jquery.pagination-1.2.7.js'/>"></script>
<!-- Charts JS -->
<script>
    var app = new Vue({
        el: '#main',
        data: {
            users: [],
            roles: [],
            depts: [],
            searchInfo: {
                realName: null,
                mobile: null,
                roleId: null,
                page: 1,
                pageSize: 30
            },
            user: {},
            staff: {
                gender: 0,
                roleId: 0
            }
        },
        created: function () {
            this.searchInfo.page = 1;
            $('#pageMenu').page('destroy');
            this.query();
            this.findRole();
            this.findDept();
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
            findRole: function () {
                var url = contentPath + "/api/staff/roleList";
                this.$http.post(url, this.searchInfo).then(function (response) {
                    this.roles = response.data.data;
                }, function (error) {
                    swal(error.body.msg);
                });
            },
            findDept: function () {
                var url = contentPath + "/api/staff/deptList";
                this.$http.post(url, this.searchInfo).then(function (response) {
                    this.depts = response.data.data;
                }, function (error) {
                    swal(error.body.msg);
                });
            },
            query: function () {
                var url = contentPath + "/api/staff/userList";
                this.$http.post(url, this.searchInfo).then(function (response) {
                    this.users = response.data.data.list;
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
            addStaff: function () {
                this.staff.deptName = $("#add_modal_dept_select").find("option:selected").text();
                this.staff.roleName = $("#add_modal_role_select").find("option:selected").text();
                if (this.staff.realName == null) {
                    sweetAlert("请输入员工姓名");
                } else if (this.staff.mobile == null) {
                    sweetAlert("请输入员工手机号码")
                } else if (this.staff.deptId == null) {
                    sweetAlert("请选择员工所属部门")
                } else {
                    var url = contentPath + "/api/staff/addUser";
                    this.$http.post(url, this.staff).then(function (response) {
                        $("#addStaff").modal('hide');
                        swal("操作成功！", "", "success");
                        this.query();
                    }, function (error) {
                        swal(error.body.msg);
                    });
                }
                console.log(this.staff);
            },
            setRoleBtn: function (user) {
                this.user = user;
            },
            setRole: function () {
                this.user.roleName = $("#modal_role_select").find("option:selected").text();
                var url = contentPath + "/api/staff/setRole";
                this.$http.post(url, this.user).then(function (response) {
                    $("#setRole").modal('hide');
                    swal("操作成功！", "", "success");
                    this.query();
                }, function (error) {
                    swal(error.body.msg);
                });
            }
        }
    });
</script>
</body>
</html>
