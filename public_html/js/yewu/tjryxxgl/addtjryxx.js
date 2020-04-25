$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		
var frameId = parseInt(window.location.href.split('?')[1].split('=')[1])-1;
		 	var codeData = {};
		   getCodeData(codeData);

     //执行保存的data对象
		$('.saveTijian').click(function() {
		//单位名称
		var	companycode = $("#companycode").val();
		var companyname = $("#companycode option:checked").text();
		//姓名
		var name = $("#name").val();
		//岗位
		var gzgw = $("#gzgw").val();
		//危害因素
		var whys = $("#whys").val();
		//身份证
		var sfz = $("#sfz").val();
		//联系电话
		var brdh = $("#brdh").val();
		//体检状态
		var tjbs = $("#tjbs").val();
		var params = {
			companycode: companycode,
			companyname: companyname,
			name: name,
			gzgw: gzgw,
			whys: whys,
			sfz: sfz,
			brdh: brdh,
			tjbs: tjbs,
		}
		//alert(JSON.stringify(params));
		$.ajax({
			type:"post",
			url:BaseUrl+'grjbxx/add',
			async:true,
			data:JSON.stringify(params),
			processData: false,//是否序列化
            contentType: "application/json", 
            success: function(data) {
				
					if(data.success) {
						layer.alert('保存成功', {
							skin: 'layui-layer-lan',
							closeBtn: 0,
							anim: 4 //动画类型
						}, function(tc, layero) {
							parent.layer.closeAll();
							var dom=$(top.window[frameId].document.getElementById('intSearch'));
							dom.trigger('click');
						});
					} else {
						layer.msg(data.message);
					}

				}
		});
			
			
			
		})
 
//接受结果集，往表格进行数据填充
	function setCodedata(result) {
	//debugger;
		var html = '';
			html += `<option value="">请选择单位</option>`;
		for(var i = 0; i < result.length; i++) {
			html += `<option value="${result[i].companycode}">${result[i].companyname}</option>`;
		}
		$('#companycode').html(html);
		getnull();
		form.render();

	}
//查询单位
function getCodeData(data) {
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
					setCodedata(result);
				} else {
					layer.msg(data.message);
				}

			}
		});
	}


	});
})
	