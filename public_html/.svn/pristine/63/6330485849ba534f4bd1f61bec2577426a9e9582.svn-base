$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		var id = window.location.href.split('?')[1].split('&')[1].split('=')[1];

		laydate.render({
			elem: '#fcsj',
		});

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
				if(data.success) {
					var list = data.queryResult.list[0];
					$('#name').val(list.name);
					$('#sfz').val(list.sfz);
					$('#tmh').val(list.tmh);
					$('#tjrq').val(list.tjrq);
					var jielun = list.jielun;
					if(jielun.length>0){
					jielun = jielun.split(",");
					for(var i=0;i<jielun.length;i++){
						 $("input[name='jielun'][value="+jielun[i]+"]").prop("checked",true);
					}
					
					 $("input[name='jielun'][value="+"目前未见异常"+"]").prop("checked",false);
					
					}
					
				

					var jyfcbs = list.jyfcbs;
					$('input[name="jyfcbs"]').each(function() {
						if($(this).val() == jyfcbs) {
							$(this).prop('checked', true);
						}
					})

					$('#fcyy').val(list.fcyy);
					//	$('#fcdd').val(list.fcdd);
					var fcdd = list.fcdd
					$('input[name="fcdd"]').each(function() {
						if($(this).val() == fcdd) {
							$(this).prop('checked', true);
						}
					})

					$('#fcsj').val(list.fcsj);

					$('#yszybbs').val(list.yszybbs);
					var yszybbs = list.yszybbs
					$('input[name="yszybbs"]').each(function() {
						if($(this).val() == yszybbs) {
							$(this).prop('checked', true);
						}
					})

					$('#yszybwb').val(list.yszybwb);
					$('#yszybyc').val(list.yszybyc);

					$('#jjzbs').val(list.jjzbs);
					var jjzbs = list.jjzbs;
					$('input[name="jjzbs"]').each(function() {
						if($(this).val() == jjzbs) {
							$(this).prop('checked', true);
						}
					})

					$('#jjzwb').val(list.jjzwb);
					$('#jjzyc').val(list.jjzyc);

					$('#zjjcjl').val(list.zjjcjl);
					$('#zjclyj').val(list.zjclyj);

					form.render();

				} else {
					layer.msg('data.message');
				}
			},
			error: function(data) {
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
			//建议复查标识		
			var jyfcbs = $('input[name="jyfcbs"]:checked').val();
			if(jyfcbs == undefined) {
				jyfcbs = '';
			}
			//复查原因
			var fcyy = $('#fcyy').val();
			//复查地点
			//var fcdd = $('#fcdd').val();

			// var fcdd = $("input[name='fcdd']:checked").val();
			var fcdd = $('input:radio[name="fcdd"]:checked').val();

			function test() {
				var value = document.getElementsByName("fcdd");
				var fcdd = "";
				for(var i = 0; i < value.length; i++) {
					if(value[i].checked == true) {
						fcdd = fcdd[i].value;
					}
				}
			}

			//结论（多选）
			var jielun = getCheckArry("jielun");
			//复查时间
			var fcsj = $('#fcsj').val();

			//疑似职业病标识
			var yszybbs = $('input[name="yszybbs"]:checked').val();
			if(yszybbs == undefined) {
				yszybbs = '';
			}

			//疑似职业病文本
			var yszybwb = $('#yszybwb').val();
			//疑似职业病异常
			var yszybyc = $('#yszybyc').val();

			//职业红禁忌标识
			var jjzbs = $('input[name="jjzbs"]:checked').val();
			if(jjzbs == undefined) {
				jjzbs = '';
			}

			//禁忌文本
			var jjzwb = $('#jjzwb').val();
			//职业病禁忌异常
			var jjzyc = $('#jjzyc').val();
			//检查结论
			var zjjcjl = $('#zjjcjl').val();
			//处理意见
			var zjclyj = $('#zjclyj').val();
			var params = {
				jyfcbs: jyfcbs,
				fcyy: fcyy,
				fcdd: fcdd,
				fcsj: fcsj,
				yszybbs: yszybbs,
				yszybwb: yszybwb,
				yszybyc: yszybyc,
				jjzbs: jjzbs,
				jjzwb: jjzwb,
				jjzyc: jjzyc,
				id: id,
				zjjcjl: zjjcjl,
				zjclyj: zjclyj,
				jielun:jielun,
				tjflagzj: '1'
			}

			$.ajax({
				type: "put",
				url: BaseUrl + 'grjbxxfc/edit/' + id,
				async: true,
				data: JSON.stringify(params),
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
							location.reload(true);
						});
					} else {
						layer.msg(data.message);
					}
				},
				error: function(data) {
					layer.msg('程序错误!');
					layer.close(loading);
				}
			});
		})
	});
})