<%@ page import="java.time.format.DateTimeFormatter" %>
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
            line-height: 60px;
        }

        /* 设置表单元素的字体和大小 */
        button, input, select, textarea {
            font-family: inherit;
            font-size: 100%;
        }

        /* 设置表格的边框和间距 */
        table {
            border-collapse: collapse;
            border-spacing: 0;
        }

        /* 设置表格的奇数行和偶数行的背景色 */
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:nth-child(odd) {
            background-color: #ffffff;
        }


        /* 设置表格单元格的样式 */
        td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }

        /* 设置输入框的样式 */
        input[type=text], input[type=number] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            box-sizing: border-box;
            border: 2px solid #ccc;
            border-radius: 4px;
        }

        /* 设置输入框在获取焦点时的样式 */
        input[type=text]:focus, input[type=number]:focus {
            background-color: lightblue;
            border-color: #555;
        }

        /* 设置提交按钮的样式 */
        input[type=submit] {
            width: 100%;
            background-color: #d7d7d7;
            color: black;
            border: none;
            cursor: pointer;
        }
        /*禁止换行*/
        .no-wrap {
            white-space: nowrap;
        }

        div.cn {
            width: 300px;
            height: 50px;
            overflow: hidden;
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
                    <c:forEach items="${sessionScope.slist }" var="category" varStatus="s">
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
    <!--导航 2021-1-19-end-->
    <!--body-->
    <div class="row" style="height: 300px; text-align: center;">
        <div class="col-md-10 col-md-push-1">

            <form action="doorderhistory" method="post">
                <table class="table table-striped">
                    <tr height="35px">
                        <th>订单编号</th>
                        <th>用户ID</th>
                        <th>商品编号</th>
                        <th>商品名称</th>
                        <th>商品分类</th>
                        <th>单价</th>
                        <th>购买数量</th>
                        <th>总价</th>
                        <th>收货地址</th>
                        <th>下单时间</th>
                    </tr>
                    <%
                        int i = 0;
                    %>
                    <c:forEach var="order" items="${sessionScope.orderlist}" varStatus="status">
                        <tr height="30px">
                            <td>${order.orderid}</td>
                            <td>${order.userid}</td>
                            <td>${order.productid}</td>
                            <td class="no-wrap" title="${order.name}"><div class="cn">${order.name}</div></td>
                            <td>${order.descn}</td>
                            <td>${order.price}</td>
                            <td>${order.num}</td>
                            <td>${order.totalprice}</td>
                            <td>${order.address}</td>
                            <td>${order.time}</td>
                        </tr>
                        <%
                            i++;
                        %>
                    </c:forEach>
                    <tr height="35px">
                        <td><input type="submit" name="btn" value="Query"/></td>
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