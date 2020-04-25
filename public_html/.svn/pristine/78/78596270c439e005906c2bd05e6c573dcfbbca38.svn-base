$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		
		laydate.render({
			elem: '#tjrqstart', //指定元素
		});
		laydate.render({
			elem: '#tjrqend', //指定元素
			
		});
		
		var data = {};
		getData(data);
		
		
		var codeData = {};
		getCodeData(codeData);
		
		
		//条件查询事件
		$('body').on('click','#searchBtn',function(){
			var danwei=$('#danwei').val();
			var tjrqstart =$('#tjrqstart').val();
			var tjrqend = $('#tjrqend').val();
			var sfz = $('#sfz').val();
			var name = $('#name').val();
			var searchdata={
				companycode:danwei,
				tjrqState: tjrqstart,
				tjrqEnd: tjrqend,
				sfz: sfz,
				name: name,
			}
			getData(searchdata);
			});
		
		
		$('body').on('click', '.lookBtn', function() {
					var tmh=$(this).attr('data-tmh');
					var personalid=$(this).attr('data-personalid');
					top.layer.open({
						type: 2,
						title: '体检结果汇总',
						shadeClose: true,
						shade: 0.8,
						area: ['90%', '90%'],
						content: 'page/tjgl/chakan.html?tmh='+tmh+'&personalid='+personalid,
					});
				})
		
		
	//接受结果集，往表格进行数据填充
	function setCodedata(result) {
	debugger;
		var html = '';
			html += `<option value="">请选择</option>`;
		for(var i = 0; i < result.length; i++) {
			html += `<option value="${result[i].companycode}">${result[i].companyname}</option>`;
		}
		$('#danwei').html(html);
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
	//初次请求第一页的数据，并初始化分页,如果条件查询，请传入data
function getData(data) {
	if(typeof(data)=="undefined"){
		data={};
	}
	var urls = 'grjbxx/list/1/' + pageSize;
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
						var data={};
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
								<td>${result[i].companyname}</td>
								<td>${result[i].sfz}</td>
								<td>${result[i].name}</td>
								<td>${result[i].tjrq}</td>
								<td>${result[i].zjjcjl}</td>
								<td>${result[i].brdh}</td>
								
								<th>
									<span class="layui-btn chaxunBtn lookBtn" data-tmh="${result[i].tmh}" data-personalid="${result[i].personalid}">详情</span>
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
	var urls = 'grjbxx/list/' + page + '/' + pageSize;
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