<?php

	$felhasznalonev = "root";
	$jelszo = "Karalyos1994";
	$hoszt = "localhost";
	
	$csatlakozas = mysql_connect($hoszt,$felhasznalonev,$jelszo);
	$select = mysql_select_db('arduino', $csatlakozas);
	
	/*if($csatlakozas)
	{
		echo "Sikeresen csatlakoztál az adatbázishoz!";
	}
	else
	{
		echo "Sikertelen csatlakozás az adatbázishoz!";
	}*/

?>