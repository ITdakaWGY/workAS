$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		 laydate.render({
			    elem: '#starttime' //指定元素
			 });
		var danwei = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var flag = window.location.href.split('?')[1].split('&')[1].split('=')[1];
	
		//应体检
		if(flag=="1"){
			var data = {
			companycode:danwei
		     }
		}
	
		//已体检
		if(flag=="2"){
			var data = {
			companycode:danwei,
			res1:"1",
		     }
		}
	
		//未体检
		if(flag=="3"){
			var data = {
			companycode:danwei,
			res1:"2",
		     }
		}
	
		getData(data);		       	  		  		  

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
			alert(data.message);
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
								<td>${result[i].name}</td>
								<td>${result[i].sfz}</td>
								<td>${result[i].gwgw}</td>
								<td>${result[i].brdh}</td>
								<td>${result[i].companyname}</td>
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