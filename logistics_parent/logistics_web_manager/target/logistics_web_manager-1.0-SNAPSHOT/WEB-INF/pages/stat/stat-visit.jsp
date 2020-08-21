<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            统计分析
            <small>访问次数统计</small>
        </h1>
    </section>
    <section class="content">
        <div class="box box-primary">
            <div id="main" style="width: 100%;height:580px;"></div>
        </div>
    </section>
</div>
</body>
<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="../../plugins/echarts/echarts.min.js"></script>
<script type="text/javascript">
    $.get(
        "/stat/getVisitData.do",
        function (visitData) {
            var titles = [];
            var values = [];
            for (var i = 0; i < visitData.length; i++) {
                titles[i] = visitData[i].name;
                values[i] = visitData[i].value;
            }

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            var option = {
                xAxis: {
                    type: 'category',
                    data: titles,
                    axisLabel: {
                        rotate:70
                    }
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: values,
                    type: 'line',
                    smooth: true
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    );
</script>
</html>