<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户反馈</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <%@ include file="/common/backend/searchBar.jsp"%>
	<div class="i_bg bg_color">
		<!--Begin 用户中心 Begin -->
		<div class="m_content">
			<%@ include file="/common/backend/leftBar.jsp"%>
			<div class="m_right">
				<p></p>
				<div class="mem_tit">用户反馈</div>
				<table border="0" class="order_tab"
					style="width: 930px; text-align: center; margin-bottom: 30px;"
					cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td width="25%">反馈内容</td>
							<td width="10%">反馈满意度</td>
							<td width="20%">用户称呼</td>
							<td width="20%">手机号</td>
						</tr>
						<c:forEach items="${FankuiList}" var="temp" varStatus="index">
							<c:if test="${index.count<10 }">
								<tr>
									<td>${temp.faxie }</td>
									<td>${temp.manyidu }</td>
									<td>${temp.name }</td>
									<td>${temp.ipone }</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
				<%@ include file="/common/pre/pagerBar.jsp"%>
			</div>
		</div>
		<%@ include file="/common/pre/footer.jsp"%>
	</div>
  </body>
</html>
