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
    <title>平头物流管理系统</title>
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
            厂家管理
            <small>新增厂家</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">厂家管理</a></li>
            <li class="active">新增厂家</li>
        </ol>
    </section>
    <!-- 正文区域 -->
    <section class="content">
        <form action="/cargo/factory/edit.do" method="post">
            <div class="panel panel-default">
                <div class="row data-type" style="margin: 0px">
                    <div class="row data-type" style="margin: 0px">
                        <div class="col-md-2 title">工厂名称</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="工厂名称" name="fullName"/>
                        </div>
                        <div class="col-md-2 title">简称</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="简称" name="factoryName"/>
                        </div>
                        <div class="col-md-2 title">联系人</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="联系人" name="contacts"/>
                        </div>
                        <div class="col-md-2 title">电话</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="电话" name="phone"/>
                        </div>
                        <div class="col-md-2 title">手机</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="手机" name="mobile"/>
                        </div>
                        <div class="col-md-2 title">传真</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="传真" name="fax"/>
                        </div>
                        <div class="col-md-2 title">地址</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="地址" name="address"/>
                        </div>
                    <div class="col-md-2 title">验货员</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="验货员" name="inspector"/>
                    </div>
                        <div class="col-md-2 title">说明</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="说明" name="remark"/>
                        </div>
                        <div class="col-md-2 title">排序号</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="排序号" name="orderNo"/>
                        </div>
                    <div class="col-md-2 title">状态</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio"  name="state" value="0" checked>停用</label></div>
                            <div class="radio"><label><input type="radio" name="state" value="1">正常</label></div>
                        </div>
                    </div>
                        <div class="col-md-2 title">厂家类型</div>
                        <div class="col-md-4 data">
                            <div class="form-group form-inline">
                                <div class="radio"><label><input type="radio"  name="ctype" value="货物" checked>货物</label></div>
                                <div class="radio"><label><input type="radio" name="ctype" value="附件">附件</label></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--工具栏-->
            <div class="box-tools text-center">
                <button type="submit"  class="btn bg-maroon">保存</button>
                <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
            </div>
        </form>
        <!--工具栏/-->
    </section>
</div>
<!-- 内容区域 /-->
</body>
</html>