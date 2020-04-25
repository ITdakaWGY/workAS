var isadd = true;
$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		laydate.render({
			elem: '#tjrq',
			format: 'yyyy-MM-dd',
			value: new Date()
		});
		laydate.render({
			elem: '#createtime',
		});
		$('#sstmh').focus();
		$('#sstmh').bind('input propertychange', function() {
			var tmh = $(this).val();
			if(tmh.length == 8) {
				$('#searchBtn').trigger('click');
			}
		});
		var a = getCurrentDate();
		$('#createtime').val(a);
		form.on('checkbox(chiliezc)', function(data) {
			if(data.elem.checked) {
				$('input[name="cl"]:not(:first)').each(function() {
					$(this).prop('checked', false);
					$('.chilieyc').hide();
					$('.chilieyc input').each(function() {
						$(this).val('');
					})
					form.render();
				})
			}
		});

		//初次请求
		/* var companycode = localStorage.getItem('companycode');
		$("#tjrq").val(getCurrentDate());
		$('.leftTiJian p').eq('1').trigger('click');
		$('.leftTiJian p').eq('0').trigger('click'); */

		//体检日期时间框赋值为今日
		$("#tjrq").val(getCurrentDate());
		//初次请求左侧已体检和未体检人数
		var a = getCurrentDate();
		//已体检人数接口获取数据
		var params = {
			tjrq: a,
			tjflagnk: "1",
		}
		var ytjdata = isChecked(params);
		var ytjhtml = ytjdata.html;
		var ytjtotal = ytjdata.total;
		//未体检人数接口获取数据
		var params = {
			tjrq: a,
			tjflagnk: "0",
		}
		var wtjdata = isChecked(params);
		var wtjhtml = wtjdata.html;
		var wtjtotal = wtjdata.total;

		$('#ytjrs').html(ytjtotal);
		$('#wtjrs').html(wtjtotal);
		$('.leftTiJian ul').eq(0).html(wtjhtml);
		$('.leftTiJian ul').eq(1).html(ytjhtml);

        var userLogin = eval('(' + window.localStorage.getItem('userLogin') + ')');
		$('#ysqz').val(userLogin.xm);
		$('#searchMuBan').click(function() {
			var ysxx = userLogin.yhbh;
			if(ysxx.trim() == '') {
				layer.msg('请先登录');
				$('#ysqz').focus();
				return false;
			}
			var params = {
				ysxx: ysxx
			}
			var loading = layer.msg('正在查询...', {
				icon: 16,
				shade: 0.2,
				time: false
			});

			setTimeout(function() {
				$.ajax({
					type: "get",
					url: BaseUrl + 'template/all',
					async: false,
					data: params,
					success: function(data) {
						layer.close(loading);
						if(data.success) {
							var html = '';

							for(var i = 0; i < data.queryResult.list.length; i++) {
								html += `
                        			<div class='textarea-block'>
										<input name="desc" value="${data.queryResult.list[i].mbxx}" class="layui-input textarea-item fl addInput" type="text" placeholder="请输入值">
										<span class="btnOk fl ml20 iconfont">&#xe627;</span>
										<span class="deletemb fl ml20 iconfont">&#xe608;</span>
										<div class="textarea-count">
											<span class="textareaInput">0</span>/<span class="textareaTotal">100</span>
										</div>
									</div>`;
							}
							$('#mubanList').html(html);

						}
					},
					error: function(data) {
						layer.close(loading);
						layer.msg(data.message);
					}
				})
			})
		})

		$('#savemuban').click(function() {

			var loading = layer.msg('正在保存...', {
				icon: 16,
				shade: 0.2,
				time: false
			});

			$('.addInput').each(function() {
				var mbxx = $(this).val();
				var ysxx = userLogin.yhbh;
				var params = {
					ysxx: ysxx,
					mbxx: mbxx
				}
				$.ajax({
					type: "post",
					url: BaseUrl + '/template/add',
					async: true,
					data: JSON.stringify(params),
					processData: false,
					contentType: "application/json",
					success: function(data) {
						layer.close(loading);
						if(data.success) {
							layer.alert('保存成功', {
								skin: 'layui-layer-lan',
								closeBtn: 0,
								anim: 4 //动画类型
							}, function(tc, layero) {
								window.location.reload();

							});
						} else {
							layer.msg(data.message);
						}
					},
					error: function(data) {
						layer.close(loading);
						layer.msg(data.message);
					}
				});
			})
		})

		$('#searchBtn').click(function() {
			//执行未体检人数查询
			//var companycode=$('input[name="companycode"]').val();
			var tmh = $('#sstmh').val();
			var tjrq = $('#tjrq').val();

			if(tmh.trim() == '') {
				layer.msg('条码号不能为空');
				$('#sstmh').focus();
				return false;
			}
			var params = {
				tmh: tmh,
				tjrq: tjrq,
			}

			var loading = layer.msg('正在查询...', {
				icon: 16,
				shade: 0.2,
				time: false
			});
			//是否执行赋值操作
			var isComing = false;
			setTimeout(function() {
				$.ajax({
					type: "get",
					url: BaseUrl + 'grjbxx/list/1/' + pageSize,
					async: false,
					data: params,
					success: function(data) {
						layer.close(loading);
						if(data.success) {
							var list = data.queryResult.list;
							if(list.length > 0) {
								checkItem(tmh);
								var data = list[0];
								localStorage.setItem('companycode', data.companycode);
								$('input[name="companycode"]').val(data.companycode);
								$('input[name="companyname"]').val(data.companyname);
								$('input[name="tmh"]').val(data.tmh);
								$('input[name="xm"]').val(data.name);
								$('input[name="sfz"]').val(data.sfz);
								$('input[name="gzgw"]').val(data.gzgw);
								//var companycode=$('input[name="companycode"]').val();
								//已体检人数接口获取数据
								var params = {
									tjrq: tjrq,
									companycode: data.companycode,
									tjflagnk: "1",
									djbs: "1",
								}
								var ytjdata = isChecked(params);
								var ytjhtml = ytjdata.html;
								var ytjtotal = ytjdata.total;
								//未体检人数接口获取数据
								var params = {
									tjrq: tjrq,
									companycode: data.companycode,
									tjflagnk: "0",
									djbs: "1",
								}

								var wtjdata = isChecked(params);
								var wtjhtml = wtjdata.html;
								var wtjtotal = wtjdata.total;

								$('#ytjrs').html(ytjtotal);
								$('#wtjrs').html(wtjtotal);
								$('.leftTiJian ul').eq(0).html(wtjhtml);
								$('.leftTiJian ul').eq(1).html(ytjhtml);

								var params = {
									tmh: $('#sstmh').val(),
								}
								var urls = 'InquiryNk/list/1/' + pageSize;
								$.ajax({
									type: "get",
									url: BaseUrl + urls,
									async: true,
									data: params,
									success: function(data) {
										if(data.success) {
											var result = data.queryResult.list;
											if(result.length > 0) {
												isadd = false;
												var xk = result[0].xk;
												$('input[name="xk"]').each(function() {
													if($(this).val() == xk) {
														$(this).prop('checked', true);
													}
												});
												var xlqk = result[0].xlqk;
												$('input[name="xlqk"]').each(function() {
													if($(this).val() == xlqk) {
														$(this).prop('checked', true);
													}
												});
												var zy = result[0].zy;
												$('input[name="zy"]').each(function() {
													if($(this).val() == zy) {
														$(this).prop('checked', true);
													}
												});
												var hxy = result[0].hxy;
												$('input[name="hxy"]').each(function() {
													if($(this).val() == hxy) {
														$(this).prop('checked', true);
													}
												});
												var hxy = result[0].hxy;
												$('input[name="hxy"]').each(function() {
													if($(this).val() == hxy) {
														$(this).prop('checked', true);
													}
												});
												var ly = result[0].ly;
												$('input[name="ly"]').each(function() {
													if($(this).val() == ly) {
														$(this).prop('checked', true);
													}
												});
												var yt = result[0].yt;
												$('input[name="yt"]').each(function() {
													if($(this).val() == yt) {
														$(this).prop('checked', true);
													}
												});
												var bk = result[0].bk;
												$('input[name="bk"]').each(function() {
													if($(this).val() == bk) {
														$(this).prop('checked', true);
													}
												});
												var gd = result[0].gd;
												$('input[name="gd"]').each(function() {
													if($(this).val() == gd) {
														$(this).prop('checked', true);
													}
												});
												var pd = result[0].pd;
												$('input[name="pd"]').each(function() {
													if($(this).val() == pd) {
														$(this).prop('checked', true);
													}
												});
												var ydxzy = result[0].ydxzy;
												$('input[name="ydxzy"]').each(function() {
													if($(this).val() == ydxzy) {
														$(this).prop('checked', true);
													}
												});

												$('input[name="xkqt"]').val(result[0].xkqt);
												$('input[name="xkbz"]').val(result[0].xkbz);
												$('input[name="xlnumber"]').val(result[0].xlnumber);
												$('input[name="zyms"]').val(result[0].zyms);
												$('input[name="xzbz"]').val(result[0].xzbz);
												$('input[name="hxyms"]').val(result[0].hxyms);
												$('input[name="hxybz"]').val(result[0].hxybz);
												$('input[name="lyms"]').val(result[0].lyms);
												$('input[name="fbz"]').val(result[0].fbz);
												$('input[name="ytms"]').val(result[0].ytms);
												$('input[name="bkms"]').val(result[0].bkms);
												$('input[name="dams"]').val(result[0].dams);
												$('input[name="pdms"]').val(result[0].pdms);
												$('input[name="ydxzyms"]').val(result[0].ydxzyms);
												$('input[name="fbbz"]').val(result[0].fbbz);
												$('input[name="qt"]').val(result[0].qt);
												$('input[name="ysqz"]').val(result[0].ysqz);
												$('input[name="res1"]').val(result[0].res1);
												$('input[name="id"]').val(result[0].id);
												$('input[name="dy"]').val(result[0].dy);
												$('input[name="gy"]').val(result[0].gy);
												form.render(); //更新渲染
											} else {
												intValue();
												form.render();

											}

										} else {
											layer.msg(data.message);
										}
									},
									error: function(data) {
										layer.close(loading);
										layer.msg(data.message);
									}

								});

							} else {
								layer.msg('暂无该人员相关信息,请先进行登记');
								zhikong('xinXiDetail');
								intValue();
								form.render();
								//刷新左侧列表
								//未体检人数接口获取数据
								var params = {
									tjflagnk: "0",
									tjrq: tjrq,
								}
								var wtjdata = isChecked(params);
								var wtjhtml = wtjdata.html;
								var wtjtotal = wtjdata.total;
								$('#wtjrs').html(wtjtotal);
								$('.leftTiJian ul').eq(0).html(wtjhtml);

								//已体检人数接口获取数据
								var params = {
									tjflagnk: "1",
									tjrq: tjrq,
								}
								var ytjdata = isChecked(params);
								var ytjhtml = ytjdata.html;
								var ytjtotal = ytjdata.total;
								$('#ytjrs').html(ytjtotal);
								$('.leftTiJian ul').eq(1).html(ytjhtml);

							}
						}
					},
					error: function(data) {
						layer.close(loading);
						layer.msg(data.message);
					}
				});
			}, 100)

		})

		$('.saveTijian').click(function() {
			if($('input[name="tmh"]').val() == '') {
				layer.msg('条码号不能为空');
				return false;
			}
			if($('input[name="sfz"]').val() == '') {
				layer.msg('身份证不能为空');
				return false;
			}
			var loading = layer.msg('正在保存...', {
				icon: 16,
				shade: 0.2,
				time: false
			});
			var id = $('input[name="id"]').val();
			var params = returnData();
			if(isadd) {
				$.ajax({
					type: "post",
					url: BaseUrl + '/InquiryNk/add',
					async: true,
					data: JSON.stringify(params),
					processData: false,
					contentType: "application/json",
					success: function(data) {
						layer.close(loading);
						if(data.success) {
							layer.alert('保存成功', {
								skin: 'layui-layer-lan',
								closeBtn: 0,
								anim: 4 //动画类型
							}, function(tc, layero) {
								window.location.reload();
							});
						} else {
							layer.msg(data.message);
						}
					},
					error: function(data) {
						layer.close(loading);
						layer.msg(data.message);
					}
				});
			} else {
				$.ajax({
					type: "put",
					url: BaseUrl + 'InquiryNk/edit/' + id,
					async: true,
					data: JSON.stringify(params),
					contentType: "application/json",
					processData: false,
					success: function(data) {
						layer.close(loading);
						if(data.success) {
							layer.alert('保存成功', {
								skin: 'layui-layer-lan',
								closeBtn: 0,
								anim: 4 //动画类型
							})
						} else {
							layer.msg(data.message);
						}
					},
					error: function(data) {
						layer.msg('程序错误!');
						layer.close(loading);
					}
				});

			}

			var id = $('input[name="id"]').val();
			//身份证
			var sfz = $('#sfz').val();
			//姓名
			var xm = $('#xm').val();
			//体检日期
			var tjrq = $('#tjrq').val();
			//条码号
			var tmh = $('#sstmh').val();
			var brdh = $('#brdh').val();
			//工作单位
			var companyname = $('#companyname').val();
			//单位编号
			var companycode = $('#companycode').val();
			//保存进度
			var params2 = {
				id: id,
				sfz: sfz,
				xm: xm,
				tjrq: tjrq,
				tmh: tmh,
				brdh: brdh,
				companycode: companycode,
				companyname: companyname
			}

			$.ajax({
				type: "post",
				url: BaseUrl + '/progress/add?flag=wk',
				async: true,
				data: JSON.stringify(params2),
				processData: false,
				contentType: "application/json",
				success: function(data) {
					layer.close(loading);
					if(data.success) {
						layer.alert('保存成功', {
							skin: 'layui-layer-lan',
							closeBtn: 0,
							anim: 4 //动画类型
						}, function(tc, layero) {
							window.location.reload();
						});
					} else {
						layer.msg(data.message);
					}
				},
				error: function(data) {
					layer.close(loading);
					layer.msg(data.message);
				}
			});
		})

		//展示既往史的选项
		form.on('checkbox(chilie)', function(data) {
			if(data.elem.checked) {
				$('input[name="cl"]').eq(0).prop('checked', false);
			}
			if(data.value.indexOf('缺齿') >= 0 && data.elem.checked) {
				$('.chilieyc').show();
				$('#qcdoms').show();
			} else if(data.value.indexOf('缺齿') >= 0 && !(data.elem.checked)) {
				$('#qcdoms').hide();
			}
			if(data.value.indexOf('龋齿') >= 0 && data.elem.checked) {
				$('.chilieyc').show();
				$('#jcdoms').show();
			} else if(data.value.indexOf('龋齿') >= 0 && !(data.elem.checked)) {
				$('#jcdoms').hide();
			}

			if(data.value.indexOf('义齿（假牙）') >= 0 && data.elem.checked) {
				$('.chilieyc').show();
				$('#ycdoms').show();
			} else if(data.value.indexOf('义齿（假牙）') >= 0 && !(data.elem.checked)) {
				$('#ycdoms').hide();
			}
			form.render();
		});
	});
	// 选项卡
	$('.leftTiJian ul').hide();
	$('.leftTiJian ul').eq(0).show();
	$('.leftTiJian p').click(function() {
		id = $(this).index();
		$(this).siblings().removeClass('active');
		$(this).addClass('active');
		$('.leftTiJian ul').hide();
		$('.leftTiJian ul').eq(id).show();
		var companycode = localStorage.getItem('companycode');
		var tjrq = $("#tjrq").val();
		/*		if(tjrq.trim() == ''){
		            tjrq = getCurrentDate();
		        }*/
		if(id == 0) {
			//未体检人数接口获取数据
			var params = {
				companycode: companycode,
				tjflagnk: "0",
				tjrq: tjrq,
				djbs: "1",
			}
			var wtjdata = isChecked(params);
			var wtjhtml = wtjdata.html;
			var wtjtotal = wtjdata.total;
			$('#wtjrs').html(wtjtotal);
			$('.leftTiJian ul').eq(0).html(wtjhtml);

		} else if(id == 1) {
			//已体检人数接口获取数据
			var params = {
				companycode: companycode,
				tjflagnk: "1",
				tjrq: tjrq,
				djbs: "1",
				
			}
			var ytjdata = isChecked(params);
			var ytjhtml = ytjdata.html;
			var ytjtotal = ytjdata.total;
			$('#ytjrs').html(ytjtotal);
			$('.leftTiJian ul').eq(1).html(ytjhtml);
		}
	})
})
