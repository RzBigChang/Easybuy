<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	List list = (List) request.getAttribute("listOrderDetail");
	if (list == null) {
		String conPath = request.getContextPath();
		out.print("<script>location.href = '" + conPath + "/index'</script>");

	}
%>
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
				<div class="mem_tit">订单列表</div>
				<table border="0" class="order_tab"
					style="width: 930px; text-align: center; margin-bottom: 30px;"
					cellspacing="0" cellpadding="0">
					<tbody>
						<c:forEach items="${orderList}" var="temp">
							<tr class="td_bg">
								<td>用户名:${temp.loginName}</td>
								<td>订单号:${temp.serialNumber}</td>
								<td>地址:${temp.userAddress}</td>
								<td>￥${temp.cost}</td>
							</tr>
							<tr>
							</tr>
							<tr>
								<td colspan="4">
									<table border="0" class="order_tab"
										style="width: 930px; text-align: center; margin-bottom: 30px;"
										cellspacing="0" cellpadding="0">
										<tbody>
											<tr>
												<td width="20%">商品名称</td>
												<td width="20%">商品图片</td>
												<td width="20%">数量</td>
												<td width="20%">价格</td>
												<td width="10%">
													<c:if test="${temp.cancel==0}"><a href="">取消订单</a></c:if>
													<c:if test="${temp.cancel==1}"><a href="">该订单已取消</a></c:if>
												</td>
											</tr>
											<c:forEach items="${listOrderDetail}" var="listOrderDetail">
												<c:if test="${listOrderDetail.orderId==temp.id }">
													<tr>
														<td>${listOrderDetail.name}</td>
														<td><img src="${ctx}/files/${listOrderDetail.fileName}"
																width="50" height="50"></img></td>
														<td>${listOrderDetail.quantity}</td>
														<td>${listOrderDetail.cost}</td>
													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
								</td>
							</tr>
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


