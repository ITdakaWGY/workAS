$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form','laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
			var laydate = layui.laydate;
		laydate.render({
		  elem: '#signtime' //指定元素
		});

		var companycode = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var frameId = window.location.href.split('?')[1].split('&')[1].split('=')[1];
		//执行保存的data对象
		$('.saveTijian').click(function() {
			$('#FormVerification').bootstrapValidator('validate');
			var flag = $("#FormVerification").data('bootstrapValidator').isValid();
			if(!flag) {
				layer.alert("填写有误，请重新填写！");
				return false;
			}

		//签约日期
		var signtime = $("#signtime").val();
		//体检人数
		var physicalnumber  = $("#physicalnumber").val();
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
		//签约编号
		var qybh = $("#qybh").val();
			
		var params = {
		signtime: signtime,
		companycode: companycode,
		physicalnumber: physicalnumber,
		linkman: linkman,
		phone: phone,
		address: address,
		protocol: protocol,
		qtcl: qtcl, 
		qybh: qybh,
		}
		
		//alert(JSON.stringify(params));
		$.ajax({
			type:"post",
			url:BaseUrl+'signinfo/add',
			async:true,
			data:JSON.stringify(params),
			processData: false,//是否序列化
            contentType: "application/json", 
            success: function(data) {
				
					if(data.success) {
						layer.alert('保存成功', {
							skin: 'layui-layer-lan',
							closeBtn: 0,
							anim: 4 //动画类型
						}, function(tc, layero) {
							layer.close(tc);
							parent.layer.close(top.window.document.getElementById(frameId).contentWindow.aaaaa);
							var dom=$(top.window.document.getElementById(frameId).contentWindow.document.getElementById('intSearch'));
							dom.trigger('click');
						});
					} else {
						layer.msg(data.message);
					}

				}
		});
		
		});
		
		


		
		

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
	if(id=="file2") {
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
		if(id=="file2") {
			$("#protocol").val(res);
		} else if(id=="file3") {
			$("#qtcl").val(res);
		}

		
	}).fail(function(res) {
		console.log(res)
		alert("fail:" + res);
	});

}