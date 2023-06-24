<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <!--BootStrap设计的页面支持响应式的 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <!--引入BootStrap的CSS-->
    <link rel="stylesheet" href="./css/bootstrap.css" type="text/css"/>
    <!--引入JQuery的JS文件：JQuery的JS文件要在BootStrap的js的文件的前面引入-->
    <script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
    <!--引入BootStrap的JS的文件-->
    <script type="text/javascript" src="./js/bootstrap.js"></script>
    <style>
        #logo ul li {
            list-style: none;
            float: left;
            padding: 5px 10px;
            /*margin-top: 15px;*/
            line-height: 60px;
        }
    </style>
</head>
<body>
<div class="container">
    <!--logo-->
    <div id="logo" class="row">
        <div class="col-md-4">
            <h2>雅趣电商平台</h2>
        </div>
        <div class="col-md-4">
            <img src="./img/header.png"/>
        </div>
        <div class="col-md-4">
            <ul>
                <li><a href="login.jsp">登录</a></li>
                <li><a href="register.jsp">注册</a></li>
                <li><a href="cart.jsp">购物车</a></li>
                <li><a href="orderHistory.jsp">订单</a></li>
            </ul>
        </div>
    </div>
    <!--导航 2021-1-19-start-->
    <div id="">
        <nav class="navbar navbar-inverse" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span> <span
                        class="icon-bar"></span> <span class="icon-bar"></span> <span
                        class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index2.jsp">首页</a>
            </div>
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <form class="navbar-form navbar-right" role="search"
                      action="doproduct3">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search"
                               name="pname">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </nav>
    </div>
    <%
        if (session.getAttribute("errorinfo") != null) {
            String info = (String) session.getAttribute("errorinfo");
            out.print(info);
            session.removeAttribute("errorinfo");
        }
    %>
    <div style="margin-top: 90px;">

        <form class="form-horizontal" role="form" action="dologin">
            <div class="form-group" style="margin-left: 200px;">
                <label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="inputEmail3"
                           placeholder="用户名" name="username">
                </div>
            </div>
            <div class="form-group" style="margin-left: 200px">
                <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-5">
                    <input type="password" class="form-control" id="inputPassword3"
                           placeholder="密码" name="password">
                </div>
            </div>
            <div class="form-group" style="margin-left: 200px;">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label> <input type="checkbox"> Remember me
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group" style="margin-left: 200px;">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Sign in</button>
                </div>
            </div>
        </form>
    </div>
    <!--bottom-->
    <!--版权部分-->
    <div>
        <div align="center" style="margin-top: 20px;">
            <img src="./img/footer.png" width="100%">
        </div>
        <div>
            <!--友情链接-->
            <%@ include file="footer.jsp" %>
            <!-- 页面底部信息 -->
        </div>
    </div>
</div>
</body>
</html>
