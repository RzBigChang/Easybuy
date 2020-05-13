/**
 * 窗体加载事件！
 * 
 * @returns
 */
$(document).ready(function() {
	/**
	 * 登陆按钮点击事件！
	 */
	$("#log_btn").click(function() {
		loginCheck();
	});

	/**
	 * 回车键点击事件！
	 */
	$('form').on('keypress', function(event) {
		if (event.keyCode == 13) {
			loginCheck();
		}
	});

	/**
	 * 登录验证！
	 */
	function loginCheck() {
		var name = $(".l_user").val();
		var pwd = $(".l_pwd").val();
		
		if (name == "" || name == null) {
			mizhu.alert('操作提醒', "用户名不能为空!");
			return;
		} else if (pwd == "" || pwd == null) {
			mizhu.alert('操作提醒', "密码不能为空!");
			return;
		} else {

			$.ajax({
				url : contextPath + "/deng",
				method : "post",
				data : {
					"action" : "loginBtn",
					"name" : name,
					"pwd" : pwd
				},
				success : function(jsonStr) {
					//var result = eval("(" + jsonStr + ")");
					if (jsonStr == 1) {
						// 登录成功！跳转登录界面！！
						location.href = contextPath + "/zhu";
					} else {
						mizhu.alert('操作提醒', result.message);
					}
				}
			});
		}
	}
	
});

