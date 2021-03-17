<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html;charset=UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>

    <meta charset="utf-8">
    <title>登录页面</title>
    <meta name="author" content="www.xmoban.cn">
    <meta name="copyright" content="Copyright   www.xmoban.cn">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->
    <link rel='stylesheet' href='http://fonts.useso.com/css?family=PT+Sans:400,700'>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/supersized.css">
    <link rel="stylesheet" href="css/style.css">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

</head>
<style>
    .buttons {    cursor: pointer;
        width: 300px;
        height: 44px;
        margin-top: 25px;
        padding: 0;
        background: #ef4300;
        -moz-border-radius: 6px;
        -webkit-border-radius: 6px;
        border-radius: 6px;
        border: 1px solid #ff730e;
        -moz-box-shadow:
                0 15px 30px 0 rgba(255,255,255,.25) inset,
                0 2px 7px 0 rgba(0,0,0,.2);
        -webkit-box-shadow:
                0 15px 30px 0 rgba(255,255,255,.25) inset,
                0 2px 7px 0 rgba(0,0,0,.2);
        box-shadow:
                0 15px 30px 0 rgba(255,255,255,.25) inset,
                0 2px 7px 0 rgba(0,0,0,.2);
        font-family: 'PT Sans', Helvetica, Arial, sans-serif;
        font-size: 14px;
        font-weight: 700;
        color: #fff;
        text-shadow: 0 1px 2px rgba(0,0,0,.1);
        -o-transition: all .2s;
        -moz-transition: all .2s;
        -webkit-transition: all .2s;
        -ms-transition: all .2s;
    }
    .son{
        width: 40px;
        height: 20px;
        margin: 15px auto;
    }
    .buttons a{
        color: white;
        text-align: center;
    }
    .buttons:hover {
        -moz-box-shadow:
                0 15px 30px 0 rgba(255,255,255,.15) inset,
                0 2px 7px 0 rgba(0,0,0,.2);
        -webkit-box-shadow:
                0 15px 30px 0 rgba(255,255,255,.15) inset,
                0 2px 7px 0 rgba(0,0,0,.2);
        box-shadow:
                0 15px 30px 0 rgba(255,255,255,.15) inset,
                0 2px 7px 0 rgba(0,0,0,.2);
    }
</style>
<body>

<div class="page-container">
    <h1>用户登录</h1>
    <form action="${pageContext.request.contextPath}/user/login" method="post">
        <input type="text" name="username" class="username" placeholder="用户名">
        <input type="password" name="password" class="password" placeholder="密码">
        <button type="submit">登录</button>
        <div class="buttons">
            <div class="son">
                <a href="regist.jsp" style="text-decoration:none;">注册</a>
            </div>
        </div>
        <div class="error"><span>+</span></div>
    </form>
    <div class="connect">
        <p>
            <a class="facebook" href=""></a>
            <a class="twitter" href=""></a>
        </p>
    </div>
</div>


<!-- Javascript -->
<script src="js/jquery-1.8.2.min.js"></script>
<script src="js/supersized.3.2.7.min.js"></script>
<script src="js/supersized-init.js"></script>
<script src="js/scripts.js"></script>
<script type="text/javascript">

    if (top.location !== self.location) {
        top.location=self.location;
    }
</script>
</body>

</html>