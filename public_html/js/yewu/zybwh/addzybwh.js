$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form','laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
			var laydate = layui.laydate;
			
		form.on('select(hazardType)',function (data) {
		
			if(data.value=="粉尘"){
				$('.hazard_fc').show();
				$('.hazard_hx').hide();
				$('.hazard_wl').hide();
				$('.hazard_sw').hide();
				$('.hazard_ts').hide();
			}else if(data.value=="化学因素"){
				$('.hazard_fc').hide();
				$('.hazard_hx').show();
				$('.hazard_wl').hide();
				$('.hazard_sw').hide();
				$('.hazard_ts').hide();
			}else if(data.value=="物理因素"){
				$('.hazard_fc').hide();
				$('.hazard_hx').hide();
				$('.hazard_wl').show();
				$('.hazard_sw').hide();
				$('.hazard_ts').hide();
			}else if(data.value=="生物因素"){
				$('.hazard_fc').hide();
				$('.hazard_hx').hide();
				$('.hazard_wl').hide();
				$('.hazard_sw').show();
				$('.hazard_ts').hide();
			}else if(data.value=="特殊作业"){
				$('.hazard_fc').hide();
				$('.hazard_hx').hide();
				$('.hazard_wl').hide();
				$('.hazard_sw').hide();
				$('.hazard_ts').show();
			}
       	
       	form.render('select');
 		})
		
		
		var data = {};
		getData(data);
		
		var frameId=window.location.href.split('?')[1].split('=')[1];
		$('.saveTijian').click(function(){
			var hazardType=[];
				$('.whysParent').each(function(){
					var index=$(this).index();
					var length=$(this).find('input[name="hazardName"]:checked').length;
					if(length>0){
						var m=$('.hazardType option').eq(index).val();
						hazardType.push(m);
					}
					
				})

			var hazardName = [];
			$('input[name="hazardName"]:checked').each(function(i) {
					hazardName.push($(this).val());
			})

			var companycode = $('#companycode').val();
			var params = {
				hazardType: hazardType.toString(),
				hazardName: hazardName.toString(),
				companycode: companycode,
			};
			$.ajax({
				type: "post",
				url: BaseUrl + '/Hazardinfo/add',
				async: true,
                data:JSON.stringify(params),
                processData: false,
                contentType: "application/json", 
				success: function(data) {
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
	});
})