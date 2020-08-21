<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <base href="${ctx}/">
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>平头物流管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
    <link rel="stylesheet" href="plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="plugins/ztree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>

    <script type="text/javascript">

        var zTreeObj;
        var setting = {
            check: {
                enable: true,
                chkStyle: "checkbox"
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        $(document).ready(function () {
            var url = "/system/role/getZtreeNodes.do";
            var param = {"roleid": "${role.id}"};
            //ajax调后台方法
            $.get(url, param, function (data) {
                // 调用初始化树的方法
                initZtree(data);
            }, "json");

        });

        var oldmoduleIds = "";

        function initZtree(zNodes) {

            for (i = 0; i < zNodes.length; i++) {
                if (zNodes[i].checked == "true") {
                    if (oldmoduleIds != "") {
                        oldmoduleIds += ",";
                    }
                    oldmoduleIds += zNodes[i].id;
                }
            }

            $("#oldmoduleIds").val(oldmoduleIds); //保留原始菜单id

            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            //展开树结点
            zTreeObj.expandAll(true);

        }


        //获取所有选择的节点
        function submitCheckedNodes() {
            var nodes = new Array();
            nodes = zTreeObj.getCheckedNodes(true);		//取得选中的结点
            var str = "";
            for (i = 0; i < nodes.length; i++) {
                if (str != "") {
                    str += ",";
                }
                str += nodes[i].id;
            }
            // 使用jq给隐藏域赋值
            $('#moduleIds').val(str);

            //提交表单
            $("form").submit();
        }
    </script>
</head>

<body style="overflow: visible;">
<div id="frameContent" class="content-wrapper" style="margin-left:0px;height: 1200px" >
    <section class="content-header">
        <h1>
            菜单管理
            <small>菜单列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">角色 [${role.name}] 权限列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">
                    <!--工具栏-->
                    <div class="box-tools text-left">
                        <button type="button" class="btn bg-maroon" onclick="submitCheckedNodes();">保存</button>
                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                    </div>
                    <!--工具栏/-->
                    <!-- 树菜单 -->
                    <form id="icform" name="icform" method="post" action="/system/role/updateRoleModule.do">
                        <input type="hidden" name="roleid" value="${role.id}"/>
                        <input type="hidden" id="oldmoduleIds" name="oldmoduleIds" value=""/>
                        <input type="hidden" id="moduleIds" name="moduleIds" value=""/>
                        <div class="content_wrap">
                            <div class="zTreeDemoBackground left" style="overflow: visible">
                                <ul id="treeDemo" class="ztree"></ul>
                            </div>
                        </div>

                    </form>
                    <!-- 树菜单 /-->

                </div>
                <!-- /数据表格 -->

            </div>
            <!-- /.box-body -->
        </div>
    </section>
</div>
</body>
</html>