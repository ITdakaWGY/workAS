$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form','laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
			var laydate = layui.laydate;
			
		var id = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var frameId = window.location.href.split('?')[1].split('&')[1].split('=')[1];
		
		var loadData = layer.msg('数据加载中...', {
			icon: 16,
			shade: 0.2,
			time: false
		});
		$.ajax({
			type: "get",
			url: BaseUrl + '/sysOrg/findBySingle',
			async: true,
			data: {
				id: id
			},
			contentType: "application/json", 
			success: function(data) {
				layer.close(loadData);
				if(data.success) {
				var sysOrg = data.sysOrg;
					
					$('#orgname').val(sysOrg.orgname);
					$('#phone').val(sysOrg.phone);
					$('#address').val(sysOrg.address);
					$('#state').val(sysOrg.state);
					if(sysOrg.state==1){
						$('#state option').eq(0).attr('selected',true)
					}else if(sysOrg.state==2){
						$('#state option').eq(1).attr('selected',true)
					}
					form.render();
				}else{
					layer.msg('data.message');
				}
			},
			error:function(data){
				layer.close(loadData);
				layer.msg('程序错误!');
			}
		});

		$('.saveTijian').click(function() {
			$('#FormVerification').bootstrapValidator('validate'); 
  			var flag = $("#FormVerification").data('bootstrapValidator').isValid(); 
  			if(!flag){
  				layer.alert("填写有误，请重新填写！"); 
  				return false;
  			}
			var loading = layer.msg('正在修改数据...', {
				icon: 16,
				shade: 0.2,
				time: false
			});
			var params = {
				orgname: $('#orgname').val(),
				phone: $('#phone').val(),
				address: $('#address').val(),
				state: $('#state').val(),
				id: id,
			
			};
			$.ajax({
				type: "put",
				url: BaseUrl + 'sysOrg/update/'+id,
				async: true,
                data:JSON.stringify(params),
                contentType: "application/json", 
                processData: false,
				success: function(data) {
					layer.close(loading);
					if(data.success) {
						layer.alert('保存成功', {
							skin: 'layui-layer-lan',
							closeBtn: 0,
							anim: 4 //动画类型
						}, function(tc, layero) {
							parent.layer.closeAll();
							var dom=$(top.window.document.getElementsByName(frameId)[0].contentWindow.document.getElementById('intSearch'));
							var newpage=parseInt(dom.attr('data-page'));
							top.window.document.getElementsByName(frameId)[0].contentWindow.getPageData(newpage);
						});
					} else {
						layer.msg(data.message);
					}
				},
				error:function(data){
					layer.msg('程序错误!');
					layer.close(loading);
				}
			});
		})
		
})

})