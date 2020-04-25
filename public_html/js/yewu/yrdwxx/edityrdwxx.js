$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		var id = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var frameId = window.location.href.split('?')[1].split('&')[1].split('=')[1];
		var companycode = window.location.href.split('?')[1].split('&')[2].split('=')[1];
		
		var signinfoData = {
			companycode:companycode,
		};
		getSigninfo(signinfoData);
		
		$('#intSearch').click(function(){
			getSigninfo(signinfoData);
		})
		//监听其他
		form.on('checkbox(whyszlqt)', function(data) {
			if(data.elem.checked) {
				$('#harmtypeqt').show();
			} else {
				$('#harmtypeqt').hide();
			}
		});
		var loadData = layer.msg('数据加载中...', {
			icon: 16,
			shade: 0.2,
			time: false
		});
		var str;
		$.ajax({
			type: "get",
			url: BaseUrl + 'companyinfo/findBySingle',
			async: true,
			data: {
				companycode: companycode
			},
			success: function(data) {
				layer.close(loadData);
				if(data.success) {
					var list = data.companyinfo;
					$('#companyname').val(list.companyname);
					$('#address').val(list.address);
					$('#industrypaging').val(list.industrypaging);
					var companytype = list.companytype;
					$('input[name="companytype"]').each(function() {
						if($(this).val() == companytype) {
							$(this).prop('checked', true);
						}
					})
					var risktype = list.risktype;
					$('input[name="risktype"]').each(function() {
						if($(this).val() == risktype) {
							$(this).prop('checked', true);
						}
					})
					if(list.harmtype != null) {
						if(list.harmtype.indexOf('其他')){
							$('#harmtypeqt').show();
						}
						var harmtype = list.harmtype.split(',');
						intCheckbox('harmtype',harmtype);
					}
					if(list.harmevaluatetype != null) {
						var harmevaluatetype = list.harmevaluatetype.split(',');
						intCheckbox('harmevaluatetype',harmevaluatetype);
					}

					$('#legalperson').val(list.legalperson);
					$('#linkman').val(list.linkman);
					$('#principal').val(list.principal);
					$('#phone').val(list.phone);
					$('#staffnumber').val(list.staffnumber);
					$('#harmperson').val(list.harmperson);
					$('#harmtypeqt').val(list.harmtypeqt);
					if(list.harmevaluatenamecode!=""&&list.harmevaluatenamecode.length>0){
						$('#harmevaluatenamecode').val(list.harmevaluatenamecode);
						var url = BaseUrl+list.harmevaluatenamecode;
						$('#url').attr('href',url); 
						$('#url').show();
					}
					str = list.orgcode;
					var data = {};
					getOrgData(data,str);
					
					form.render();
				}else{
					layer.msg('data.message');
				}
			},
			error:function(data){
				layer.close(loadData);
				layer.msg('程序错误!');
			}

		});

		$('.saveTijian').click(function() {
			$('#FormVerification').bootstrapValidator('validate'); 
  			var flag = $("#FormVerification").data('bootstrapValidator').isValid(); 
  			if(!flag){
  				layer.alert("填写有误，请重新填写！"); 
  				return false;
  			}
  			
			var loading = layer.msg('正在修改数据...', {
				icon: 16,
				shade: 0.2,
				time: false
			});
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

			var params = {
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
				id:id,
			}
			$.ajax({
				type: "put",
				url: BaseUrl + 'companyinfo/update/'+id,
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
	function setOrgdata(result,str) {
	debugger;
		var html = '';
			html += '<option value="">请选择</option>';
		for(var i = 0; i < result.length; i++) {
			
			if(result[i].orgno==str){
				html += `<option value="${result[i].orgno}" selected>${result[i].orgname}</option>`;	
			}else{
				html += `<option value="${result[i].orgno}">${result[i].orgname}</option>`;	
			}
			
		}
		$('#orgcode').html(html);
		getnull();
		form.render();

	}

	function getOrgData(data,str) {
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
					setOrgdata(result,str);
				} else {
					layer.msg(data.message);
				}

			}
		});
	}
	
	
	function setSigninfo(result){
		if(result.length == 0) {
		var nodata = '<div class="nodata">暂无数据!</div>';
		$('#qytbody').html(nodata);
		return false;
		}
		var html = '';
		for(var i = 0; i < result.length; i++) {
			var protocolname = "无";
			var protocolurl = "";
			if(result[i].protocol!=null&&result[i].protocol.length>0){
			var protocolurl = BaseUrl + result[i].protocol;
			var protocolname = "下载查看";
			}
			var qtclname = "无";
			var qtclurl = "";
			if(result[i].qtcl!=null&&result[i].qtcl.length>0){
			var qtclurl = BaseUrl + result[i].qtcl;
			var qtclname = "下载查看";
			}
			html += `<tr>
									<td>${i+1}</td>
									<td>${result[i].signtime}</td>
									<td>${result[i].physicalnumber}</td>
									<td>${result[i].address}</td>
									<td>${result[i].linkman}</td>
									<td>${result[i].phone}</td>
									<td><a href="${protocolurl}">${protocolname}</a></td>
									<td><a href="${qtclurl}">${qtclname}</a></td>
									<th>
										<span class="layui-btn layui-btn-danger deleteBtn" data-id="${result[i].id}">删除</span>
									</th>
								</tr>`;
		}
		$('#qytbody').html(html);
		getnull();
	}
	
	
	function getSigninfo(data){
		$.ajax({
				type: "post",
				url: BaseUrl + '/signinfo/select',
				async: true,
                data:JSON.stringify(data),
                processData: false,
                contentType: "application/json", 
				success: function(data) {
						if(data.message){
							result = data.queryResult.list;
							setSigninfo(result);
						} else {
							layer.msg(data.message);
					}
				}
				
			});
	}
	
	
	
	$('body').on('click', '.add_qianyue', function() {
			var frameId = $(window.frameElement).attr('name');
			aaaaa=top.layer.open({
				type: 2,
				title: '新增签约信息',
				shadeClose: true,
				shade: 0.8,
				area: ['65%', '80%'],
				content: 'page/yrdwxx/addqianyue.html?companycode='+companycode+'&frameId='+frameId,
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
					url: BaseUrl + 'signinfo/delete/'+id,
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
								getSigninfo(signinfoData);
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
	
	});
})


function handleFile(file) {
	var formData = new FormData();
	var fileArry = file.files;
	var fileName = ""
	for(var i = 0; i < fileArry.length; i++) {
		if(/.(PDF|pdf|DOC|doc|DOCX|docx)$/.test(fileArry[i].name)) { //文件必须为文档
			formData.append("files", fileArry[i]); //文件上传处理
			fileName +=fileArry[i].name;
		} else {
			alert('上传文件必须是PDF或DOC !!');
			return false;
		}
	}
	$("#xywbName").html(fileName);
	//请求上传接口
	//提交请求后,置空file的选项，需要返回对应的文件名称和uuid，最后提交的是uuid，（循环页面上的相关uuid即可）
	 $.ajax({
		        url : BaseUrl+"companyinfo/fileUpload",
		        type : 'POST',
		        data : formData,
		        processData : false,
		        contentType : false,
			    cache: false
		    }).done(function(res) { //回调函数
				console.log(res)
				$("#harmevaluatenamecode").val(res);
			}).fail(function(res) { 
				console.log(res)
				alert("fail:"+res);
			});

}


