<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>抽奖活动</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="http://www.yiban.cn/school/index/id/5370538">易班</a>
        </div>
    </div>
</nav>
<div class="resultmain">
    <div class="container-fluid">
        <div class="row">
            <h1>中奖用户</h1>
            <br><br><br>

            <div class="col-md-2"></div>
            <div class="col-md-8">
                <table class="table">
                    <thead>
                    <tr>
                        <th class="text-center">序号</th>
                        <th class="text-center">中奖人学号</th>
                        <th class="text-center">中奖等级</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
                    include "connect.php";
                    $result = mysql_query("SELECT * FROM message WHERE message='特等奖'");
                    $i = 1;
                    while ($row = mysql_fetch_array($result)) {
                        echo "<tr><td>$i</td><td>" . $row['stuid'] . "</td><td>特等奖</td></tr>";
                        $i++;
                    }
                    $result = mysql_query("SELECT * FROM message WHERE message='一等奖'");
                    while ($row = mysql_fetch_array($result)) {
                        echo "<tr><td>$i</td><td>" . $row['stuid'] . "</td><td>一等奖</td></tr>";
                        $i++;
                    }
                    $result = mysql_query("SELECT * FROM message WHERE message='二等奖'");
                    while ($row = mysql_fetch_array($result)) {
                        echo "<tr><td>$i</td><td>" . $row['stuid'] . "</td><td>二等奖</td></tr>";
                        $i++;
                    }
                    $result = mysql_query("SELECT * FROM message WHERE message='三等奖'");
                    while ($row = mysql_fetch_array($result)) {
                        echo "<tr><td>$i</td><td>" . $row['stuid'] . "</td><td>三等奖</td></tr>";
                        $i++;
                    }
                    ?>
                    </tbody>
                </table>
            </div>
            <div class="col-md-2"></div>
        </div>
    </div>
</div>
</body>
</html>