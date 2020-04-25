$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form','laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
			var laydate = layui.laydate;
		laydate.render({
		  elem: '#signtime' //指定元素
		});
		
				var frameId = parseInt(window.location.href.split('?')[1].split('=')[1])-1;
					var codeData = {};
		   getCodeData(codeData);
		   //生成签约编号
			var timestamp=new Date().getTime();
			$("#qybh").val(timestamp);

		//监听其他
		/*form.on('checkbox(whyszlqt)', function(data){
			if(data.elem.checked){
				$('#whyszlqt').show();
			}else{
				$('#whyszlqt').hide();
			}
		});*/	
	
		//执行保存的data对象
		$('.saveTijian').click(function() {
		//签约日期
		var signtime = $("#signtime").val();
		//公司名称
		var companycode = $("#companycode").val();
		var companyname = $("#companycode option:checked").text();
		//体检人数
		var physicalnumber  = $("#physicalnumber").val();
		//联系人
		var linkman = $("#linkman").val();
		//联系电话
		var phone = $("#phone").val();
		//职业危害
		var occupationharm = $("#occupationharm").val();
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
		companyname: companyname,
		physicalnumber: physicalnumber,
		linkman: linkman,
		phone: phone,
		occupationharm: occupationharm,
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
							parent.layer.closeAll();
							var dom=$(top.window[frameId].document.getElementById('intSearch'));
							dom.trigger('click');
						});
					} else {
						layer.msg(data.message);
					}

				}
		});
		
		});
		
		
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
//查询单位
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


function handleFile(sgin,file) {
	var formData = new FormData();
	var fileArry = file.files;
	var fileName = "";
	formData.append("qybh",$("#qybh").val());
	for(var i = 0; i < fileArry.length; i++) {
		if(/.(PDF|pdf|DOC|doc|DOCX|docx)$/.test(fileArry[i].name)) { //文件必须为文档
			formData.append("files", fileArry[i]); //文件上传处理
			fileName +=fileArry[i].name+","
		} else {
			alert('上传文件必须是PDF或DOC !!');
			return false;
		}
	}
	fileName = fileName.substring(0,fileName.length-1);
	switch (sgin){
		case '1':
		$("#xywbName").html(fileName);
			break;
		case '2':
		$("#qtclName").html(fileName);
			break;
	}
	
	//请求上传接口
	//提交请求后,置空file的选项，需要返回对应的文件名称和uuid，最后提交的是uuid，（循环页面上的相关uuid即可）
	/*$.ajax({
		type:"post",
		url:BaseUrl+"signinfo/fileUpload",
		async:true,
		cache: false,//上传文件无需缓存
       	processData: false,//用于对data参数进行序列化处理 这里必须false
		success:function(){
			$('#files').val('');
			
		}
	});*/
	
	    $.ajax({
		        url : BaseUrl+"signinfo/fileUpload",
		        type : 'POST',
		        data : formData,
		        processData : false,
		        contentType : false,
			    cache: false
		    }).done(function(res) { //回调函数
				console.log(res)
				
				switch (sgin){
					case '1':
					$("#protocol").val(res);
						break;
					case '2':
					$("#qtcl").val(res);
						break;
				}
			}).fail(function(res) { 
				console.log(res)
				alert("fail:"+res);
			});

 
	
}