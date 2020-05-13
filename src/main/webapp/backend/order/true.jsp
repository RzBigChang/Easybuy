<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/common/pre/header.jsp"%>
</head>
<body>
	<%@ include file="/common/backend/searchBar.jsp"%>
	<!--End Header End-->
	<div class="i_bg bg_color">
		<!--Begin 用户中心 Begin -->
		<div class="m_content">
			<%@ include file="/common/backend/leftBar.jsp"%>
			<div class="m_right" id="content">
				<p></p>
				<p></p>
				<div class="mem_tit">取消订单反馈</div>
				<table border="0" class="order_tab"
					style="width: 930px; text-align: center; margin-bottom: 30px;"
					cellspacing="0" cellpadding="0">
					<tbody>
	 
    	<table border="0" width="40%" align="center">
    	    <tr>
    	    	<td colspan="2" align="center"></td>
    	    </tr>
    	    <tr style="display:none">
    			<td>订单编号:</td>
    			<td><input type="text" name="txtId" id="Id" value="${temp.id}"/></td>
    		</tr>
    		<tr>
    			<td>用户名:</td>
    			<td><input type="text" name="txtloginName" id="loginName" value="${temp.loginName}" disabled="disabled"></input></td>
    		</tr>
    		<tr>
    			<td>取消时间:</td>
    			<td><input type="text" name="txtTime" id="Time" value="${temp.time}" disabled="disabled"></input></td>
    		</tr>
    		<tr>
    			<td>订单号:</td>
    			<td><input type="text" name="txtserialNumber" id="serialNumber" value="${temp.serialNumber}" disabled="disabled"></input></td>
    		</tr>
    		<tr>
    			<td>取消订单的原因:</td>
    			<td><textarea name="txtMessage" id="Message" style="width:200px;height:80px;">${temp.message}</textarea></td>
    		</tr>
    		<tr>
    	    	<td colspan="4" align="center">
    	    	<input type="button" name="btn" value="返  回" style="margin-right:40px" onclick="javascript:history.back(-1);"></input>
    	    	</td>
    	    </tr>
    	</table>
					</tbody>
				</table>
				<%@ include file="/common/pre/pagerBar.jsp"%>
			</div>
		</div>
		<%@ include file="/common/pre/footer.jsp"%>
	</div>
</body>
</html>


