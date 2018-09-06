<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>servlet async support</title>

</head>
<body>
<div id="msgFrompPush"></div>
<script type="text/javascript" src="assets/js/jquery.js"></script>
<script type="text/javascript">

    var s="";
	// deferred();//①
	
	// sseEmitter();
	
	
	if (!!window.EventSource) {
       var source = new EventSource('sseEmitter'); 
       s='';
       source.addEventListener('message', function(e) {
           s+=e.data+"<br/>";
           $("#msgFrompPush").html(s);
       });

       source.addEventListener('open', function(e) {
            console.log("连接打开.");
       }, false);
       
       source.addEventListener('finish', function(e) {
           console.log("数据接收完毕，关闭EventSource");
           source.close();
           console.log(e);
      }, false);

       source.addEventListener('error', function(e) {
            if (e.readyState == EventSource.CLOSED) {
               console.log("连接关闭");
            } else {
                console.log(e);
                alert(e);
            }
       }, false);
    } else {
            console.log("你的浏览器不支持SSE");
    }
	
	
	function deferred(){
	    $.get('defer',function(data){  // defer
	        console.log(data); //②
	    	s+=data+"<br/>";
	    	$("#msgFrompPush").html(s);
	        // deferred(); //③
	    });
	}
	
	function sseEmitter(){
	    $.get('sseEmitter',function(data){
	        console.log(data); //②
	    	s+=data+"<br/>";
	    	$("#msgFrompPush").html(s);
	    });
	}
	
</script>
</body>
</html>