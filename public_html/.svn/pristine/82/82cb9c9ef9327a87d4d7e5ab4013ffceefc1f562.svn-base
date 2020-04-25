var urlType;
//判断 ，如果直接进入首页，跳转菜单界面，如果没有登录，菜单界面会直接跳转登录界面
if(window.location.href.indexOf('?')>=0){
	
	 urlType = window.location.href.split('?')[1].split('=')[1];	
}else{
	window.location.href="welcome.html";
}
var navs = [];

var rolecode = window.localStorage.getItem("rolecode");

	$.ajax({
		type: "get",
		url: BaseUrl + 'sysMenu/selectRelation/'+rolecode+'/'+urlType,
		async: false,
		success: function(data) {
			if(data.success) {
				navs = data.queryResult.list;
			} else {
				layer.msg(data.message);
			}
		},
		error: function(data) {
			layer.msg('程序错误!');
		}
	});

