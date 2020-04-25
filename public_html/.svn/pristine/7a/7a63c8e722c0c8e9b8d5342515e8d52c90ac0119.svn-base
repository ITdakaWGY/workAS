$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		
		var tmh = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var params = {
				tmh: tmh,
		}
		getCode(params);
		
		
		
		
		function getCode(param){
			
		var urls = 'grjbxx/list/1/' + pageSize;
					$.ajax({
						type: "get",
						url: BaseUrl + urls,
						async: true,
						data:params,
						success: function(data) {
							if(data.success) {
								var result = data.queryResult.list;
								if(result.length > 0) {
									$('#xm').html(result[0].name);
									$('#tjrq').html(result[0].tjrq);
									form.render();
								} else {
									form.render();
								}
							} else {
								layer.msg(data.message);
							}
						}
					});
			
			
		var urls = 'symptominfo/list/1/' + pageSize;
					$.ajax({
						type: "get",
						url: BaseUrl + urls,
						async: true,
						data: params,
						success: function(data) {
							if(data.success) {
								var result = data.queryResult.list;
								if(result.length > 0) {
									
									$('#zgres1').html(result[0].res1);
									form.render();
								} else {
									form.render();
								}
							} else {
								layer.msg(data.message);
							}
						}
					});
					
		urls = 'InquiryWgk/list/1/' + pageSize;
		$.ajax({
				type: "get",
				url: BaseUrl + urls,
				async: true,
				data: params,
				success: function(data) {
					if(data.success) {
						var result = data.queryResult.list;
						if(result.length > 0) {
							$('#wgkres1').html(result[0].res1);
							form.render();
						} else {
							zhikong('layui-table');
							form.render();
						}
					} else {
						layer.msg(data.message);
					}
				}
			});
			
		urls = 'InquiryWk/list/1/' + pageSize;
		$.ajax({
				type: "get",
				url: BaseUrl + urls,
				async: true,
				data: params,
				success: function(data) {
					if(data.success) {
						var result = data.queryResult.list;
						if(result.length > 0) {
							$('#wkres1').html(result[0].res1);
							form.render();
						} else {
							zhikong('layui-table');
							form.render();
						}
					} else {
						layer.msg(data.message);
					}
				}
			});
			
		urls = 'InquiryNk/list/1/' + pageSize;
		$.ajax({
				type: "get",
				url: BaseUrl + urls,
				async: true,
				data: params,
				success: function(data) {
					if(data.success) {
						var result = data.queryResult.list;
						if(result.length > 0) {
							$('#nkres1').html(result[0].res1);
							form.render();
						} else {
							zhikong('layui-table');
							form.render();
						}
					} else {
						layer.msg(data.message);
					}
				}
			});
			
		urls = 'InquirySjk/list/1/' + pageSize;
		$.ajax({
				type: "get",
				url: BaseUrl + urls,
				async: true,
				data: params,
				success: function(data) {
					if(data.success) {
						var result = data.queryResult.list;
						if(result.length > 0) {
							$('#sjkres1').html(result[0].res1);
							form.render();
						} else {
							zhikong('layui-table');
							form.render();
						}
					} else {
						layer.msg(data.message);
					}
				}
			});
	}
		
	});
})