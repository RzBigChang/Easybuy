/**
 * 点击修改事件
 * @param id
 * @param currentPage
 * @returns
 */
function updateByProductId(id, currentPage) {
	$(".left_m ul>li>a").removeClass();
	var ise = {
		//"action" : "getProduct",
		"id" : id,
		"currentPage" : currentPage
	};
	$(".m_right").load(contextPath + "/getProduct .m_right>*", ise);
}
function sx(){
	contextPath + "/spgl";
}
/**
 * 商品上架/修改商品 点击事件！
 * 
 * @returns
 */
function checkProduct() {
	// 获取ID判断用户是修改还是删除！
	var id = $("#id").val();
	// 获取用户需要上架的数据！
	var one = $("#productCategoryLevel1").val();
	var two = $("#productCategoryLevel2").val();
	var three = $("#productCategoryLevel3").val();
	// 获取图片路径名称！
	var img = $("#img").attr("src");

	var name = $("#name").val();
	var imgval = $("#fiel").val();
	var price = $("#price").val();
	var stock = $("#stock").val();
	var description = $("#description").val();
	/* 非空验证 */
	if (name == "" || name == null) {
		mizhu.alert('操作提醒', "商品名称不能为空！");
		return false;
	} else if (price == "" || price == null) {
		mizhu.alert('操作提醒', "请输入单价哎！！");
		return false;
	} else if (stock == "" || stock == null) {
		mizhu.alert('操作提醒', "请输入库存哎！！");
		return false;
	}else if(one==0){
		mizhu.alert('操作提醒', "请选择一级分类！");
		return false;
	}else if(two==0){
		mizhu.alert('操作提醒', "请选择二级分类！");
		return false;
	}else if(three==0){
		mizhu.alert('操作提醒', "请选择三级分类！");
		return false;
	}
	
	if (img == null|| img == null) {
		if (imgval == "" || imgval == null) {
			mizhu.alert('操作提醒', "请选择图片！！");
			return false;
		}
	}

	var formData = new FormData($("#productAdd")[0]);
	formData.append('file', $('#fiel')[0].files[0]);
	$
			.ajax({
				url : contextPath + "/getImgs",
				method : "post",
				data : formData,
				contentType : false,
				processData : false,
				success : function(data) {
					var result = eval("(" + data + ")");
					if (result.status == 1) {
						mizhu.toast("操作成功",1500);
					}else{
						mizhu.alert('操作提醒', result.message);
						window.location.reload();
					}
					
					
					if (result.status == 1) {
						var id = $("#id").val();
						var currentPage=$("#currentPage").val();
						var ise = {
							"id" : id,
							"currentPage":currentPage
						};
						$(".m_right").load(
										contextPath
												+ "/updateAndDel .m_right>*",
										ise);
					}
					window.location.reload();
				}
			});
}

/**
 * 选中一级分类获得二级分类对应信息！
 * 
 * @param thisId
 * @returns
 */
function change1() {
	// 获取当前标签的商品ID！
	var Id = $("#productCategoryLevel1").val();
	var item = {
		//"action" : "getProductCategoryTwo",
		"parentId" : Id
	};
	$("#productCategoryLevel2").load(
			contextPath + "/getProductCategoryTwo #productCategoryLevel2>*",
			item, leve2);
}

/**
 * 二级分类！
 * @returns
 */
function change2() {
	leve2();
}
/**
 * 选中二级分类获得三级分类对应信息！
 * 
 * @param thisId
 * @returns
 */
function leve2() {
	// 获取当前标签的商品ID！
	var Id = $("#productCategoryLevel2").val();
	var item = {
		//"action" : "getProductCategoryThree",
		"parentId" : Id
	};
	$("#productCategoryLevel3").load(
			contextPath + "/getProductCategoryThree #productCategoryLevel3>*",
			item);
}
