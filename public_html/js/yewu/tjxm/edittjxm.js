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
			url: BaseUrl + 'DiseaseItem/findBySingle',
			async: true,
			data: {
				id: id
			},
			contentType: "application/json", 
			success: function(data) {
				
				layer.close(loadData);
				if(data.success) {
					var diseaseItem = data.diseaseItem;
					$('#iteamName').val(diseaseItem.iteamName);
					$('#price').val(diseaseItem.price);
					$('#state').val(diseaseItem.state);
					$('#remarks').val(diseaseItem.remarks);
					str = diseaseItem.orgcode;
					var data = {};
					getOrgData(data,str);
					
					
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
			
			var iteamName = $('#iteamName').val();
			var price = $('#price').val();
			var state = $('#state').val();
			var remarks = $('#remarks').val();
			var orgcode = $('#orgcode').val();
			var params = {
				iteamName: iteamName,
				price: price,
				state: state,
				remarks: remarks,
				orgcode: orgcode,
				id:id,
			};
			$.ajax({
				type: "put",
				url: BaseUrl + 'DiseaseItem/update/'+id,
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
	function setOrgdata(result,str) {
	debugger;
		var html = '';
			html += '<option value="">请选择</option>';
		for(var i = 0; i < result.length; i++) {
			
			if(result[i].orgno==str){
				html += `<option value="${result[i].orgno}" selected>${result[i].orgname}</option>`;	
			}else{
				html += `<option value="${result[i].orgno}">${result[i].orgname}</option>`;	
			}
			
		}
		$('#orgcode').html(html);
		getnull();
		form.render();

	}

	function getOrgData(data,str) {
		if(typeof(data) == "undefined") {
			data = {};
		}
		var result;
		$.ajax({
			type: "get",
			url: BaseUrl + 'sysOrg/selectList',
			data: data,
			async: true,
			success: function(data) {
				if(data.success) {
					result = data.queryResult.list;
					setOrgdata(result,str);
				} else {
					layer.msg(data.message);
				}

			}
		});
	}
		
		
	});
})


	