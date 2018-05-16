<?php include "../includes/db.php" ?>
<?php
  if (isset($_POST['register'])) {
    $username=$_POST['uname'];
    $password=$_POST['psw'];
    $email=$_POST['email'];
    $aadhar=$_POST['anumber'];
    $query="insert into users(username,password,email,aadhar) values('{$username}','{$password}','{$email}','{$aadhar}')";
    $insert_user=mysqli_query($connection,$query);

    echo "<h1>you have successfully registerd</h1>";
    
  }
?>
<?php
  if(isset($_POST['login']))
  {
    $username=$_POST['uname'];
    $password=$_POST['psw'];
    $query="select * from users where username='{$username}' and password='{$password}' ";
    $select=mysqli_query($connection,$query);
    if (mysqli_num_rows($select)) {
      $data=mysqli_fetch_assoc($select);
      session_start();
      $_SESSION['username']=$username;
      $_SESSION['aadhar']=$data['aadhar'];
      $_SESSION['email']=$data['email'];
      echo "<h1>you have successfully logged in</h1>";
    }
    else
    {
      echo "<h1>invalid credentials</h1>";
    }
  }
?>
<?php include "../includes/header.php" ?>
 <div id="pageintro" class="hoc clear"> 
   
    <article><em>By:</em>
      <h2 class="heading">Schalofs</h2>
      <p>University School of Information Technology</p>
      <footer><a class="btn" onclick="document.getElementById('about').style.display='block'">About</a></footer>
    </article>
   
  </div>

</div>

<div class="wrapper row3">
  <main class="hoc container clear"> 

    <ul class="nospace clear services">
      <li class="one_half first borderedbox">
        <div class="inspace-30">
          <h6 class="heading" align = "center">IDEA</h6>
          <p class="nospace">Water is the most important
    resource available to a human being.
    To have clean water and knowledge
    about the available resources is the
    right of every human being living on
    Earth. India faces a big challenge to
    provide clean and safe water supply
    to many areas.</p>
        </div>
      </li>
      <li class="one_quarter">
        <figure><img src="../images/demo/04.jpg" alt="">
          <figcaption>Save Earth</figcaption>
        </figure>
        </li>
      <li class="one_quarter"><a href="#">
        <figure><img src="../images/demo/01.jpg" alt="">
          <figcaption>Save Water</figcaption>
        </figure>
        </a></li>
    </ul>
    <div class="clear"></div>
  </main>
</div>
<div class="wrapper bgded overlay" style="background-image:url('../images/demo/backgrounds/02.jpg');">
  <article class="hoc container clear center"> 
    <h3 class="heading">Our Purpose</h3>
    <p class="btmspace-50">The main problem that the society faces is either lack of available
    resources or not having the awareness and proper knowledge about the
    available resources.</p>
    <footer><a class="btn" onclick="document.getElementById('modal-further').style.display='block'">Further Details</a></footer>
  </article>
</div>
<div class="wrapper row3">
  <section class="hoc container clear"> 
    <div class="center btmspace-80">
      <h3 class="heading">Features</h3>
    </div>
    <ul class="nospace group cta">
      <li class="one_third first">
        <article><span>Ground Water</span>
          <h6 class="heading font-x1"><a href="#"></a></h6>
          <p>A chatbot will help lower the load on the authority offices as they will provide basic details and
      solution to some of the basic problems in the App itself. This will help both the authority and the users as
      the people will have to visit the offices less often and will get details in the App.</p>
          <footer><a href="ground.php">Click Here &raquo;</a></footer>
        </article>
      </li>
      <li class="one_third">
        <article><span>Rain Predictor</span>
          <h6 class="heading font-x1"><a href="#"></a></h6>
          <p>The app will provide information about the
      level of groundwater and the annual rainfall
      received by the area on average in a year. With
      the help of machine learning model and the
      forecast by the weather departments, we can
      predict how much of rainfall will subsequent
      years receive.
      This will also help in preventing overuse of
      groundwater and take measures to face harsh
      climatic conditions by appropriate use of
      rainwater.</p>
          <footer><a href="rain.php">Click Here &raquo;</a></footer>
        </article>
      </li>
      <li class="one_third">
        <article><span>Near By Resources</span>
          <h6 class="heading font-x1"><a href="#"></a></h6>
          <p>Today, the main problem that the society faces is either lack of available
resources or not having the awareness and proper knowledge about the
available resources.
In our app, we intend to provide the solution to this problem using the data
available about the nearest source of water provided by the Government. We
will present the required information in a representable and understandable
manner.
&hellip;</p>
          <footer><a href="predictor.php">Click Here &raquo;</a></footer>
        </article>
      </li>
    </ul>

    <div class="clear"></div>
  </section>
</div>
<?php include "../includes/footer.php"; ?>