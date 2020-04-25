$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form','laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
			var laydate = layui.laydate;
			
		
		var str;
		var id = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var frameId = window.location.href.split('?')[1].split('&')[1].split('=')[1];
		
		var loadData = layer.msg('数据加载中...', {
			icon: 16,
			shade: 0.2,
			time: false
		});
		$.ajax({
			type: "get",
			url: BaseUrl + 'Jobinfo/findBySingle',
			async: true,
			data: {
				id: id
			},
			contentType: "application/json", 
			success: function(data) {
				layer.close(loadData);
				if(data.success) {
				var jobinfo = data.jobinfo;
					$('#jobname').val(jobinfo.jobname);
					str = jobinfo.companycode;
					var data = {};
					getData(data,str);
					
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
			var loading = layer.msg('正在修改数据...', {
				icon: 16,
				shade: 0.2,
				time: false
			});
			var jobname = $('#jobname').val();
			var companycode = $('#companycode').val();
			var params = {
				jobname: jobname,
				companycode: companycode,
				id,id,
			};
			$.ajax({
				type: "put",
				url: BaseUrl + 'Jobinfo/update/'+id,
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
		
	//接受结果集，往表格进行数据填充
	function setdata(result,str) {
	debugger;
		var html = '';
		for(var i = 0; i < result.length; i++) {
			
			if(result[i].companycode==str){
				html += `<option value="${result[i].companycode}" selected>${result[i].companyname}</option>`;	
			}else{
				html += `<option value="${result[i].companycode}">${result[i].companyname}</option>`;	
			}
			
		}
		$('#companycode').html(html);
		getnull();
		form.render();

	}

	function getData(data) {
		if(typeof(data) == "undefined") {
			data = {};
		}
		var result;
		$.ajax({
			type: "get",
			url: BaseUrl + 'companyinfo/selectlist',
			data: data,
			async: true,
			success: function(data) {
				if(data.success) {
					result = data.queryResult.list;
					setdata(result,str);
				} else {
					layer.msg(data.message);
				}

			}
		});
	}
})

})