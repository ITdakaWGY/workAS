$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		
		var id = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var frameId = window.location.href.split('?')[1].split('&')[1].split('=')[1];
		var param = {
			id: id,
		}
		getData(param);
		
		
		$('.saveTijian').click(function() {
		$('#FormVerification').bootstrapValidator('validate');
		var flag = $("#FormVerification").data('bootstrapValidator').isValid();
		if(!flag) {
			layer.alert("填写有误，请重新填写！");
			return false;
		}
		var params = {
			pid: id,
			menuname: $('#menuname').val(),
			menuurl: $('#menuurl').val(),
			icon:$('#icon').val(),
			menustatus: $('#menustatus').val(),
			menuexplains: $('#menuexplains').val(),
		};
		$.ajax({
			type: "post",
			url: BaseUrl + '/sysMenu/add',
			async: true,
			data: JSON.stringify(params),
			processData: false,
			contentType: "application/json",
			success: function(data) {
				debugger;
				if(data.success) {
					layer.alert('保存成功', {
						skin: 'layui-layer-lan',
						closeBtn: 0,
						anim: 4 //动画类型
					}, function(tc, layero) {
						parent.layer.closeAll();
						top.window.document.getElementsByName(frameId)[0].contentWindow.location.reload();
					});
						

				} else {
					layer.msg(data.message);
				}
			}

		});
	})
		
	});

})

function getData(param){
	if(typeof(param) == "undefined") {
			param = {};
		}
	var result;
		$.ajax({
			type: "get",
			url: BaseUrl + 'sysMenu/findBySingle',
			data: param,
			async: true,
			success: function(data) {
				if(data.success) {
					var sysMenu = data.sysMenu;
					$('#name').html(sysMenu.menuname);
					$('#url').html(sysMenu.menuurl);
				} else {
					layer.msg("添加一级菜单");
				}

			}
		});
}


