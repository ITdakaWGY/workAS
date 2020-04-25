$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form','laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
			var laydate = layui.laydate;
		laydate.render({
		  elem: '#signtime' //指定元素
		});
		var id = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var frameId = parseInt(window.location.href.split('?')[1].split('&')[1].split('=')[1])-1;
		//监听其他
		form.on('checkbox(whyszlqt)', function(data){
			if(data.elem.checked){
				$('#whyszlqt').show();
			}else{
				$('#whyszlqt').hide();
			}
		});
		
		var loadData = layer.msg('数据加载中...', {
			icon: 16,
			shade: 0.2,
			time: false
		});
	 
		 var params = {id:id};
		$.ajax({
			type: "Post",
			url: BaseUrl + '/signinfo/select',
			async:true,
			data:JSON.stringify(params),
			processData: false,//是否序列化
            contentType: "application/json", 
			success: function(data) {
				layer.close(loadData);
				if(data.success) {
					var list = data.queryResult.list[0];
					$("#signtime").val(list.signtime);
					$("#companyname").val(list.companyname);
					$("#physicalnumber").val(list.physicalnumber);
					$("#linkman").val(list.linkman);
					$("#phone").val(list.phone);
					$("#occupationharm").val(list.occupationharm);
					$("#protocol").val(list.protocol);
					$("#qtcl").val(list.qtcl);
					$("#address").val(list.address);
					$("#qybh").val(list.qybh);
					form.render();
				}else{
					layer.msg('data.message');
				}
		},
			error:function(data){
				layer.close(data.Message);
				layer.msg('程序错误!');
			}

		});
		
		//修改保存
		$('.saveTijian').click(function() {
			var loading = layer.msg('正在修改数据...', {
				icon: 16,
				shade: 0.2,
				time: false
			});
			//签约日期
		var signtime = $("#signtime").val();
		//公司名称
		var companyname = $("#companyname").val();
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
			
		var qybh = $("#qybh").val();
			
		var params = {
		signtime: signtime,
		companyname: companyname,
		physicalnumber: physicalnumber,
		linkman: linkman,
		phone: phone,
		occupationharm: occupationharm,
		address: address,
		protocol: protocol,
		qtcl: qtcl,
		qybh: qybh,
		id: id,
		}
			$.ajax({
				type: "put",
				url: BaseUrl+ 'signinfo/update/'+id,
				async: true,
                data:JSON.stringify(params),
                contentType: "application/json", 
                processData: false,
				success: function(data) {
					layer.close(loading);
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
				},
				error:function(data){
					layer.msg('程序错误!');
					layer.close(loading);
				}
			});
		})
		

	});
})


		

function handleFile(sgin,file){
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