layui.use(['layer', 'form','element','carousel'], function(){
  var layer = layui.layer
  ,form = layui.form;
   var element = layui.element;
    var carousel = layui.carousel;
    carousel.render({
    elem: '#test1'
    ,width: '100%' //设置容器宽度
    ,arrow: 'always' //始终显示箭头
    //,anim: 'updown' //切换动画方式
  });
  
var rolecode = window.localStorage.getItem("rolecode");
  if(rolecode!=null&&rolecode!=""){
  	$.ajax({
		type: "get",
		url: BaseUrl + 'sysMenu/selectList/'+rolecode,
		async: false,
		success: function(data) {
			if(data.success) {
				setdata(data.queryResult.list);
			} else {
				layer.msg(data.message);
			}
		},
		error: function(data) {
			layer.msg('程序错误!');
		}
	});
  }
  
	
	$('body').on('click', '#update', function() {
				layer.open({
					type: 1,
					skin: 'layui-layer-rim', //加上边框
					area: ['420px', '280px'], //宽高
					content: $('.updatePassword'),
					btn: ['确定', '取消'],
					yes: function(index, layero) {
						  var password = $("#password").val();
						  var exactlyPassword = $("#exactlyPassword").val();
						var userLogin = eval('(' + window.localStorage.getItem('userLogin') + ')');
						var yhbh = userLogin.yhbh;
									 $.ajax({
								type: "get",
								url: BaseUrl + 'userLogin/updatePassword/'+yhbh+'/'+password+"/"+exactlyPassword,
								async: true,
								success: function(data) {
									layer.close(index);
									layer.msg(data.message);
								}
								})
						}
				})
		})
	
	
	
	 
	
	
	
function setdata(result){
		if(result.length == 0) {
		return false;
	}
	var html = '';
	for(var i = 0; i < result.length; i++) {
		html += `<li>
								<div class="info" data-type="${result[i].id}">
									<div class="img">
										<span class="iconfont iconSize">${result[i].icon}</span>
									</div>
									<a>
										<h3 class="btn">${result[i].menuname}</h3></a>
								</div>
							</li>`;
		}
		$('.menu').html(html);
		getnull();
	}
	
  
  $('#welcomeNav').on('click','.info',function(){
  	var type=$(this).attr('data-type');
  	window.location.href="index.html?urlType="+type;
  })
});