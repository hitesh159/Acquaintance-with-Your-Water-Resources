<?php include "../includes/header.php" ?>

</div>
<div class="wrapper row2">
  <div id="breadcrumb" class="hoc clear"> 
    <ul>
      <li><a href="index.php">Home</a></li>
      <li><a href="">Find Water</a></li>
      <li><a href="ground.php">Ground Water</a></li>
    </ul>
  
  </div>
 

  <form action="" method="post">
   <h2> 
   Select the District for Ground Water Level - <select name="tehsil">
  <?php
  $data=file_get_contents("http://hitesh159.pythonanywhere.com/tehsil_list");
  $decoded=json_decode($data)->tehsils;
  foreach ($decoded as $key=>$value) { 
    echo '<option value="'.$value.'">'.$value.'</option>';
     

  }
?> 
  </select>
  <br><br>
  <div class="container" align = "center">

    <button type="submit" name="predict">Submit</button>
    
  </div>
</h2>
</form>

<?php
  if (isset($_POST['predict'])) {
    //echo $value;
    
    $tehsil=$_POST['tehsil'];
    $data=file_get_contents("http://hitesh159.pythonanywhere.com/gw_level/?tehsil=".urlencode($tehsil));
    $value=json_decode($data)->category;
    $value1=json_decode($data)->level;
    ?><p align="center"><h2 align="center">The Ground Level of <?php echo $tehsil; ?> is <?php echo $value1; ?> for category <?php if($value==1) echo "Unsafe"; else if ($value==2) echo "Moderate"; else echo "Safe"; 
      
    ?><br><br>This cateogarisation is based on the groundwater level.</h2></p>
  <?php }
?>



<?php include "../includes/footer.php"; ?>