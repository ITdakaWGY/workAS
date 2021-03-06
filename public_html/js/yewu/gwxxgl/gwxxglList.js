var pagecount = '';
$(function() {
	
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
			
		//初次读取数据，第一页
		var data = {};
		getData(data);
		
		var codeData = {};
		getCodeData(codeData);
		
		$('#intSearch').click(function(){
			getData();
		})
			
			
		//条件查询事件
		$('body').on('click','#searchBtn',function(){
			var companycode=$('#companycode').val();
			if(companycode==""){
				getData();
			}
			var searchdata={
				companycode:companycode
			}
			getData(searchdata);
		})
		
		$('body').on('click', '#AddBtn', function() {
			var frameId=$(window.frameElement).attr('name');
			top.layer.open({
				type: 2,
				title: '岗位信息添加',
				shadeClose: true,
				shade: 0.8,
				area: ['50%', '60%'],
				content: 'page/gwxxgl/addgwxxgl.html?frameId='+frameId,
			});
		})

		$('body').on('click', '.editBtn', function() {
			var id = $(this).attr('data-id');
			var frameId=$(window.frameElement).attr('name');
			top.layer.open({
				type: 2,
				title: '岗位信息修改',
				shadeClose: true,
				shade: 0.8,
				area: ['70%', '50%'],
				content: 'page/gwxxgl/editgwxxgl.html?id=' + id+'&frameId='+frameId,
			});
		})
		
		/*查看危险因素*/
		$('body').on('click', '.seeFactor', function() {
			var jobcode = $(this).attr('data-code');
			top.layer.open({
				type: 2,
				title: '查看危险因素',
				shadeClose: true,
				shade: 0.8,
				area: ['90%', '90%'],
				content: 'page/gwxxgl/ckWxys.html?jobcode='+jobcode,
			});
		})
		
		
		/*新增危险因素*/
		$('body').on('click', '.seeAdd', function() {
			var jobcode = $(this).attr('data-code');
			var companycode = $(this).attr('data-companycode');
			top.layer.open({
				type: 2,
				title: '查新增危险因素',
				shadeClose: true,
				shade: 0.8,
				area: ['90%', '90%'],
				content: 'page/gwxxgl/AddWxys.html?jobcode='+jobcode+'&companycode='+companycode,
			});
		})

		$('body').on('click', '.deleteBtn', function() {
			var id = $(this).attr('data-id');
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
					url: BaseUrl + '/Jobinfo/del/' + id,
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
	function setCodedata(result) {
	debugger;
		var html = '';
			html += `<option value="">请选择</option>`;
		for(var i = 0; i < result.length; i++) {
			html += `<option value="${result[i].companycode}">${result[i].companyname}</option>`;
		}
		$('#companycode').html(html);
		getnull();
		form.render();

	}

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
	
function getData(param) {
	if(typeof(param)=="undefined"){
		param={};
	}
	var urls = 'Jobinfo/list/1/' + pageSize;
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
		data: param,
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
						$('#intSearch').attr("data-page",current);
						getPageData(current, param);

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
								<td>${i+1}</td>
								<td>${result[i].companycode}</td>
								<td>${result[i].companyname}</td>
								<td>${result[i].jobcode}</td>
								<td>${result[i].jobname}</td>
								
								<th>
									<span class="layui-btn chaxunBtn seeFactor" data-code="${result[i].jobcode}">查看危险因素</span>
									<span class="layui-btn chaxunBtn seeAdd" data-companycode="${result[i].companycode}" data-code="${result[i].jobcode}">添加危险因素</span>
									<span class="layui-btn chaxunBtn editBtn" data-id="${result[i].id}">修改</span>
									<span class="layui-btn chaxunBtn deleteBtn" data-id="${result[i].id}">删除</span>
								</th>
							</tr>`;
	}
	$('#tbody').html(html);
	getnull();
}



//获取指定的页码数据，如果是条件查询，请传入data参数
function getPageData(page, data) {
	if(typeof(data)=="undefined"){
		data={};
	}
	var urls = 'Jobinfo/list/' + page + '/' + pageSize;
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
		data:data,
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
