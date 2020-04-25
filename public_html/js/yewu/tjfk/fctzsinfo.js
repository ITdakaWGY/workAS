$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		var id = window.location.href.split('?')[1].split('&')[0].split('=')[1];
	
		var loadData = layer.msg('数据加载中...', {
			icon: 16,
			shade: 0.2,
			time: false
		});
		$.ajax({
			type: "get",
			url: BaseUrl + 'grjbxxfc/list/1/' + pageSize + '/',
			async: true,
			data: {
				id: id
			},
			success: function(data) {
				layer.close(loadData);
				debugger;
				if(data.success) {
					var list = data.queryResult.list[0];
					$('#companyname1').html(list.companyname);
					$('#companyname2').html(list.companyname);
					$('#name1').html(list.name);
					$('#name2').html(list.name);
					$('#name3').html(list.name);
					$('#fcyy1').html(list.fcyy);
					$('#fcyy2').html(list.fcyy);
					$('#tjrq1').html(list.fcsj);
					$('#tjrq2').html(list.fcsj);
					$('#fcsj').html(list.fcsj);
					$('#createtime1').html(getDate(list.createtime));
					$('#createtime2').html(getDate(list.createtime));
					$('#createtime3').html(getDate(list.createtime));
					$('#createtime4').html(getDate(list.createtime));
					$('#sfz').html(list.sfz);
				}else{
					layer.msg('data.message');
				}
			},
			error:function(data){
				layer.close(loadData);
				layer.msg('程序错误!');
			}

		});

	});
})
