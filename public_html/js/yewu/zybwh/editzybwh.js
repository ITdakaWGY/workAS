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
			url: BaseUrl + 'Hazardinfo/findBySingle',
			async: true,
			data: {
				id: id
			},
			contentType: "application/json", 
			success: function(data) {
				
				layer.close(loadData);
				if(data.success) {
					var hazardinfo = data.hazardinfo;
					var hazardType = hazardinfo.hazardType;
					var hazardName = hazardinfo.hazardName;
					var whysname = [];
					whysname = hazardName.split(",");
					for(var i=0;i<whysname.length;i++){
						 $("input[name='hazardName'][value="+whysname[i]+"]").prop("checked",true);
					}
					str = hazardinfo.companycode;
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
				id: id,
			};
			$.ajax({
				type: "put",
				url: BaseUrl + 'Hazardinfo/update/'+id,
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
	});
})


	