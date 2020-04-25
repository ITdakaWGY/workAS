var zybType=0;
$(function() {


	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		laydate.render({
			elem: '#csny' //指定元素
		});

		laydate.render({
			elem: '#tjrq' //指定元素
		});


		

		//初始化相关事件dom
		loadsj('kssj');
		loadsj('jssj');
		loadsj('zdsj');
		loadsj('jwssj');
		loadsj('sssj');
		loadsj('wssj');
		loadsj('sxsj');
		
		
		
		
		
		$('#sstmh').focus();
		$('#sstmh').bind('input propertychange', function() {
			var tmh = $(this).val();
			if (tmh.length == 8) {
				$('#searchBtn').trigger('click');
			}
		});

		var codeData = {};
		getCodeData(codeData);
		var a = getCurrentDate();
		$('#tjrq').val(a);

		$.ajax({
				type: "get",
				url: "../../json/minzu.json",
				async: true,
				success: function(data) {
					var list = data.data;
					var html = '';
					for(var i = 0; i < list.length; i++) {
						html += `<option  data-id="${list[i].id}" value="${list[i].name}">${list[i].name}</option>`
					}
					$('#minzu').html(html);
					form.render();
				}
			});

		//已体检人数接口获取数据
		var params = {
			tjrq: a,
			tjbs: "1",
		}
		var ytjdata = isChecked(params);
		var ytjhtml = ytjdata.html;
		var ytjtotal = ytjdata.total;
		//未体检人数接口获取数据
		var params = {
			tjrq: a,
			tjbs: "0",
		}
		var wtjdata = isChecked(params);
		var wtjhtml = wtjdata.html;
		var wtjtotal = wtjdata.total;

		$('#ytjrs').html(ytjtotal);
		$('#wtjrs').html(wtjtotal);
		$('.leftTiJian ul').eq(0).html(wtjhtml);
		$('.leftTiJian ul').eq(1).html(ytjhtml);








		//展示民族其他的选项
		form.on('radio(minzu)', function(data) {
			if (data.value == 2) {
				$('#mzqt').show();
			} else {
				$('#mzqt').hide();
			}
		});
		//展示婚姻其他的选项
		form.on('radio(hunyin)', function(data) {
			if (data.value == 6) {
				$('#hyzkqt').show();
			} else {
				$('#hyzkqt').hide();
			}
		});
		//展示职业病危害因素其他的选项
		form.on('checkbox(jcwhysqt)', function(data) {
			if (data.elem.checked) {
				$('#jcwhysqt').show();
			} else {
				$('#jcwhysqt').hide();
			}
		});

		//展示饮酒种类其他其他的选项
		form.on('checkbox(yjzlqt)', function(data) {
			if (data.elem.checked) {
				$('#yjzlqt').show();
			} else {
				$('#yjzlqt').hide();
			}
		});
		//展示既往史无的选项
		form.on('checkbox(jwswu)', function(data) {
			if (data.elem.checked) {
				$('input[name="jws"]:not(:first)').each(function() {
					$(this).prop('checked', false);
				})
				$('.jwsjb').hide();
				$('#exzltext').hide();
				$('#zybtext').hide();
				form.render();
			}
		});




		//展示既往史的选项
		form.on('checkbox(jwsjb)', function(data) {
			if (data.value == 13 && data.elem.checked) {
				$('.jwsjb').show();
			} else if (data.value == 13 && !(data.elem.checked)) {
				$('.jwsjb').hide();
			}
			if (data.value == 6 && data.elem.checked) {
				$('#exzltext').show();
			} else if (data.value == 6 && !(data.elem.checked)) {
				$('#exzltext').hide();
			}

			if (data.value == 12 && data.elem.checked) {
				$('#zybtext').show();
			} else if (data.value == 12 && !(data.elem.checked)) {
				$('#zybtext').hide();
			}

			if (data.elem.checked) {
				$('input[name="jws"]').eq(0).prop('checked', false);
				form.render();
			}
		});

		//展示家族史-父亲无的选项
		form.on('checkbox(jzswufq)', function(data) {
			if (data.elem.checked) {
				$('input[name="jzsfq"]:not(:first)').each(function() {
					$(this).prop('checked', false);
				})
				form.render();
			}
		});
		//展示家族史父亲的选项
		form.on('checkbox(jzsfq)', function(data) {
			if (data.elem.checked) {
				$('input[name="jzsfq"]').eq(0).prop('checked', false);
				form.render();
			}
		});


		//展示家族史-母亲无的选项
		form.on('checkbox(jzswumq)', function(data) {
			if (data.elem.checked) {
				$('input[name="jzsmq"]:not(:first)').each(function() {
					$(this).prop('checked', false);
				})
				form.render();
			}
		});
		//展示家族史母亲的选项
		form.on('checkbox(jzsmq)', function(data) {
			if (data.elem.checked) {
				$('input[name="jzsmq"]').eq(0).prop('checked', false);
				form.render();
			}
		});


		//展示家族史-兄弟姐妹无的选项
		form.on('checkbox(jzswuxdjm)', function(data) {
			if (data.elem.checked) {
				$('input[name="jzsxd"]:not(:first)').each(function() {
					$(this).prop('checked', false);
				})
				form.render();
			}
		});
		//展示家族史兄弟姐妹的选项
		form.on('checkbox(jzsxdjm)', function(data) {
			if (data.elem.checked) {
				$('input[name="jzsxd"]').eq(0).prop('checked', false);
				form.render();
			}
		});

		//展示家族史-子女无的选项
		form.on('checkbox(jzswuzn)', function(data) {
			if (data.elem.checked) {
				$('input[name="jzszn"]:not(:first)').each(function() {
					$(this).prop('checked', false);
				})
				form.render();
			}
		});
		//展示家族史子女的选项
		form.on('checkbox(jzszn)', function(data) {
			if (data.elem.checked) {
				$('input[name="jzszn"]').eq(0).prop('checked', false);
				form.render();
			}
		});


		//展示药物过敏史无的选项
		form.on('checkbox(ywgmswu)', function(data) {
			if (data.elem.checked) {
				$('input[name="ywgms"]:not(:first)').each(function() {
					$(this).prop('checked', false);
				})
				form.render();
			}
		});
		//展示药物过敏史的选项
		form.on('checkbox(ywgms)', function(data) {
			if (data.elem.checked) {
				$('input[name="ywgms"]').eq(0).prop('checked', false);
				form.render();
			}
		});






		$('body').on('click', '#addzys', function() {
			var addzys =
				`<div class="fl p-l">
									<span class="fl">
										<input  type="text" class="layui-input width100 kssj" />
									</span>

									<span class="fl">
										<input  type="text" class="layui-input width100 jssj" />
									</span>
									<span class="fl">
										<input type="text" class="gzdw layui-input width100"  placeholder="请输入工作单位" />
									</span>

									<span class="fl">
										<input type="text" class="layui-input width100 cj" placeholder="车间" />
									</span>
									<span class="fl">
											<input type="text" class="layui-input width100 gz" placeholder="工种" />
										
									</span>
									<span class="fl">
											<input type="text" class="layui-input width100 yhys" placeholder="有害因素" />
										
									</span>
									<span class="fl">
											<input type="text" class="layui-input width100 fhcs" placeholder="防护措施" />
										
									</span>
									<font class="pum">-</font>
								</div>`;
			$('.zhiyeshi').eq(1).append(addzys);
			loadsj('kssj');
			loadsj('jssj');

		})

		//职业病史


		$('body').on('click', '#addzybs', function() {
			var timestamp=new Date().getTime();
			var addzybs =
				`<div class="fl p-l">
									<span class="fl">
										<input  type="text" class="layui-input width100 illnessname" placeholder="请输入病名" />
									</span>

									<span class="fl">
										<input  type="text" class="layui-input width100 diagnosisdata zdsj" placeholder="请输入诊断日期" />
									</span>
									<span class="fl">
											<input type="text" class="layui-input width100 diagnosiscompany" placeholder="请输入诊断单位" />
									</span>

									<span class="fl lastspan">
										<input type="radio" name="sfrecovery${timestamp}" value="1" title="已痊愈" checked="">
										<input type="radio" name="sfrecovery${timestamp}" value="2" title="未痊愈">
									</span>
									
									<font class="pum">--</font>
								</div>`;
			$('.zhiyebingshi').append(addzybs);
			loadsj('zdsj');

			form.render('radio');
			zybType++;

		})


		$('body').on('click', '.pum', function() {
			$(this).parent().remove();
		})

		$('body').on('click', '#addjws', function() {

			var addjws =
				`
								<div class="p-l">
									<span>
										<input  type="text" class="layui-input qtjbs" placeholder="疾病名称"/>
									</span>
									<span>
										<input  type="text" class="layui-input jwssj" placeholder="确诊时间"/>
									</span>
									<font class="pum">--</font>
								</div>`;
			$('.jwsjb').append(addjws);
			loadsj('jwssj');
		})
		$('body').on('click', '#addsss', function() {
			var addjws =
				`
								<div class="fl p-l">
      									<span>
      										<input type="text" class="layui-input ssmc" placeholder="手术名称"/>
      									</span>
      									<span>
      										<input type="text" class="layui-input sssj" placeholder="手术时间"/>
      									</span>
      									<font class="pum">--</font>
      								</div>`;
			$('#ssparent').append(addjws);
			loadsj('sssj');
		})

		$('body').on('click', '#addws', function() {
			var addjws =
				`
								<div class="fl p-l">
      									<span>
      										<input type="text" class="layui-input wsmc" placeholder="外伤名称"/>
      									</span>
      									<span>
      										<input type="text" class="layui-input wssj" placeholder="外伤时间"/>
      									</span>
      									<font class="pum">--</font>
      								</div>`;
			$('#wsparent').append(addjws);
			loadsj('wssj');
		})

		$('body').on('click', '#addsx', function() {
			var addjws =
				`
								<div class="fl p-l">
      									<span>
      										<input type="text" class="layui-input sxmc" placeholder="输血名称"/>
      									</span>
      									<span>
      										<input type="text" class="layui-input sxsj" placeholder="输血时间"/>
      									</span>
      									<font class="pum">--</font>
      								</div>`;
			$('#sxparent').append(addjws);
			loadsj('sxsj');
		})


		function loadsj(a) {
			$('.' + a).each(function() {
				laydate.render({
					elem: this, //指定元素
					trigger: 'click'
				});
			})
		}


		$('#searchBtn').click(function() {
			var tmh = $('#sstmh').val();
			var tjrq = $('#tjrq').val();
			if (tmh == '') {
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
			setTimeout(function() {
				$.ajax({
					type: "get",
					url: BaseUrl + 'grjbxx/list/1/' + pageSize,
					async: false,
					data: params,
					success: function(data) {
						//debugger;
						layer.close(loading);
						if (data.success) {
							var list = data.queryResult.list;
							if (list.length > 0) {
								var data = list[0];
								$('input[name="companycode"]').val(data.companycode);
								$('input[name="companyname"]').val(data.companyname);
								$('input[name="xm"]').val(data.name);
								$('input[name="sfz"]').val(data.sfz);
								$('input[name="gzgw"]').val(data.gzgw);
								var companycode = $('input[name="companycode"]').val();
								var tjrq = $("#tjrq").val();
								//已体检人数接口获取数据
								var params = {
									companycode: companycode,
									tjbs: "1",
									tjrq:tjrq,
								}
								var ytjdata = isChecked(params);
								var ytjhtml = ytjdata.html;
								var ytjtotal = ytjdata.total;
								//未体检人数接口获取数据
								var params = {
									companycode: companycode,
									tjbs: "0",
									tjrq:tjrq,
								}
								var wtjdata = isChecked(params);
								var wtjhtml = wtjdata.html;
								var wtjtotal = wtjdata.total;

								$('#ytjrs').html(ytjtotal);
								$('#wtjrs').html(wtjtotal);
								$('.leftTiJian ul').eq(0).html(wtjhtml);
								$('.leftTiJian ul').eq(1).html(ytjhtml);
							} else {
								layer.msg('暂无改人员相关信息,请先进行登记');
								return false;
							}
						}
					},
					error: function(data) {
						layer.close(loading);
						layer.msg(data.message);
					}
				});
			}, 200);
			var urls = 'grjbxx/selectWhole';
			$.ajax({
				type: "post",
				url: BaseUrl + urls,
				async: true,
				data: JSON.stringify(params),
				processData: false, //是否序列化
				contentType: "application/json",
				success: function(data) {
					layer.close(loading);
					if (data.success) {
						var list = data.queryResult.list;
						if (list.length > 0) {
							zhikong("layui-table");
							var arr = list[0];
							//console.log(JSON.stringify(arr));
							var data = arr.grjbxx;
							var jzs = arr.grjbxxJzs;
							var jbs = arr.grjbxxJbs;
							var zys = arr.grjbxxZys;
							var ws = arr.grjbxxWs;
							var ss = arr.grjbxxSs;
							var sx = arr.grjbxxSx;
							var zyb = arr.grjbxxZyb;
							console.log(JSON.stringify(arr));
							/*手术*/
							if (ss != null && ss.length > 0) {
								var ssxs = "";
								intRadio("sfss", ss[0].sfss);
								if (ss[0].sfss == "2") {
									for (var i = 0; i < ss.length; i++) {
										ssxs += `<span>` +
											`<input type="text" class="layui-input ssmc" value="${ss[i].ssmc}" placeholder="手术名称"/>` +
											`</span><span>` +
											`<input type="text" class="layui-input sssj" value="${ss[i].sssj}" placeholder="手术时间"/>` +
											`</span>` +
											`<font class="pum">--</font>`
									}
									$(".ss").html(ssxs);
								}

							}else{
								intRadio("sfss","1");
							}

							/*输血*/
							if (sx != null && sx.length > 0) {
								var sxxs = "";
								intRadio("sfsx", sx[0].sfsx);
								if (sx[0].sfsx == "2") {
									for (var i = 0; i < sx.length; i++) {
										sxxs += `<span>` +
											`<input type="text" class="layui-input sxmc" value="${sx[i].sxyy}" placeholder="输血名称"/>` +
											`</span><span>` +
											`<input type="text" class="layui-input sxsj" value="${sx[i].sxsj}" placeholder="输血时间"/>` +
											`</span>` +
											`<font class="pum">--</font>`
									}
									$(".sx").html(sxxs);
								}

							}else{
								intRadio("sfsx","1");
							}

							/*外伤*/
							if (ws != null && ws.length > 0) {
								var wsxs = "";
								intRadio("sfws", ws[0].sfws);
								if (ws[0].sfws == "2") {
									for (var i = 0; i < ws.length; i++) {
										wsxs += `<span>` +
											`<input type="text" class="layui-input wsmc" value="${ws[i].wsmc}" placeholder="外伤名称"/>` +
											`</span><span>` +
											`<input type="text" class="layui-input wssj" value="${ws[i].wssj}" placeholder="外伤时间"/>` +
											`</span>` +
											`<font class="pum">--</font>`
									}
									$(".ws").html(wsxs);
								}



							}else{
								intRadio("sfws","1");
							}


							/*家族史*/
							if (jzs != null && jzs.length > 0) {
								var jzsxs = "";
								for (var i = 0; i < jzs.length; i++) {
									switch (jzs[i].jzsgx) {
										case "1":
											intCheckbox("jzsfq", jzs[i].jzsjbdm.split(","));
											$("#jzsqtfq").val(jzs[i].jzsOther);
											break;
										case "2":
											intCheckbox("jzsmq", jzs[i].jzsjbdm.split(","));
											$("#jzsqtmq").val(jzs[i].jzsOther);
											break;
										case "3":
											intCheckbox("jzsxd", jzs[i].jzsjbdm.split(","));
											$("#jzsqtxdjm").val(jzs[i].jzsOther);
											break;
										case "4":
											intCheckbox("jzszn", jzs[i].jzsjbdm.split(","));
											$("#jzsqtzn").val(jzs[i].jzsOther);
											break;
										default:
											break;
									}

								}

							}else{
								intCheckbox("jzsfq","1");
								intCheckbox("jzsmq","1");
								intCheckbox("jzsxd","1");
								intCheckbox("jzszn","1");
							}
							/*职业史*/
							if (zys != null && zys.length > 0) {
								var zysxs = "";
								for (var i = 0; i < zys.length; i++) {
									zysxs += `<span class="fl">` +
										`<input  type="text" class="layui-input width100 kssj" value="${zys[i].qzrq}"/>` +
										`</span><span class="fl">` +
										`<input  type="text" class="layui-input width100 jssj" value="${zys[i].jssj}"/>` +
										`</span><span class="fl">` +
										`<input type="text" class="layui-input width100 gzdw" value="${zys[i].gzdw}" placeholder="请输入工作单位" />` +
										`</span><span class="fl">` +
										`<input type="text" class="layui-input width100 cj" value="${zys[i].cj}" placeholder="请输入车间" />` +
										`</span><span class="fl">` +
										`<input type="text" class="layui-input width100 gz" value="${zys[i].gz}" placeholder="请输入工种" />` +
										`</span><span class="fl">` +
										`<input type="text" class="layui-input width100 yhys" value="${zys[i].yhys}" placeholder="请输入有害因素" />` +
										`</span><span class="fl">` +
										`<input type="text" class="layui-input width100 fhcs" value="${zys[i].fhcs}" placeholder="请输入防护措施" />` +
										`</span><font class="pum">--</font>`

								}

								$(".zys").html(zysxs);
							}
							//debugger;
							//职业病
							if(zyb!=null && zyb.length>0){
								var zybxs ='';
								var pm='';
								var pa='';
								var py=''
								for (var i = 0; i < zyb.length; i++) {
									pm=`<div  style="width:100%;height:40px; position:relative">
											<p style="clear:both"></p>
											<span class="fl">
												<input  type="text" class="layui-input width100 illnessname" value="${zyb[i].illnessname}" placeholder="请输入工作单位" />
											</span> <span class="fl">
												<input  type="text" class="layui-input width100 diagnosisdata zdsj" value="${zyb[i].diagnosisdata}" placeholder="请输入诊断日期" />
											</span> <span class="fl">
													<input type="text" class="layui-input width100 diagnosiscompany" value="${zyb[i].diagnosiscompany}" placeholder="请输入诊断单位" />
											</span> `;

									if(zyb[i].sfrecovery==1){
										pa=`<span class="fl lastspan">
												<input type="radio" name="sfrecovery${i}" value="1" title="已痊愈" checked>
												<input type="radio" name="sfrecovery${i}" value="2" title="未痊愈">
											</span> <font class="pum">--</font></div>`;
									}else if(zyb[i].sfrecovery==2){
										pa=`<span class="fl lastspan">
												<input type="radio" name="sfrecovery${i}" value="1" title="已痊愈" >
												<input type="radio" name="sfrecovery${i}" value="2" title="未痊愈" checked>
											</span> <font class="pum">--</font></div>`;
									}
									py=pm+pa;
									zybxs += py;
								}
								$(".zyb").html(zybxs);
							}


							/*疾病史*/
							if (jbs != null && jbs.length > 0) {
								intCheckbox("jws", jbs[0].jbsdm.split(","));
								$("#exzltext").val(jbs[0].exzlms);
								$("#exzltext").show();
								$("#zybtext").val(jbs[0].zybms);
								$("#zybtext").show();
								var jbsqt = "";
								for (var i = 0; i < jbs.length; i++) {
									$(".jwsjb").show();
									jbsqt += `<span>` +
										`<input  type="text" class="layui-input qtjbs" value="${jbs[i].qtjbs}" placeholder="疾病名称"/>` +
										`</span><span>` +
										`<input  type="text" class="layui-input jwssj" value="${jbs[i].qzsj}" placeholder="确诊时间"/>` +
										`</span> <font class="pum">--</font>`

								}
								$(".jbs").html(jbsqt);


							}else{
								intCheckbox("jws","1");
							}
							//性别
							intRadio('sex', data.sex)
							//判断是否为男
							Prohibit(data.sex);
							//出身日期
							$("#csny").val(data.csrq);
							//身份证
							$("#sfz2").val(data.sfz);
							//工作单位
							$("#companycode").val(data.companycode);
							//本人电话
							$("#brdh").val(data.brdh);
							//民族
							$('#minzu').val(data.mz);
							//出生地
							$("#birthplace").val(data.birthplace);
							//家庭地址
							$("#familyaddress").val(data.familyaddress);
							//总工龄
							$("#gl").val(data.gl);
							//接害工龄
							$("#jhgl").val(data.jhgl);
							//邮政编码
							$("#yzbm").val(data.yzbm);
							//初潮
							$("#cc").val(data.cc);
							//经期
							$("#jq").val(data.jq);
							//周期
							$("#zq").val(data.zq);
							//停经年龄
							$("#tjnl").val(data.tjnl);
							//子女数
							$("#znnumber").val(data.znnumber);
							//流产次
							$("#lcnumber").val(data.lcnumber);
							//早产次
							$("#zcnumber").val(data.zcnumber);
							//死产
							$("#scnumber").val(data.scnumber);
							//异常胎
							$("#yctcnumber").val(data.yctcnumber);
							//先天畸形
							$("#xtjxnumber").val(data.xtjxnumber);
							//婚姻状况
							intRadio("hunyin", data.hyzk)
							//婚姻状况其他
							$("#hyzkqt").val(data.hyzkqt);
							//接触的危害因素
							intCheckbox("jcwhys", data.whys);
							//接触的危害因素其他
							$("#jcwhysqt").val(data.jcwhysqt);
							//吸烟情况
							intRadio("xyzk", data.xyzk);
							initRadio("xyzk", data.xyzk);
							//日吸烟量
							$("#rxyl").val(data.rxyl);
							//开始吸烟年龄
							$("#ksxynl").val(data.ksxynl);
							//戒烟年龄
							$("#jynl").val(data.jynl);
							//对吸烟状况做出规范
							smoke($("input[name='xyzk']:checked").val());
							//饮酒频率
							intRadio("yjpl", data.yjpl);
							initRadio("yjpl", data.yjpl);
							//日饮酒量
							$("#ryjl").val(data.ryjl);
							//是否戒酒
							intRadio("sfjj", data.sfjj);
							//戒酒年龄
							$("#jjnl").val(data.jjnl);
							//开始饮酒年龄
							$("#ksyjnl").val(data.ksyjnl);
							//是否醉酒
							intRadio("sfzj", data.sfzj);
							//对饮酒状况做出规范
							drinkWine($("input[name='yjpl']:checked").val());
							//饮酒种类
							intCheckbox("yjzl", data.yjzl)
							//饮酒种类其他
							$("#yjzlqt").val(data.yjzlqt);
							//遗传病史
							intRadio("ycbs", data.ycbs);
							initRadio("ycbs", data.ycbs);
							//疾病名称
							$("#ycbsms").val(data.ycbsms);
							//药物过敏史
							intCheckbox("ywgms", data.ywgms);
							initCheckbox("ywgms", data.ywgms);
							//药物过敏史其他描述
							$("#ywgmsqtms").val(data.ywgmsqtms);

							$("#personalid").val(data.personalid);

							form.render(); //更新渲染
						} else {
							layer.msg('暂无改人员相关信息,请先进行登记');
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


		})


		//字段为空，给单选框赋初始化
		function initRadio(name,data){
			data==""?null:data;
			if(data!=null)
			return;
			intRadio(name,"1");
		}
		//字段为空，给多选框赋初始化
		function initCheckbox(name,data){
			data==""?null:data;
			if(data!=null)
			return;
			intCheckbox(name,"1");
		}
		
		
		//接受结果集，往表格进行数据填充
		function setCodedata(result) {
			//debugger;
			var html = '';
			html += `<option value="">请选择单位</option>`;
			for (var i = 0; i < result.length; i++) {
				html += `<option value="${result[i].companycode}">${result[i].companyname}</option>`;
			}
			$('#companycode').html(html);
			getnull();
			form.render();

		}
		//查询单位
		function getCodeData(data) {
			if (typeof(data) == "undefined") {
				data = {};
			}
			var result;
			$.ajax({
				type: "get",
				url: BaseUrl + 'companyinfo/selectlist',
				data: data,
				async: true,
				success: function(data) {
					if (data.success) {
						result = data.queryResult.list;
						setCodedata(result);
					} else {
						layer.msg(data.message);
					}

				}
			});
		}

//吸烟
form.on('radio(xyzk)', function(data){
	smoke(data.value);
})
//饮酒
form.on('radio(yjpl)', function(data){
	drinkWine(data.value);
})

//判断月经史和生育史是否需要禁用
form.on('radio(sex)', function(data){
	Prohibit(data.value);
})
function Prohibit(path){
	if(path=="男"){
		$(".Prohibit").attr("disabled","disabled");
		$('.nvxing').addClass('buke')
		}
	else{
	   $(".Prohibit").attr("disabled",false);
	   $('.nvxing').removeClass('buke')
	}
}


//吸烟情况
function smoke(xyzk){
		switch (xyzk){
			case "1"://从不
			$("#jynl").attr("disabled","disabled");
			$("#rxyl").attr("disabled","disabled");
			$("#ksxynl").attr("disabled","disabled");
			$('.xiyan').addClass('buke')
			
			
				break;
			case "2"://戒烟
			$("#rxyl").attr("disabled","disabled");
			$("#ksxynl").removeAttr('disabled');
			$("#jynl").removeAttr('disabled');
			$('.xiyan').addClass('buke')
			$('.xiyan').eq(1).removeClass('buke')
				$('.jieyan').removeClass('buke')
				break;
			default://吸烟
			$("#jynl").attr("disabled","disabled");
			$("#ksxynl").removeAttr('disabled');
			$("#rxyl").removeAttr('disabled');
			$('.xiyan').removeClass('buke');
			$('.jieyan').addClass('buke')
				break;
		}	
}
//饮酒
function drinkWine(yjpl){
		switch (yjpl){
			case "1"://从不
			$("#ryjl").attr("disabled","disabled");
			$("#jjnl").attr("disabled","disabled");
			$("#ksyjnl").attr("disabled","disabled");
			$('input[name="sfjj"]').prop('checked',false);
			$('input[name="sfjj"]').attr("disabled","disabled");
			$('input[name="yjzl"]').attr("disabled","disabled");
			intRadio("sfzj","2");
			$('input[name="sfzj"]').attr("disabled","disabled");
				$('.yinjiu').addClass('buke')
			form.render();
				break;
				
			case "3"://戒酒
			$("#ryjl").attr("disabled","disabled");
			$("#jjnl").removeAttr("disabled");
			$("#ksyjnl").removeAttr("disabled");
		
			$('input[name="sfjj"]').eq(1).prop('checked',true);
			$('input[name="sfjj"]').attr("disabled","disabled");
			$('input[name="sfzj"]').attr("disabled",false);
			$('input[name="yjzl"]').attr("disabled","disabled");
		    $('.yinjiu').removeClass('buke');
		    $('.yinjiu').eq(0).addClass('buke');
			form.render();
			    break;
			default://饮酒
				$("#jjnl").attr("disabled","disabled");
			    $("#ryjl").removeAttr("disabled");
			   $("#ksyjnl").removeAttr("disabled");
			$('input[name="sfjj"]').eq(0).prop('checked',true);
			$('input[name="sfjj"]').attr("disabled","disabled");
			$('input[name="sfzj"]').attr("disabled",false);
			$('input[name="yjzl"]').attr("disabled",false);
			$('.yinjiu').removeClass('buke');
			$('.yinjiu').eq(1).addClass('buke')
			form.render();
				break;
			 
				
				
			 
		}
}
	     



















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
		var companycode = $("#companycode").val();
		var tjrq = $("#tjrq").val();
		if (id == 0) {
			$('.leftTiJian ul').eq(id).show();
			//未体检人数接口获取数据
			var params = {
				companycode: companycode,
				tjbs: "0",
				tjrq:tjrq,
			}
			var wtjdata = isChecked(params);
			var wtjhtml = wtjdata.html;
			var wtjtotal = wtjdata.total;
			$('#wtjrs').html(wtjtotal);
			$('.leftTiJian ul').eq(0).html(wtjhtml);
		} else if (id == 1) {
			//已体检人数接口获取数据
			var params = {
				companycode: companycode,
				tjrq: tjrq,
				tjbs: "1"
			}
			var ytjdata = isChecked(params);
			var ytjhtml = ytjdata.html;
			var ytjtotal = ytjdata.total;
			$('#ytjrs').html(ytjtotal);
			$('.leftTiJian ul').eq(1).html(ytjhtml);
		}
	})

	//拼接class=gzdw的值
	function splicing(gzdw) {
		var arr = "";
		$("." + gzdw).each(function() {
			arr = arr + $(this).val() + ",";
		})
		arr = arr.substring(0, arr.length - 1);

		return arr;

	}
	//获取多选框的值
	function splicingCheckbox(name) {
		obj = document.getElementsByName(name);
		check_val = [];
		for (k in obj) {
			if (obj[k].checked)
				check_val.push(obj[k].value);
		}
		return check_val;

	}

	//执行保存的data对象
	$('.saveTijian').click(function() {
			$('#regular').bootstrapValidator('validate');
			var flag = $("#regular").data('bootstrapValidator').isValid();
			if(!flag){
				layer.msg("校验未通过");
				return false;
			}

		//个人信息
		//性别
		var sex = $("input[name='sex']:checked").val();
		//出身日期
		var csrq = $("#csny").val();
		//身份证
		var sfz = $("#sfz").val();
		//工作单位
		var companycode = $("#companycode").val();
		var companyname = $("#companycode option:checked").text();
		//本人电话
		var brdh = $("#brdh").val();
		//民族
		var mz = $("#minzu option:checked").text();
		//出生地
		var birthplace = $("#birthplace").val();
		//家庭地址
		var familyaddress = $("#familyaddress").val();
		//总工龄
		var gl = $("#gl").val();
		//接害工龄
		var jhgl = $("#jhgl").val();
		//邮政编码
		var yzbm = $("#yzbm").val();
		//初潮
		var cc = $("#cc").val();
		//经期
		var jq = $("#jq").val();
		//周期
		var zq = $("#zq").val();
		//停经年龄
		var tjnl = $("#tjnl").val();
		//子女数
		var znnumber = $("#znnumber").val();
		//流产次
		var lcnumber = $("#lcnumber").val();
		//早产次
		var zcnumber = $("#zcnumber").val();
		//死产
		var scnumber = $("#scnumber").val();
		//异常胎
		var yctcnumber = $("#yctcnumber").val();
		//先天畸形
		var xtjxnumber = $("#xtjxnumber").val();
		//婚姻状况
		var hyzk = $("input[name='hunyin']:checked").val();
		//婚姻状况其他
		var hyzkqt = $("#hyzkqt").val();
		//接触的危害因素
		var whys = splicingCheckbox("jcwhys").toString();
		//接触的危害因素其他
		var jcwhysqt = $("#jcwhysqt").val();
		//吸烟情况
		var xyzk = $("input[name='xyzk']:checked").val();
		//日吸烟量
		var rxyl = $("#rxyl").val();
		//开始吸烟年龄
		var ksxynl = $("#ksxynl").val();
		//戒烟年龄
		var jynl = $("#jynl").val();
		//饮酒频率
		var yjpl = $("input[name='yjpl']:checked").val();
		//日饮酒量
		var ryjl = $("#ryjl").val();
		//是否戒酒
		var sfjj = $("input[name='sfjj']:checked").val();
		//戒酒年龄
		var jjnl = $("#jjnl").val();
		//开始饮酒年龄
		var ksyjnl = $("#ksyjnl").val();
		//是否醉酒
		var sfzj = $("input[name='sfzj']:checked").val();
		//饮酒种类
		var yjzl = splicingCheckbox("yjzl").toString();
		//饮酒种类其他
		var yjzlqt = $("#yjzlqt").val();
		//遗传病史
		var ycbs = $("input[name='ycbs']:checked").val();
		//疾病名称
		var ycbsms = $("#ycbsms").val();
		//药物过敏史
		var ywgms = splicingCheckbox("ywgms").toString();
		//药物过敏史其他描述
		var ywgmsqtms = $("#ywgmsqtms").val();
		//档案号
		var personalid = $("#personalid").val();

		var params = {
			sex: sex,
			csrq: csrq,
			sfz: sfz,
			companyname: companyname,
			companycode: companycode,
			brdh: brdh,
			mz: mz,
			birthplace: birthplace,
			familyaddress: familyaddress,
			gl: gl,
			jhgl: jhgl,
			yzbm: yzbm,
			cc: cc,
			jq: jq,
			zq: zq,
			tjnl: tjnl,
			znnumber: znnumber,
			lcnumber: lcnumber,
			zcnumber: zcnumber,
			scnumber: scnumber,
			yctcnumber: yctcnumber,
			xtjxnumber: xtjxnumber,
			hyzk: hyzk,
			hyzkqt: hyzkqt,
			whys: whys,
			jcwhysqt: jcwhysqt,
			xyzk: xyzk,
			rxyl: rxyl,
			ksxynl: ksxynl,
			jynl: jynl,
			yjpl: yjpl,
			ryjl: ryjl,
			sfjj: sfjj,
			jjnl: jjnl,
			ksyjnl: ksyjnl,
			sfzj: sfzj,
			yjzl: yjzl,
			yjzlqt: yjzlqt,
			ycbs: ycbs,
			ycbsms: ycbsms,
			ywgms: ywgms,
			ywgmsqtms: ywgmsqtms,
			personalid: personalid,
			tjbs: "1",

		}

		//家族史
		//父亲
		var jzsfq = splicingCheckbox("jzsfq");
		//父亲其他
		var fqqt = $("#jzsqtfq").val();
		//母亲
		var jzsmq = splicingCheckbox("jzsmq");
		//母亲其他
		var mqqt = $("#jzsqtmq").val();
		//兄弟
		var jzsxd = splicingCheckbox("jzsxd");
		//兄弟其他
		var xdqt = $("#jzsqtxdjm").val();
		//子女
		var jzszn = splicingCheckbox("jzszn");
		//子女其他
		var znqt = $("#jzsqtzn").val();

		var jzsjbdm = jzsfq + ";" + jzsmq + ";" + jzsxd + ";" + jzszn;
		var jzsOther = fqqt + ";" + mqqt + ";" + xdqt + ";" + znqt;
		var paramsJzs = {
			jzsjbdm: jzsjbdm,
			jzsOther: jzsOther,
			personalid: personalid,
		}


		//疾病史
		//疾病史代码
		var jbsdm = splicingCheckbox("jws").toString();
		//恶性肿瘤描述
		var exzlms = $("#exzltext").val();
		//职业病描述
		var zybms = $("#zybtext").val();
		//其他疾病
		var qtjbs = splicing("qtjbs");
		//确诊时间
		var qzsj = splicing("jwssj");

		var paramsJbs = {
			jbsdm: jbsdm,
			exzlms: exzlms,
			zybms: zybms,
			qtjbs: qtjbs,
			qzsj: qzsj,
			personalid: personalid,
		}



		//手术
		//是否手术
		var sfss = $("input[name='sfss']:checked").val();
		//手术名称
		var ssmc = splicing("ssmc");
		//手术时间
		var sssj = splicing("sssj");
		var paramsSs = {
			sfss: sfss,
			ssmc: ssmc,
			sssj: sssj,
			personalid: personalid,
		}


		//外伤
		//是否 外伤
		var sfws = $("input[name='sfws']:checked").val();
		//外伤名称
		var wsmc = splicing("wsmc");
		//外伤时间
		var wssj = splicing("wssj");

		var paramsWs = {
			sfws: sfws,
			wsmc: wsmc,
			wssj: wssj,
			personalid: personalid,
		}

		//输血
		//是否输血
		var sfsx = $("input[name='sfsx']:checked").val();
		//输血名称
		var sxmc = splicing("sxmc");
		//输血时间
		var sxsj = splicing("sxsj");

		var paramsSx = {
			sfsx: sfsx,
			sxyy: sxmc,
			sxsj: sxsj,
			personalid: personalid,
		}





		//职业史
		//开始时间
		var kssj = splicing("kssj");
		//结束时间
		var jssj = splicing("jssj");
		//工作单位
		var gzdw = splicing("gzdw");
		//车间
		var cj = splicing("cj");
		//工种
		var gz = splicing("gz");
		//有害因素
		var yhys = splicing("yhys");
		//防护措施
		var fhcs = splicing("fhcs");

		var paramsZys = {
			qzrq: kssj,
			jssj: jssj,
			gzdw: gzdw,
			cj: cj,
			gz: gz,
			yhys: yhys,
			fhcs: fhcs,
			personalid: personalid,
		};
		//职业病
		//病名
		var illnessname = splicing("illnessname");
		//诊断日期
		var diagnosisdata = splicing("diagnosisdata");
		//诊断单位
		var diagnosiscompany = splicing("diagnosiscompany");
		debugger;
		//是否痊愈
     var sfrecovery = ""
     $('.lastspan').each(function(){
				var m=$(this).find('input[type="radio"]');
				m.each(function(){
					if($(this).prop('checked')){
						sfrecovery +=$(this).val()+",";
					}
				})
			})
		sfrecovery = sfrecovery.substring(0,sfrecovery.length-1);
		 var paramsZyb = {
		 	illnessname: illnessname,
		 	diagnosisdata: diagnosisdata,
		 	diagnosiscompany: diagnosiscompany,
		 	personalid: personalid,
		 	sfrecovery: sfrecovery,


		 }
        var paramsq = {
            grjbxx: params,
            jbxxJzs:paramsJzs,
            jbxxJbs:paramsJbs,
            jbxxWs: paramsWs,
            jbxxSx: paramsSx,
            jbxxSs: paramsSs,
            jbxxZys: paramsZys,
            jbxxZyb: paramsZyb,
        }
          $.ajax({
			type: "post",
			url: BaseUrl + 'grjbxx/adddWhole',
			async: true,
			data: JSON.stringify(paramsq),
			processData: false, //是否序列化
			contentType: "application/json",
			success: function(data) {

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


	});
	
})

  

		
		
