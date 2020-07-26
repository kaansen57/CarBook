<?php

$servername = "localhost";
$username = "root";
$password = "";
$db = "carbook";

$conn = mysqli_connect($servername, $username, $password, $db);

if($_SERVER['REQUEST_METHOD'] == 'GET') {
 	$carid = $_GET['carid'];
    $sqlquery = " SELECT * FROM carmodel WHERE carbrid = '$carid' ";
    $result = mysqli_query($conn, $sqlquery);
    $json = array();
		while($row = mysqli_fetch_array($result))
		 {
        	$json[] = $row;
   		 }
    echo json_encode($json);
}
?>