<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>易买网首页-用户反馈调查</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.11.1.min_044d0927.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/jquery.bxslider_e88acd1b.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/confic/ui.js"></script>
<script type="text/javascript">
			/**
			 * 验证手机号码正则表达式！
			 */
			
			function checkMobile(phone) {
				var reg =  /^[1][3,4,5,7,8][0-9]{9}$/;
				if (reg.test(phone)) {
				 document.getElementById("ywz").style.display="none";
					return true;
				} else {
				    document.getElementById("ywz").style.display="block";
				}
			}
			</script>
  </head>
  <link rel="stylesheet" type="text/css" media="all" href="statics/css/Fankui.css">
  <body>
  <form action="<%=basePath%>/EasybuyFankuiServlet?action=SaveFankui" method="post">
  <div>
   <table class="welcome-table">
   <tr>
   <td>
        <img src="statics/images/login.png" width="400px">  
        </td>
   </tr>
   <tr>
    <td class="survey-description">
     <h1 class="syters">易买网首页-用户反馈调查</h1>
   </td>
   </tr>
   <tr>
   <td class="survey-welcome">
   <span class="surveywelcome">
   <span style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;尊敬的用户:&nbsp;</span>
   <p>
   	<span style="font-size: 14px;">
   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您好!为了给您提供更好的服务，我们希望收集您使用
   	<strong>
   	<span style="color: rgb(255,0,0);">易买网首页</span>
   	</strong>
   	时的看法和建议。对您的配合和支持表示衷心感谢!
   	</span>
   </p>
   </span>
   <span class="x-questions"></span>
   </td> 
   </tr>
   </tbody>
   </table>
   <table class="question-wrapper">
   <tbody>
   <tr>
   <td class="questiontext">
   <span class="asterisk"></span>
   <span class="" style="display: none;">  </span>
   <span style="font-size: 14px;">
   <strong>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 如果您在使用
   <span style="color: rgb(255,0,0);">易买网首页</span>
   时,有什么好或者不好的地方,请大声说出来！我们会关注您的反馈,不断优化产品,为您提供更好的服务！
   </strong>
   </span>
   <br>
   </td>
   </tr>
   <tr>
   <td class="survey-question-help"></td>
   </tr>
   <tr>
   <td class="answer">
   <p class="question ">
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea class="textarea" rows="8" cols="70" name="txtpl"></textarea>
   </p>
   </td>
   </tr>
   </tbody>
   </table>
   <table class="question-wrapper">
   <tbody>
   <tr>
   <td class="questiontext">
   <span class="asterisk"></span>
   <span class="" style="display: none;">  </span>
   <span style="font-size: 14px;">
   <span style="color: rgb(255,0,0)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*</span>
   <strong>
   您对
   <span style="color: rgb(255,0,0);">易买网首页</span>
  的整体满意度如何？
   </strong>
   </span>
   </td>
   </tr>
   <tr>
   <td class="answer">
   <ul class="answers-list radio-list">
   <li id="fcmy" class="answer-item radio-item">
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="radio" type="radio" value="1" name="f" id="my">
   <label for="my" class="answertext">
   <span style="font-size: 14px;">非常满意</span>
   </label>
   </li>
   <li id="my" class="answer-item radio-item">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="radio" type="radio" value="2" name="f" id="m">
   <label for="m" class="answertext">
   <span style="font-size: 14px;">满意</span>
   </label>
   </li>
   <li id="yiban" class="answer-item radio-item">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="radio" type="radio" value="3" name="f" id="yiban">
   <label for="yiban" class="answertext">
   <span style="font-size: 14px;">一般</span>
   </label>
   </li>
   <li id="bmy" class="answer-item radio-item">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="radio" type="radio" value="4" name="f" id="bmy">
   <label for="bmy" class="answertext">
   <span style="font-size: 14px;">不满意</span>
   </label>
   </li>
   <li id="fcbmy" class="answer-item radio-item">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="radio" type="radio" value="5" name="f" id="myb">
   <label for="myb" class="answertext">
   <span style="font-size: 14px;">非常不满意</span>
   </label>
   </li>
   </ul>
   </td>
   </tr>
   </tbody>
   </table>
   <div id="tj">
   <input type="hidden" name="screen" id="screen">
   <table class="group">
   <tbody>
   <tr>
   <td align="center">
   <br>
   <div class="left">
   <span class="group-name"></span>
   </div>
   <br>
   <table class="question-wrapper">
   <tbody>
   <tr>
   <td class="questiontext">
   <span class="asterisk"></span>
   <span class="qnumcode" style="display: none;">  </span>
   <p>
   <span style="font-size: 14px">
   <strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我们会不定期邀请用户参与面对面的交流。如果您有意参与，请填写如下信息，方便我们与您联系，谢
   谢！(信息仅作为内部资料绝不外泄)</strong>
   </span>
   </p>
   <p>
   <span style="font-size: 14px;">
   <strong>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果您对京东的使用体验有任何想法,欢迎微信搜索并关注“
  <span style="color: #b22222;">易买网用户体验中心</span>
 ”公众号，参与更多用户体验活动。 
   </strong>
   </span>
   </p>
   <br>
   <span class="questionhelp"></span>
   <span class="questionhelp" id="vsm_20"></span>
   </td>
   </tr>
   <tr>
   <td class="survey-question-help">
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt="Help" src="statics/images/help.gif">
   	由于我们的体验中心在北京，访谈活动仅限北京地区用户参加，给您带来的不便还请谅解。
   </td>
   </tr>
   <tr>
   <td class="answer">
   <ul class="subquestions-list questions-list text-list">
   <li id="txtName" class="question-item answer-item text-item">
   <label for="answerName">
   	<span style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您的称呼： &nbsp;</span>
   </label>
   <span>
   <input class="text empty" type="text" size="20" name="8879" id="se">
   </span>
   </li>
   <li id="txtPWD" class="question-item answer-item text-item">
   <label for="answerPWD">
   	<span style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手机号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
   </label>
   <span>
   <input type="text/javascript" size="20" value="" name="8878" id="sed" onblur="checkMobile(this.value)"></br>
   </span>
   <span  id="ywz" style="display:none; color: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt="Help" src="statics/images/help.gif">&nbsp;输入正确的手机格式!</span>
   </li>
   </ul>
   </td>
   </tr>
   </tbody>
   </table>
   </td>
   </tr>
   </tbody>
   </table>
   </div>
   <table class="navigator-table">
   <tbody>
   <tr>
   <td class="submit-buttons">
   <input type="hidden" name="move" value="movenext" id="movenxt">
   <button class="submit ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" type="submit" accesskey="1" value="提交">
   <span class="ui-button-text">提交</span>
   </button>
   
      </td>
   </tr>
   </tbody>
   </table>
   </div>
   </form>
  </body>
</html>
