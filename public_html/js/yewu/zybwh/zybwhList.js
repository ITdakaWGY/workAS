var pagecount = '';
$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
			
		//初次读取数据，第一页
		var data = {};
		getData(data);	
		
		$('#intSearch').click(function(){
			getData(data);
		})
		
		//条件查询事件
		$('body').on('click','#searchBtn',function(){
			var hazardName=$('#name').val();
			if(hazardName==""){
				getData();
			}
			var searchdata={
				hazardName:hazardName
			}
			getData(searchdata);
		})
		
		
		$('body').on('click', '#AddBtn', function() {
			var frameId = $(window.frameElement).attr('name');
			top.layer.open({
				type: 2,
				title: '职业病危害因素添加',
				shadeClose: true,
				shade: 0.8,
				area: ['70%', '60%'],
				content: 'page/tjxmxx/addzybwhys.html?frameId='+frameId,
			});
		})

		$('body').on('click', '.editBtn', function() {
			var frameId = $(window.frameElement).attr('name');
			var id = $(this).attr('data-id');
			top.layer.open({
				type: 2,
				title: '职业病危害因素修改',
				shadeClose: true,
				shade: 0.8,
				area: ['70%', '60%'],
				content: 'page/tjxmxx/editzybwhys.html?id='+id+'&frameId='+frameId,
			});
		})
		
		/*查看危险因素*/
		$('body').on('click', '.seeFactor', function() {
			var hazardCode = $(this).attr('data-code');
			top.layer.open({
				type: 2,
				title: '查看危险因素',
				shadeClose: true,
				shade: 0.8,
				area: ['90%', '90%'],
				content: 'page/tjxmxx/ckItem.html?hazardCode='+hazardCode,
			});
		})
		
		
		/*新增危险因素*/
		$('body').on('click', '.seeAdd', function() {
			var hazardCode = $(this).attr('data-code');
			top.layer.open({
				type: 2,
				title: '查新增危险因素',
				shadeClose: true,
				shade: 0.8,
				area: ['90%', '90%'],
				content: 'page/tjxmxx/AddItem.html?hazardCode='+hazardCode,
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
					url: BaseUrl + '/Hazardinfo/del/' + id,
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

	});
})


//初次请求第一页的数据，并初始化分页,如果条件查询，请传入data
function getData(param) {
	if(typeof(param)=="undefined"){
		param={};
	}
	var urls = 'Hazardinfo/list/1/' + pageSize;
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
								<td>${result[i].hazardName}</td>
								<td>${result[i].hazardType}</td>
								<td>${result[i].companyname}</td>
								<td>${result[i].hazardCode}</td>
								<th>
									<span class="layui-btn chaxunBtn seeFactor" data-code="${result[i].hazardCode}">查看项目</span>
									<span class="layui-btn chaxunBtn seeAdd" data-code="${result[i].hazardCode}">添加项目</span>
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
	var urls = 'Hazardinfo/list/' + page + '/' + pageSize;
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