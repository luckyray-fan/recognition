
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body>
<meta charset="utf-8">
<button onclick="getLocation()">确认开会地点</button>
<input type="hidden" id="location" name="location">
<script type="text/javascript">
    var x=document.getElementById("location");
    function getLocation()
    {
        if (navigator.geolocation)
        {
            navigator.geolocation.getCurrentPosition(showPosition);
        }
        else{x.value="浏览器定位不支持使用";}
    }
    function showPosition(position)
    {
        x.value=position.coords.latitude + "，" + position.coords.longitude;
        document.location.href="chooselocation?loc="+x.value;
    }
</script>
</body>
</html>
