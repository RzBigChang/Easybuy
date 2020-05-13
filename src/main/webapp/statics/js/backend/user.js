/**
 * 用户列表删除点击事件！
 */
function delUser(id) {
	mizhu.confirm('温馨提醒', '要删除该用户吗？','确认', '取消', function(flag) {
		if (flag) {
			// ajax实现删除！
			$.post("del", "id=" + id, function(del) {
				// 删除成功与否弹出信息！
				var result = eval("(" + del + ")");
				
				if (result.status == 1) {
					mizhu.toast(result.message, 2000);
					var currentPage = $("#thisCurrentPage").val();
					var ise = {
						"currentPage" : currentPage
					};
					$(".m_right").load(
							contextPath
									+ "/user .m_right>*",
							ise);
				}else{
					mizhu.alert('操作提醒',result.message);
				}
				
			});
		}
	})

}
/**
 * 点击修改
 * @param id
 * @returns
 */
function updateByUserId(id,currentPage) {
	var ise = {//"action":"toUpdateUser",
			"id" : id,"currentPage":currentPage
			};
	$(".m_right").load(contextPath + "/toUpdateUser .m_right>*",ise);
	}
/**
 * 点击添加
 * @returns
 */
function addUser() {
	var ise = {
		//"action":"/add"
	};
	$(".m_right").load(contextPath + "/tianjiauser .m_right>*",ise);
}