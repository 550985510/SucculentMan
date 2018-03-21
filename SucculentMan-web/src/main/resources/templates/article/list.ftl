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
                                <th>发布状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="article in articles">
                                <td>{{article.id}}</td>
                                <td>{{article.title}}</td>
                                <td>{{article.author}}</td>
                                <td>
                                    <label class="label label-success" v-for="keyword in article.keywordList" style="margin-left: 5px">{{keyword}}</label>
                                </td>
                                <td>{{article.moduleName}}</td>
                                <td>
                                    <label v-if="article.status === 0" class="label label-warning">待审核</label>
                                    <label v-if="article.status === 1" class="label label-danger">未通过</label>
                                    <label v-if="article.status === 2" class="label label-success">已发布</label>
                                    <label v-if="article.status === 3" class="label label-default">已下架</label>
                                </td>
                                <td>
                                    <button v-if="article.status === 0" class="btn btn-primary">
                                        <i class="fa fa-eye"></i> 审核
                                    </button>
                                    <button v-if="article.status === 1 || article.status === 3" class="btn btn-info">
                                        <i class="fa fa-tags"></i> 申请上线
                                    </button>
                                    <button v-if="article.status === 2" class="btn btn-danger">
                                        <i class="fa fa-arrow-circle-down"></i> 下架
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
                    <div class="panel-footer">
                        <button class="btn btn-success btn-lg" data-toggle='modal' data-target="#addArticle">
                            <i class="fa fa-plus-circle"></i> 添加文章
                        </button>
                    </div>
                </div>
            <#--<div class="panel-footer">-->

            <#--</div>-->
            </div>
        </div>
    </section>

    <!-- 添加文章 -->
    <div id="addArticle" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加文章</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="title" class="control-label">文章标题</label>
                        <input id="title" class="form-control" v-model="addArticle.title"/>
                    </div>
                    <div class="form-group">
                        <label for="input-file" class="control-label">上传首页缩略图</label>
                        <input id="input-file" type="file" name="myFileName" class="file-loading"/>
                    </div>
                    <div class="form-group">
                        <label for="author" class="control-label">文章作者</label>
                        <select id="author" class="form-control" v-model="addArticle.staffId">
                            <option v-for="author in authors" v-bind:value="author.id">{{author.realName}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="control-label">关键词</label>
                        <div>
                            <input type="text" id="tagmanager" class="form-control" placeholder="请输入关键词">
                            <div class="tag-container tags"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="module" class="control-label">所属模块</label>
                        <select id="module" class="form-control" v-model="addArticle.moduleId">
                            <option v-for="module in modules" v-bind:value="module.id">{{module.name}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="control-label">文章内容</label>
                        <div id="content_add_editor"></div>
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
<script src="<@s.url '/plugins/holder/holder.min.js'/>"></script>
<script src="<@s.url '/plugins/typeahead/typeahead.bundle.min.js'/>"></script>
<script src="<@s.url '/plugins/tagmanager/tagmanager.js'/>"></script>
<script src="<@s.url '/js/wangEditor/wangEditor.min.js'/>"></script>
<!-- Charts JS -->
<script>
    var app = new Vue({
        el: '#main',
        data: {
            editor: {},
            searchInfo: {
                page: 1,
                pageSize: 20
            },
            modules: [],
            authors: [],
            addArticle: {},
            article: {},
            articles: []
        },
        created: function () {
            this.searchInfo.page = 1;
            $('#pageMenu').page('destroy');
            this.query();
            this.findModule();
            this.findAuthor();
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
                _self.addArticle.img = result.response.msg;
            });

            //jquery tagmanager插件
            var $tagmanager = $("#tagmanager");
            $tagmanager.tagsManager({
                tagsContainer: '.tags',
                prefilled: _self.addArticle.keywordList,
                tagClass: 'tm-tag-info'
            });
            //删除事件处理
            $tagmanager.on('tm:pushed', function (e, tag) {
                _self.addArticle.keywordList = $(this).tagsManager('tags');
            });
            $tagmanager.on('tm:spliced', function (e, tag) {
                _self.addArticle.keywordList = $(this).tagsManager('tags');
            });

            //富文本编辑器
            var E = window.wangEditor;
            this.editor = new wangEditor('#content_add_editor');
            this.editor.customConfig.menus = [
                'head',  // 标题
                'bold',  // 粗体
                'fontSize',  // 字号
                'fontName',  // 字体
                'italic',  // 斜体
                'underline',  // 下划线
                'strikeThrough',  // 删除线
                'foreColor',  // 文字颜色
                'backColor',  // 背景颜色
                'link',  // 插入链接
                'list',  // 列表
                'justify',  // 对齐方式
                'quote',  // 引用
                'image',  // 插入图片
                'table',  // 表格
                'undo'  // 撤销
            ];
            // 配置服务器端地址
            this.editor.customConfig.uploadImgServer = contentPath + '/api/upload/img';
            // 定义文件名
            this.editor.customConfig.uploadFileName = 'myFileName';
            this.editor.customConfig.uploadImgHooks = {
                success: function (xhr, editor, result) {
                    // 图片上传并返回结果，图片插入成功之后触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
                    swal("上传成功！", "", "success");
                },
                customInsert: function (insertImg, result, editor) {
                    // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
                    // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
                    // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
                    var url = result.msg;
                    insertImg(url);

                    // result 必须是一个 JSON 格式字符串！！！否则报错
                }
            };
            this.editor.create();
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
            findModule: function () {
                var url = contentPath + "/api/module/list";
                this.$http.post(url).then(function (response) {
                    this.modules = response.data.data;
                }, function (error) {
                    swal(error.body.msg);
                });
            },
            findAuthor: function () {
                var url = contentPath + "/api/staff/authorList";
                this.$http.post(url).then(function (response) {
                    this.authors = response.data.data;
                }, function (error) {
                    swal(error.body.msg);
                });
            },
            add: function () {
                this.addArticle.content = this.editor.txt.html();
                var url = contentPath + "/api/article/add";
                this.$http.post(url, this.addArticle).then(function (response) {
                    $("#addArticle").modal('hide');
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
