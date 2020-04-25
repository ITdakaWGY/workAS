$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form','laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		
		var orgData = {};
		getOrgData(orgData);
		
		var frameId = window.location.href.split('?')[1].split('=')[1];
		$('.saveTijian').click(function(){
			$('#FormVerification').bootstrapValidator('validate'); 
  			var flag = $("#FormVerification").data('bootstrapValidator').isValid(); 
  			if(!flag){
  				layer.alert("填写有误，请重新填写！"); 
  				return false;
  			}
			
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
			};
			$.ajax({
				type: "post",
				url: BaseUrl + '/DiseaseItem/add',
				async: true,
                data:JSON.stringify(params),
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
	function setOrgdata(result) {
	debugger;
		var html = '';
		for(var i = 0; i < result.length; i++) {
			html += `<option value="${result[i].orgno}">${result[i].orgname}</option>`;
		}
		$('#orgcode').html(html);
		getnull();
		form.render();

	}

	function getOrgData(data) {
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
					setOrgdata(result);
				} else {
					layer.msg(data.message);
				}

			}
		});
	}
		

	});
})