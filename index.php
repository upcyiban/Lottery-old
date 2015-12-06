<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>抽奖活动</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <script src="js/jquery-2.1.4.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="http://www.yiban.cn/school/index/id/5370538">易班</a>
        </div>
    </div>
</nav>
<div id="container" class="container">
    <h1>石大易班线上抽奖页面</h1>

    <div class="panel panel-default">
        <div class="panel-body">
            <form action="server.php" method="post">
                <div class="form-group">
                    <label for="stuid">输入学号抽奖</label>
                    <input class="form-control" type="text" id="stuid" name="stuid" placeholder="请输入学号"></br>
                    <input type="submit" class="btn btn-primary form-control">
                </div>
            </form>
            <p>
                使用说明：每个人只有一次机会，输入学号点击提交即可进行抽奖，请勿输入他人学号，也不要乱输入学号。中奖的同学将会被记录，活动进行时到现场进行领奖即可！
            </p>
        </div>
    </div>

    <footer class="text-center">
        <p>Powered by upcyiban</p>
    </footer>
</div>


</body>
</html>