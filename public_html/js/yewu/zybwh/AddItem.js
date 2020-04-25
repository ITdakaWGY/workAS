$(function() {
	
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
			
		var hazardCode = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		//初次读取数据，第一页
		getData(hazardCode);

		$('body').on('click', '.AddBtn', function() {
			
			var itemCode = $(this).attr('data-code');
			var params = {
				itemCode: itemCode,
				hazardCode: hazardCode,
			};
			$.ajax({
				type: "post",
				url: BaseUrl + '/tjHazardItem/add',
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
							getData(hazardCode);
							
						});
						
					} else {
						layer.msg(data.message);
					}
				}
				
			});
		})
	});
	
	
	
function getData(hazardCode) {
	var urls = 'DiseaseItem/hazardItemNotinList/'+hazardCode+'/1/' + pageSize;
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
						var data={};
						getPageData(current, hazardCode);

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
								<td>${result[i].iteamName}</td>
								<td></td>
								<td>${result[i].price}</td>
								<td>${result[i].itemCode}</td>
								<td>${result[i].remarks}</td>
								<th>
									<span class="layui-btn chaxunBtn AddBtn" data-code="${result[i].itemCode}">新增</span>
								</th>
							</tr>`;
	}
	$('#tbody').html(html);
	getnull();
}



//获取指定的页码数据，如果是条件查询，请传入data参数
function getPageData(page, hazardCode) {
	if(typeof(data)=="undefined"){
		data={};
	}
	var urls = 'DiseaseItem/hazardItemNotinList/'+hazardCode+'/'+ page + '/' + pageSize;
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