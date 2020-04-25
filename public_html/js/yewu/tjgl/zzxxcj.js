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
		$('#sstmh').bind('input propertychange', function () {
			  var tmh=$(this).val();
			  if(tmh.length==8){
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


		//体检日期时间框赋值为今日
		$("#tjrq").val(getCurrentDate());
		//初次请求左侧已体检和未体检人数
		var a = getCurrentDate();
		//已体检人数接口获取数据
		var params = {
			tjrq: a,
			tjflagzz: "1",
			djbs: "1",
		}
		var ytjdata = isChecked(params);
		var ytjhtml = ytjdata.html;
		var ytjtotal = ytjdata.total;
		//未体检人数接口获取数据
		var params = {
			tjrq: a,
			tjflagzz: "0",
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
		$('#ysqm').val(userLogin.xm);
        $('#selectMuban').click(function () {
            var ysxx = userLogin.yhbh;
            if (ysxx.trim() == '') {
                layer.msg('请先登录');
                $('#ysqm').focus();
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





		//var tjrq = $('#tjrq').val();
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
            checkItem(tmh);
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
									tjrq:tjrq,
									companycode: data.companycode,
									tjflagzz: "1",
									djbs: "1",
								}
								var ytjdata = isChecked(params);
								var ytjhtml = ytjdata.html;
								var ytjtotal = ytjdata.total;
								//未体检人数接口获取数据
								var params = {
									tjrq:tjrq,
									companycode: data.companycode,
									tjflagzz: "0",
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
								var urls = 'symptominfo/list/1/' + pageSize;
								//请求之前先置空一下表单
								$.ajax({
									type: "get",
									url: BaseUrl + urls,
									async: true,
									data: params,
									success: function(data) {
										layer.close(loading);
										if(data.success) {
											var result = data.queryResult.list;
											if(result.length > 0) {
												isadd = false;
												$('input[name="id"]').val(result[0].id);
												var tt = result[0].tt;
												$('input[name="tt"]').each(function() {
													if($(this).val() == tt) {
														$(this).prop('checked', true);
													}
												});

												var ty = result[0].ty;
												$('input[name="ty"]').each(function() {
													if($(this).val() == ty) {
														$(this).prop('checked', true);
													}
												});

												var xy = result[0].xy;
												$('input[name="xy"]').each(function() {
													if($(this).val() == xy) {
														$(this).prop('checked', true);
													}
												});

												var sm = result[0].sm;
												$('input[name="sm"]').each(function() {
													if($(this).val() == sm) {
														$(this).prop('checked', true);
													}
												});

												var ss = result[0].ss;
												$('input[name="ss"]').each(function() {
													if($(this).val() == ss) {
														$(this).prop('checked', true);
													}
												});

												var ss = result[0].ss;
												$('input[name="ss"]').each(function() {
													if($(this).val() == ss) {
														$(this).prop('checked', true);
													}
												});

												var dm = result[0].dm;
												$('input[name="dm"]').prop('checked', false);
												$('input[name="dm"]').each(function() {
													if($(this).val() == dm) {
														$(this).prop('checked', true);
													}
												});

												var jylst = result[0].jylst
												$('input[name="jylst"]').prop('checked', false);
												$('input[name="jylst"]').each(function() {
													if($(this).val() == jylst) {
														$(this).prop('checked', true);
													}
												});

												var pfwl = result[0].pfwl
												$('input[name="pfwl"]').each(function() {
													if($(this).val() == pfwl) {
														$(this).prop('checked', true);
													}
												});

												var yjd = result[0].yjd
												$('input[name="yjd"]').each(function() {
													if($(this).val() == yjd) {
														$(this).prop('checked', true);
													}
												});

												var dr = result[0].dr
												$('input[name="dr"]').each(function() {
													if($(this).val() == dr) {
														$(this).prop('checked', true);
													}
												});

												var dh = result[0].dh
												$('input[name="dh"]').each(function() {
													if($(this).val() == dh) {
														$(this).prop('checked', true);
													}
												});

												var duoh = result[0].duoh
												$('input[name="duoh"]').each(function() {
													if($(this).val() == duoh) {
														$(this).prop('checked', true);
													}
												});

												var qsst = result[0].qsst
												$('input[name="qsst"]').each(function() {
													if($(this).val() == qsst) {
														$(this).prop('checked', true);
													}
												});

												var ssmh = result[0].ssmh
												$('input[name="ssmh"]').each(function() {
													if($(this).val() == ssmh) {
														$(this).prop('checked', true);
													}
												});

												var slxj = result[0].slxj
												$('input[name="slxj"]').each(function() {
													if($(this).val() == slxj) {
														$(this).prop('checked', true);
													}
												});

												var yg = result[0].yg
												$('input[name="yg"]').each(function() {
													if($(this).val() == yg) {
														$(this).prop('checked', true);
													}
												});

												var yant = result[0].yant
												$('input[name="yant"]').each(function() {
													if($(this).val() == yant) {
														$(this).prop('checked', true);
													}
												});

												var xium = result[0].xium
												$('input[name="xium"]').each(function() {
													if($(this).val() == xium) {
														$(this).prop('checked', true);
													}
												});

												var liul = result[0].liul
												$('input[name="liul"]').each(function() {
													if($(this).val() == liul) {
														$(this).prop('checked', true);
													}
												});

												var xjjt = result[0].xjjt
												$('input[name="xjjt"]').each(function() {
													if($(this).val() == xjjt) {
														$(this).prop('checked', true);
													}
												});

												var bse = result[0].bse
												$('input[name="bse"]').each(function() {
													if($(this).val() == bse) {
														$(this).prop('checked', true);
													}
												});

												var bgan = result[0].bgan
												$('input[name="bgan"]').each(function() {
													if($(this).val() == bgan) {
														$(this).prop('checked', true);
													}
												});

												var liubxue = result[0].liubxue
												$('input[name="liubxue"]').each(function() {
													if($(this).val() == liubxue) {
														$(this).prop('checked', true);
													}
												});

												var erm = result[0].erm
												$('input[name="erm"]').each(function() {
													if($(this).val() == erm) {
														$(this).prop('checked', true);
													}
												});

												var erl = result[0].erl
												$('input[name="erl"]').each(function() {
													if($(this).val() == erl) {
														$(this).prop('checked', true);
													}
												});

												var xshou = result[0].xshou
												$('input[name="xshou"]').each(function() {
													if($(this).val() == xshou) {
														$(this).prop('checked', true);
													}
												});

												var kouk = result[0].kouk
												$('input[name="kouk"]').each(function() {
													if($(this).val() == kouk) {
														$(this).prop('checked', true);
													}
												});

												var kouk = result[0].kouk
												$('input[name="kouk"]').each(function() {
													if($(this).val() == kouk) {
														$(this).prop('checked', true);
													}
												});

												var liux = result[0].liux
												$('input[name="liux"]').each(function() {
													if($(this).val() == liux) {
														$(this).prop('checked', true);
													}
												});

												var yatong = result[0].yatong
												$('input[name="yatong"]').each(function() {
													if($(this).val() == yatong) {
														$(this).prop('checked', true);
													}
												});

												var ycsd = result[0].ycsd
												$('input[name="ycsd"]').each(function() {
													if($(this).val() == ycsd) {
														$(this).prop('checked', true);
													}
												});

												var yyzz = result[0].yyzz
												$('input[name="yyzz"]').each(function() {
													if($(this).val() == yyzz) {
														$(this).prop('checked', true);
													}
												});

												var yycx = result[0].yycx
												$('input[name="yyzz"]').each(function() {
													if($(this).val() == yycx) {
														$(this).prop('checked', true);
													}
												});

												var kqky = result[0].kqky
												$('input[name="kqky"]').each(function() {
													if($(this).val() == kqky) {
														$(this).prop('checked', true);
													}
												});

												var kqyw = result[0].kqyw
												$('input[name="kqyw"]').each(function() {
													if($(this).val() == kqyw) {
														$(this).prop('checked', true);
													}
												});

												var xmen = result[0].xmen
												$('input[name="xmen"]').each(function() {
													if($(this).val() == xmen) {
														$(this).prop('checked', true);
													}
												});

												var qduan = result[0].qduan
												$('input[name="qduan"]').each(function() {
													if($(this).val() == qduan) {
														$(this).prop('checked', true);
													}
												});

												var xtong = result[0].xtong
												$('input[name="xtong"]').each(function() {
													if($(this).val() == xtong) {
														$(this).prop('checked', true);
													}
												});

												var kesou = result[0].kesou
												$('input[name="kesou"]').each(function() {
													if($(this).val() == kesou) {
														$(this).prop('checked', true);
													}
												});

												var ketan = result[0].ketan
												$('input[name="ketan"]').each(function() {
													if($(this).val() == ketan) {
														$(this).prop('checked', true);
													}
												});

												var kaxie = result[0].kaxie
												$('input[name="kaxie"]').each(function() {
													if($(this).val() == kaxie) {
														$(this).prop('checked', true);
													}
												});

												var kaxie = result[0].kaxie
												$('input[name="kaxie"]').each(function() {
													if($(this).val() == kaxie) {
														$(this).prop('checked', true);
													}
												});

												var xiaoc = result[0].xiaoc
												$('input[name="xiaoc"]').each(function() {
													if($(this).val() == xiaoc) {
														$(this).prop('checked', true);
													}
												});

												var xiaoc = result[0].xiaoc
												$('input[name="xiaoc"]').each(function() {
													if($(this).val() == xiaoc) {
														$(this).prop('checked', true);
													}
												});

												var xinji = result[0].xinji
												$('input[name="xinji"]').each(function() {
													if($(this).val() == xinji) {
														$(this).prop('checked', true);
													}
												});

												var xqqbs = result[0].xqqbs
												$('input[name="xqqbs"]').each(function() {
													if($(this).val() == xqqbs) {
														$(this).prop('checked', true);
													}
												});

												var futong = result[0].futong
												$('input[name="futong"]').each(function() {
													if($(this).val() == futong) {
														$(this).prop('checked', true);
													}
												});

												var fuzhang = result[0].fuzhang
												$('input[name="fuzhang"]').each(function() {
													if($(this).val() == futong) {
														$(this).prop('checked', true);
													}
												});

												var fuxie = result[0].fuxie
												$('input[name="fuxie"]').each(function() {
													if($(this).val() == fuxie) {
														$(this).prop('checked', true);
													}
												});

												var bianm = result[0].bianm
												$('input[name="bianm"]').each(function() {
													if($(this).val() == bianm) {
														$(this).prop('checked', true);
													}
												});

												var gqttong = result[0].gqttong
												$('input[name="gqttong"]').each(function() {
													if($(this).val() == gqttong) {
														$(this).prop('checked', true);
													}
												});

												var pxcxue = result[0].pxcxue
												$('input[name="pxcxue"]').each(function() {
													if($(this).val() == pxcxue) {
														$(this).prop('checked', true);
													}
												});

												var pfsy = result[0].pfsy
												$('input[name="pfsy"]').each(function() {
													if($(this).val() == pfsy) {
														$(this).prop('checked', true);
													}
												});

												var pizhen = result[0].pizhen
												$('input[name="pizhen"]').each(function() {
													if($(this).val() == pizhen) {
														$(this).prop('checked', true);
													}
												});

												var tuofa = result[0].tuofa
												$('input[name="tuofa"]').each(function() {
													if($(this).val() == tuofa) {
														$(this).prop('checked', true);
													}
												});

												var gjt = result[0].gjt
												$('input[name="gjt"]').each(function() {
													if($(this).val() == gjt) {
														$(this).prop('checked', true);
													}
												});

												var jrcc = result[0].jrcc
												$('input[name="jrcc"]').each(function() {
													if($(this).val() == jrcc) {
														$(this).prop('checked', true);
													}
												});

												var xzczg = result[0].xzczg
												$('input[name="xzczg"]').each(function() {
													if($(this).val() == xzczg) {
														$(this).prop('checked', true);
													}
												});

												var dzblh = result[0].dzblh
												$('input[name="dzblh"]').each(function() {
													if($(this).val() == dzblh) {
														$(this).prop('checked', true);
													}
												});

												var szmm = result[0].szmm
												$('input[name="szmm"]').each(function() {
													if($(this).val() == szmm) {
														$(this).prop('checked', true);
													}
												});

												var szdh = result[0].szdh
												$('input[name="szdh"]').each(function() {
													if($(this).val() == szdh) {
														$(this).prop('checked', true);
													}
												});

												var szfl = result[0].szfl
												$('input[name="szfl"]').each(function() {
													if($(this).val() == szfl) {
														$(this).prop('checked', true);
													}
												});

												var ysqhai = result[0].ysqhai
												$('input[name="ysqhai"]').each(function() {
													if($(this).val() == ysqhai) {
														$(this).prop('checked', true);
													}
												});

												var niaop = result[0].niaop
												$('input[name="niaop"]').each(function() {
													if($(this).val() == niaop) {
														$(this).prop('checked', true);
													}
												});

												var niaoji = result[0].niaoji
												$('input[name="niaoji"]').each(function() {
													if($(this).val() == niaoji) {
														$(this).prop('checked', true);
													}
												});

												var niaoxue = result[0].niaoxue
												$('input[name="niaoxue"]').each(function() {
													if($(this).val() == niaoxue) {
														$(this).prop('checked', true);
													}
												});

												var fuzhong = result[0].fuzhong
												$('input[name="fuzhong"]').each(function() {
													if($(this).val() == fuzhong) {
														$(this).prop('checked', true);
													}
												});

												var yjyc = result[0].yjyc
												$('input[name="yjyc"]').each(function() {
													if($(this).val() == yjyc) {
														$(this).prop('checked', true);
													}
												});

												var xyjt = result[0].xyjt
												$('input[name="xyjt"]').each(function() {
													if($(this).val() == xyjt) {
														$(this).prop('checked', true);
													}
												});

												var ysqm = result[0].ysqm
												$('input[name="ysqm"]').each(function() {
													if($(this).val() == ysqm) {
														$(this).prop('checked', true);
													}
												});

												var ysqm = result[0].ysqm
												$('input[name="ysqm"]').each(function() {
													if($(this).val() == ysqm) {
														$(this).prop('checked', true);
													}
												});
												$('input[name="res1"]').val(result[0].res1);
												$('input[name="ysqm"]').val(result[0].ysqm);
												form.render();
											} else {
												layer.msg('暂未查询到相关数据');
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
									tjflagzz: "0",
									tjrq: tjrq,
									djbs: "1",
								}
								var wtjdata = isChecked(params);
								var wtjhtml = wtjdata.html;
								var wtjtotal = wtjdata.total;
								$('#wtjrs').html(wtjtotal);
								$('.leftTiJian ul').eq(0).html(wtjhtml);

								//已体检人数接口获取数据
								var params = {
									tjflagzz: "1",
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
			var strs = returnData();
			var id = $('input[name="id"]').val();
			var xm = $('input[name="xm"]').val();
			var tmh = $('input[name="sstmh"]').val();
			var params = {
				id: id,
				tmh: tmh,
				name: xm,
				tt: strs.tt,
				syjt: strs.syjt,
				ty: strs.ty,
				xy: strs.xy,
				sm: strs.sm,
				ss: strs.ss,
				dm: strs.dm,
				jylst: strs.jylst,
				pfwl: strs.pfwl,
				yjd: strs.yjd,
				dr: strs.dr,
				dh: strs.dh,
				duoh: strs.duoh,
				qsst: strs.qsst,
				ssmh: strs.ssmh,
				slxj: strs.slxj,
				yg: strs.yg,
				yant: strs.yant,
				xium: strs.xium,
				liul: strs.liul,
				xjjt: strs.xjjt,
				bse: strs.bse,
				bgan: strs.bgan,
				liubxue: strs.liubxue,
				erm: strs.erm,
				erl: strs.erl,
				xshou: strs.xshou,
				kouk: strs.kouk,
				liux: strs.liux,
				yatong: strs.yatong,
				ycsd: strs.ycsd,
				yyzz: strs.yyzz,
				yycx: strs.yycx,
				kqky: strs.kqky,
				kqyw: strs.kqyw,
				xmen: strs.xmen,
				qduan: strs.qduan,
				xtong: strs.xtong,
				kesou: strs.kesou,
				ketan: strs.ketan,
				kaxie: strs.kaxie,
				xiaoc: strs.xiaoc,
				xinji: strs.xinji,
				xqqbs: strs.xqqbs,
				futong: strs.futong,
				fuzhang: strs.fuzhang,
				fuxie: strs.fuxie,
				bianm: strs.bianm,
				gqttong: strs.gqttong,
				pxcxue: strs.pxcxue,
				pfsy: strs.pfsy,
				pizhen: strs.pizhen,
				tuofa: strs.tuofa,
				gjt: strs.gjt,
				jrcc: strs.jrcc,
				xzczg: strs.xzczg,
				dzblh: strs.dzblh,
				szmm: strs.szmm,
				szdh: strs.szdh,
				szfl: strs.szfl,
				ysqhai: strs.ysqhai,
				niaop: strs.niaop,
				niaoji: strs.niaoji,
				niaoxue: strs.niaoxue,
				fuzhong: strs.fuzhong,
				yjyc: strs.yjyc,
				xyjt: strs.xyjt,
				res1: strs.res1,
				ysqm: strs.ysqm,
			}

			if(isadd) {
				$.ajax({
					type: "post",
					url: BaseUrl + '/symptominfo/add',
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
			}else{
				$.ajax({
					type: "put",
					url: BaseUrl + 'symptominfo/edit/' + id,
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
                url: BaseUrl + '/progress/add?flag=zz',
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
		if(id == 0) {
			//未体检人数接口获取数据
			var params = {
				companycode: companycode,
				tjflagzz: "0",
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
				tjflagzz: "1",
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
