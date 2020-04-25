$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		
		var id = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var frameId =window.location.href.split('?')[1].split('&')[1].split('=')[1];
		alert(frameId);
		
	/*	var loadData = layer.msg('数据加载中...', {
			icon: 16,
			shade: 0.2,
			time: false
		});*/
		
		var params = {id:id};
		
		$.ajax({
			type:"post",
			url:BaseUrl+"role/select",
			async:true,
			data: JSON.stringify(params),
			processData: false,
			contentType: "application/json",
			success:function(data){
				if(data.success){
					var list = data.queryResult.list[0];
					$("#rolename").val(list.rolename);
					$("#rolecode").val(list.rolecode);
					$("#rolestate").val(list.rolestate);
					$("#roleexplain").val(list.roleexplain);
					form.render();
				}else{
					layer.msg(data.message);
				}
			},
			error: function(data){
				layer.close(data.Message);
				layer.msg('程序错误!');
			}
			
			
		});//ajax  end
		
		
		//修改保存		
		$(".saveTijian").click(function(){
		//角色名称
		var  rolename =	$("#rolename").val();
		//角色编码
		var rolecode = $("#rolecode").val();
		//角色状态
		var rolestate = $("#rolestate").val();
		//角色说明
		var roleexplain = $("#roleexplain").val();
		
		var params = {
			rolename: rolename,
			rolecode: rolecode,
			rolestate: rolestate,
			roleexplain: roleexplain,
			id:id,
		}
			
			$.ajax({
				type:"put",
				url:BaseUrl+"role/update/"+id,
				async:true,
				data:JSON.stringify(params),
				processData: false,
				contentType: "application/json",
				success:function(data){
					if(data.success) {
						
						layer.alert('保存成功', {
							skin: 'layui-layer-lan',
							closeBtn: 0,
							anim: 4 //动画类型
						}, function(tc, layero) {
							parent.layer.closeAll();
							debugger;
						var dom=$(top.window.document.getElementsByName(frameId)[0].contentWindow.document.getElementById('intSearch'));
     							 var newpage=parseInt(dom.attr('data-page'));
     							 top.window.document.getElementsByName(frameId)[0].contentWindow.getPageData(newpage);
						});
					} else {
						layer.msg(data.message);
					}
				},
				error:function(date){
					layer.msg("程序错误");
				}
				
			});//ajax  end
			
			
		})//修改保存 end
			
		
		
		
		
		
		
		
		
		
		
		
		
	});
})



