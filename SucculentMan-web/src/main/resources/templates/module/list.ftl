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
                    <span class="panel-title"> 模块列表</span>
                </div>
                <div class="panel-body">
                    <div class="panel-body">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>模块名称</th>
                                <th>模块类型</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="module in modules">
                                <td>{{module.id}}</td>
                                <td>{{module.name}}</td>
                                <td v-if="module.type === 0">文章</td>
                                <td v-if="module.type === 1">帖子</td>
                                <td>
                                    <button class="btn btn-primary" data-toggle='modal' data-target="#editModule"
                                            v-on:click="editBtn(module)">编辑模块
                                    </button>
                                    <button class="btn btn-primary" v-on:click="deleteModule(module)">删除模块</button>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-center" colspan="20" v-if="modules.length == 0">没有数据 ！</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="panel-footer">
                        <button class="btn btn-success btn-lg" data-toggle='modal' data-target="#addModule">
                            <i class="fa fa-plus-circle"></i> 添加模块
                        </button>
                    </div>
                </div>
            <#--<div class="panel-footer">-->

            <#--</div>-->
            </div>
        </div>
    </section>
    <!-- 修改模块 -->
    <div id="editModule" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">修改模块信息</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group input-group">
                        <label for="moduleName" class="input-group-addon">模块名称</label>
                        <input id="moduleName" class="form-control" v-model="module.name">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" v-on:click="edit">确认</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <!-- 添加文章 -->
    <div id="addModule" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加文章</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group input-group">
                        <label for="moduleName" class="input-group-addon">模块名称</label>
                        <input id="moduleName" class="form-control" v-model="addModule.name">
                    </div>
                    <div class="form-group input-group">
                        <label for="moduleType" class="input-group-addon">模块类型</label>
                        <select id="moduleType" class="form-control" v-model="addModule.type">
                            <option value="0">文章</option>
                            <option value="1">帖子</option>
                        </select>
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
            module: {},
            modules: [],
            addModule: {}
        },
        created: function () {
            this.query();
        },
        methods: {
            query: function () {
                var url = contentPath + "/api/module/list?type=3";
                this.$http.post(url).then(function (response) {
                    this.modules = response.data.data;
                }, function (error) {
                    swal(error.body.msg);
                });
            },
            editBtn: function (module) {
                this.module = module;
            },
            edit: function () {
                var url = contentPath + "/api/module/edit";
                this.$http.post(url, this.module).then(function (response) {
                    $("#editModule").modal('hide');
                    swal("操作成功！", "", "success");
                    this.query();
                }, function (error) {
                    swal(error.body.msg);
                });
            },
            deleteModule: function (module) {
                var that = this;
                swal({
                    title: "确定删除该模块？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定删除！",
                    cancelButtonText: "取消删除！",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        var url = contentPath + "/api/module/delete";
                        that.$http.post(url, module).then(function (response) {
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
                var url = contentPath + "/api/module/add";
                this.$http.post(url, this.addModule).then(function (response) {
                    $("#addModule").modal('hide');
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
