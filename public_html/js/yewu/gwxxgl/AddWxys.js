$(function() {
	
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
			
		var jobcode = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var companycode = window.location.href.split('?')[1].split('&')[1].split('=')[1];
		//初次读取数据，第一页
		getData(jobcode,companycode);

		$('body').on('click', '.AddBtn', function() {
			
			var hazardCode = $(this).attr('data-id');
			var params = {
				hazardCode: hazardCode,
				jobcode: jobcode,
			};
			$.ajax({
				type: "post",
				url: BaseUrl + '/tjHazardJob/add',
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
							layer.close(tc);
							getData(jobcode,companycode);
						});
						
					} else {
						layer.msg(data.message);
					}
				}
				
			});
		})
	});
	
	
	
function getData(jobcode,companycode) {
	var urls = 'Hazardinfo/hazardJobNotinList/'+jobcode+'/'+companycode+'/1/' + pageSize;
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
						getPageData(current, jobcode);

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
								<td>${result[i].hazardCode}</td>
								<td>${result[i].hazardType}</td>
								<td>${result[i].companyname}</td>
								
								<th>
									<span class="layui-btn AddBtn" data-id="${result[i].hazardCode}">添加</span>
								</th>
							</tr>`;
	}
	$('#tbody').html(html);
	getnull();
}



//获取指定的页码数据，如果是条件查询，请传入data参数
function getPageData(page, jobcode) {
	var urls = 'Hazardinfo/hazardJobNotinList/'+jobcode+'/'+ page + '/' + pageSize;
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
		success: function(data) {
			if(data.success) {
				result = data.queryResult.list;
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
})