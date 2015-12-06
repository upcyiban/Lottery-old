<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>抽奖结果</title>
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
<div id="container" class="container">
    <h1>
        <?php

        include "connect.php";

        $stuid = $_POST['stuid'];
        $result = mysql_query("SELECT * FROM message WHERE stuid='$stuid'");
        if (mysql_num_rows($result) >= 1) {
            echo "你已经抽过奖啦。";
            return;
        }

        $reward = array("0", "0", "0", "0");
        $i = 0;
        $result1 = mysql_query("SELECT * FROM reward");
        while ($row = mysql_fetch_array($result1)) {
            $reward[$i] = $row['number'];
            //echo $row['number'];
            $i++;
        }
        $rand = rand(1, 600);
        if ($reward[0] > 0 && $rand <= 333 && $rand >=330) {
            $reward[0]--;
            mysql_query("UPDATE reward SET number = '$reward[0]' WHERE id = '1'");
            mysql_query("INSERT INTO message (stuid,message) VALUES ('$stuid', '特等奖')");
            echo "恭喜您获得了特等奖！人品真是好的没话说！请赛后现场进行领取!";
        } else if ($reward[1] > 0 && $rand < 10) {
            $reward[1]--;
            mysql_query("UPDATE reward SET number = '$reward[1]' WHERE id = '2'");
            mysql_query("INSERT INTO message (stuid,message) VALUES ('$stuid', '一等奖')");
            echo "恭喜您获得了一等奖！人品真是好的没话说！请赛后现场进行领取！";
        } else if ($reward[2] > 0 && $rand < 20) {
            $reward[2]--;
            mysql_query("UPDATE reward SET number = '$reward[2]' WHERE id = '3'");
            mysql_query("INSERT INTO message (stuid,message) VALUES ('$stuid', '二等奖')");
            echo "恭喜您获得了二等奖！人品不错哦！请赛后现场进行领取！";
        } else if ($reward[3] > 0 && $rand < 30) {
            $reward[3]--;
            mysql_query("UPDATE reward SET number = '$reward[3]' WHERE id = '4'");
            mysql_query("INSERT INTO message (stuid,message) VALUES ('$stuid', '三等奖')");
            echo "恭喜您获得了三等奖！人品不错哦！请赛后现场进行领取！";
        } else {
            mysql_query("INSERT INTO message (stuid,message) VALUES ('$stuid', '未中奖')");
            echo "真是可惜你没中奖，但是不要气馁，欢迎来参加我们的定向越野大赛哦！";
        }
        //echo $reward[0]."  ".$reward[1]."  ".$reward[2]."  ".$reward[3]."</br>";
        //echo $rand;
        ?>
    </h1>
    <footer class="text-center">
        <p>Powered by upcyiban</p>
    </footer>
</div>
</body>
