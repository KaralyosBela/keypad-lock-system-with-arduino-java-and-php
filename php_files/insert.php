<?php

include("connectserver.php");

$id = $_GET['kod'];
$nev = $_GET['nev'];
$date = time();
$valami = (date("Y-m-d H:i:s",$date));
//$valami = (date("Y-m-d",$date));


$sql_insert = "insert into tabla (id,nev,datum,datum2) values ('$id','$nev','$valami','$valami')";

mysql_query($sql_insert);

if($sql_insert)
{
	echo "Sikerült beilleszteni!";
}
else
	echo "Sikertelen beillesztés!";

?>