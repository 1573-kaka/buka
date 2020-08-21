<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            反馈管理
            <small>反馈表单</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">反馈管理</a></li>
            <li class="active">反馈表单</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">反馈信息详情</div>
            <form id="editForm" action="${ctx}/cargo/feedback/edit.do" method="post">
                <input type="hidden" name="id" value="${feedback.id}">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">id</div>
                    <div class="col-md-4 data" style="line-height:34px">
                        ${feedback.id}
                    </div>

                    <div class="col-md-2 title">反馈人</div>
                    <div class="col-md-4 data" style="line-height:34px">
                        ${feedback.sendPer}
                    </div>

                    <div class="col-md-2 title">接受邮箱</div>
                    <div class="col-md-4 data" style="line-height:34px">
                        <div class="input-group date">
                            ${feedback.receivePer}
                        </div>
                    </div>

                    <div class="col-md-2 title">状态</div>
                    <div class="col-md-4 data" style="line-height:34px">
                       ${feedback.state==0?'未读':'已读'}
                    </div>

                    <div class="col-md-2 title">发送邮箱</div>
                    <div class="col-md-4 data" style="line-height:34px">
                        ${feedback.sendMail}
                    </div>

                    <div class="col-md-2 title">内容</div>
                    <div class="col-md-4 data" style="line-height:34px">
                        ${feedback.content}
                    </div>
                </div>
          </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

</div>
<!-- 内容区域 /-->
</body>
<script src="../../plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="../../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="../../css/style.css">
<script>
    $('#signingDate').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#deliveryPeriod').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#shipTime').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>
</html>