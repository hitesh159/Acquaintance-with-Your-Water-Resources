<?php include "../includes/header.php";
session_start(); ?>
<div class="wrapper row2">
  <div id="breadcrumb" class="hoc clear"> 
    <ul>
      <li><a href="index.php">Home</a></li>
	  <li><a href="authority.php">Account Details</a></li>
    </ul>
  
  </div>
</div>

<div class="wrapper row3">
  <main class="hoc container clear"> 
    <div class="content"> 
	   <h1> Aadar Card Number :- <?php echo $_SESSION['aadhar']; ?></h1>
	   <h1>Name :-<?php echo $_SESSION['username']; ?> </h1>      
      <h1>Contact :- </h1>
	  <h1>Email :- <?php echo $_SESSION['email']; ?></h1>
    <div class="clear"></div>
  </main>
</div>

<?php include "../includes/footer.php"; ?>