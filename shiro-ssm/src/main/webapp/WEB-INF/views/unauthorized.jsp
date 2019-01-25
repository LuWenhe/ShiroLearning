<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>unauthorized</title>
    <style type="text/css">
        span.desc{
            margin-left:20px;
            color:gray;
        }
        div.workingroom{
            margin:200px auto;
            width:400px;
        }
        div.workingroom a{
            display:inline-block;
            margin-top:20px;
        }
        div.loginDiv{
            text-align: left;
        }
        div.errorInfo{
            color:red;
            font-size:0.65em;
        }
    </style>
</head>
<body>

    <div class="workingroom">
        权限不足,具体原因：${ex.message}
        <br>
        <a href="#" onClick="javascript:history.back()">返回</a>
    </div>

</body>
</html>
