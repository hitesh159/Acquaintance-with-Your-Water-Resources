<!DOCTYPE html>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Resources</title>
  <style>
    #map{
      height:590px;
      width:100%;
    }
  </style>
</head>
<body>
  <h1>Resources Near You</h1>
  <div id="map"></div>
  <script>
    function initMap(){
      // Map options
      var options = {
        zoom:8,
        center:{lat:26.27222222,lng:79.05833333}
      }

      // New map
      var map = new google.maps.Map(document.getElementById('map'), options);

      

      // Listen for click on map
      google.maps.event.addListener(map, 'click', function(event){

        // Add marker
        addMarker({coords:event.latLng});
        infowindow.open(map,marker);

      });
      var icon2 = {
    url: "https://lh4.ggpht.com/Tr5sntMif9qOPrKV_UVl7K8A_V3xQDgA7Sw_qweLUFlg76d_vGFA7q1xIKZ6IcmeGqg=w300", // url
    scaledSize: new google.maps.Size(30, 30), // scaled size
    origin: new google.maps.Point(0,0), // origin
    anchor: new google.maps.Point(0, 0) // anchor
};
 var icon3 = {
    url: "http://files.softicons.com/download/web-icons/vista-map-markers-icons-by-icons-land/png/256x256/MapMarker_Marker_Outside_Azure.png", // url
    scaledSize: new google.maps.Size(30, 30), // scaled size
    origin: new google.maps.Point(0,0), // origin
    anchor: new google.maps.Point(0, 0) // anchor
};
 var icon1 = {
    url: "http://ceramicapeno.es/wp-content/uploads/2016/06/address.png", // url
    scaledSize: new google.maps.Size(30, 30), // scaled size
    origin: new google.maps.Point(0,0), // origin
    anchor: new google.maps.Point(0, 0) // anchor
};
      var request = new XMLHttpRequest();
request.open('GET', 'http://hitesh159.pythonanywhere.com/grd_lat_lng/', false);  // `false` makes the request synchronous
request.send(null);

if (request.status === 200) {
  console.log(request.responseText);
  var data=JSON.parse(request.responseText);
  for(var i=0;i<data.length;i++)
  {

    if(data[i].Category===3){
    var marker = new google.maps.Marker({
        position:{lat:data[i].LAT,lng:data[i].LON},
        map:map,
        icon: icon3,
        title: 'Want to take a look at this resource? Just click it. It seems to be pretty good, you should look at it'
      });
    var infowindow = new google.maps.InfoWindow({
      content: "The resource marker"
   });
    google.maps.event.addListener(marker, 'click', function() {
      infowindow.open(map,marker);
   });
  }
  if(data[i].Category===2)
  {
    var marker = new google.maps.Marker({
        position:{lat:data[i].LAT,lng:data[i].LON},
        map:map,
        icon: icon2,
        title: 'Want to take a look at this resource? Just click it. Just believe it will work fine.'
      });
  }
  if(data[i].Category===1)
  {
    var marker = new google.maps.Marker({
        position:{lat:data[i].LAT,lng:data[i].LON},
        map:map,
        icon: icon1,
        title: 'Want to take a look at this resource? Just click it. Beware it may be a endangered resource.'
      });
  }
  }
}
      /*
      // Add marker
      var marker = new google.maps.Marker({
        position:{lat:42.4668,lng:-70.9495},
        map:map,
        icon:'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png'
      });

      var infoWindow = new google.maps.InfoWindow({
        content:'<h1>Lynn MA</h1>'
      });

      marker.addListener('click', function(){
        infoWindow.open(map, marker);
      });
      */

      // Array of markers
      var markers = [
        {
          coords:{lat:42.4668,lng:-70.9495},
          iconImage:'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png',
          content:'<h1>Lynn MA</h1>'
        },
        {
          coords:{lat:42.8584,lng:-70.9300},
          content:'<h1>Amesbury MA</h1>'
        },
        {
          coords:{lat:42.7762,lng:-71.0773}
        }
      ];

      // Loop through markers
      for(var i = 0;i < markers.length;i++){
        // Add marker
        addMarker(markers[i]);
      }

      // Add Marker Function
      function addMarker(props){
        var marker = new google.maps.Marker({
          position:props.coords,
          map:map,
          title:'Ahemdabad'
          //icon:props.iconImage
        });

        // Check for customicon
        if(props.iconImage){
          // Set icon image
          marker.setIcon(props.iconImage);
        }

        // Check content
        if(props.content){
          var infoWindow = new google.maps.InfoWindow({
            content:props.content
          });

          marker.addListener('click', function(){
            infoWindow.open(map, marker);
          });
        }
      }
    }
       
  </script>
  <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDAL-KHUPgSqbcbUWH0tRXUbA0_NTrWSmc&callback=initMap">
    </script>
</body>
</html>



<!-- <html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Water Resources near me</title>
  <style>
    #map{
      height:570px;
      width:100%;
    }
  </style>
</head>
<body>
  <h1>Water Resources near you</h1>
  <div id="map"></div>
  <script>
    var request = new XMLHttpRequest();
request.open('GET', 'http://hitesh159.pythonanywhere.com/groundwater/', false);  // `false` makes the request synchronous
request.send(null);

if (request.status === 200) {
  console.log(request.responseText);
}
  </script>
  <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDAL-KHUPgSqbcbUWH0tRXUbA0_NTrWSmc&callback=initMap">
    </script>
</body>
</html>
 -->