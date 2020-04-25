$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		
		
		var tmh = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var params = {
				tmh: tmh,
		}
		getCode(params);	
		
		
	function getCode(param){
		
		var urls = 'grjbxx/selectWhole';
			
				$.ajax({
					type: "post",
					url: BaseUrl + urls,
					async: true,
					data:JSON.stringify(params),
					processData: false,//是否序列化
            		contentType: "application/json", 
					success: function(data) {
					if (data.success) {
						var list = data.queryResult.list;
						if (list.length > 0) {
							zhikong("layui-table");
							var arr = list[0];
							var data = arr.grjbxx;
							var jzs = arr.grjbxxJzs;
							var jbs = arr.grjbxxJbs;
							var zys = arr.grjbxxZys;
							var ws = arr.grjbxxWs;
							var ss = arr.grjbxxSs;
							var sx = arr.grjbxxSx;
							var zyb = arr.grjbxxZyb;
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


							}
							
							$("#personalid2").val(data.personalid);
							$("#xm2").val(data.name);
							$("#sfz2").val(data.sfz);
							$("#tmh2").val(data.tmh);
							$("#tjrq2").val(data.tjrq);
							//性别
							intRadio('sex', data.sex)
							//出身日期
							$("#csny").val(data.csrq);
							//身份证
							$("#sfz").val(data.sfz);
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
							//日吸烟量
							$("#rxyl").val(data.rxyl);
							//开始吸烟年龄
							$("#ksxynl").val(data.ksxynl);
							//戒烟年龄
							$("#jynl").val(data.jynl);
							//饮酒频率
							intRadio("yjpl", data.yjpl);
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
							//饮酒种类
							intCheckbox("yjzl", data.yjzl)
							//饮酒种类其他
							$("#yjzlqt").val(data.yjzlqt);
							//遗传病史
							intRadio("ycbs", data.ycbs);
							//疾病名称
							$("#ycbsms").val(data.ycbsms);
							//药物过敏史
							intCheckbox("ywgms", data.ywgms);
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
						layer.msg(data.message);
					}

				});
	}
	});
})