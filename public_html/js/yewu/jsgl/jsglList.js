var getPageData;
var pagecount = '';
$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;

		//初次读取数据，第一页
		var data = {};
		getData(data);

		//隐藏的修改后重新调用查询接口的事件
		$('#intSearch').click(function() {
			getData();
		})

		$('body').on('click', '#AddBtn', function() {
			var frameId = $(window.frameElement).attr('name');
			top.layer.open({
				type: 2,
				title: '新增角色',
				shadeClose: true,
				shade: 0.8,
				area: ['40%', '40%'],
				content: 'page/jsgl/jsAdd.html?frameId=' + frameId,
			});
		})
		
		$('body').on('click', '.saveJs', function() {
			var length = $('input[name="jscheckbox"]:checked').length;
			if(length==0) {
				layer.msg('至少设置一个角色');
				return false;
			}
			var id = $('input[name="jscheckbox"]:checked').val().split(";")[1];
				var frameId = $(window.frameElement).attr('name');
			top.layer.open({
				type: 2,
				title: '修改角色',
				shadeClose: true,
				shade: 0.8,
				area: ['40%', '40%'],
				content: 'page/jsgl/jsEdit.html?id=' + id+"&frameId="+frameId,
			});
		})
		
		
		
		
		

		$('body').on('click', '.editBtn', function() {
			var length = $('input[name="jscheckbox"]:checked').length;
			if(length > 1) {
				layer.msg('只能选择一个角色进行修改');
				return false;
			} else if(length == 0) {
				layer.msg('请选择角色后进行修改');
				return false;
			}
			var id = $('input[name="jscheckbox"]:checked').val().split(";")[1];
			var frameId = $(window.frameElement).attr('name');
			top.layer.open({
				type: 2,
				title: '修改角色',
				shadeClose: true,
				shade: 0.8,
				area: ['40%', '40%'],
				content: 'page/jsgl/jsEdit.html?id=' + id+"&frameId="+frameId,
			});
		})

		//关联菜单
		$('body').on('click', '.glCaidan', function() {
			var length = $('input[name="jscheckbox"]:checked').length;
			if(length > 1) {
				layer.msg('只能选择一个角色关联菜单');
				return false;
			} else if(length == 0) {
				layer.msg('请选择角色进行操作');
				return false;
			}
			var rolecode = $('input[name="jscheckbox"]:checked').val().split(";")[0];
			var frameName = $(window.frameElement).attr('name');
			top.layer.open({
				type: 2,
				title: '修改角色',
				shadeClose: true,
				shade: 0.8,
				area: ['50%', '65%'],
				content: 'page/jsgl/glcd.html?rolecode=' + rolecode+"&frameName="+frameName,
			});
			

		})

		$('body').on('click', '.deleteBtn', function() {
			var length = $('input[name="jscheckbox"]:checked').length;
			if(length > 1) {
				layer.msg('只能选择一个角色进行删除');
				return false;
			} else if(length == 0) {
				layer.msg('请选择角色后进行删除');
				return false;
			}
			var id = $('input[name="jscheckbox"]:checked').val().split(";")[1];
			alert(id)
			layer.confirm('确定删除数据', {
				btn: ['确定', '取消'] //按钮
			}, function(index, layero) {
				var loading = layer.msg('正在删除...', {
					icon: 16,
					shade: 0.2,
					time: false
				});
				//确定函数
				$.ajax({
					type: "delete",
					url: BaseUrl + 'role/delete/' + id,
					async: true,
					success: function(data) {
						layer.close(loading);
						if(data.success) {
							layer.alert('删除成功', {
								skin: 'layui-layer-lan',
								closeBtn: 0,
								anim: 4 //动画类型
							}, function(tc, layero) {
								layer.close(tc);
								//删除后更新列表
								var deldata={}
								getData();
							});
						} else {
							layer.msg(data.message);
						}
					},
					error: function() {
						layer.close(loading);
						layer.msg(data.message);
					}
				});
			}, function(index, layero) {
				//取消函数
				layer.close(index);
			})
		})

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
								<td><input type="checkbox" name="jscheckbox" value="${result[i].rolecode};${result[i].id}" lay-skin="primary" ></td>
								<td>${i+1}</td>
								<td>${result[i].rolename}</td>
								<td>${result[i].rolecode}</td>
								<td>${result[i].rolestate}</td>
								<td>${result[i].roleexplain}</td>

							</tr>`;
			}
			$('#tbody').html(html);
			form.render()
			getnull();
		}

		//初次请求第一页的数据，并初始化分页,如果条件查询，请传入data
		function getData(data) {
			if(typeof(data) == "undefined") {
				data = {};
			}
			var urls = 'role/list/1/' + pageSize;
			var loading = layer.msg('正在查询...', {
				icon: 16,
				shade: 0.2,
				time: false
			});
			var total;
			var result;
			$.ajax({
				type: "get",
				url: BaseUrl + urls,
				data: data,
				async: true,
				success: function(data) {
					if(data.success) {
						result = data.queryResult.list;
						total = data.queryResult.total;
						var totalPage = total % pageSize == 0 ? (total / pageSize) : (Math.floor(total / pageSize) + 1);
						$("#pagination3").pagination({
							//totalPage---总条数   isshow:是否显示上一页下一页 首页末页   cunt  渲染的页码展示总数   
							totalPage: totalPage,
							pageCount: total,
							isShow: true,
							count: 10,
							homePageText: "首页",
							endPageText: "尾页",
							prevPageText: "上一页",
							nextPageText: "下一页",
							callback: function(current) {
								var data = {};
								$("#intSearch").attr("data-page",current);
								getPageData(current, data);

							}
						});
						setdata(result);
					} else {
						layer.msg(data.message);
					}
					layer.close(loading);
				},
				error: function(data) {
					layer.msg('程序错误!');
					layer.close(loading);
				}

			});
		}
		//获取指定的页码数据，如果是条件查询，请传入data参数
		getPageData=function (page, data) {
			if(typeof(data) == "undefined") {
				data = {};
			}

			var urls = 'role/list/' + page + '/' + pageSize;
			var loading = layer.msg('正在查询...', {
				icon: 16,
				shade: 0.2,
				time: false
			});
			var result;
			$.ajax({
				type: "get",
				url: BaseUrl + urls,
				async: true,
				data: data,
				success: function(data) {
					if(data.success) {
						result = data.queryResult.list;
						pagecount = data.queryResult.total;
						$('.pageCount').find('font').html(pagecount);
						setdata(result);
					} else {
						layer.msg(data.message);
					}
					layer.close(loading);
				},
				error: function(data) {
					layer.msg('程序错误');
					layer.close(loading);
				}
			});
		}

	});
})
