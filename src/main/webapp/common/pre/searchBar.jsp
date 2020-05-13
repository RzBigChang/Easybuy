<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
        $(document).ready(function(){
            $("div").mousemove(function(){    
                $(this).children("input:last").show();
            }).mouseout(function(){
                $(this).children("input:last").hide();
            })
        });
    var contextPath = "${ctx}";
var right=false;
function chack(){
	var name=$("#price1").val();
	var shou=$("#price2").val();
  if(name==""){
    alert("商品最低价不能为空");
    return false;
    }
     if(shou==""){
    alert("商品最高价不能为空");
    return false;
    }
     return true;
     }
</script>
</script>
<style type="text/css">  
	.input {
    width: 42px;
    padding: 1px 1px 1px 3px;
    height: 15px;
    border: 1px solid #ccc;
    color: #777;
}
.a{
	font-size:13px;
}
.b{
	display: inline-block;
    margin-left: 14px;
    padding: 0 10px;
    line-height: 20px;
    border-radius: 1px;
    background: #ff4200;
    color: #fff;
}
             }
 .clearfix:before {
    display: table;
    content: "";
}
.price_area {
    float: left;
    cursor: pointer;
    line-height: 38px;
    
  	
}
.option_filter {
    background: #fff;
    border: 1px solid #e8e8e8;
    color: #747474;

   
}
.price_area {
    float: left;
    cursor: pointer;
    line-height: 38px;
    padding: 0 10px;
    border: none;
    
}
.shadow:hover{
    position: relative;
    margin-left: -10px;
    margin-right: -80px;
    padding-left: 10px;
    width: 220px;
    box-shadow:1px 1px 6px #aaa;
    background: #fff;
    z-index: 10;
    line-height: 38px;
             }
textarea {
    font: 12px/1.5 tahoma,arial,'Hiragino Sans GB','\5b8b\4f53',sans-serif;
}
</style> 
<div class="top">
    <div class="logo">
        <a href="${ctx }/pre/Index.jsp"><img src="${ctx}/statics/images/logo.png"></a>
    </div>
    
    <div class="search">
        <form action="${ctx}/queryLikeProductList" method="post">
            <input type="text"  name="keyWord" class="s_ipt">
            <input type="submit" value="搜索" class="s_btn">
        </form>
        <!--推荐最热商品-->
    <form action="ProductServlet?action=queryProductList6" method="post" onsubmit="return chack()">
    <div class="option_filter clearfix">
    <div class="price_area out">
    <div class="shadow">
   			<span class="a">价格:</span>
        	<input type="text" id="price1" class="input" name="price1" placeholder="￥" id="rmb" value="<%=request.getParameter("price1")==null?"":request.getParameter("price1")%>" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
        	<span>-</span>
        	<input type="text" id="price2" class="input" name="price2" placeholder="￥" id="rmb"  value="<%=request.getParameter("price2")==null?"":request.getParameter("price2")%>" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
        	<input style="display:none" type="submit" value="确定" class="b">
     </div>
     </div>
     </div>
     </form>
    </div>
    
    <div class="i_car">
        <div class="car_t">
            购物车 [
            <span>
                <c:if test="${sessionScope.cart!=null && sessionScope.cart.items.size()>0}">
                    ${sessionScope.cart.items.size()}
                </c:if>
                <c:if test="${sessionScope.cart==null || sessionScope.cart.items.size()<=0}">
                    空
                </c:if>
            </span>]
        </div>
        <div class="car_bg">
            <!--Begin 购物车未登录 Begin-->
            <c:if test="${sessionScope.easybuyUserLogin==null}">
                <div class="un_login">还未登录！<a href="${ctx }/pre/Login.jsp" style="color:#ff4e00;">马上登录</a></div>
            </c:if>
            <c:if test="${sessionScope.easybuyUserLogin!=null}">
                <div class="un_login">我的购物车</div>
            </c:if>
            <!--End 购物车未登录 End-->
            <!--Begin 购物车已登录 Begin-->
            <ul class="cars">
                <c:forEach items="${sessionScope.cart.items}" var="temp">
                    <li>
                        <div class="img"><a href="${ctx}/queryProductDeatil?id=${temp.product.id}"><img src="${ctx}/files/${temp.product.fileName}" width="58" height="58" /></a></div>
                        <div class="name"><a href="${ctx}/queryProductDeatil?id=${temp.product.id}">${temp.product.name}</a></div>
                        <div class="price"><font color="#ff4e00">￥${temp.product.price}</font> X${temp.quantity}</div>
                    </li>
                </c:forEach>
            </ul>
            <div class="price_sum">共计&nbsp;<font color="#ff4e00">￥</font><span>${sessionScope.cart.sum}</span></div>
            <c:if test="${sessionScope.easybuyUserLogin==null}">
                <div class="price_a"><a href="${ctx }/pre/Login.jsp">去登录</a></div>
            </c:if>
            <c:if test="${sessionScope.easybuyUserLogin!=null}">
                <div class="price_a"><a href="${ctx}/toSettlement">去结算</a></div>
            </c:if>
            <!--End 购物车已登录 End-->
        </div>
    </div>
</div>
