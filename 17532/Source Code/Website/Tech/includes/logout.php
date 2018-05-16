<?php
	session_start();
	$_SESSION['username']=null;
	$_SESSION['email']=null;
	$_SESSION['aadhar']=null;
	session_destroy();
	header("Location : ../pages/index.php ");
?>