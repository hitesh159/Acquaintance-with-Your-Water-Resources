<div class="bgded overlay" style="background-image:url('../images/demo/backgrounds/03.jpg');">
  <footer id="footer" class="hoc clear center"> 
    <h3 class="heading uppercase">Water Acquaintance</h3> 
  </footer>
  <div id="copyright" class="hoc clear center"> 
    <p>Copyright &copy; 2018 - All Rights Reserved - Schalofs</p>
  </div>
  
</div>
<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a>  
<script src="../layout/scripts/jquery.min.js"></script>
<script src="../layout/scripts/jquery.backtotop.js"></script>
<script src="../layout/scripts/jquery.mobilemenu.js"></script>
<div id="modal-login" class="modal">
  
  <form class="modal-content animate" action="" method="post">
        
    <div class="imgcontainer">
      <span onclick="document.getElementById('modal-login').style.display='none'" class="close" title="Close PopUp">&times;</span>
      <img src="../login.jpg" alt="Avatar" class="avatar">
    </div>

    <div class="container">
      <?php if(!isset($_SESSION['username'])): ?>
      <input type="text" placeholder="Enter Username" name="uname">
      <input type="password" placeholder="Enter Password" name="psw">        
      <button type="submit" name="login">Login</button>
	 <p style="color:black;" align="center">
		 <input type="checkbox" align="center"> Remember Me
     <?php else: ?>
      <h2><p style="color:black;" align="center">You Aready Login in</p></h2><br><button type="submit"><a href="../includes/logout.php">Logout</a></button>
	  </p>
    <?php endif; ?>
	 </div>
		
    
  </form>
  
</div>

<div id="modal-register" class="modal">
  
  <form class="modal-content animate" action="" method="post">
        
    <div class="imgcontainer">
      <span onclick="document.getElementById('modal-register').style.display='none'" class="close" title="Close PopUp">&times;</span>
      <img src="../register.jpg" alt="Avatar" class="avatar">
    </div>

    <div class="container">
      <input type="number" placeholder="Enter Aadar Card Number" name="anumber">
	  <input type="text" placeholder="Enter Username" name="uname">
	   <input type="email" placeholder="Enter Email" name="email">
      <input type="password" placeholder="Enter Password" name="psw">        
      <button type="submit" name="register">Register</button>
	 </div>
  </form>
  
</div>

<div id="modal-further" class="modal">

    <h1 align="center">OUR PURPOSE</h1><br>
    <p align="center"><h2>
       Our main purpose is to bridge the gap between the people living in that area
      and the resources provided by the Government nearby.<br><br>
      Today, the main problem that the society faces is either lack of available
      resources or not having the awareness and proper knowledge about the
      available resources.<br><br>
      In our app, we intend to provide the solution to this problem using the data
      available about the nearest source of water provided by the Government. We
      will present the required information in a representable and understandable
      manner.
  </h2></p>
  <script>
// Get the modal
var modal = document.getElementById("modal-further");

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>

</div>
<div id="about" class="modal">

    <h1 align="center">ABOUT US</h1><br>
    <p align="center"><h2>
       The main purpose is to bridge the gap between the people living in that area
       and the resources provided by the Government nearby.<br><br>
       The information about the
       level of groundwater and the annual rainfall
       received by the area on average in a year will be displayed and updated.<br><br>
       The locations of nearby water resources will be shown in a 2d map.<br>
  </h2></p>
   <script>
// Get the modal
var modal = document.getElementById("about");

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>

</div>

</body>
</html>