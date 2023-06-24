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
        table {
            width: 100%;
            border-collapse: collapse;
        }

        table td, table th {
            border: 1px solid black;
            padding: 5px;
        }

        table thead tr {
            background-color: #FFFFCC;
            text-align: center;
        }

        table tbody tr:hover td {
            color: #1abc9c;
        }
    </style>

    <% if (request.getAttribute("insufficientBalance") != null) { %>
    <script>
        alert('余额不足');
    </script>
    <% } %>

</head>
<body>
<div class="container">
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

    <div class="row" style="height: 500px; text-align: center;">
        <div class="col-md-10 col-md-push-1">


            <form class="form-horizontal" role="form" action="doorder"
                  method="post" autocomplete="on">

                <table class="table table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th colspan="4">用户信息</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">姓名:</th>
                        <td colspan="3">${sessionScope.user }</td>
                    </tr>
                    <tr>
                        <th scope="row">邮箱:</th>
                        <td colspan="3">${sessionScope.email }</td>
                    </tr>
                    <tr>
                        <th scope="row">收货地址:</th>
                        <td colspan="3">${sessionScope.address }</td>
                    </tr>
                    <tr>
                        <th scope="row">余额:</th>
                        <td colspan="3">${sessionScope.balance }</td>
                    </tr>
                    <thead>
                    <tr>
                        <th colspan="4">下单商品</th>
                    </tr>
                    </thead>
                    <c:forEach var="cart" items="${sessionScope.cartlist}" varStatus="status">
                        <tr height="30px">
                            <td>${cart.name}</td>
                            <td>${cart.descn}</td>
                            <td>单价：${cart.price}</td>
                            <td>数量：${cart.purchasenum}</td>
                        </tr>
                    </c:forEach>
                    <tr bgcolor="#FFFFCC">
                        <th scope="row">应付金额:</th>
                        <td colspan="3">${sessionScope.total }</td>
                    </tr>
                    </tbody>
                </table>

                <br/>
                <table class="table table-striped">
                    <tr>
                        <td><input type="submit" id="sub" name="sub"
                                   value=" 提      交   "></td>
                    </tr>
                </table>

            </form>


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
            <%@ include file="footer.jsp" %>
            <!-- 页面底部信息 -->

        </div>
    </div>
</div>
</body>
</html>