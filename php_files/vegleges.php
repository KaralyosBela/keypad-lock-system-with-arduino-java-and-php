<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>Nyílvántartás</title>
	<meta name="viewport" content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
    <link rel='stylesheet' type='text/css' href='formazas.css' />
	</head>

<body>
<div class="table-title">
<h3>Mai nyílvántartás</h3>
</div>
<table class="table-fill">
<thead>
<tr>
<!--<th class="text-left">Belepő kód</th>-->
<th class="text-left">Dolgozó</th>
<th class="text-left">Belépés dátum</th>
</tr>
</thead>
<tbody class="table-hover">
<?php
			include("connectserver.php");
			$lekerdezes = mysql_query("select * from tabla where datum = CURDATE()");
			
			while($tomb = mysql_fetch_array($lekerdezes))
			{
				echo '<tr>';	
				//echo '<td>'.$tomb["id"].'</td>';
				echo '<td>'.$tomb["nev"].'</td>';
				echo '<td>'.$tomb["datum2"].'</td>';
				echo '<tr>';
			}
?>
</tbody>
</table>
  

  </body>