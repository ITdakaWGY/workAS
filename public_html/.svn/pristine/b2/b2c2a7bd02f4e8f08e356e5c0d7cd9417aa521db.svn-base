var pagecount = '';
$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
			var laydate = layui.laydate;
			laydate.render({
			  elem: '#starttime' //指定元素
			});
			laydate.render({
			  elem: '#endtime' //指定元素
			});
			
			//初次读取数据，第一页
			var data = {djbs: "1"};
			getData(data); 
			  
			  
 
			var codeData = {};
		   getCodeData(codeData);

		//拼装下拉框数据
		/*function setselectdata(result) {
			if(result.length == 0) {
				var nodata = '<div class="nodata">暂无数据!</div>';
				$('#schoolName').html(nodata);
				return false;
			}
			var html = '';
			for(var i = 0; i < result.length; i++) {
				    html += `
				    <option value="${result[i].orgno}">${result[i].orgname}</option>`;
			}
			$('#schoolName').html(html);
			getnull();
			form.render();
		}
			});
		})
*/		

			  
			  
		$('#intSearch').click(function(){
			//初次读取数据，第一页
			var data = {djbs: "1"};
			getData(data); 
		})
			 //条件查询事件
		$('body').on('click','#searchBtn',function(){
			var starttime=$('#starttime').val();
			var endtime = $("#endtime").val();
			var companycode = $("#companycode").val();
			var searchdata={
				tjrqState: starttime,
				companycode: companycode,
				tjrqEnd: endtime,
				djbs: "1",
			}
			getData(searchdata);
		})
 
			
		//体检人员登记
		$('body').on('click', '#AddBtn', function() {
			//获取当前自增的弹窗id
//			var frameId=$(window.frameElement).attr('data-id');
			var frameId = $(window.frameElement).attr('name');
			var id=0;
			top.layer.open({
				type: 2,
				title: '职工信息登记',
				shadeClose: true,
				shade: 0.8,
				area: ['80%', '95%'],
				content: 'page/tjgl/dengji.html?type=add&id='+id+'&frameId='+frameId,
			});
		})
		
		$('body').on('dblclick', '.tableTop table tbody tr', function() {
			var frameId = $(window.frameElement).attr('name');
			var id=$(this).find('.hiddenValue').val();
			top.layer.open({
				type: 2,
				title: '职工信息详情',
				shadeClose: true,
				shade: 0.8,
				area: ['80%', '95%'],
				content: 'page/tjgl/dengji.html?type=look&id='+id+'&frameId='+frameId,
				
			});
		})
		
		$('body').on('click', '.editBtn', function() {
			//获取当前自增的弹窗id
			/*var frameId=$(window.frameElement).attr('data-id');*/
			var frameId = $(window.frameElement).attr('name');
			var id=$(this).attr('data-id');
			top.layer.open({
				type: 2,
				title: '修改职工信息详情',
				shadeClose: true,
				shade: 0.8,
				area: ['80%', '95%'],
//				content: 'page/tjgl/dengji.html?id='+id+'&frameId='+frameId,
				content: 'page/tjgl/dengji.html?type=edit&id='+id+'&frameId='+frameId,
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
					url: BaseUrl + 'grjbxx/delete/'+id,
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
	//debugger;
		var html = '';
			html += `<option value="">请选择单位</option>`;
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


//初次请求第一页的数据，并初始化分页,如果条件查询，请传入data
 
function getData(params) {
	if(typeof(params)=="undefined"){
		params={};
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
		data: params,
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
						$('#intSearch').attr('data-page',current);
						//var data={};
						getPageData(current, params);

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
								<td>${result[i].name}</td>
								<td>${result[i].sex}</td>
								<td>${result[i].sfz}</td>
								<td>${result[i].brdh}</td>
								<td>${result[i].companyname}</td>
								<td>${result[i].gzgw}</td>
								<th>
									<span class="layui-btn chaxunBtn editBtn" data-id="${result[i].id}">修改</span>
									<span class="layui-btn chaxunBtn deleteBtn" data-id="${result[i].id}">删除</span>
								</th>
								<input type="hidden" value="${result[i].id}" class="hiddenValue" >
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