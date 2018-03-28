<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>管理员登陆</title>
	<link rel="stylesheet" href="css/reset.css" />
	<link rel="stylesheet" href="css/login.css" />
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/layer/layer.js"></script>
    <script type="text/javascript" src="js/canvas-particle.js"></script>
	<link rel="stylesheet" href="js/layer/skin/default/layer.css">
</head>
<body>
<div class="page">
	<div class="loginwarrp">
		<div class="logo">管理员登陆</div>
        <div class="login_form">
				<li class="login-item">
					<span>用户名：</span>
					<input type="text" id="username" name="username" class="login_input" >
				</li>
				<li class="login-item">
					<span>密　码：</span>
					<input type="password" id="password" name="password" class="login_input" >
				</li>
				
				<li class="login-item verify">
					<span>验证码：</span>
					<input type="text" name="checkCode" id="checkCode" class="login_input verify_input">
				</li>
				<input type="button" id="code" onclick="createCode()" />
				<div class="clearfix"></div>
				 
				<li class="login-sub">
					<input type="button" name="login" value="登录" onclick="login()" />
                    <input type="button" name="Reset" value="重置" onclick="reset()" />
				</li> 
		</div>
	</div>
</div>
<script type="text/javascript">
		window.onload = function() {
			var config = {
				vx : 4,
				vy : 4,
				height : 2,
				width : 2,
				count : 100,
				color : "121, 162, 185",
				stroke : "100, 200, 180",
				dist : 6000,
				e_dist : 20000,
				max_conn : 10
			};
			CanvasParticle(config);
			createCode();
		};
		
		
		var code; //在全局定义验证码  
        function createCode() {
            code = "";
            var codeLength = 4; //验证码的长度  
            var checkCode = document.getElementById("code");
            var random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //随机数  
            for(var i = 0; i < codeLength; i++) { //循环操作  
                var index = Math.floor(Math.random() * 36); //取得随机数的索引（0~35）  
                code += random[index]; //根据索引取得随机数加到code上  
            }
            checkCode.value = code; //把code值赋给验证码  
        }
		
        $(function(){
        	document.getElementById("username").value="";
        	document.getElementById("password").value="";
        	document.getElementById("checkCode").value="";
        	$('#username').blur(checkName);
        	$('#password').blur(checkPassword);
        });

        //校验用户名
        function checkName(){
        	var name = $('#username').val();
        	if(name == null || name == ""){
        		//提示错误
        		layer.msg("用户名不能为空");
        		return false;
        	}
        	var reg = /^\w{6,16}$/;
        	if(!reg.test(name)){
        		layer.msg("输入6-16个字母或数字或下划线");
        		return false;
        	}
        	return true;
        }

        //校验密码
        function checkPassword(){
        	var password = $('#password').val();
        	if(password == null || password == ""){
        		//提示错误
        		layer.msg("密码不能为空");
        		return false;
        	}
        	var reg = /^\w{5,16}$/;
        	if(!reg.test(password)){
        		layer.msg("输入6-16个字母或数字或下划线");
        		return false;
        	}
        	return true;
        }
		        
        
        function login(){
        	var username = $('#username').val();
        	var password = $('#password').val();
        	var checkCode = $('#checkCode').val().toUpperCase(); 
        	if(username == ""){
        		layer.msg("还没有输入用户名噢",{time:0,btn:['朕知道了', '退下']});
        	}
        	else if(password == ""){
        		layer.msg("您忘了输入密码了皇上",{time:0,btn:['朕知道了', '退下']});
        	}
        	else if(checkCode == ""){
        		layer.msg("怎么能忘了验证码呢",{time:0,btn:['朕知道了', '退下']});
        	}
        	else if(checkCode != code) { //若输入的验证码与产生的验证码不一致时  
            	layer.msg("验证码输入错误",{time:0,btn:['朕知道了', '退下']});//则弹出验证码输入错误  
                createCode(); //刷新验证码  
                document.getElementById("checkCode").value = ""; //清空文本框  
            }
        	else{
        	    //跳转loading加载动画
                var index = layer.load(0, {shade: false});
            	$.ajax({
            		url:"/manager/api/staff/login",
            		data:"username="+username+"&password="+password,
            		type:"POST",
            		success:function(result){
            		    console.log(result)
            			if(result.retcode == 2000000){
            				window.location.href='/manager/index';
            			}
            			else{
                            layer.msg("用户不存在或用户名密码输入错误", {time:0,btn:['朕知道了', '退下']} ,function () {
                                createCode(); //刷新验证码
                                reset(); //清空文本框
                                location.reload();
                            });
            			}
            		}
            	});	
            }
        }
        
        function reset(){
        	createCode();
        	$('#username').val("");
        	$('#password').val("");
        	$('#checkCode').val("");
        }
	</script>
</body>
</html>