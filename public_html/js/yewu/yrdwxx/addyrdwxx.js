$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		laydate.render({
			elem: '#signtime', //指定元素
			value: new Date,
		});
		var orgData = {};
		getOrgData(orgData);
		/*laydate.render({
			elem: '#createtime' //指定元素
		});*/
		//获取上一步业务流--》增加弹窗的id，用作执行页s面刷新方法。
		var frameId = window.location.href.split('?')[1].split('=')[1];
		//监听危害因素种类其他
		form.on('checkbox(whyszlqt)', function(data) {
			if(data.elem.checked) {
				$('#harmtypeqt').show();
			} else {
				$('#harmtypeqt').hide();
			}
		});
		//执行保存的data对象
		$('.saveTijian').click(function() {
			$('#FormVerification').bootstrapValidator('validate');
			var flag = $("#FormVerification").data('bootstrapValidator').isValid();
			if(!flag) {
				layer.alert("填写有误，请重新填写！");
				return false;
			}

			//用人单位全称
			var companyname = $('#companyname').val();
			//地址
			var address = $('#address').val();
			//行业分类
			var industrypaging = $('#industrypaging').val();
			//企业规模
			var companytype = $('input[name="companytype"]:checked').val();
			//法定代表人
			var legalperson = $('#legalperson').val();
			//主管负责人
			var linkman = $('#linkman').val();
			//职业卫生管理机构负责人
			var principal = $('#principal').val();
			//联系电话
			var phone = $('#phone').val();
			//职业病危害风险分类
			var risktype = $('input[name="risktype"]:checked').val();
			//在岗职工总人数
			var staffnumber = $('#staffnumber').val();
			//接触职业病危害人数
			var harmperson = $('#harmperson').val();
			//存在的职业病危害因素种类
			var harmtype = getCheckArry('harmtype');
			//存在的职业病危害因素种类其他
			var harmtypeqt = $('#harmtypeqt').val();
			//职业病危害检测(评价)报告类型
			var harmevaluatetype = getCheckArry('harmevaluatetype');
			//职业病危害检测(评价)报告名称及编号
			var harmevaluatenamecode = $('#harmevaluatenamecode').val();
			//职业卫生技术服务机构
			var orgcode = $('#orgcode').val();

			var companyinfo = {
				companyname: companyname,
				address: address,
				industrypaging: industrypaging,
				companytype: companytype,
				legalperson: legalperson,
				linkman: linkman,
				principal: principal,
				phone: phone,
				risktype: risktype,
				staffnumber: staffnumber,
				harmperson: harmperson,
				harmtype: harmtype,
				harmtypeqt: harmtypeqt,
				harmevaluatetype: harmevaluatetype,
				harmevaluatenamecode: harmevaluatenamecode,
				orgcode: orgcode,
			}

			//签约日期
			var signtime = $("#signtime").val();
			//体检人数
			var physicalnumber = $("#physicalnumber").val();
			//联系人
			var linkman = $("#linkman").val();
			//联系电话
			var phone = $("#phone").val();
			//签约地址
			var address = $("#address").val();
			//协议文本
			var protocol = $("#protocol").val();
			//其他资料
			var qtcl = $("#qtcl").val();

			var signinfo = {
				signtime: signtime,
				physicalnumber: physicalnumber,
				companyname: companyname,
				linkman: linkman,
				phone: phone,
				address: address,
				protocol: protocol,
				qtcl: qtcl,
			}

			var params = {
				companyinfo: companyinfo,
				signinfo: signinfo,
			}

			$.ajax({
				type: "post",
				url: BaseUrl + '/companyinfo/add',
				async: true,
				data: JSON.stringify(params),
				processData: false,
				contentType: "application/json",
				success: function(data) {
					if(data.success) {
						layer.alert('保存成功', {
							skin: 'layui-layer-lan',
							closeBtn: 0,
							anim: 4 //动画类型
						}, function(tc, layero) {
							parent.layer.closeAll();
							var dom=$(top.window.document.getElementsByName(frameId)[0].contentWindow.document.getElementById('intSearch'));
							var newpage=parseInt(dom.attr('data-page'));
							top.window.document.getElementsByName(frameId)[0].contentWindow.getPageData(newpage);
						});
					} else {
						layer.msg(data.message);
					}

				}
			});

		})

		//接受结果集，往表格进行数据填充
		function setOrgdata(result) {
			var html = '';
			for(var i = 0; i < result.length; i++) {
				html += `<option value="${result[i].orgno}">${result[i].orgname}</option>`;
			}
			$('#orgcode').html(html);
			getnull();
			form.render();

		}

		function getOrgData(data) {
			if(typeof(data) == "undefined") {
				data = {};
			}
			var result;
			$.ajax({
				type: "get",
				url: BaseUrl + 'sysOrg/selectList',
				data: data,
				async: true,
				success: function(data) {
					if(data.success) {
						result = data.queryResult.list;
						setOrgdata(result);
					} else {
						layer.msg(data.message);
					}

				}
			});
		}

	});
})

function handleFile(file) {
	var formData = new FormData();
	var fileArry = file.files;
	var id = file.id;
	var fileName = ""
	for(var i = 0; i < fileArry.length; i++) {
		if(/.(PDF|pdf|DOC|doc|DOCX|docx)$/.test(fileArry[i].name)) { //文件必须为文档
			formData.append("files", fileArry[i]); //文件上传处理
			fileName += fileArry[i].name;
		} else {
			alert('上传文件必须是PDF或DOC !!');
			return false;
		}
	}
	if(id=="file1") {
		$("#xywbName").html(fileName);
	} else if(id=="file2") {
		$("#protName").html(fileName);
	} else if(id=="file3") {
		$("#qtclName").html(fileName);
	}

	//请求上传接口
	//提交请求后,置空file的选项，需要返回对应的文件名称和uuid，最后提交的是uuid，（循环页面上的相关uuid即可）
	$.ajax({
		url: BaseUrl + "companyinfo/fileUpload",
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		cache: false
	}).done(function(res) { //回调函数
		console.log(res)
		if(id=="file1") {
			$("#harmevaluatenamecode").val(res);
		} else if(id=="file2") {
			$("#protocol").val(res);
		} else if(id=="file3") {
			$("#qtcl").val(res);
		}

		
	}).fail(function(res) {
		console.log(res)
		alert("fail:" + res);
	});

}