<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <!--BootStrap设计的页面支持响应式的 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <!--引入BootStrap的CSS-->
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css"/>
    <!--引入JQuery的JS文件：JQuery的JS文件要在BootStrap的js的文件的前面引入-->
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <!--引入BootStrap的JS的文件-->
    <script type="text/javascript" src="js/bootstrap.js"></script>
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
    <!--logo-->
    <div id="logo" class="row">
        <div class="col-md-4">
            <h2>雅趣电商平台</h2>
        </div>
        <div class="col-md-4">
            <img src="img/header.png"/>
        </div>
        <div class="col-md-4">
            <ul>
                <li>
                    <%
                        if (session.getAttribute("user") != null) {
                            String uname = (String) session.getAttribute("user");
                            out.print("欢迎" + uname);
                            out.print("<a href='doInvalidate.jsp'>注销</a>");
                        } else {
                            out.print("<a href='login.jsp'>登录</a>");
                        }
                    %>
                </li>
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
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav">

                    <c:forEach items="${sessionScope.slist }" var="category"
                               varStatus="s">
                        <c:if test="${s.index<=4 }">
                            <li><a href="doproduct2?cid=${category.catid }">${category.catname}</a></li>
                        </c:if>
                    </c:forEach>
                    <li class="dropdown"><a href="#" class="dropdown-toggle"
                                            data-toggle="dropdown">其他 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <c:forEach items="${sessionScope.slist }" var="category"
                                       varStatus="s">
                                <c:if test="${s.index>=5 }">
                                    <li><a href="#">${category.catname}</a></li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-right" role="search"
                      action="doproduct3">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search"
                               name="pname">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
            <!-- /.navbar-collapse -->
        </nav>
    </div>
    <!--页面主题-->
    <div class="row">
        <!--类别列表-->
        <div class="col-md-4">
            <ul class="nav nav-pills nav-stacked">
                <li><a href="#">当前流行</a></li>
                <c:forEach items="${sessionScope.slist }" var="category"
                           varStatus="s">
                    <c:if test="${s.index<=4 }">
                        <li><a href="doproduct2?cid=${category.catid }">${category.catname}</a></li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
        <!--轮播图-->
        <div class="col-md-8">
            <div>
                <div id="carousel-example-generic" class="carousel slide">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0"
                            class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner">
                        <div class="item active">
                            <img src="./img/xiaomi13dz.jpg" alt="...">
                        </div>

                        <div class="item ">
                            <img src="./img/hstx.png" alt="...">
                        </div>

                        <div class="item">
                            <img src="./img/redmi.jpg" alt="...">
                        </div>
                        <div class="item">
                            <img src="./img/xaomi13.webp" alt="...">
                        </div>
                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#carousel-example-generic"
                       data-slide="prev"> <span
                            class="glyphicon glyphicon-chevron-left"></span>
                    </a> <a class="right carousel-control"
                            href="#carousel-example-generic" data-slide="next"> <span
                        class="glyphicon glyphicon-chevron-right"></span>
                </a>
                </div>
            </div>
        </div>
    </div>
    <!--bottom-->
    <!--版权部分-->
    <div>
        <div align="center" style="margin-top: 20px;">
            <img src="img/footer.png" width="100%">
        </div>
        <div>
            <!--友情链接-->
            <div align="center">
                <!--友情链接-->
                <%@ include file="footer.jsp" %>
            </div>
        </div>
    </div>
</div>
</body>
</html>