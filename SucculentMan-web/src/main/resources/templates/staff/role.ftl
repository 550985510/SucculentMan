<#import "/spring.ftl" as s>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <title>多肉达人后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                    <span class="panel-title"> 角色列表</span>
                </div>
                <div class="panel-body">
                    <div class="panel-body">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>角色名称</th>
                                <th>描述信息</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="role in roles">
                                <td>{{role.id}}</td>
                                <td>{{role.name}}</td>
                                <td>{{role.brief}}</td>
                                <td>
                                    <button class="btn btn-primary" data-toggle='modal' data-target="#editRole"
                                            v-on:click="editRoleBtn(role)">编辑角色
                                    </button>
                                    <button class="btn btn-primary" v-on:click="deleteRole(role)">删除角色</button>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-center" colspan="20" v-if="roles.length == 0">没有数据 ！</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="panel-footer">
                        <button class="btn btn-success btn-lg" data-toggle='modal' data-target="#addRole">
                            <i class="fa fa-plus-circle"></i> 添加角色
                        </button>
                    </div>
                </div>
            <#--<div class="panel-footer">-->

            <#--</div>-->
            </div>
        </div>
    </section>
    <!-- 角色设置 -->
    <div id="editRole" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">修改角色信息</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group input-group">
                        <label for="roleName" class="input-group-addon">角色名称</label>
                        <input id="roleName" class="form-control" v-model="role.name">
                    </div>
                    <div class="form-group input-group">
                        <label for="brief" class="input-group-addon">角色描述</label>
                        <input id="brief" class="form-control" v-model="role.brief">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" v-on:click="editRole">确认</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <!-- 添加角色 -->
    <div id="addRole" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加角色</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group input-group">
                        <label for="roleName" class="input-group-addon">角色名称</label>
                        <input id="roleName" class="form-control" v-model="addRole.name">
                    </div>
                    <div class="form-group input-group">
                        <label for="brief" class="input-group-addon">角色描述</label>
                        <input id="brief" class="form-control" v-model="addRole.brief">
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
<!-- Charts JS -->
<script>
    var app = new Vue({
        el: '#main',
        data: {
            role: {},
            roles: [],
            addRole: {}
        },
        created: function () {
            this.query();
        },
        methods: {
            query: function () {
                var url = contentPath + "/api/staff/roleList";
                this.$http.post(url, this.searchInfo).then(function (response) {
                    this.roles = response.data.data;
                }, function (error) {
                    swal(error.body.msg);
                });
            },
            editRoleBtn: function (role) {
                this.role = role;
            },
            editRole: function () {
                var url = contentPath + "/api/staff/editRole";
                this.$http.post(url, this.role).then(function (response) {
                    $("#editRole").modal('hide');
                    swal("操作成功！", "", "success");
                    this.query();
                }, function (error) {
                    swal(error.body.msg);
                });
            },
            deleteRole: function (role) {
                var that = this;
                swal({
                    title: "确定删除该角色？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定删除！",
                    cancelButtonText: "取消删除！",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        var url = contentPath + "/api/staff/deleteRole";
                        that.$http.post(url, role).then(function (response) {
                            swal("删除！", "", "success");
                            that.query();
                        }, function (error) {
                            swal(error.body.msg);
                        });
                    } else {
                        swal("取消！", "", "error");
                    }
                });
            },
            add: function () {
                var url = contentPath + "/api/staff/addRole";
                this.$http.post(url, this.addRole).then(function (response) {
                    $("#addRole").modal('hide');
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
