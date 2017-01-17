<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/_resource.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title></title>
  <link rel="shortcut icon" href="resources/fc/images/icon/favicon.ico">
  <!--[if lt IE 9]>
   <script src="plug-in/login/js/html5.js"></script>
  <![endif]-->
  <!--[if lt IE 7]>
  <script src="plug-in/login/js/iepng.js" type="text/javascript"></script>
  <script type="text/javascript">
	EvPNG.fix('div, ul, img, li, input'); //EvPNG.fix('包含透明PNG图片的标签'); 多个标签之间用英文逗号隔开。
</script>
  <![endif]-->
  <link href="<%=basePath%>/static/plug-in/login/css/zice.style.css" rel="stylesheet" type="text/css" />
  <link href="<%=basePath%>/static/plug-in/login/css/buttons.css" rel="stylesheet" type="text/css" />
  <link href="<%=basePath%>/static/plug-in/login/css/icon.css" rel="stylesheet" type="text/css" />
  <link href="<%=basePath%>/static/plug-in/login/css/tipsy.css" rel="stylesheet" type="text/css" media="all" />
  <style type="text/css">
html {
	background-image: none;
}

label.iPhoneCheckLabelOn span {
	padding-left: 0px
}

#versionBar {
	background-color: #212121;
	position: fixed;
	width: 100%;
	height: 35px;
	bottom: 0;
	left: 0;
	text-align: center;
	line-height: 35px;
	z-index: 11;
	-webkit-box-shadow: black 0px 10px 10px -10px inset;
	-moz-box-shadow: black 0px 10px 10px -10px inset;
	box-shadow: black 0px 10px 10px -10px inset;
}

.copyright {
	text-align: center;
	font-size: 10px;
	color: #CCC;
}

.copyright a {
	color: #A31F1A;
	text-decoration: none
}

#login .logo {
	width: 500px;
	height: 51px;
	left:100px;
}
.button_bottom{
	width:360px;
}
.button_bottom li a{
	padding: 10px 64px;
    border-radius: 6px;
    color: #fff;
    background: #1f92ff;
    outline: none;
}
</style>
 </head>
 <body>
  <div id="alertMessage"></div>
  <div id="successLogin"></div>
  <div class="text_success">
   <img src="<%=basePath%>/static/plug-in/login/images/loader_green.gif" alt="Please wait" />
   <span>登陆成功!请稍后....</span>
  </div>
  <div id="login">
   <div class="ribbon" style="background:url(static/plug-in/login/images/type_login.png) no-repeat 0 2px;"></div>
   <div class="inner">
    <div class="logo">
     <img src="<%=basePath%>/static/plug-in/login/images/top_title.png"/>
    </div>
    <div class="formLogin">
     <form name="formLogin" id="formLogin" action="" check="/sysUser/login.do" method="post">
      <div class="tip">
       <input class="userName" name="email" type="text" id="email"  value='' title="用户名" iscookie="true"  nullmsg="请输入用户名!"/>
      </div>
      <div class="tip">
       <input class="password" name="pwd" type="password"  value='' id="pwd" title="密码" nullmsg="请输入密码!"/>
      </div>
      <div class="loginButton">
       <div style="padding: 10px 0; margin-right: -12px;">
        <div>
         <ul class="uibutton-group button_bottom">
          <li>
           <a class=" normal" href="#" id="but_login">登 录</a>
          </li>
          <li style="float: right;">
           <a class=" normal" href="#" id="forgetpass">重 置</a>
          </li>
         </ul>
        </div>
       </div>
       <div class="clear"></div>
      </div>
     </form>
    </div>
   </div>
   <div class="shadow"></div>
  </div>
  <!--Login div-->
  <div class="clear"></div>
  <div id="versionBar">
   <div class="copyright">
    &copy; 版权所有
    <span class="tip"> (推荐使用IE8+,谷歌浏览器可以获得更快,更安全的页面响应速度) </span>
   </div>
  </div>
  <!-- Link JScript-->
  <script type="text/javascript" src="<%=basePath%>/static/B-JUI/js/jquery-1.11.3.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>/static/B-JUI/js/jquery.cookie.js"></script>
  <script type="text/javascript" src="<%=basePath%>/static/plug-in/login/js/jquery-jrumble.js"></script>
  <script type="text/javascript" src="<%=basePath%>/static/plug-in/login/js/jquery.tipsy.js"></script>
  <script type="text/javascript" src="<%=basePath%>/static/plug-in/login/js/iphone.check.js"></script>
  <script type="text/javascript" src="<%=basePath%>/static/plug-in/login/js/login.js"></script>
  
  <script type="text/javascript" src="<%=basePath%>/static/commons/Barrett.js"></script>
  <script type="text/javascript" src="<%=basePath%>/static/commons/BigInt.js"></script>
  <script type="text/javascript" src="<%=basePath%>/static/commons/RSA.js"></script>
  <Script type="text/javascript" >
  
//重置
  $('#forgetpass').click(function(e) {
  	$(":input").each(function() {
  	$('#'+this.name).val("");
  	});
  });
  // 点击登录
  $('#but_login').click(function(e) {
  	submit();
  });
  //回车登录
  $(document).keydown(function(e){
  	if(e.keyCode == 13) {
  		submit();
  	}
  });
  //表单提交
  function submit()
  {
  	var submit = true;
  	$("input[nullmsg]").each(function() {
  		if ($("#" + this.name).val() == "") {
  			showError($("#" + this.name).attr("nullmsg"), 500);
  			jrumble();
  			setTimeout('hideTop()', 3000);
  			submit = false;
  			return false;
  		}
  	});
  	if (submit) {
  		hideTop();
  		loading('核实中..', 1);
  		setTimeout("unloading()", 2000);
  		setTimeout("Login()", 2500);
  	}

  }
  //登录处理函数
  function Login() {
  	setCookie();
  	var actionurl=$('form').attr('action');//提交路径
  	var checkurl=$('form').attr('check');//验证路径
  	 var formData = new Object();
  	var data=$(":input").each(function() {
  		 formData[this.name] =$("#"+this.name ).val();
  	});
  	
  	setMaxDigits(130);//1024位就是130，2048位就是260
  	var tkey = new RSAKeyPair("<%=request.getAttribute("e")%>","","<%=request.getAttribute("n")%>");	//从服务端获取到的n和e可以得出公钥
	var encode_content = encodeURIComponent(formData['pwd']);
	console.log("encodeURIComponent后的值");
	console.log(encode_content);
	var encryptData = encryptedString(tkey,encode_content);
	console.log("客户端公钥加密后的值");
	console.log(encryptData);
	formData['pwd']=encryptData;
	
  	$.ajax({
  		async : false,
  		cache : false,
  		type : 'POST',
  		url : checkurl,// 请求的action路径
  		data : formData,
  		error : function() {// 请求失败处理函数
  		  alert('错误');
  		},
  		success : function(data) {
  			if (data.success) {
  				loginsuccess();
  				setTimeout("window.location.href='index.shtml'", 100);
  			} else {
  				showError(data.msg);
  			}
  		}
  	});
  }
  //设置cookie
  function setCookie()
  {
  	if ($('#on_off').val() == '1') {
  		$("input[iscookie='true']").each(function() {
  			$.cookie(this.name, $("#"+this.name).val(), "/",24);
  			$.cookie("COOKIE_NAME","true", "/",24);
  		});
  	} else {
  		$("input[iscookie='true']").each(function() {
  			$.cookie(this.name,null);
  			$.cookie("COOKIE_NAME",null);
  		});
  	}
  }
  
//验证通过加载动画
  function loginsuccess()
  {
  	$("#login").animate({
  		opacity : 1,
  		top : '49%'
  	}, 200, function() {
  		$('.userbox').show().animate({
  			opacity : 1
  		}, 500);
  		$("#login").animate({
  			opacity : 0,
  			top : '60%'
  		}, 500, function() {
  			$(this).fadeOut(200, function() {
  				$(".text_success").slideDown();
  				$("#successLogin").animate({
  					opacity : 1,
  					height : "200px"
  				}, 1000);
  			});
  		});
  	});
  }
  </Script>

 </body>
</html>