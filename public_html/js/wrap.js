//var BaseUrl = 'http://localhost:8181/';
var BaseUrl = 'http://60.216.10.28:8181/';




var pageSize = 10;
//条码号打印驱动参数配置 ip地址 port 端口号 PrinterName 打印机名称  PrintTokens token 需要在参数中配置
var ip="127.0.0.1";
var port="9595";
var TmhPrintMachineName='BTP-L525(U)';
var PrintTokens="aishengkeji";
$(function() {
	var token = localStorage.getItem('token');
	if(token != 1) {
		window.location.href = "login.html";
	}
	$('.goWelcome').click(function() {
		window.location.href = "welcome.html";
	})

	$('.loginOut').click(function() { 
		localStorage.removeItem('token');
		window.location.href = "login.html";
	})

	//问诊模块左侧人员点击事件
	$('.leftTiJian').on('click', 'ul li', function(event) {
		event.stopPropagation();
		var tmh = $(this).attr('data-tmh');
		$('#sstmh').val(tmh);
		$('#searchBtn').trigger('click');
	})
	$('#show').click(function(){
		$('#checkItem').toggle();
	})
})

//格式化日期
function getriqi(riqi) {
	var a;
	if(riqi == '' || riqi == null || riqi == undefined) {
		a = '';
	} else {
		a = new Date(parseInt(riqi.substring(6, riqi.length - 2)));
		a = a.getFullYear() + '-' + (a.getMonth() + 1) + '-' + a.getDate();
	}

	return a;
}

//获取checkbox的所有选中值，并用逗号分隔
function getCheckArry(checkName) {
	var ids = '';
	$('input[name="' + checkName + '"]:checked').each(function(i) {
		ids += $(this).val() + ',';
	})
	ids = ids.substring(0, ids.length - 1);
	return ids;
}

//如果页面有原始数据null 则置空
function getnull() {
	$('.layui-table tr td').each(function() {
		var _this = $(this);
		if(_this.html() == 'null' || _this.html() == 'undefined') {
			_this.html('');
		}
	})
}

/*function loadPage(total,data) {
	var totalPage = total % pageSize == 0 ? (total / pageSize) : (Math.floor(total / pageSize) + 1);
	$("#pagination3").pagination({
		//totalPage---总条数   isshow:是否显示上一页下一页 首页末页   cunt  渲染的页码展示总数
		pageCount: total,
		totalPage: totalPage,
		isShow: true,
		count: 10,
		homePageText: "首页",
		endPageText: "尾页",
		prevPageText: "上一页",
		nextPageText: "下一页",
		callback: function(current) {
			getData(current,data);
		}
	});
}*/

//传入符合的数组，初始化checkbox----》 checkbox的name值需要与传入的数组名称保持一致
function intCheckbox(dom, arry) {
	if(arry==null||arry==''){
		return;
	}
	for(var i = 0; i < arry.length; i++) {
		$('input[name="' + dom + '"]').each(function() {
			if($(this).val() == arry[i]) {
				$(this).prop('checked', true);
			}
		})
	}
}

//初始化radio
function intRadio(dom, str) {
	$('input[name="' + dom + '"]').each(function() {
		if($(this).val() == str) {
			$(this).prop('checked', true);
		}
	})
}
//根据name与数据库字段保持统一，封装radio和input的值,input 需要添加属性data="data"，防止附表字段污染对象
function returnData() {
	var data = {};
	$('input[data="data"]').each(function() {
		data[$(this).attr('name')] = $(this).val();
	})

	$('input[data="radio"]').each(function() {
		var _this = $(this);
		if(_this.prop('checked')) {
			data[_this.attr('name')] = _this.val();
		}
	})
	return data;
}

//转化时间戳2020-02-27T00:00:00.000+0000为年月日
function getDate(str) {
	var dates;
	if(str != '' && str != undefined) {
		var dateee = new Date(str).toJSON();
		dates = new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '');
		dates = dates.substring(0, dates.length - 9);
	} else {
		dates = '';
	}
	return dates;
}

//获取当前时间 年月日
function getCurrentDate() {
	var date = new Date();
	var year = date.getFullYear(); //获取完整的年份(4位)
	var month = date.getMonth() + 1;
	if(month < 10) {
		month = '0' + month;
	}

	var day = date.getDate(); //获取当前日(1-31)
	if(day < 10) {
		day = '0' + day;
	}

	return year + '-' + month + '-' + day;
}

//已经体检人数统计
function isChecked(params) {
	var html = '';
	var total = '';
	$.ajax({
		type: "get",
		url: BaseUrl + 'grjbxx/list/1/10000',
		async: false,
		data: params,
		success: function(data) {
			var list = data.queryResult.list;
			total = list.length;
			if(list.length > 0) {
				for(var i = 0; i < list.length; i++) {
					html += '<li data-tmh="' + list[i].tmh + '">' + list[i].name + '/' + list[i].gzgw + '</li>';
				}
			}
		}
	});
	return {
		'html': html,
		'total': total
	}
}

function zhikong(dom) {
	$('.' + dom + ' input[type="text"]').each(function() {
		$(this).val('');

	})
	$('.' + dom + ' input[type="number"]').each(function() {
		$(this).val('');
	})
	$('.' + dom + ' input[type="tel"]').each(function() {
		$(this).val('');
	})

	$('.' + dom + ' textarea').each(function() {
		$(this).val('');
	})
	$('.' + dom + ' input[type="checkbox"]').each(function() {
		$(this).prop('checked', false);
	})
	$('.' + dom + ' input[type="radio"]').each(function() {
		$(this).prop('checked', false);
	})

}

function doNotEdit(dom) {
	$('.' + dom + ' input[type="text"]').each(function() {
		$(this).attr('readonly', true);
		$(this).addClass('bg-hui')
	})
	$('.' + dom + ' input[type="number"]').each(function() {
		$(this).attr('readonly', true);
		$(this).addClass('bg-hui')
	})

	$('.' + dom + ' input[type="tel"]').each(function() {
		$(this).attr('readonly', true);
		$(this).addClass('bg-hui')
	})
	$('.' + dom + ' textarea').each(function() {
		$(this).val('');
		$(this).addClass('bg-hui')
	})
	$('.' + dom + ' input[type="checkbox"]').each(function() {
		$(this).attr('disabled', true);
	})
	$('.' + dom + ' input[type="radio"]').each(function() {
		$(this).attr('disabled', true);
	})
}

function checkItem(tmh) {
	$.ajax({
		type: "get",
		url: BaseUrl + "/grjbxx/getProject/" + tmh,
		async: true,
		success: function(data) {
			var html = '';
			var yz = 0;
			var zs = 0;
			if(data.length > 0) {
				for(var i = 0; i < data.length; i++) {
					var type = data[i].state;
					if(type == 0) {
						type = '未做';
						type = '未检';
					} else if(type == 1) {
						type = '已做';
						type = '已检';
						yz++;
					}
					html += `<tr>
							<td>${data[i].projctName}</td>
							<td colspan="2">${type}</td>
						</tr>`;
						zs++;
				}
				$('#jcxm').html(yz+"/"+zs);
				$('#checkItem').html(html);
			}

		}
	});
}



//所有赋值为默认值

function intValue(){
	//置空input的值
	$('table tr td input[type="text"]').each(function(){
		$(this).val('');
	});
	//设置初始默认值 radio  checkbox
	$('table tr td').each(function(){
		$(this).find('input[type="checkbox"]').eq(0).prop('checked',true);
		$(this).find('input[type="radio"]').eq(0).prop('checked',true);
	})
	$('input[name="createtime"]').val(getCurrentDate());
}

