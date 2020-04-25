$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form','laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
			var laydate = layui.laydate;
		laydate.render({
		  elem: '#qyrq' //指定元素
		});
		
		//科室其他
		form.on('radio(keshi)', function(data) {
	
			if (data.value == '其他') {
				$('#keshi_qita').show();
			} else {
				$('#keshi_qita').hide();
			}
		});
		
		
		var id = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var frameId = window.location.href.split('?')[1].split('&')[1].split('=')[1];
		 var dataCode = {};
		 getOrgData(dataCode);
		var loadData = layer.msg('数据加载中...', {
			icon: 16,
			shade: 0.2,
			time: false
		});
		 var params = {id:id};
			$.ajax({
			type: "get",
			url: BaseUrl + 'userLogin/list/1/1',
			async:true,
			data: params,
			success: function(data) {
				layer.close(loadData);
				if(data.success) {
					var list = data.queryResult.list[0];
					$("#name").val(list.name);
					$("#userNumber").val(list.yhbh);
					 $("#password").val(list.password);
					 $("#exactlyPassword").val(list.password);
					 $("#userType").val(list.userType);
					 $("#userState").val(list.userState);
					 $("#systemCode").val(list.xtdm);
					 $("#orgcode").val(list.orgcode);
					 $("#sketch").val(list.sketch);
					 intRadio("keshi",list.department);
					 $("#keshi_qita").val(list.departmentqt);
					 $("#zhicheng").val(list.title);
					 $("#xm").val(list.xm);
					 debugger;
					 if(list.res0!=null&&list.res0!=""){
					 	 var url = BaseUrl+list.res0;
						$('#img').show().attr('src',url);
				 	 	$("#url").val(list.res0);
					 }
					
					 
					form.render();
				}else{
					layer.msg(data.message);
				}
		},
			error:function(data){
				layer.close(data.Message);
				layer.msg('程序错误!');
			}

		});
		
		
		//执行修改保存的data对象
		$('.saveTijian').click(function() {
			var loading = layer.msg('正在修改数据...', {
				icon: 16,
				shade: 0.2,
				time: false
			});
			var name = $("#name").val();
			var userNumber = $("#userNumber").val();
			var password = $("#password").val();
			var userType = $("#userType option:selected").val();
			var userState = $("#userState option:selected").val();
			var systemCode = $("#systemCode").val();
			var orgcode = $("#orgcode option:selected").val();
			var orgname = $("#orgcode option:selected").text();
			var res0 = $("#url").val();
			var department = $("input[name='keshi']:checked").val();
			var title = $("#zhicheng").val();
			var sketch = $("#sketch").val();
			var departmentqt = $("#keshi_qita").val();
			var xm = $("#xm").val();
			if(password==null||password==''){
			 	layer.msg("密码不能为空");
				return;
			}
			var params = {
			  name: name,
			  yhbh: userNumber,
			  password: password,
			  userType: userType,
			  userState: userState,
			  xtdm: systemCode,
			  orgcode: orgcode,
			  res0:res0,
			  orgname: orgname,
			  department:department,
			  title: title,
			  sketch: sketch,
			  departmentqt: departmentqt,
			}
			$.ajax({
				type: "put",
				url: BaseUrl+ 'userLogin/update/'+id,
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
							var dom=$(top.window.document.getElementsByName(frameId)[0].contentWindow.document.getElementById('intSearch'));
     							 var newpage=parseInt(dom.attr('data-page'));
     							 top.window.document.getElementsByName(frameId)[0].contentWindow.getPageData(newpage);
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
	debugger;
	var formData = new FormData();
	  var fileArry = file.files;;
		if(/.(jpg|png|jpeg)$/.test(fileArry[0].name)) { //文件必须为图片
			 formData.append("img",fileArry[0]);
		} else {
			layer.msg("图片格式不正确，格式只支持jpg|png|jpeg");
			return false;
		}


	 $.ajax({
		 	 url:BaseUrl+'userLogin/upload',
		 	 type:"post",
		 	 data : formData,
		 	 processData: false,//是否序列化
		 	 contentType:false,//设置你发送给服务器的格式
		 	 cache: false,//清除缓存
		 	 success:function(data){
		 	 	debugger;
		 	 	var url = BaseUrl+data.substring(3);
				$('#img').show().attr('src',url);
		 	 	$("#url").val(data);
		 	 },
		 	 error:function(){
		 	 	layer.msg("失败");
		 	 }
		 	 
		 	
		 }) 


}