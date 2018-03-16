<!doctype html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>多肉达人后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="H-ui/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="H-ui/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="H-ui/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="H-ui/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="H-ui/static/h-ui.admin/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="plugins/sweetAlert/sweetalert.css"/>

    <!-- jQuery -->
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="js/vue.min.js"></script>
    <script type="text/javascript" src="plugins/sweetAlert/sweetalert.min.js"></script>

    <!-- Theme Javascript -->
    <script type="text/javascript" src="js/utility.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="js/vue-resource.min.js"></script>
    <script type="text/javascript" src="js/constant.js"></script>


    <script type="text/javascript" src="H-ui/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="H-ui/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="H-ui/static/h-ui/js/H-ui.min.js"></script>
    <script type="text/javascript" src="H-ui/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
</head>
<body>
<div id="main">
    <header class="navbar-wrapper">
        <div class="navbar navbar-fixed-top">
            <div class="container-fluid cl"><a class="logo navbar-logo f-l mr-10 hidden-xs"
                                               href="index.ftl">多肉达人后台管理</a>
                <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
                <nav class="nav navbar-nav">
                    <ul class="cl">
                        <li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i
                                class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
                            <ul class="dropDown-menu menu radius box-shadow">
                                <li><a href="javascript:;" onclick="article_add('添加资讯','member-add.html','','510')"><i
                                        class="Hui-iconfont">&#xe616;</i> 资讯</a></li>
                                <li><a href="javascript:;" onclick="picture_add('添加资讯','member-add.html','','510')"><i
                                        class="Hui-iconfont">&#xe613;</i> 图片</a></li>
                                <li><a href="javascript:;" onclick="product_add('添加资讯','member-add.html','','510')"><i
                                        class="Hui-iconfont">&#xe620;</i> 产品</a></li>
                                <li><a data-toggle='modal' data-target="#addStaff"><i class="Hui-iconfont">&#xe60d;</i>
                                    员工</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
                <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                    <ul class="cl">
                        <li>${Session.user.roleName}</li>
                        <li class="dropDown dropDown_hover">
                            <a href="#" class="dropDown_A">${Session.user.realName}<i class="Hui-iconfont">&#xe6d5;</i></a>
                            <ul class="dropDown-menu menu radius box-shadow">
                                <li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
                                <li><a href="#">切换账户</a></li>
                                <li><a href="#">退出</a></li>
                            </ul>
                        </li>
                        <li id="Hui-skin" class="dropDown right dropDown_hover"><a href="javascript:;"
                                                                                   class="dropDown_A" title="换肤"><i
                                class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
                            <ul class="dropDown-menu menu radius box-shadow">
                                <li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
                                <li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
                                <li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
                                <li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
                                <li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
                                <li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </header>
    <aside class="Hui-aside">
        <div class="menu_dropdown bk_2">
            <dl id="menu-product">
                <dt><i class="Hui-iconfont">&#xe616;</i> 部门管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
                </dt>
                <dd>
                    <ul>
                        <li><a data-href="department.jsp" data-title="部门管理" href="javascript:void(0)">部门管理</a></li>
                    </ul>
                </dd>
            </dl>
            <dl id="menu-picture">
                <dt><i class="Hui-iconfont">&#xe613;</i> 图片管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
                </dt>
                <dd>
                    <ul>
                        <li><a data-href="picture-list.html" data-title="图片管理" href="javascript:void(0)">图片管理</a></li>
                    </ul>
                </dd>
            </dl>
            <dl id="menu-article">
                <dt><i class="Hui-iconfont">&#xe620;</i> 文章管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
                </dt>
                <dd>
                    <ul>

                    </ul>
                </dd>
            </dl>
            <dl id="menu-comments">
                <dt><i class="Hui-iconfont">&#xe622;</i> 评论管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
                </dt>
                <dd>
                    <ul>
                        <li><a data-href="http://h-ui.duoshuo.com/admin/" data-title="评论列表" href="javascript:;">评论列表</a>
                        </li>
                        <li><a data-href="feedback-list.html" data-title="意见反馈" href="javascript:void(0)">意见反馈</a></li>
                    </ul>
                </dd>
            </dl>
            <dl id="menu-users">
                <dt><i class="Hui-iconfont">&#xe60d;</i> 用户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
                </dt>
                <dd>
                    <ul>
                        <li><a data-href="/manager/user/list" data-title="用户列表" href="javascript:;">会员列表</a></li>
                        <!--
                        <li><a data-href="member-del.html" data-title="删除的会员" href="javascript:;">删除的会员</a></li>
                        <li><a data-href="member-level.html" data-title="等级管理" href="javascript:;">等级管理</a></li>
                        <li><a data-href="member-scoreoperation.html" data-title="积分管理" href="javascript:;">积分管理</a></li>
                        <li><a data-href="member-record-browse.html" data-title="浏览记录" href="javascript:void(0)">浏览记录</a></li>
                        <li><a data-href="member-record-download.html" data-title="下载记录" href="javascript:void(0)">下载记录</a></li>
                        <li><a data-href="member-record-share.html" data-title="分享记录" href="javascript:void(0)">分享记录</a></li>
                        -->
                    </ul>
                </dd>
            </dl>
            <dl id="menu-admin">
                <dt><i class="Hui-iconfont">&#xe62d;</i> 员工管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
                </dt>
                <dd>
                    <ul>
                        <li><a data-href="/manager/staff/user" data-title="员工列表" href="javascript:void(0)">员工列表</a></li>
                        <li><a data-href="/manager/staff/dept" data-title="部门列表" href="javascript:void(0)">部门列表</a></li>
                    </ul>
                </dd>
            </dl>
            <dl id="menu-tongji">
                <dt><i class="Hui-iconfont">&#xe61a;</i> 模块管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
                </dt>
                <dd>
                    <ul>
                        <li><a data-href="/manager/module/list" data-title="模块列表" href="javascript:void(0)">模块列表</a></li>
                        <!--
                        <li><a data-href="charts-2.html" data-title="时间轴折线图" href="javascript:void(0)">时间轴折线图</a></li>
                        <li><a data-href="charts-3.html" data-title="区域图" href="javascript:void(0)">区域图</a></li>
                        <li><a data-href="charts-4.html" data-title="柱状图" href="javascript:void(0)">柱状图</a></li>
                        <li><a data-href="charts-5.html" data-title="饼状图" href="javascript:void(0)">饼状图</a></li>
                        <li><a data-href="charts-6.html" data-title="3D柱状图" href="javascript:void(0)">3D柱状图</a></li>
                        <li><a data-href="charts-7.html" data-title="3D饼状图" href="javascript:void(0)">3D饼状图</a></li>
                        -->
                    </ul>
                </dd>
            </dl>
            <dl id="menu-system">
                <dt><i class="Hui-iconfont">&#xe62e;</i> 权限管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
                </dt>
                <dd>
                    <ul>
                        <li><a data-href="/manager/staff/role" data-title="角色列表" href="javascript:void(0)">角色列表</a></li>
                        <!--
                        <li><a data-href="system-category.html" data-title="栏目管理" href="javascript:void(0)">栏目管理</a></li>
                        <li><a data-href="system-data.html" data-title="数据字典" href="javascript:void(0)">数据字典</a></li>
                        <li><a data-href="system-shielding.html" data-title="屏蔽词" href="javascript:void(0)">屏蔽词</a></li>
                        <li><a data-href="system-log.html" data-title="系统日志" href="javascript:void(0)">系统日志</a></li>
                        -->
                    </ul>
                </dd>
            </dl>
        </div>
    </aside>
    <div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a>
    </div>
    <section class="Hui-article-box">
        <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
            <div class="Hui-tabNav-wp">
                <ul id="min_title_list" class="acrossTab cl">
                    <li class="active">
                        <span title="我的桌面" data-href="welcome.jsp">我的桌面</span>
                        <em></em></li>
                </ul>
            </div>
            <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S"
                                                      href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
                    id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i
                    class="Hui-iconfont">&#xe6d7;</i></a></div>
        </div>
        <div id="iframe_box" class="Hui-article">
            <div class="show_iframe">
                <div style="display:none" class="loading"></div>
                <iframe scrolling="yes" frameborder="0" src=""></iframe>
            </div>
        </div>
    </section>

    <!-- 添加员工 -->
    <div id="addStaff" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog radius" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加员工</h4>
                </div>
                <div class="modal-body">
                    <form class="form form-horizontal" role="form">
                        <div class="row cl">
                            <label for="add_modal_name_input" class="form-label col-xs-4 col-sm-3">员工姓名</label>
                            <div class="formControls col-xs-8 col-sm-9">
                                <input id="add_modal_name_input" class="input-text radius" v-model="staff.realName">
                            </div>
                        </div>
                        <div class="row cl">
                            <label class="form-label col-xs-4 col-sm-3">员工性别</label>
                            <div class="formControls skin-minimal col-xs-8 col-sm-9">
                                <div class="radio-box">
                                    <input type="radio" id="gender-0" name="gender" value="0" v-model="staff.gender" checked>
                                    <label for="gender-0">男</label>
                                </div>
                                <div class="radio-box">
                                    <input type="radio" id="gender-1" name="gender" value="1" v-model="staff.gender">
                                    <label for="gender-1">女</label>
                                </div>
                            </div>
                        </div>
                        <div class="row cl">
                            <label for="add_modal_mobile_input" class="form-label col-xs-4 col-sm-3">手机号码</label>
                            <div class="formControls col-xs-8 col-sm-9">
                                <input id="add_modal_mobile_input" class="input-text radius" v-model="staff.mobile">
                            </div>
                        </div>
                        <div class="row cl">
                            <label for="add_modal_role_select" class="form-label col-xs-4 col-sm-3">选择角色</label>
                            <div class="formControls col-xs-8 col-sm-9">
                                <select id="add_modal_role_select" class="input-text radius" v-model="staff.roleId">
                                    <option value="0">无角色</option>
                                    <option :value="role.id" v-for="role in roles">{{role.name}}</option>
                                </select>
                            </div>
                        </div>
                        <div class="row cl">
                            <label for="add_modal_dept_select" class="form-label col-xs-4 col-sm-3">所属部门</label>
                            <div class="formControls col-xs-8 col-sm-9">
                                <select id="add_modal_dept_select" class="input-text radius" v-model="staff.deptId">
                                    <option :value="dept.id" v-for="dept in depts"
                                            :disabled="dept.id == 1 ? true : false">{{dept.name}}
                                    </option>
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
<script>

    var app = new Vue({
        el: '#main',
        data: {
            roles: [],
            depts: [],
            staff: {
                gender: 0,
                roleId: 0
            }
        },
        created: function () {
            this.findRole();
            this.findDept();
        },
        methods: {
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
            }
        }
    });

//    function myselfinfo() {
//        layer.open({
//            type: 1,
//            area: ['300px', '200px'],
//            fix: false, //不固定
//            maxmin: true,
//            shade: 0.4,
//            title: '查看信息',
//            content: '<div>管理员信息</div>'
//        });
//    }
//
//    /*资讯-添加*/
//    function article_add(title, url) {
//        var index = layer.open({
//            type: 2,
//            title: title,
//            content: url
//        });
//        layer.full(index);
//    }
//
//    /*图片-添加*/
//    function picture_add(title, url) {
//        var index = layer.open({
//            type: 2,
//            title: title,
//            content: url
//        });
//        layer.full(index);
//    }
//
//    /*产品-添加*/
//    function product_add(title, url) {
//        var index = layer.open({
//            type: 2,
//            title: title,
//            content: url
//        });
//        layer.full(index);
//    }
//
//    /*用户-添加*/
//    function member_add(title, url, w, h) {
//        layer_show(title, url, w, h);
//    }


</script>
</body>
</html>