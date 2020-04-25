$(function() {//一般直接写在一个js文件中
layui.use(['layer', 'form', 'laydate'], function() {
	var layer = layui.layer,
		form = layui.form;
	var laydate = layui.laydate;

	var data = {};
	getData(data);

	var frameId = window.location.href.split('?')[1].split('=')[1];
	//监听其他

	$('.saveTijian').click(function() {
		var jobname = $('#jobname').val();
		var companycode = $('#companycode').val();
		var params = {
			jobname: jobname,
			companycode: companycode,
		};
		$.ajax({
			type: "post",
			url: BaseUrl + '/Jobinfo/add',
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
						var dom=$(top.window.document.getElementsByName(frameId)[0].contentWindow.document.getElementById('intSearch'));
						var newpage=parseInt(dom.attr('data-page'));
						top.window.document.getElementsByName(frameId)[0].contentWindow.getPageData(newpage);
					});

				} else {
					layer.msg(data.message);
				}
			}

		});
	})

	//接受结果集，往表格进行数据填充
	function setdata(result) {
	debugger;
		var html = '';
		for(var i = 0; i < result.length; i++) {
			html += `<option value="${result[i].companycode}">${result[i].companyname}</option>`;
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
					setdata(result);
				} else {
					layer.msg(data.message);
				}

			}
		});
	}

});})