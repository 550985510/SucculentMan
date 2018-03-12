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
                    <span class="panel-title"> 部门列表</span>
                </div>
                <div class="panel-body">
                    <div class="panel-body">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>部门名称</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="dept in depts">
                                <td>{{dept.id}}</td>
                                <td>{{dept.name}}</td>
                                <td>
                                    <button class="btn btn-primary" data-toggle='modal' data-target="#editRole"
                                            v-on:click="editDeptBtn(dept)">编辑部门
                                    </button>
                                    <button class="btn btn-primary" v-on:click="deleteDept(dept)">删除部门</button>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-center" colspan="20" v-if="depts.length == 0">没有数据 ！</td>
                            </tr>
                            </tbody>
                        </table>
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
                        <label for="deptName" class="input-group-addon">角色名称</label>
                        <input id="deptName" class="form-control" v-model="dept.name">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" v-on:click="editDept">确认</button>
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
            dept: {},
            depts: []
        },
        created: function () {
            this.query();
        },
        methods: {
            query: function () {
                var url = contentPath + "/api/staff/deptList";
                this.$http.post(url, this.searchInfo).then(function (response) {
                    this.depts = response.data.data;
                }, function (error) {
                    swal(error.body.msg);
                });
            },
            editDeptBtn: function (dept) {
                this.dept = dept;
            },
            editDept: function () {
                var url = contentPath + "/api/staff/editRole";
                this.$http.post(url, this.dept).then(function (response) {
                    $("#editRole").modal('hide');
                    swal("操作成功！", "", "success");
                    this.query();
                }, function (error) {
                    swal(error.body.msg);
                });
            },
            deleteDept: function (dept) {
                var that = this;
                swal({
                    title: "确定删除该部门？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定删除！",
                    cancelButtonText: "取消删除！",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        var url = contentPath + "/api/staff/deleteDept";
                        that.$http.post(url, dept).then(function (response) {
                            swal("删除！", "", "success");
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
