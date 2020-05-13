<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Tiaozhuan.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	 <link rel="stylesheet" type="text/css" media="all" href="statics/css/Tiaozhuan.css">
  </head>
  	<script type="text/javascript">
  	var b;
  	function auto(){
  	var val=document.getElementById("spanValue");
  	b=val.textContent;
  	if(b>1){
  	b--;
  	document.getElementById("spanValue").innerHTML='<font color="red">'+b+'</font>';
  	var bb=setInterval("auto()",1000);
  	}else{
  	clearInterval(bb);
  	location.href="pre/Index.jsp";
  	}
  	}
  	</script>
  <body onload="auto()">
   <div style="margin:0 auto">
   <table align="center">
   <tr>
   <td class="tds">您的反馈以收到，感谢您的支持</td>
   </tr>
   <tr>
   <td class="tds">
   	&nbsp;将于 <span id="spanValue">5</span>秒后自动跳转进主页面
   </td>
   </tr>
   <tr>
   <td class="tds">&nbsp;&nbsp;&nbsp;<a href="pre/Index.jsp">https://www.ymw.com</a></td>
   </tr>
   </table>
   </div>
  </body>
</html>

