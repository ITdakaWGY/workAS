$(function() {

	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		var yhbh = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var params = {
			rolestate: "1"
		};
		$.ajax({
			type: "post",
			url: BaseUrl + "role/select",
			async: true,
			data: JSON.stringify(params),
			processData: false,
			contentType: "application/json",
			success: function(data) {
				if(data.success) {
					var list = data.queryResult.list;
					setdata(list);
				} else {
					layer.msg(data.message);
				}
			},
			error: function(data) {
				layer.close(data.Message);
				layer.msg('程序错误!');
			}

		}); //ajax  end

		//接受结果集，往表格进行数据填充
		function setdata(result) {
			if(result.length == 0) {
				var nodata = '<div class="nodata">暂无数据!</div>';
				$('#tbody').html(nodata);
				return false;
			}
			var html = '';
			for(var i = 0; i < result.length; i++) {
				html += `<tr>
								<td><input type="checkbox" name="jscheckbox" value="${result[i].rolecode}" lay-skin="primary" ></td>
								<td>${i+1}</td>
								<td>${result[i].rolename}</td>
								<td>${result[i].rolecode}</td>
							</tr>`;
			}
			$('#tbody').html(html);
			form.render()
			getnull();
		}

		/*保存*/
		$(".saveJs").click(function() {
			
			if($('input[name="jscheckbox"]:checked').length==0){
				layer.msg('请至少选择一个角色！');
				return false;
			}
			
			var rolecode = getCheckArry("jscheckbox");
			var params = {
				rolecode: rolecode,
				yhbh: yhbh
			};
			var loading = layer.msg('正在保存角色...', {
				icon: 16,
				shade: 0.2,
				time: false
			});
			$.ajax({
				type: "post",
				url: BaseUrl + 'roleRelationUserlogin/add',
				async: true,
				data: JSON.stringify(params),
				processData: false, //是否序列化
				contentType: "application/json",
				success: function(data) {
					layer.close(loading);
					if(data.success) {
						layer.alert('角色设置成功', {
							skin: 'layui-layer-lan',
							closeBtn: 0,
							anim: 4 //动画类型
						}, function(tc, layero) {
							parent.layer.closeAll();
						});
					} else {
						layer.msg(data.message);
					}

				},
				error:function(data){
					layer.close(loading);
					layer.msg(data.message);
				}
			});

		})

	}) //layui  end

}) //就绪  end