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
            厂家管理
            <small>修改厂家</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">厂家管理</a></li>
            <li class="active">修改厂家</li>
        </ol>
    </section>
    <!-- 内容头部 /-->
    <form id="editForm" action="${ctx}/cargo/factory/edit.do" method="post">
        <input type="hidden" name="id" value="${factory.id}" >
        <%--<input type="hidden" name="contractNo" value="${orderticket.customerContract}">--%>
        <!-- 正文区域 -->
        <section class="content">
            <div class="panel panel-default">
                <div class="panel-heading">厂家修改</div>
                    <div class="row data-type" style="margin: 0px">
                        <div class="col-md-2 title">工厂名称</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="工厂名称" name="fullName" value="${factory.fullName}"/>
                        </div>
                        <div class="col-md-2 title">简称</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="简称" name="factoryName"  value="${factory.factoryName}"/>
                        </div>
                        <div class="col-md-2 title">联系人</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="联系人" name="contacts"  value="${factory.contacts}"/>
                        </div>
                        <div class="col-md-2 title">电话</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="电话" name="phone"  value="${factory.phone}"/>
                        </div>
                        <div class="col-md-2 title">手机</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="手机" name="mobile" value="${factory.mobile}"/>
                        </div>
                        <div class="col-md-2 title">传真</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="传真" name="fax"  value="${factory.fax}"/>
                        </div>
                        <div class="col-md-2 title">地址</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="地址" name="address"  value="${factory.address}"/>
                        </div>
                        <div class="col-md-2 title">验货员</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="验货员" name="inspector"  value="${factory.inspector}"/>
                        </div>
                        <div class="col-md-2 title">说明</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="说明" name="remark"  value="${factory.remark}"/>
                        </div>
                        <div class="col-md-2 title">排序号</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="排序号" name="orderNo" value="${factory.orderNo}"/>
                        </div>
                        <div class="col-md-2 title">状态</div>
                        <div class="col-md-4 data">
                            <div class="form-group form-inline">
                                <div class="radio"><label><input type="radio"  ${factory.state==0?'checked':''} name="state" value="0">停用</label></div>
                                <div class="radio"><label><input type="radio" ${factory.state==0?'checked':''} name="state" value="1">正常</label></div>
                            </div>
                        </div>
                        <div class="col-md-2 title">厂家类型</div>
                        <div class="col-md-4 data">
                            <div class="form-group form-inline">
                                <div class="radio"><label><input type="radio"  ${factory.ctype=='货物'?'checked':''} name="ctype" value="货物">货物</label></div>
                                <div class="radio"><label><input type="radio" ${factory.ctype=='附件'?'checked':''} name="ctype" value="附件">附件</label></div>
                            </div>
                        </div>
                    </div>
                </div>
            <!-- .box-body -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">报运单列表</h3>
                </div>

                <div class="box-body">

                    <!-- 数据表格 -->
                    <div class="table-box">
                        <!--数据列表-->
                        <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                            <thead>
                            <tr>
                                <td><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                                <th class="sorting">名称</th>
                                <th class="sorting">单价</th>
                                <th class="sorting">类型</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${factory.goods}" var="o" varStatus="status">
                                    <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                        <td><input type="checkbox" name="id" value="${o.id}"/></td>
                                        <td>${o.name}</td>
                                        <td>${o.price}</td>
                                        <td>
                                            <c:if test="${o.type=='货物'}">货物</c:if>
                                            <c:if test="${o.type=='附件'}"><font color="green">附件</font></c:if>
                                                <%-- <c:if test="${o.state==2}"><font color="red">已报运</font></c:if>--%>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <!--数据列表/-->
                        <!--工具栏/-->
                    </div>
                    <!-- 数据表格 /-->
                </div>
                <!-- /.box-body -->

            </div>

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