<#import "spring.ftl" as s>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <title>趣学君管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body class="error-page sb-l-o sb-r-c onload-check">
<div id="main">
    <header id="vueheader" class="navbar navbar-fixed-top">
        <div class="navbar-branding dark">
            <a class="navbar-brand" href="<@s.url '/'/>">
                <b>趣学君管理系统</b>
            </a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle fw600 p15" data-toggle="dropdown" aria-expanded="false">
                    <img src="<@s.url "/assets/images/avatars/1.jpg"/>" alt="avatar" class="mw30 br64 mr15">
                    <b></b>
                    <span class="caret caret-tp hidden-xs"></span>
                </a>
                <ul class="dropdown-menu list-group dropdown-persist w250" role="menu">
                    <li class="list-group-item">
                        <a href="<@s.url '/logout'/>" class="animated animated-short fadeInUp">
                            <span class="fa fa-power-off"></span> 登出 </a>
                    </li>
                </ul>
            </li>
        </ul>
    </header>
    <section id="content_wrapper" style="margin-left: 0px">
        <section id="content" class="pn">
            <div class="center-block mt70 mw800">
                <h1 class="error-title"> 403! </h1>
                <h2 class="error-subtitle">没有相关权限</h2>
            </div>
        </section>
    </section>
</div>
</body>
</html>
