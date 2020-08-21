<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../../base.jsp"%>
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
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            商品管理
            <small>修改商品</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">商品管理</a></li>
            <li class="active">修改商品</li>
        </ol>
    </section>
    <!-- 内容头部 /-->
    <form id="editForm" action="${ctx}/cargo/goods/edit.do" method="post">
        <input type="hidden" name="id" value="${goods.id}" >
        <input type="hidden" id="factoryName" name="factoryName" value="${goods.factoryName}">
        <!-- 正文区域 -->
        <section class="content">
            <div class="panel panel-default">
                <div class="panel-heading">货物修改</div>
                    <div class="row data-type" style="margin: 0px">
                        <div class="col-md-2 title">货物名称</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="货物名称" name="name" value="${goods.name}"/>
                        </div>
                        <div class="col-md-2 title">货物价格</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="货物价格" name="price" value="${goods.price}"/>
                        </div>
                        <div class="col-md-2 title">货物描述</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="货物描述" name="describes" value="${goods.describes}"/>
                        </div>
                        <div class="col-md-2 title">货物类型</div>
                        <div class="col-md-4 data">
                            <div class="form-group form-inline">
                                <div class="radio"><label><input type="radio"  name="type" ${goods.type=='货物'?'checked':''} value="货物">货物</label></div>
                                <div class="radio"><label><input type="radio" name="type" ${goods.type=='附件'?'checked':''} value="附件">附件</label></div>
                            </div>
                        </div>
                        <div class="col-md-2 title">生产厂家</div>
                        <div class="col-md-4 data">
                            <select class="form-control" name="factoryId" id="factoryInfo" onchange="document.getElementById('factoryName').value=this.options[this.selectedIndex].text">
                                <option value="">请选择</option>
                                <c:forEach items="${factoryList}" var="factory">
                                    <option value="${factory.id}" ${goods.factoryId eq factory.id ? "selected" : ""}>
                                            ${factory.factoryName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            <!-- .box-body -->
            <!--工具栏-->
            <%--  <div class="box-tools text-center">
                  <button type="submit"  class="btn bg-maroon">保存</button>
                  <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
              </div>--%>
            <!--工具栏/-->
            <!--工具栏-->
            <div class="box-tools text-center">
                <button type="submit"  class="btn bg-maroon">保存</button>
                <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
            </div>
            <!--工具栏/-->
        </section>
    </form>
</div>
<!-- 内容区域 /-->
</body>
</html>