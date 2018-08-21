<?php

include("connectserver.php");

$belepokod = $_GET['kod'];

$sql_select = "SELECT nev from tabla where id ='$belepokod'";

$ebbol = mysql_query($sql_select);

while($row = mysql_fetch_array($ebbol))
{
	echo $row['nev'];
	echo "\n";
}



?>