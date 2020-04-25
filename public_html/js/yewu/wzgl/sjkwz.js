var isadd = true;
$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		laydate.render({
			elem: '#tjrq', //指定元素
			format: 'yyyy-MM-dd',
			value: new Date()
		});
		laydate.render({
			elem: '#createtime',
		});
		$('#sstmh').focus();
		$('#sstmh').bind('input propertychange', function () {
			  var tmh=$(this).val();
			  if(tmh.length==8){
				  $('#searchBtn').trigger('click');
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
			tjflagsjk: "1",
			djbs: "1",
		}
		var ytjdata = isChecked(params);
		var ytjhtml = ytjdata.html;
		var ytjtotal = ytjdata.total;
		//未体检人数接口获取数据
		var params = {
			tjrq: a,
			tjflagsjk: "0",
			djbs: "1",
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

        $('#selectMuban').click(function () {
            var ysxx = userLogin.yhbh;
            if (ysxx.trim() == '') {
                layer.msg('请先登录');
                $('#ysqz').focus();
                return false;
            }
            var params = {ysxx: ysxx}
            var loading = layer.msg('正在查询...', {
                icon: 16,
                shade: 0.2,
                time: false
            });

            setTimeout(function () {
                $.ajax({
                    type: "get",
                    url: BaseUrl + 'template/all',
                    async: false,
                    data: params,
                    success: function (data) {
                        layer.close(loading);
                        if (data.success) {
                            var html = '';


                            for (var i = 0; i < data.queryResult.list.length; i++) {
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
                    error: function (data) {
                        layer.close(loading);
                        layer.msg(data.message);
                    }
                })
            })
        })

        $('#savemuban').click(function () {
            var loading = layer.msg('正在保存...', {
                icon: 16,
                shade: 0.2,
                time: false
            });


            $('.addInput').each(function () {
                var mbxx = $(this).val();
                var ysxx = userLogin.yhbh;
                var params = {ysxx: ysxx, mbxx: mbxx}
                $.ajax({
                    type: "post",
                    url: BaseUrl + '/template/add',
                    async: true,
                    data: JSON.stringify(params),
                    processData: false,
                    contentType: "application/json",
                    success: function (data) {
                        layer.close(loading);
                        if (data.success) {
                            layer.alert('保存成功', {
                                skin: 'layui-layer-lan',
                                closeBtn: 0,
                                anim: 4 //动画类型
                            }, function (tc, layero) {
                                window.location.reload();

                            });
                        } else {
                            layer.msg(data.message);
                        }
                    },
                    error: function (data) {
                        layer.close(loading);
                        layer.msg(data.message);
                    }
                });
            })
        })


		$('#searchBtn').click(function() {
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
								$('input[name="tmh"]').val($('#sstmh').val());
								$('input[name="xm"]').val(data.name);
								$('input[name="sfz"]').val(data.sfz);
								$('input[name="gzgw"]').val(data.gzgw);
								var companycode = $('input[name="companycode"]').val();
								//已体检人数接口获取数据
								var params = {
									tjrq:tjrq,
									companycode: companycode,
									tjflagsjk: "1",
									djbs: "1",

								}
								var ytjdata = isChecked(params);
								var ytjhtml = ytjdata.html;
								var ytjtotal = ytjdata.total;
								//未体检人数接口获取数据
								var params2 = {
									tjrq:tjrq,
									companycode: companycode,
									tjflagsjk: "0",
									djbs: "1",
								}
								var wtjdata = isChecked(params2);
								var wtjhtml = wtjdata.html;
								var wtjtotal = wtjdata.total;

								$('#ytjrs').html(ytjtotal);
								$('#wtjrs').html(wtjtotal);
								$('.leftTiJian ul').eq(0).html(wtjhtml);
								$('.leftTiJian ul').eq(1).html(ytjhtml);
								isComing = true;
								var params = {
									tmh: $('#sstmh').val(),
								}
								debugger;
								var urls = 'InquirySjk/list/1/' + pageSize;
								$.ajax({
									type: "get",
									url: BaseUrl + urls,
									async: true,
									data: params,
									success: function(data) {
										layer.close(loading);
										if(data.success) {
											var list = data.queryResult.list;
											if(list.length > 0) {
												zhikong('layui-table');
												form.render();
												isadd = false;
												var data = list[0];
												$('input[name="id"]').val(data.id);
												$('input[name="jwsms"]').val(data.jwsms);
												$('input[name="jwsbz"]').val(data.jwsbz);
												$('input[name="jlms"]').val(data.jlms);
												$('input[name="jlbz"]').val(data.jlbz);
												$('input[name="jzlms"]').val(data.jzlms);
												$('input[name="jzlbz"]').val(data.jzlbz);
												$('input[name="scms"]').val(data.scms);
												$('input[name="scbz"]').val(data.scbz);
												$('input[name="gjydms"]').val(data.gjydms);
												$('input[name="gjydbz"]').val(data.gjydbz);
												$('input[name="tjms"]').val(data.tjms);
												$('input[name="cjms"]').val(data.cjms);
												$('input[name="qgnbz"]').val(data.qgnbz);
												$('input[name="sgjms"]').val(data.sgjms);
												$('input[name="sgjbz"]').val(data.sgjbz);
												$('input[name="jfsbz"]').val(data.jfsbz);
												$('input[name="blfsbz"]').val(data.blfsbz);
												$('input[name="zzsjms"]').val(data.zzsjms);
												$('input[name="zzsjbz"]').val(data.zzsjbz);
												$('input[name="ysqz"]').val(data.ysqz);
												$('input[name="createtime"]').val(data.createtime);
												intRadio('jws', data.jws);
												intRadio('jl', data.jl);
												intRadio('jzl', data.jzl);
												intRadio('sc', data.sc);
												intRadio('cj', data.cj);
												intRadio('gjyd', data.gjyd);
												intRadio('tj', data.tj);
												intRadio('sgj', data.sgj);
												intRadio('jfs', data.jfs);
												intRadio('blfs', data.blfs);
												intRadio('zzsj', data.zzsj);
												$('input[name="res1"]').val(data.res1);
												form.render();
											} else {
												layer.msg('暂未查询到相关数据');
												intValue();
												form.render();
											}
										} else {
											layer.msg(data.message);
										}
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
									tjflagsjk: "0",
									tjrq: tjrq,
								}
								var wtjdata = isChecked(params);
								var wtjhtml = wtjdata.html;
								var wtjtotal = wtjdata.total;
								$('#wtjrs').html(wtjtotal);
								$('.leftTiJian ul').eq(0).html(wtjhtml);

								//已体检人数接口获取数据
								var params = {
									tjflagsjk: "1",
									tjrq: tjrq,
									djbs: "1",
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
			}, 200)

		})
		$('.saveTijian').click(function() {
			var loading = layer.msg('正在保存...', {
				icon: 16,
				shade: 0.2,
				time: false
			});
			var params = returnData();
			if(isadd) {
				$.ajax({
					type: "post",
					url: BaseUrl + '/InquirySjk/add',
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
				var params = returnData();

				//定义promise对象
				var Pro = new Promise(function(resolve, reject) {
					var params = returnData();
					resolve(params);
				})
				Promise.all([Pro]).then((result) => {
					debugger;
					var m=result[0];
					$.ajax({
						type: "put",
						url: BaseUrl + 'InquirySjk/edit/' + m.id,
						async: true,
						data: JSON.stringify(m),
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
				})
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
                url: BaseUrl + '/progress/add?flag=sjk',
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
	});

	// 选项卡
	$('.leftTiJian ul').hide()
	$('.leftTiJian ul').eq(0).show()
	$('.leftTiJian p').click(function() {
		id = $(this).index();
		$(this).siblings().removeClass('active');
		$(this).addClass('active');
		$('.leftTiJian ul').hide();
		$('.leftTiJian ul').eq(id).show();
		//	var companycode = $('input[name="companycode"]').val();
		var companycode = localStorage.getItem('companycode');
		var tjrq = $("#tjrq").val();
/*		if(tjrq.trim() == ''){
			tjrq = getCurrentDate();
		}*/
		if(id == 0) {
			//未体检人数接口获取数据
			var params = {
				companycode: companycode,
				tjflagsjk: "0",
				tjrq:tjrq,
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
				tjflagsjk: "1",
				tjrq:tjrq,
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
