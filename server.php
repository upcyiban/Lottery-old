<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>抽奖结果</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<h1 class="h1 result">
<?php
/**
 * Created by IntelliJ IDEA.
 * User: skyADMIN
 * Date: 15/10/21
 * Time: 下午8:24
 */

include "connect.php";

$stuid = $_POST['stuid'];
$result = mysql_query("SELECT * FROM message WHERE stuid='$stuid'");
if (mysql_num_rows($result) >= 1){
    echo "你已经抽过奖啦。";
    return ;
}

$reward = array("0","0","0","0");
$i = 0;
$result1 = mysql_query("SELECT * FROM reward");
while($row = mysql_fetch_array($result1)) {
    $reward[$i]=$row['number'];
    //echo $row['number'];
    $i++;
}
$rand = rand(1,200);
if($reward[0]>0 && $rand<2){
    $reward[0]--;
    mysql_query("UPDATE reward SET number = '$reward[0]' WHERE id = '1'");
    mysql_query("INSERT INTO message (stuid,message) VALUES ('$stuid', '特等奖')");
    echo "恭喜获得特等奖，请到现场来领奖！";
}else if($reward[1]>0 && $rand<10){
    $reward[1]--;
    mysql_query("UPDATE reward SET number = '$reward[1]' WHERE id = '2'");
    mysql_query("INSERT INTO message (stuid,message) VALUES ('$stuid', '一等奖')");
    echo "恭喜获得一等奖，请到现场来领奖！";
}else if($reward[2]>0 && $rand<20){
    $reward[2]--;
    mysql_query("UPDATE reward SET number = '$reward[2]' WHERE id = '3'");
    mysql_query("INSERT INTO message (stuid,message) VALUES ('$stuid', '二等奖')");
    echo "恭喜获得二等奖，请到现场来领奖！";
}else if($reward[3]>0 && $rand<30){
    $reward[3]--;
    mysql_query("UPDATE reward SET number = '$reward[3]' WHERE id = '4'");
    mysql_query("INSERT INTO message (stuid,message) VALUES ('$stuid', '三等奖')");
    echo "恭喜获得三等奖，请到现场来领奖！";
}else{
    mysql_query("INSERT INTO message (stuid,message) VALUES ('$stuid', '未中奖')");
    echo "可惜，你没中奖！";
}
//echo $reward[0]."  ".$reward[1]."  ".$reward[2]."  ".$reward[3]."</br>";
//echo $rand;
?>
    </h1>
</body>
