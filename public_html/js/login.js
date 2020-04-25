$(function() {

    //var BaseUrl = 'http://localhost:8181/';
	var BaseUrl = 'http://60.216.10.28:8181/';

	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		laydate.render({
			elem: '#qyrq' //指定元素
		});

		document.onkeydown = keydown;
		function keydown(e) {
			var currKey = 0,
				e = e || event;
			currKey = e.keyCode || e.which || e.charCode; //支持IE、FF
			if(currKey == 13) {
				$('.dlbtn').trigger('click');
			}
		}

		$('.dlbtn').click(function() {
			var username = $('.username').val();
			var userpass = $('.userpass').val();
			if(username == '') {
				layer.msg('用户名不能为空', {
					shade: 0.8,
					time: 1000
				});
				$('.username').focus();
				return false;
			}
			if(userpass == '') {
				layer.msg('密码不能为空', {
					shade: 0.8,
					time: 1000
				});
				return false;
			}
			var name = $("#username").val();
			var password = $("#password").val();
			var params = {
				name: name,
				password: password,
				userState: "0"
			};
			$.ajax({
				type: "Post",
				url: BaseUrl + 'userLogin/select',
				async: true,
				data: JSON.stringify(params),
				processData: false, //是否序列化
				contentType: "application/json",
				success: function(data) {
					if(data.success) {
						var list = data.userLogin;
						window.localStorage.setItem('token', '1');

						window.localStorage.setItem('userLogin', JSON.stringify(list));
						getRolecode(list.yhbh);

					} else {
						layer.msg(data.message);
					}
				},
				error: function(data) {
					layer.close(data.Message);
					layer.msg('程序错误!');
				}

			});

		})



	//获取用户的角色
function getRolecode(yhbh){


	$.ajax({
	type:"get",
	url:BaseUrl+"role/getRole/"+yhbh,
	async:true,
	success: function(data){
		if(data.success){
			var list = data.queryResult.list;
		     var rolecode = "";
		     for (i=0;i<list.length;i++) {
		     	rolecode += list[i].rolecode+","
		     }
		    rolecode= rolecode.substring(0,rolecode.length-1);

			window.localStorage.setItem('rolecode',rolecode);
			window.location.href = "welcome.html";
		}
	},
	error: function(data){
		layer.msg('程序错误!');
	}
	});

}


});
})
