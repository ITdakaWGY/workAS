//选中的关联菜单id，用逗号分隔
var checkId;
function zTreeOnCheck(event, treeId, treeNode) {
	checkId=[];
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	for(var i = 0; i < nodes.length; i++) {
			checkId.push(nodes[i].id);
		}
};


$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		//角色id
		var rolecode = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onCheck: zTreeOnCheck
			}
		};
		
		//执行保存的data对象
		$('.saveTijian').click(function() {
			
			if(typeof(checkId)=="undefined"||checkId.length==0){
				layer.msg('至少选择一个菜单或者修改一个菜单');
				return false;
			}
			
			var params = {
				rolecode: rolecode,
				menuid: checkId,
			}
			var loading = layer.msg('正在保存...', {
					icon: 16,
					shade: 0.2,
					time: false
			});

			$.ajax({
				type: "post",
				url: BaseUrl + '/menuRoleRelation/add',
				async: true,
				data: JSON.stringify(params),
				processData: false,
				contentType: "application/json",
				success: function(data) {
					layer.close(loading);
					if(data.success) {
						layer.alert('保存成功', {
							skin: 'layui-layer-lan',
							closeBtn: 0,
							anim: 4 //动画类型
						}, function(tc, layero) {
							parent.layer.closeAll();
						});
					} else {
						layer.close(loading);
						layer.msg(data.message);
					}

				}
			});

		})
		
		
		
		getdata(rolecode);
		
		function getdata(rolecode) {
			var urls = 'sysMenu/roleMenuRelation/'+rolecode;
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
	});
})

