<%--
  Created by IntelliJ IDEA.
  User: haoren
  Date: 2020/11/30
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>

    <script type="text/javascript">
        $(function () {

            $("#btn01").click(function () {
                $.ajax({
                    url : "send/array.html",
                    data:{'array':[5,8,12]},
                    dataType: "text",
                    success: function (data) {
                        console.log(data)
                    },
                    error:function (data) {
                        console.log(data);
                    }
                })
            })

            $("#btn02").click(function () {
                var array = [5,8,12];
                var requestBody = JSON.stringify(array);

                $.ajax({
                    url: "send/array/json.json",
                    type: "post",
                    data: requestBody,
                    contentType:"application/json;charset=UTF-8",
                    dataType: "json",
                    success:function (data) {
                        console.log(data)
                    },
                    error:function (data) {
                        console.log(data)
                    }
                })

            })

            $("#btn03").click(function () {
                layer.msg("Hello!")

            })


        })
    </script>
</head>
<body>

    <a href="test/ssm.html">测试ssm</a>
    <br/>
    <br/>

    <button id="btn01"> send [5,8,12] one</button>
    <br/>
    <br/>

    <button id="btn02">send [5,8,12] two</button>
    <br/>
    <br/>

    <button id="btn03">layer</button>

</body>
</html>
