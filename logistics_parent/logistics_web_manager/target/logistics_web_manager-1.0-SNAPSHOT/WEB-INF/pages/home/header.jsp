<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<header class="main-header">
    <a href="/login.do" class="logo">
        <span class="logo-mini"><img src="../img/logo.png"></span>
        <span class="logo-lg">
                    <img src="../img/export.png">
                    <i> 平头物流管理系统</i>
                </span>
    </a>

    <nav class="navbar navbar-static-top">
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <li class="dropdown messages-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-envelope-o"></i>
                        <span class="label label-success">4</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">你有4个邮件</li>
                        <li>
                            <ul class="menu">
                                <li>
                                    <a href="#">
                                        <div class="pull-left">
                                            <img src="../img/user2-160x160.jpg" class="img-circle" alt="User Image">
                                        </div>
                                        <h4>
                                            系统消息
                                            <small><i class="fa fa-clock-o"></i> 5 分钟前</small>
                                        </h4>
                                        <p>欢迎登录系统?</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="pull-left">
                                            <img src="../img/user3-128x128.jpg" class="img-circle" alt="User Image">
                                        </div>
                                        <h4>
                                            团队消息
                                            <small><i class="fa fa-clock-o"></i> 2 小时前</small>
                                        </h4>
                                        <p>你有新的任务了</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="pull-left">
                                            <img src="../img/user4-128x128.jpg" class="img-circle" alt="User Image">
                                        </div>
                                        <h4>
                                            Developers
                                            <small><i class="fa fa-clock-o"></i> Today</small>
                                        </h4>
                                        <p>Why not buy a new awesome theme?</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="pull-left">
                                            <img src="../img/user3-128x128.jpg" class="img-circle" alt="User Image">
                                        </div>
                                        <h4>
                                            Sales Department
                                            <small><i class="fa fa-clock-o"></i> Yesterday</small>
                                        </h4>
                                        <p>Why not buy a new awesome theme?</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="pull-left">
                                            <img src="../img/user4-128x128.jpg" class="img-circle" alt="User Image">
                                        </div>
                                        <h4>
                                            Reviewers
                                            <small><i class="fa fa-clock-o"></i> 2 days</small>
                                        </h4>
                                        <p>Why not buy a new awesome theme?</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="footer"><a href="#">See All Messages</a></li>
                    </ul>
                </li>
                <!-- Notifications: style can be found in dropdown.less -->
                <li class="dropdown notifications-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-bell-o"></i>
                        <span class="label label-warning" id="megSize">${messageSize}</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header" id="msgSize">你有${messageSize}个新消息</li>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <ul class="menu" id="menus">
                                <c:forEach items="${message}" var="s">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/feedback/toView.do?id=${s.id}" onclick="selectNew(${s.id})" target="iframe">
                                            <i class="fa fa-users text-aqua"></i>${s.content}
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                        <li class="footer"><a href="${pageContext.request.contextPath}/feedback/list.do" target="iframe">View all</a></li>.
                    </ul>
                </li>
                <script>
                    function selectNew(id){
                        $.post("${pageContext.request.contextPath}/feedback/shuaxin.do",{"id":id}, function (data) {
                            var test="";
                            var elementById = document.getElementById("menus");
                            var index=0;
                            for (index;index<data.length;index++)
                            {
                                test+="<li>";
                                test+="<a href='${pageContext.request.contextPath}/feedback/toView.do?id="+data[index].id+"' onclick='selectNew("+data[index].id+")' target='iframe'>";
                                test+="<i class='fa fa-users text-aqua'></i>"+data[index].content;
                                test+="</a></li>";
                            }
                            var size = document.getElementById("megSize");
                            size.innerText=index;
                            var size1 = document.getElementById("msgSize");
                            size1.innerText="你有"+index+"个新消息";
                            elementById.innerHTML=test;
                        });
                    }
                </script>


                <!-- Tasks: style can be found in dropdown.less -->
                <li class="dropdown tasks-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-flag-o"></i>
                        <span class="label label-danger">9</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">你有9个新任务</li>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <ul class="menu">
                                <li>
                                    <!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Design some buttons
                                            <small class="pull-right">20%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                <span class="sr-only">20% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                                <li>
                                    <!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Create a nice theme
                                            <small class="pull-right">40%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                <span class="sr-only">40% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                                <li>
                                    <!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Some task I need to do
                                            <small class="pull-right">60%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                <span class="sr-only">60% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                                <li>
                                    <!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Make beautiful transitions
                                            <small class="pull-right">80%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                <span class="sr-only">80% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                            </ul>
                        </li>
                        <li class="footer">
                            <a href="#">View all tasks</a>
                        </li>
                    </ul>
                </li>
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="../img/user2-160x160.jpg" class="user-image" alt="User Image">
                        <span class="hidden-xs"> ${sessionScope.user.userName}</span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="../img/user2-160x160.jpg" class="img-circle" alt="User Image">

                            <p>
                                ${sessionScope.loginUser.userName}
                            </p>
                        </li>

                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a data-toggle="modal" onClick="javascript:viewFile()" href="#" data-target="#myModal1">修改密码</a>
                            </div>
                            <div class="pull-right">
                                <a href="/logout.do" class="btn btn-default btn-flat">注销</a>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>
<!-- 页面头部 /-->
<body>
<div id="myModal1" class="modal modal-primary" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <form id="update" action="/system/user/updatePassword.do" method="post">
                <div class="modal-body">
                    <div class="box-body">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label  for="inputPassword1" class="col-sm-2 control-label">原密码:</label>
                                <div class="col-sm-5">
                                    <input type="password" id="inputPassword1" class="form-control" placeholder="原密码" name="oldPass">
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label  for="inputPassword2" class="col-sm-2 control-label">新密码:</label>
                                <div class="col-sm-5">
                                    <input type="password" id="inputPassword2" class="form-control" placeholder="新密码" name="newPass">
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label  for="inputPassword3" class="col-sm-2 control-label">确定密码:</label>
                                <div class="col-sm-5">
                                    <input type="password" id="inputPassword3" class="form-control" placeholder="确定密码" >
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-outline" data-dismiss="modal" onclick=" document.getElementById('update').submit();">保存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>

    <!-- /.modal-dialog -->
</div>
</body>

