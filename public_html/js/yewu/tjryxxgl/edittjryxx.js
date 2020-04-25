$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		 var id = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var frameId = parseInt(window.location.href.split('?')[1].split('&')[1].split('=')[1])-1;
		var codeData = {};
		   getCodeData(codeData);

		 	var loadData = layer.msg('数据加载中...', {
			icon: 16,
			shade: 0.2,
			time: false
		});
	 
		 var params = {id:id};
		$.ajax({
			type: "Post",
			url: BaseUrl + '/grjbxx/select',
			async:true,
			data:JSON.stringify(params),
			processData: false,//是否序列化
            contentType: "application/json", 
			success: function(data) {
				layer.close(loadData);
				if(data.success) {
					var list = data.queryResult.list[0];
					$("#companycode").val(list.companycode);
					$("#name").val(list.name);
					$("#gzgw").val(list.gzgw);
					$("#whys").val(list.whys);
					$("#sfz").val(list.sfz);
					$("#brdh").val(list.brdh);
					$("#tjbs").val(list.tjbs);
					form.render();
				}else{
					layer.msg('data.message');
				}
		},
			error:function(data){
				layer.close(data.Message);
				layer.msg('程序错误!');
			}

		});
		
		//修改保存
		$('.saveTijian').click(function() {
			var loading = layer.msg('正在修改数据...', {
				icon: 16,
				shade: 0.2,
				time: false
			});
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
			id: id,
		}
			$.ajax({
				type: "put",
				url: BaseUrl+ 'grjbxx/update/'+id,
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
							var dom=$(top.window[frameId].document.getElementById('intSearch'));
							dom.trigger('click');
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