<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SSE Demo</title>

</head>
<body>
<div id="msgFrompPush"></div>
<script type="text/javascript" src="<c:url value="assets/js/jquery.js" />"></script>
<script type="text/javascript">

<%-- 
    使用EventSouce，观察浏览器F12网络，其实也是不断的请求服务器。这和不断请求轮询服务器相比，功能上没什么区别。
 EventSource对象并不能够自行设定刷新时间，这依赖于浏览器的具体实现，比如在Chrome中的刷新时间是3秒，Firefox中为5秒。
--%>

 if (!!window.EventSource) { //①
	   var source = new EventSource('pushV2'); 
	   s='';
	   var count = 0;
	   source.addEventListener('message', function(e) {//②
		   s+=e.data+"<br/>";
		   $("#msgFrompPush").html(s);
		   count++;
		   console.log("请求次数："+count);
		   if(count>10){
			   source.close();
		   }
	   });

	   source.addEventListener('open', function(e) {
	        console.log("连接打开.");
	   }, false);

	   source.addEventListener('error', function(e) {
	        if (e.readyState == EventSource.CLOSED) {
	           console.log("连接关闭");
	        } else {
	            console.log(e); 
	            source.close();
	        }
	   }, false);
	} else {
	        console.log("你的浏览器不支持SSE");
	} 
</script>
</body>
</html>