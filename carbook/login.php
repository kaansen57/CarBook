<?php 
	
	define('HOST','localhost');
	define('USER','root');
	define('PASS','');
	define('DB','carbook');
	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Bağlanılamadı...');

	if($_SERVER['REQUEST_METHOD']=='POST'){
 
		$username = $_POST['username'];
		$password = $_POST['password'];
		
		$sql = "SELECT * FROM users WHERE username='$username' AND password='$password'";
	
		$result = mysqli_query($con,$sql);
		$check = mysqli_fetch_array($result);
		
		 
		if(isset($check)){
			echo "success";
		}else{
			echo "failure";
		}
		mysqli_close($con);
	}