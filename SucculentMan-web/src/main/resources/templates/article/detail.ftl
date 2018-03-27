<#import "/spring.ftl" as s>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <title>多肉达人后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<#include '../include/baselink.ftl'>
    <link href="<@s.url '/css/fileinput.css'/>" rel="stylesheet" type="text/css">
</head>
<body class="dashboard-page">
<div id="main">
    <section id="content" class="table-layout">
        <div class="tray tray-center">
            <div class="col-md-6">
                <div class="panel">
                    <div class="panel-heading">
                        <span class="panel-icon">
                            <i class="fa fa-bar-chart-o"></i>
                        </span>
                        <span class="panel-title"> 文章详情</span>
                    </div>
                    <div class="panel-body">
                        <div class="panel-body">
                            <div class=" text-center">
                                <h3>{{article.title}}</h3>
                            </div>
                            <div v-html="article.content">
                                <template>{{article.content}}</template>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="panel">
                    <div class="panel-heading">
                        <span class="panel-icon">
                            <i class="fa fa-bar-chart-o"></i>
                        </span>
                        <span class="panel-title"> 文章编辑</span>
                    </div>
                    <div class="panel-body">
                        <div class="panel-body">
                            <div class="form-group">
                                <label for="title" class="control-label">文章标题</label>
                                <input id="title" class="form-control" v-model="article.title"/>
                            </div>
                            <div class="form-group">
                                <label for="input-file" class="control-label">上传首页缩略图</label>
                                <input id="input-file" type="file" name="myFileName" class="file-loading"/>
                            </div>
                            <div class="form-group">
                                <label for="author" class="control-label">文章作者</label>
                                <select id="author" class="form-control" v-model="article.staffId">
                                    <option v-for="author in authors" v-bind:value="author.id">{{author.realName}}
                                    </option>
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
                                <select id="module" class="form-control" v-model="article.moduleId">
                                    <option v-for="module in modules" v-bind:value="module.id">{{module.name}}
                                    </option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">文章内容</label>
                                <div id="content_add_editor"></div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <button class="btn btn-success btn-lg" v-on:click="edit">
                            <i class="fa fa-pencil"></i> 修改文章
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<#include '../include/footer.ftl'/>
<!-- Charts JS -->
<script src="<@s.url '/js/fileinput.js'/>"></script>
<script src="<@s.url '/js/zh.js'/>"></script>
<script src="<@s.url '/plugins/holder/holder.min.js'/>"></script>
<script src="<@s.url '/plugins/typeahead/typeahead.bundle.min.js'/>"></script>
<script src="<@s.url '/plugins/tagmanager/tagmanager.js'/>"></script>
<script src="<@s.url '/js/wangEditor/wangEditor.min.js'/>"></script>
<script>
    var app = new Vue({
        el: '#main',
        data: {
            editor: {},
            id: getQueryString("id"),
            modules: [],
            authors: [],
            article: {}
        },
        created: function () {
            this.query();
            this.findModule();
            this.findAuthor();
        },
        methods: {
            init: function () {
                var _self = this;
                //jquery tagmanager插件
                var $tagmanager = $("#tagmanager");
                $tagmanager.tagsManager({
                    tagsContainer: '.tags',
                    prefilled: _self.article.keywordList,
                    tagClass: 'tm-tag-info',
                });
                //删除事件处理
                $tagmanager.on('tm:pushed', function (e, tag) {
                    _self.article.keywordList = $(this).tagsManager('tags');
                });
                $tagmanager.on('tm:spliced', function (e, tag) {
                    _self.article.keywordList = $(this).tagsManager('tags');
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
            query: function () {
                var url = contentPath + "/api/article/detail?id=" + this.id;
                this.$http.get(url, this.id).then(function (response) {
                    this.article = response.data.data;
                    var _self = this;
                    var imageAvatar = response.data.data.img;

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
                            _self.article.img = result.response.msg;
                        });
                        //初始化tagmanager
                        this.init();
                        this.editor.txt.html(this.article.content);
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
            edit: function () {
                console.log(this.article);
                var that = this;
                swal({
                    title: "确定修改该文章吗？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定！",
                    cancelButtonText: "取消！",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        that.article.content = that.editor.txt.html();
                        var url = contentPath + "/api/article/edit";
                        that.$http.post(url, that.article).then(function (response) {
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
