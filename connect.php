<?php
/**
 * Created by IntelliJ IDEA.
 * User: skyADMIN
 * Date: 15/10/21
 * Time: 下午8:26
 */
$con = mysql_connect("138.128.212.138","yiban","yiban");
if (!$con)
{
    die('Could not connect: ' . mysql_error());
}
mysql_select_db("yiban", $con);
// some code
