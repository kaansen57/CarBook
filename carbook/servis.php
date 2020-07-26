<?php

$servername = "localhost";
$username = "root";
$password = "";
$db = "carbook";

$conn = mysqli_connect($servername, $username, $password, $db);

if(isset($_GET['carGet'])) {

    $sqlquery = " SELECT * FROM carbrand ";
    $result = mysqli_query($conn, $sqlquery);
    $json = array();
    while($row = mysqli_fetch_array($result))
    {
        $json[] = $row;
    }

    echo json_encode($json);
  
}
?>