var navId = '';
var navPid = '';
$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		//关联菜单
		var setting = {
			callback: {
				onClick: zTreeOnClick
			}
		};

		getdata();

		function getdata() {
			var urls = 'sysMenu/list';
			var loading = layer.msg('正在查询...', {
				icon: 16,
				shade: 0.2,
				time: false
			});
			var zNodes = [];
			$.ajax({
				type: "get",
				url: BaseUrl + urls,
				async: false,
				success: function(data) {
					if(data.success) {
						zNodes = data.queryResult.list;
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
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}

		function zTreeOnClick(event, treeId, treeNode) {
			navId = treeNode.id;
			navPid = treeNode.pid;
		};

		$('.addBtn').click(function() {
			var frameId = $(window.frameElement).attr('name');
			if(navId == '') {
				navId = 0;
			}
			var url = 'page/cdgl/cdglAdd.html?id=' + navId+'&frameId='+frameId;
			top.layer.open({
				type: 2,
				title: '新增角色',
				shadeClose: true,
				shade: 0.8,
				area: ['50%', '50%'],
				content: url,
			});
		})

		$('.editBtn').click(function() {
			if(navId == '') {
				layer.msg('请选择一个菜单进行操作');
				return false;
			}
			var frameId = $(window.frameElement).attr('name');
			top.layer.open({
				type: 2,
				title: '修改角色',
				shadeClose: true,
				shade: 0.8,
				area: ['50%', '50%'],
				content: 'page/cdgl/cdglEdit.html?id=' + navId + '&pid='+navPid + '&frameId='+frameId,
			});
		})
		
		$('.deleteBtn').click(function() {
			if(navId == '') {
				layer.msg('请选择一个菜单进行操作');
				return false;
			}
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
					url: BaseUrl + '/sysMenu/del/' + navId,
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
								getdata();
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
		})

	});
})


})
