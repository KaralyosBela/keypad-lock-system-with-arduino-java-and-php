<?php

include("connectserver.php");

//$nev = $_GET['nev'];

//$sql_result = "SELECT id FROM tabla WHERE nev='$nev'";
$sql_result = "SELECT id FROM tabla WHERE nev='proba'";

$eredmeny = mysql_query($sql_result);

if($eredmeny)
{
	echo "Siker!";
}
else
	echo "Sikertelen!";

while($row = mysql_fetch_array($eredmeny)) {
  echo $row['id'];
  echo "<br>"; 
}

?>