<?php include "../includes/header.php" ?>

</div>
<div class="wrapper row2">
  <div id="breadcrumb" class="hoc clear"> 
    <ul>
      <li><a href="../index.html">Home</a></li>
      <li><a href="">Find Water</a></li>
      <li><a href="../pages/rain.html">Rainfall</a></li>
    </ul>
  
  </div>
 

  <form action="" method="post">
   <h2> 
   Select the State for Rainfall Prediction - <select name="state">
  <?php
  $data=file_get_contents("http://hitesh159.pythonanywhere.com/get_all/");
  $decoded=json_decode($data)->states;
  foreach ($decoded as $key=>$value) { 
    echo '<option value="'.$value.'">'.$value.'</option>';
     

  }
?>

  
  </select>
  <br>
  Select the Month for Rainfall Prediction - 
 <select name = "month">
  <?php
  $data=file_get_contents("http://hitesh159.pythonanywhere.com/get_months/");
  $decoded=json_decode($data)->months;
  foreach ($decoded as $key=>$value) {
    /*<!-- <option value='<?php echo $value; ?>'><?php echo $value; ?></option>
    //<optgroup> </optgroup> -->*/
    echo '<option value='.$value.'>'.$value.'</option>';
    

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
    $month=$_POST['month'];
    $state=$_POST['state'];
    $data=file_get_contents("http://hitesh159.pythonanywhere.com/rainfall_predict?name=".urlencode($state)."&month={$month}");
    $value=json_decode($data)->ans;
    ?>


    <div id="chartdiv" style="width: 100%; height: 400px;"></div>
     <p><h2>The Predicted Result : <?php echo $value; ?> mm <p id="demo"></p> State : <?php echo $state ?> , Month : <?php echo $month ?></h2></p>
 
<script src="../amcharts/amcharts.js" type="text/javascript"></script>
<script src="../amcharts/serial.js" type="text/javascript"></script>
<script type="text/javascript">
  var month="<?php echo $_POST['month'] ?>";
  var state="<?php echo $_POST['state'] ?>";
  console.log(month+ " "+ state);
  var request = new XMLHttpRequest();
  request.open('GET', 'http://hitesh159.pythonanywhere.com/graph/?name='+state+'&month='+month, false);  // `false` makes the request synchronous
  request.send(null);

  if (request.status === 200) {
    console.log(request.responseText);
    var data=JSON.parse(request.responseText);
    console.log(data.ans);
  }

    var chart;
    var avg = 0;
            var chartData = [];
            var sec = 2004;
            for(var i = 0;i<data.ans.length;i++){
                chartData.push({"country" : sec , "visits" : data.ans[data.ans.length-i-1]});
                avg+=data.ans[data.ans.length-i-1];
                sec+=1;
            }
            avg-=data.ans[0];
            avg/=14;
            AmCharts.ready(function () {
                // SERIAL CHART
                chart = new AmCharts.AmSerialChart();
                chart.dataProvider = chartData;
                chart.categoryField = "country";
                chart.startDuration = 1;

                // AXES
                // category
                var categoryAxis = chart.categoryAxis;
                categoryAxis.labelRotation = 90;
                categoryAxis.gridPosition = "start";

                // value
                // in case you don't want to change default settings of value axis,
                // you don't need to create it, as one value axis is created automatically.

                // GRAPH
                var graph = new AmCharts.AmGraph();
                graph.valueField = "visits";
                graph.balloonText = "[[category]]: <b>[[value]]</b>";
                graph.type = "column";
                graph.lineAlpha = 0;
                graph.fillAlphas = 0.8;
                chart.addGraph(graph);

                // CURSOR
                var chartCursor = new AmCharts.ChartCursor();
                chartCursor.cursorAlpha = 0;
                chartCursor.zoomable = false;
                chartCursor.categoryBalloonEnabled = false;
                chart.addChartCursor(chartCursor);

                chart.creditsPosition = "top-right";

                chart.write("chartdiv");
            });
            if(avg>data.ans[0]){
              
              var str = "(Below Average)";
               
               document.getElementById("demo").innerHTML = str.fontcolor("red");

            }else{
              var str = "(Above Average)";
              document.getElementById("demo").innerHTML = str.fontcolor("blue");

            }



  </script>











   
  <?php }
?>

 
<?php include "../includes/footer.php"; ?>