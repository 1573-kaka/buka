<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>平头物流管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
</head>

<body class="hold-transition skin-purple sidebar-mini" >

<div class="tab-pane" id="tab-model">
    <button type="button" id="btnMessage" style="display: none" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
        弹出模式对话框
    </button>

    <div id="myModal" class="modal modal-primary" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" id="closeBtn" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">您有${messageSize}条未读平台反馈</h4>
                </div>
                <div class="modal-body">

                    <div class="box-body">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <table id="ddd" class="table">
                                    <thead>
                                    <tr>
                                        <th class="sorting">标题</th>
                                        <th class="sorting">提交人</th>
                                        <th class="sorting">是否已读</th>
                                        <th class="sorting">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${message}" var="s">
                                        <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                            <td>${s.content}</td>
                                            <td>${s.sendPer}</td>
                                            <td>${s.state==0?'未读':'已读'}</td>
                                            <td><a style="color:yellow" target="iframe" onclick="closebtn()" href="${pageContext.request.contextPath}/feedback/toView.do?id=${s.id}">查看</a></td>
                                        </tr>

                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" onclick="toView()" class="btn btn-outline" data-dismiss="modal">查看</button>
                        <button type="button" class="btn btn-outline" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="wrapper">
    <!-- 页面头部 -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- 导航侧栏 -->
    <jsp:include page="aside.jsp"></jsp:include>
    <!-- 导航侧栏 /-->
    <!-- 内容区域 -->
    <div class="content-wrapper">
        <iframe id="iframe" name="iframe"
                style="overflow:visible;"
                scrolling="auto"
                frameborder="no" height="100%" width="100%"
                src="/home.do"></iframe>
    </div>
    <!-- 内容区域 /-->

    <!-- 底部导航 -->
    <jsp:include page="footer.jsp"></jsp:include>
    <!-- 底部导航 /-->
</div>


<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="../plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<script src="../plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="../plugins/raphael/raphael-min.js"></script>
<script src="../plugins/morris/morris.min.js"></script>
<script src="../plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="../plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="../plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="../plugins/knob/jquery.knob.js"></script>
<script src="../plugins/daterangepicker/moment.min.js"></script>
<script src="../plugins/daterangepicker/daterangepicker.js"></script>
<script src="../plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
<script src="../plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script src="../plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="../plugins/fastclick/fastclick.js"></script>
<script src="../plugins/iCheck/icheck.min.js"></script>
<script src="../plugins/adminLTE/js/app.min.js"></script>
<script src="../plugins/treeTable/jquery.treetable.js"></script>
<script src="../plugins/select2/select2.full.min.js"></script>
<script src="../plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script src="../plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
<script src="../plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script src="../plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
<script src="../plugins/bootstrap-markdown/js/markdown.js"></script>
<script src="../plugins/bootstrap-markdown/js/to-markdown.js"></script>
<script src="../plugins/ckeditor/ckeditor.js"></script>
<script src="../plugins/input-mask/jquery.inputmask.js"></script>
<script src="../plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="../plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script src="../plugins/datatables/jquery.dataTables.min.js"></script>
<script src="../plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="../plugins/chartjs/Chart.min.js"></script>
<script src="../plugins/flot/jquery.flot.min.js"></script>
<script src="../plugins/flot/jquery.flot.resize.min.js"></script>
<script src="../plugins/flot/jquery.flot.pie.min.js"></script>
<script src="../plugins/flot/jquery.flot.categories.min.js"></script>
<script src="../plugins/ionslider/ion.rangeSlider.min.js"></script>
<script src="../plugins/bootstrap-slider/bootstrap-slider.js"></script>
<script src="../plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
<script src="../plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script>
    function setIframeHeight(iframe) {
        if (iframe) {
            var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
            var height = iframeWin.document.getElementById('frameContent').scrollHeight +20 ;
            iframe.height =height;
        }
    };

    var iframe= document.getElementById("iframe");
    iframe.onload = function () {
        setIframeHeight(iframe);
    };
</script>
<script>
    $(function () {
        if (${not empty message})
        {
            $("#btnMessage").click();
        }
    });
</script>
<script>
    function toView() {
        <%--location.href="${pageContext.request.contextPath}/feedback/list.do";--%>
        window.open("${pageContext.request.contextPath}/feedback/list.do","iframe")
    }
    function closebtn() {
        $("#closeBtn").click();
    }
</script>
</body>

</html>
<!---->
