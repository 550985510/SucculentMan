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
    <section id="content_wrapper">
        <header id="topbar" class="alt">
            <div class="topbar-left">
                <ol class="breadcrumb">
                    <li class="crumb-active">
                        <a href="<@s.url '/'/>">控制台</a>
                    </li>
                    <li class="crumb-icon">
                        <a href="<@s.url '/'/>">
                            <span class="glyphicon glyphicon-home"></span>
                        </a>
                    </li>
                    <li class="crumb-link">
                        <a href="<@s.url '/'/>">首页</a>
                    </li>
                    <li class="crumb-trail">控制台</li>
                </ol>
            </div>
        </header>
        <section id="content" class="pn">

            <div class="center-block mt50 mw800">
                <h1 class="error-title"> 500! </h1>
                <h2 class="error-subtitle">服务器处理错误.</h2>
            </div>
        </section>
    </section>
</div>
</body>

</html>
