$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		var laydate = layui.laydate;

		var tmh = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var param = {
			tmh: tmh,
		};

		var personalid = window.location.href.split('?')[1].split('&')[1].split('=')[1];
		var params = {
			personalid: personalid,
		};
		getGtjbxxData(params);

		getZysData(params);

		getJzsData(params);

		getJbsData(params);

		getSymptomData(param);

		getSjkData(param);

		getWgkData(param);

		getWkData(param);

		getNkData(param);

		getHyData(param);

		getZybData(params);

		function getGtjbxxData(param) {
			if(typeof(param) == "undefined") {
				param = {};
			}
			$.ajax({
				type: "post",
				url: BaseUrl + 'grjbxx/select',
				data: JSON.stringify(param),
				async: true,
				processData: false,
				contentType: "application/json",
				success: function(data) {
					if(data.success) {
						var grjbxx = data.queryResult.list;
						$('.name').html(grjbxx[0].name);
						$('.companyname').html(grjbxx[0].companyname);
						$('.brdh').html(grjbxx[0].brdh);
						$('.personalid').html(grjbxx[0].personalid);
						$('.tjrq').html(grjbxx[0].tjrq);
						$('.name2').html(grjbxx[0].name);
						$('.sex').html(grjbxx[0].sex);
						$('.csrq').html(grjbxx[0].csrq);
						$('.birthplace').html(grjbxx[0].birthplace);
						$('.mz').html(grjbxx[0].mz);
						$('.brdh').html(grjbxx[0].brdh);
						$('.sfz').html(grjbxx[0].sfz);
						$('.familyaddress').html(grjbxx[0].familyaddress);
						$('.yzbm').html(grjbxx[0].yzbm);
						//体检类型
						if(grjbxx[0].tjlx != "" && typeof(grjbxx[0].tjlx) != "undefined") {
							var tjlx = grjbxx[0].tjlx;
							if(xyzk == '1') {
								tjlx = "入职前";
							}
							if(tjlx = '2') {
								tjlx = "在职";
							}
							if(tjlx = "3") {
								tjlx = "离岗";
							}
							$('.tjlx').html(tjlx);
						}

						//危害因素
						var whys = "";
						if(grjbxx[0].whys != "" && typeof(grjbxx[0].whys) != "undefined") {
							if(grjbxx[0].whys.indexOf("1") != -1) {
								whys += "粉尘,"
							}
							if(grjbxx[0].whys.indexOf("2") != -1) {
								whys += "放射物质,"
							}
							if(grjbxx[0].whys.indexOf("3") != -1) {
								whys += "物理因素,"
							}
							if(grjbxx[0].whys.indexOf("4") != -1) {
								whys += "化学物质,"
							}

							var jcwhysqt = "";
							if(grjbxx[0].jcwhysqt != "" && typeof(grjbxx[0].jcwhysqt) != "undefined") {
								whys += grjbxx[0].jcwhysqt + ",";
							}

							whys = whys.substring(0, whys.length - 1);
						}
						$('.whys').html(whys);
						$('.gl').html(grjbxx[0].gl);
						$('.jhgl').html(grjbxx[0].jhgl);
						$('.cc').html(grjbxx[0].cc);
						$('.jq').html(grjbxx[0].jq);
						$('.zq').html(grjbxx[0].zq);
						$('.tjnl').html(grjbxx[0].tjnl);
						$('.znnumber').html(grjbxx[0].znnumber);
						$('.lcnumber').html(grjbxx[0].lcnumber);
						$('.zcnumber').html(grjbxx[0].zcnumber);
						$('.scnumber').html(grjbxx[0].scnumber);
						$('.xtjxnumber').html(grjbxx[0].xtjxnumber);
						$('.xtjxnumber').html(grjbxx[0].xtjxnumber);
						$('.yctcnumber').html(grjbxx[0].xtjxnumber);
						if(grjbxx[0].xyzk != "" && typeof(grjbxx[0].xyzk) != "undefined") {
							var xyzk = grjbxx[0].xyzk;
							if(xyzk == '1') {
								xyzk = "从不吸烟";
							}
							if(xyzk = '2') {
								xyzk = "已戒烟";
							}
							if(xyzk = "3") {
								xyzk = "吸烟";
							}
							if(xyzk = "4") {
								xyzk = "经常吸烟";
							}
							$('.xyzk').html(xyzk);
						}

						$('.rxyl').html(grjbxx[0].rxyl); //待定（两边单位不一致）
						//吸烟年龄计算
						//戒烟年龄-开始吸烟年龄
						var xynl = "";
						if(grjbxx[0].ksxynl != "" && grjbxx[0].jynl != "") {
							xynl = parseInt(grjbxx[0].jynl) - parseInt(grjbxx[0].ksxynl);
						}
						//当前时间-开始吸烟年龄
						$('.jynl').html(xynl);
						if(grjbxx[0].yjpl != "" && typeof(grjbxx[0].yjpl) != "undefined") {
							var yjpl = grjbxx[0].yjpl;
							if(yjpl == "1") {
								yjpl = "从不饮酒";
							}
							if(yjpl == "2") {
								yjpl = "偶尔饮";
							}
							if(yjpl == "3") {
								yjpl = "以前常饮酒,先已戒除";
							}
							if(yjpl == "4") {
								yjpl = "现在经常饮酒";
							}
							$('.yjpl').html(yjpl); //饮酒频率
						}

						$('.ryjl').html(grjbxx[0].ryjl);

						var yjzl = "";
						if(grjbxx[0].yjzl != "" && typeof(grjbxx[0].yjzl) != "undefined") {
							if(grjbxx[0].whys.indexOf("1") != -1) {
								yjzl += "白酒,"
							}
							if(grjbxx[0].whys.indexOf("2") != -1) {
								yjzl += "啤酒,"
							}
							if(grjbxx[0].whys.indexOf("3") != -1) {
								yjzl += "黄酒,"
							}
							if(grjbxx[0].whys.indexOf("4") != -1) {
								yjzl += "红酒,"
							}
							if(grjbxx[0].yjzlqt != "" && typeof(grjbxx[0].yjzlqt) != "undefined") {
								yjzl += grjbxx[0].yjzlqt + ",";
							}

							yjzl = yjzl.substring(0, yjzl.length - 1);
						}
						$('.yjzl').html(yjzl); //饮酒种类
						$('.ksyjnl').html(grjbxx[0].ksyjnl);

						//检查结论
						var jielun = "";
						if(grjbxx[0].jielun != "" && typeof(grjbxx[0].jielun) != "undefined" && grjbxx[0].jielun != null) {
							jielun += grjbxx[0].jielun + ":";
						}
						var clyj = "";
						if(grjbxx[0].fcyy != "" && typeof(grjbxx[0].fcyy) != "undefined" && grjbxx[0].fcyy != null) {
							clyj += grjbxx[0].fcyy + ",";
						}
						if(grjbxx[0].yszybwb != "" && typeof(grjbxx[0].yszybwb) != "undefined" && grjbxx[0].yszybwb != null) {
							clyj += grjbxx[0].yszybwb + ",";
						}

						if(grjbxx[0].jjzwb != "" && typeof(grjbxx[0].jjzwb) != "undefined" && grjbxx[0].jjzwb != null) {
							clyj += result[0].jjzwb + ",";
						}
						jielun += clyj;
						jielun = jielun.substring(0, jielun.length - 1);
						$('#jielun1').html(jielun);

						//处理意见
						var clyi2 = "";
						if(grjbxx[0].jielun == "复查") {
							clyi2 = "请联系职业病检查机构进行进一步检查。";
						}
						if(grjbxx[0].jielun == "疑似职业病") {
							clyi2 = "请尽快向职业病诊断机构申请职业病诊断。";
						}
						if(grjbxx[0].jielun == "职业禁忌证") {
							clyi2 = "请建议转岗等。";
						}

						$('#clyj1').html(clyi2);

					} else {
						layer.msg(data.message);
					}

				},

			});
		}

	});
	// 选项卡
	/*	$('.leftTiJian ul').hide() $('.leftTiJian ul').eq(0).show() $('.leftTiJian p').click(function() {
			id = $(this).index()
			$(this).siblings().removeClass('active');
			$(this).addClass('active');
			$('.leftTiJian ul').hide();
			$('.leftTiJian ul').eq(id).show()
		})*/
})

function getZysData(param) {
	if(typeof(param) == "undefined") {
		param = {};
	}
	var result;
	$.ajax({
		type: "get",
		url: BaseUrl + 'grjbxxZys/select',
		data: param,
		async: true,
		success: function(data) {
			if(data.success) {
				result = data.queryResult.list;
				setZysdata(result);
			} else {
				layer.msg(data.message);
			}

		}
	});
}

//接受结果集，往表格进行数据填充
function setZysdata(result) {
	if(result.length == 0) {
		var nodata = '<div class="nodata">暂无数据!</div>';
		$('#tbody').html(nodata);
		return false;
	}
	var html = '';
	for(var i = 0; i < result.length; i++) {
		html += `<tr>
								<td>${i+1}</td>
								<td>${result[i].qzrq}-${result[i].jssj}</td>
								<td>${result[i].gzdw}</td>
								<td>${result[i].cj}</td>
								<td>${result[i].gz}</td>
								<td>${result[i].yhys}</td>
								<td>${result[i].fhcs}</td>
							</tr>`;
	}
	$('#tbody').html(html);
	getnull();
}

function getJzsData(param) {
	if(typeof(param) == "undefined") {
		param = {};
	}
	var result;
	$.ajax({
		type: "get",
		url: BaseUrl + 'grjbxxJzs/select',
		data: param,
		async: true,
		success: function(data) {
			if(data.success) {
				result = data.queryResult.list;
				setJzsdata(result);
			} else {
				layer.msg(data.message);
			}

		}
	});
}

//接受结果集，往表格进行数据填充
function setJzsdata(result) {
	var html = '';
	var jzsjbzz = '';
	var jzsjbdm = result[0].jzsjbdm;
	var strs = new Array(); //定义一数组
	var jzsfq = ""; //家族史父亲
	var jzsOther = "";

	var jzsjbzzmq = '';
	var jzsmq = ""; //家族史母亲
	var jzsmqOther = "";

	var jzsjbzzxm = '';
	var jzsxm = ""; //家族史兄妹
	var jzsxmOther = "";

	var jzsjbzzzn = '';
	var jzszn = ""; //家族史子女
	var jzsznOther = "";
	debugger;
	for(i = 0; i < result.length; i++) {
		//家族史父亲
		if(result[i].jzsgx == "1") {
			jzsjbdm = result[i].jzsjbdm;
			if(jzsjbdm != "" && typeof(jzsjbdm) != "undefined") {
				strs = jzsjbdm.split(",");
				for(a = 0; a < strs.length; a++) {
					if(strs[a] == "1") {
						jzsjbzz += "无";
					} else if(strs[a] == "2") {
						jzsjbzz += "高血压,";
					} else if(strs[a] == "3") {
						jzsjbzz += "糖尿病,";
					} else if(strs[a] == "4") {
						jzsjbzz += "冠心病,";
					} else if(strs[a] == "5") {
						jzsjbzz += "慢性阻塞性肺疾病,";
					} else if(strs[a] == "6") {
						jzsjbzz += "恶性肿瘤,";
					} else if(strs[a] == "7") {
						jzsjbzz += "脑卒中,";
					} else if(strs[a] == "8") {
						jzsjbzz += "严重精神障碍,";
					} else if(strs[a] == "9") {
						jzsjbzz += "结核病,";
					} else if(strs[a] == "10") {
						jzsjbzz += "肝炎,";
					} else if(strs[a] == "11") {
						jzsjbzz += "先天畸形,";
					} else if(strs[a] == "12") {
						jzsjbzz += "其他,";
					}
				}
			}
			if(jzsjbzz=="无"){
				jzsfq="";
			}else{
				jzsfq = "父亲:" + jzsjbzz;
			}
			if(result[i].jzsOther != "" && typeof(result[i].jzsOther) != "undefined" && result[i].jzsOther != null) {
				jzsfq += result[i].jzsOther + ",";
			}
			jzsfq = jzsfq.substring(0, jzsfq.length - 1);
		}

		//家族史母亲
		if(result[i].jzsgx == "2") {
			jzsjbdm = result[i].jzsjbdm;
			if(jzsjbdm != "" && typeof(jzsjbdm) != "undefined") {
				strs = jzsjbdm.split(",");
				for(a = 0; a < strs.length; a++) {
					if(strs[a] == "1") {
						jzsjbzzmq += "无";
					} else if(strs[a] == "2") {
						jzsjbzzmq += "高血压,";
					} else if(strs[a] == "3") {
						jzsjbzzmq += "糖尿病,";
					} else if(strs[a] == "4") {
						jzsjbzzmq += "冠心病,";
					} else if(strs[a] == "5") {
						jzsjbzzmq += "慢性阻塞性肺疾病,";
					} else if(strs[a] == "6") {
						jzsjbzzmq += "恶性肿瘤,";
					} else if(strs[a] == "7") {
						jzsjbzzmq += "脑卒中,";
					} else if(strs[a] == "8") {
						jzsjbzz += "严重精神障碍,";
					} else if(strs[a] == "9") {
						jzsjbzzmq += "结核病,";
					} else if(strs[a] == "10") {
						jzsjbzzmq += "肝炎,";
					} else if(strs[a] == "11") {
						jzsjbzzmq += "先天畸形,";
					} else if(strs[a] == "12") {
						jzsjbzzmq += "其他,";
					}
				}
			}
			if(jzsjbzzmq=="无"){
				jzsmq="";
			}else{
				jzsmq = "母亲:" + jzsjbzzmq;
			}
			if(result[i].jzsOther != "" && typeof(result[i].jzsOther) != "undefined" && result[i].jzsOther != null) {
				jzsmq += result[i].jzsOther + ",";
			}
			jzsmq = jzsmq.substring(0, jzsmq.length - 1);
		}

		//家族史兄妹
		if(result[i].jzsgx == "3") {
			jzsjbdm = result[i].jzsjbdm;
			if(jzsjbdm != "" && typeof(jzsjbdm) != "undefined") {
				strs = jzsjbdm.split(",");
				for(a = 0; a < strs.length; a++) {
					if(strs[a] == "1") {
						jzsjbzzxm += "无";
					} else if(strs[a] == "2") {
						jzsjbzzxm += "高血压,";
					} else if(strs[a] == "3") {
						jzsjbzzxm += "糖尿病,";
					} else if(strs[a] == "4") {
						jzsjbzzxm += "冠心病,";
					} else if(strs[a] == "5") {
						jzsjbzzxm += "慢性阻塞性肺疾病,";
					} else if(strs[a] == "6") {
						jzsjbzzxm += "恶性肿瘤,";
					} else if(strs[a] == "7") {
						jzsjbzzxm += "脑卒中,";
					} else if(strs[a] == "8") {
						jzsjbzz += "严重精神障碍,";
					} else if(strs[a] == "9") {
						jzsjbzzxm += "结核病,";
					} else if(strs[a] == "10") {
						jzsjbzzxm += "肝炎,";
					} else if(strs[a] == "11") {
						jzsjbzzxm += "先天畸形,";
					} else if(strs[a] == "12") {
						jzsjbzzxm += "其他,";
					}
				}
			}
			
			if(jzsjbzzxm=="无"){
				jzsxm="";
			}else{
				jzsxm = "兄妹:" + jzsjbzzxm;
			}
			if(result[i].jzsOther != "" && typeof(result[i].jzsOther) != "undefined" && result[i].jzsOther != null) {
				jzsxm += result[i].jzsOther + ",";
			}
			jzsxm = jzsxm.substring(0, jzsxm.length - 1);

		}

		//家族史子女
		if(result[i].jzsgx == "4") {
			jzsjbdm = result[i].jzsjbdm;
			if(jzsjbdm != "" && typeof(jzsjbdm) != "undefined") {
				strs = jzsjbdm.split(",");
				for(a = 0; a < strs.length; a++) {
					if(strs[a] == "1") {
						jzsjbzzzn += "无";
					} else if(strs[a] == "2") {
						jzsjbzzzn += "高血压,";
					} else if(strs[a] == "3") {
						jzsjbzzzn += "糖尿病,";
					} else if(strs[a] == "4") {
						jzsjbzzzn += "冠心病,";
					} else if(strs[a] == "5") {
						jzsjbzzzn += "慢性阻塞性肺疾病,";
					} else if(strs[a] == "6") {
						jzsjbzzzn += "恶性肿瘤,";
					} else if(strs[a] == "7") {
						jzsjbzzzn += "脑卒中,";
					} else if(strs[a] == "8") {
						jzsjbzzzn += "严重精神障碍,";
					} else if(strs[a] == "9") {
						jzsjbzzzn += "结核病,";
					} else if(strs[a] == "10") {
						jzsjbzzzn += "肝炎,";
					} else if(strs[a] == "11") {
						jzsjbzzzn += "先天畸形,";
					} else if(strs[a] == "12") {
						jzsjbzzzn += "其他,";
					}
				}
			}
			if(jzsjbzzzn=="无"){
				jzszn="";
			}else{
				jzszn = "子女:" + jzsjbzzzn;
			}
			if(result[i].jzsOther != "" && typeof(result[i].jzsOther) != "undefined" && result[i].jzsOther != null) {
				jzszn += result[i].jzsOther + ",";
			}
			jzszn = jzszn.substring(0, jzszn.length - 1);

		}

	}



	html = jzsfq + "  " + jzsmq + "  " + jzsxm + "  " + jzszn;

	$('.jzs').html(html);
	getnull();
}

function getJbsData(param) {
	if(typeof(param) == "undefined") {
		param = {};
	}
	var result;
	$.ajax({
		type: "get",
		url: BaseUrl + 'grjbxxJbs/select',
		data: param,
		async: true,
		success: function(data) {
			if(data.success) {
				result = data.queryResult.list;
				setJbsdata(result);
			} else {
				layer.msg(data.message);
			}

		}
	});
}

//接受结果集，往表格进行数据填充
function setJbsdata(result) {
	var html = '';
	for(var i = 0; i < 1; i++) {

		var jbsdm = result[i].jbsdm;
		var strs = new Array(); //定义一数组
		if(jbsdm != "" && typeof(jbsdm) != "undefined") {

			strs = jbsdm.split(","); //字符分割
			var jbszz = "症状:";
			for(a = 0; a < strs.length; a++) {
				if(strs[a] == "1") {
					jbszz += "无";
				} else if(strs[a] == "2") {
					jbszz += "高血压,";
				} else if(strs[a] == "3") {
					jbszz += "糖尿病,";
				} else if(strs[a] == "4") {
					jbszz += "冠心病,";
				} else if(strs[a] == "5") {
					jbszz += "慢性阻塞性肺疾病,";
				} else if(strs[a] == "6") {
					jbszz += "恶性肿瘤,";
				} else if(strs[a] == "7") {
					jbszz += "脑卒中,";
				} else if(strs[a] == "8") {
					jbszz += "严重精神障碍,";
				} else if(strs[a] == "9") {
					jbszz += "结核病,";
				} else if(strs[a] == "10") {
					jbszz += "肝炎,";
				} else if(strs[a] == "11") {
					jbszz += "先天畸形,";
				} else if(strs[a] == "12") {
					jbszz += "其他,";
				}
			}
		}
		var zybms = "";
		if(result[i].zybms != "" && typeof(result[i].zybms) != "undefined") {
			zybms = "职业病描述:" + result[i].zybms;
		}
		var exzlms = "";
		if(result[i].exzlms != "" && typeof(result[i].exzlms) != "undefined") {
			exzlms = "恶性肿瘤描述:" + result[i].exzlms;
		}
	}

	//处理疾病名称和时间多次,
	var qzjbss = "";
	var qzsj = "";
	var qtjbs = "";
	for(var i = 0; i < result.length; i++) {

		if(result[i].qzsj != "" && typeof(result[i].qzsj) != "undefined" && result[i].qzsj != null) {
			qzsj = "确诊时间:" + result[i].qzsj;
		}
		if(result[i].qtjbs != "" && typeof(result[i].qtjbs) != "undefined" && result[i].qtjbs != null) {
			qtjbs = "其他疾病描述:" + result[i].qtjbs + ",";
		}
		qzjbss += qtjbs + qzsj;
	}
	html += jbszz + qzjbss + zybms + exzlms;
	
	$('.jbs').html(html);
	getnull();
}

function getSymptomData(param) {
	if(typeof(param) == "undefined") {
		param = {};
	}
	var result;
	$.ajax({
		type: "post",
		url: BaseUrl + 'symptominfo/sel',
		data: JSON.stringify(param),
		async: true,
		processData: false,
		contentType: "application/json",
		success: function(data) {
			$("#tt").html(SymptomJudge(data[0].tt));
			$("#ty").html(SymptomJudge(data[0].ty));
			$("#xy").html(SymptomJudge(data[0].xy));
			$("#sm").html(SymptomJudge(data[0].sm));
			$("#ss").html(SymptomJudge(data[0].ss));
			$("#dm").html(SymptomJudge(data[0].dm));
			$("#jylst").html(SymptomJudge(data[0].jylst));
			$("#pfwl").html(SymptomJudge(data[0].pfwl));
			$("#yjd").html(SymptomJudge(data[0].yjd));
			$("#dr").html(SymptomJudge(data[0].dr));
			$("#dh").html(SymptomJudge(data[0].dh));
			$("#duoh").html(SymptomJudge(data[0].duoh));
			$("#qsst").html(SymptomJudge(data[0].qsst));
			$("#ssmh").html(SymptomJudge(data[0].ssmh));
			$("#slxj").html(SymptomJudge(data[0].slxj));
			$("#yg").html(SymptomJudge(data[0].yg));
			$("#yant").html(SymptomJudge(data[0].yant));
			$("#xium").html(SymptomJudge(data[0].xium));
			$("#liul").html(SymptomJudge(data[0].liul));
			$("#xjjt").html(SymptomJudge(data[0].xjjt));
			$("#bse").html(SymptomJudge(data[0].bse));
			$("#bgan").html(SymptomJudge(data[0].bgan));
			$("#liubxue").html(SymptomJudge(data[0].liubxue));
			$("#erm").html(SymptomJudge(data[0].erm));
			$("#erl").html(SymptomJudge(data[0].erl));
			$("#xshou").html(SymptomJudge(data[0].xshou));
			$("#kouk").html(SymptomJudge(data[0].kouk));
			$("#liux").html(SymptomJudge(data[0].liux));
			$("#yatong").html(SymptomJudge(data[0].yatong));
			$("#ycsd").html(SymptomJudge(data[0].ycsd));
			$("#yyzz").html(SymptomJudge(data[0].yyzz));
			$("#yycx").html(SymptomJudge(data[0].yycx));
			$("#kqky").html(SymptomJudge(data[0].kqky));
			$("#kqyw").html(SymptomJudge(data[0].kqyw));
			$("#xmen").html(SymptomJudge(data[0].xmen));
			$("#qduan").html(SymptomJudge(data[0].qduan));
			$("#xtong").html(SymptomJudge(data[0].xtong));
			$("#kesou").html(SymptomJudge(data[0].kesou));
			$("#ketan").html(SymptomJudge(data[0].ketan));
			$("#kaxie").html(SymptomJudge(data[0].kaxie));
			$("#xiaoc").html(SymptomJudge(data[0].xiaoc));
			$("#xinji").html(SymptomJudge(data[0].xinji));
			$("#xqqbs").html(SymptomJudge(data[0].xqqbs));
			$("#futong").html(SymptomJudge(data[0].futong));
			$("#fuzhang").html(SymptomJudge(data[0].fuzhang));
			$("#syjt").html(SymptomJudge(data[0].syjt));
			$("#fuxie").html(SymptomJudge(data[0].fuxie));
			$("#bianm").html(SymptomJudge(data[0].bianm));
			$("#gqttong").html(SymptomJudge(data[0].gqttong));
			$("#pxcxue").html(SymptomJudge(data[0].pxcxue));
			$("#pfsy").html(SymptomJudge(data[0].pfsy));
			$("#pizhen").html(SymptomJudge(data[0].pizhen));
			$("#tuofa").html(SymptomJudge(data[0].tuofa));
			$("#gjt").html(SymptomJudge(data[0].gjt));
			$("#jrcc").html(SymptomJudge(data[0].jrcc));
			$("#xzczg").html(SymptomJudge(data[0].xzczg));
			$("#dzblh").html(SymptomJudge(data[0].dzblh));
			$("#szmm").html(SymptomJudge(data[0].szmm));
			$("#szdh").html(SymptomJudge(data[0].szdh));
			$("#szfl").html(SymptomJudge(data[0].szfl));
			$("#ysqhai").html(SymptomJudge(data[0].ysqhai));
			$("#niaop").html(SymptomJudge(data[0].niaop));
			$("#niaoji").html(SymptomJudge(data[0].niaoji));
			$("#niaoxue").html(SymptomJudge(data[0].niaoxue));
			$("#fuzhong").html(SymptomJudge(data[0].fuzhong));
			$("#yjyc").html(SymptomJudge(data[0].yjyc));
			$("#xyjt").html(SymptomJudge(data[0].xyjt));
			$("#ysqm").html(data[0].ysqm);
		}
	});

}
//症状程度：2 偶有以“±”，3 较轻以“ +”，4 中等以“ ++”，5 明显以“ +++”，1 无以“ -”表示
function SymptomJudge(data) {
	if(data != null && data.length > 0) {
		if(data == "1") {
			return "-";
		} else if(data == "2") {
			return "±";
		} else if(data == "3") {
			return "+";
		} else if(data == "4") {
			return "++";
		} else if(data == "5") {
			return "+++";
		}
	}
	return null;
}

//五官科
function getWgkData(param) {

	if(typeof(param) == "undefined") {
		param = {};
	}
	var result;
	$.ajax({
		type: "get",
		url: BaseUrl + 'InquiryWgk/list/1/' + +pageSize,
		data: param,
		async: true,
		success: function(data) {
			result = data.queryResult.list;
			$("#zysl").html(result[0].zysl);
			$("#yysl").html(result[0].yysl);
			$("#zyjzsl").html(result[0].zyjzsl);
			$("#yyjzsl").html(result[0].yyjzsl);
			$("#wy").html(result[0].wy);
			$("#jt").html(result[0].jt);
			//眼底:神经乳头
			var sjrt = result[0].sjrt;
			if(result[0].sjrtms != "" && typeof(result[0].sjrtms) != "undefined" && result[0].sjrtms != null) {
				sjrt = sjrt += "/" + result[0].sjrtms;
			}
			$("#sjrt").html(sjrt);
			//眼底:视网膜血管
			var swmxg = result[0].swmxg;
			if(result[0].swmxgms != "" && typeof(result[0].swmxgms) != "undefined" && result[0].swmxgms != null) {
				swmxg = swmxg += "/" + result[0].swmxgms;
			}
			$("#swmxg").html(swmxg);

			//外耳:耳廓
			var ek = result[0].ek;
			if(result[0].ekms != "" && typeof(result[0].ekms) != "undefined" && result[0].ekms != null) {
				ek = ek += "/" + result[0].ekms;
			}
			$("#ek").html(ek);

			//外耳:外耳道
			var wed = result[0].wed;
			if(result[0].wedms != "" && typeof(result[0].wedms) != "undefined" && result[0].wedms != null) {
				wed = wed += "/" + result[0].wedms;
			}
			$("#wed").html(wed);
			//听力
			$("#ztl").html(result[0].ztl);
			$("#ytl").html(result[0].ytl);
			//鼻子
			var bwx = result[0].bwx; //外形	
			if(result[0].bwxms != "" && typeof(result[0].bwxms) != "undefined" && result[0].bwxms != null) {
				bwx = bwx += "/" + result[0].bwxms;
			}
			$("#bwx").html(bwx);
			//鼻中隔 bzg
			var bzg = result[0].bzg; //外形	
			if(result[0].bzgms != "" && typeof(result[0].bzgms) != "undefined" && result[0].bzgms != null) {
				bzg = bzg += "/" + result[0].bzgms;
			}
			$("#bzg").html(bzg);
			//鼻腔粘膜bqnm
			var bqnm = result[0].bqnm; //外形	
			if(result[0].bqnmms != "" && typeof(result[0].bqnmms) != "undefined" && result[0].bqnmms != null) {
				bqnm = bqnm += "/" + result[0].bqnmms;
			}
			$("#bqnm").html(bqnm);
			//鼻窦区压痛bdqyt
			var bdqyt = result[0].bdqyt; //外形	
			if(result[0].bdqytms != "" && typeof(result[0].bdqytms) != "undefined" && result[0].bdqytms != null) {
				bdqyt = bdqyt += "/" + result[0].bdqytms;
			}
			$("#bdqyt").html(bdqyt);

			//口腔
			$("#kc").html(result[0].kc); //口唇
			$("#cl").html(result[0].cl); //齿列
			$("#kqyb").html(result[0].kqyb); //咽部

			//咽喉
			var yhyb = result[0].yhyb; //咽部
			if(result[0].yhybms != "" && typeof(result[0].yhybms) != "undefined" && result[0].yhybms != null) {
				yhyb = yhyb += "/" + result[0].yhybms;
			}
			$("#yhyb").html(yhyb);

			//扁桃体
			var btt = result[0].btt; //扁桃体
			if(result[0].bttms != "" && typeof(result[0].bttms) != "undefined" && result[0].bttms != null) {
				btt = btt += "/" + result[0].bttms;
			}
			$("#btt").html(btt);

			//口咽部
			var kyb = result[0].kyb; //扁桃体
			if(result[0].kybms != "" && typeof(result[0].kybms) != "undefined" && result[0].kybms != null) {
				kyb = kyb += "/" + result[0].kybms;
			}
			$("#kyb").html(kyb);
			$("#wgkysqz").html(result[0].ysqz);

		},
	});
}

//内科
function getNkData(param) {
	if(typeof(param) == "undefined") {
		param = {};
	}
	var result;
	$.ajax({
		type: "get",
		url: BaseUrl + 'InquiryNk/list/1/' + +pageSize,
		data: param,
		async: true,
		success: function(data) {
			result = data.queryResult.list;
			//胸廓
			var xk = "";
			if(result[0].xk != "" && typeof(result[0].xk) != "undefined") {
				xk = result[0].xk;
				if(xk == "1") {
					xk = "正常";
				}
				if(xk == "2") {
					xk = "扁平胸";
				}
				if(xk == "3") {
					xk = "桶状胸";
				}
			}
			if(result[0].xkqt != "" && typeof(result[0].xkqt) != "undefined" && result[0].xkqt != null) {
				xk = result[0].xkqt;
			}
			$("#xk").html(xk);

			//心脏
			//心率
			var xlnumber = "";
			if(result[0].xlnumber != "" && typeof(result[0].xlnumber) != "undefined" && result[0].xlnumber != null) {
				xlnumber = result[0].xlnumber + "次/分钟";
			}
			$("#xlnumber").html(xlnumber);

			//心律
			var xlqk = "";
			if(result[0].xlqk != "" && typeof(result[0].xlqk) != "undefined") {
				xlqk = result[0].xlqk;
				if(xlqk == "1") {
					xlqk = "齐";
				}
				if(xlqk == "2") {
					xlqk = "不齐";
				}
				if(xlqk == "3") {
					xlqk = "绝对不齐";
				}
			}
			$("#xlqk").html(xlqk);

			//低压
			$("#dy").html(result[0].dy);
			//高压
			$("#gy").html(result[0].gy);

			$("#ybzkysqz").html(result[0].ysqz); //一般状况医师签字

			//杂音zy
			var zy = "";
			if(result[0].zy != "" && typeof(result[0].zy) != "undefined") {
				zy = result[0].zy;
				if(zy == "1") {
					zy = "有";
				}
				if(zy == "2") {
					zy = "无";
				}
			}

			if(result[0].zyms != "" && typeof(result[0].zyms) != "undefined" && result[0].zyms != null) {
				zy = result[0].zyms;
			}
			$("#zy").html(zy);

			//肺部
			var hxy = ""; //呼吸音
			if(result[0].hxy != "" && typeof(result[0].hxy) != "undefined" && result[0].hxy != null) {
				hxy = result[0].hxy;
				if(hxy == "1") {
					hxy = "正常";
				}
				if(hxy == "2") {
					hxy = "异常";
				}
			}
			if(result[0].hxyms != "" && typeof(result[0].hxyms) != "undefined" && result[0].hxyms != null) {
				hxy = result[0].hxyms;
			}
			$("#hxy").html(hxy);
			var ly = ""; //啰音
			if(result[0].ly != "" && typeof(result[0].ly) != "undefined" && result[0].ly != null) {
				ly = result[0].ly;
				if(ly == "1") {
					ly = "无";
				}
				if(ly == "2") {
					ly = "干啰音";
				}
				if(ly == "3") {
					ly = "湿罗音";
				}
			}
			if(result[0].lyms != "" && typeof(result[0].lyms) != "undefined" && result[0].lyms != null) {
				ly = result[0].lyms;
			}
			$("#ly").html(ly);

			//腹部
			var yt = ""; //压痛
			if(result[0].yt != "" && typeof(result[0].yt) != "undefined" && result[0].yt != null) {
				yt = result[0].yt;
				if(yt == "1") {
					yt = "无";
				}
				if(yt == "2") {
					yt = "有";
				}

			}
			if(result[0].ytms != "" && typeof(result[0].ytms) != "undefined" && result[0].ytms != null) {
				yt = result[0].ytms;
			}
			$("#yt").html(yt);

			var bk = ""; //包块
			if(result[0].bk != "" && typeof(result[0].bk) != "undefined" && result[0].bk != null) {
				bk = result[0].bk;
				if(bk == "1") {
					bk = "无";
				}
				if(bk == "2") {
					bk = "有";
				}

			}
			if(result[0].bkms != "" && typeof(result[0].bkms) != "undefined" && result[0].bkms != null) {
				bk = result[0].bkms;
			}
			$("#bk").html(bk);

			var gd = ""; //肝大
			if(result[0].gd != "" && typeof(result[0].gd) != "undefined" && result[0].gd != null) {
				gd = result[0].gd;
				if(gd == "1") {
					gd = "无";
				}
				if(gd == "2") {
					gd = "有";
				}

			}
			if(result[0].gdms != "" && typeof(result[0].gdms) != "undefined" && result[0].gdms != null) {
				gd = result[0].gdms;
			}
			$("#gd").html(gd);

			var pd = ""; //脾大
			if(result[0].pd != "" && typeof(result[0].pd) != "undefined" && result[0].pd != null) {
				pd = result[0].pd;
				if(pd == "1") {
					pd = "无";
				}
				if(pd == "2") {
					pd = "有";
				}

			}
			if(result[0].pdms != "" && typeof(result[0].pdms) != "undefined" && result[0].pdms != null) {
				pd = result[0].pdms;
			}
			$("#pd").html(pd);

			var ydxzy = ""; //移动性浊音
			if(result[0].ydxzy != "" && typeof(result[0].ydxzy) != "undefined" && result[0].ydxzy != null) {
				ydxzy = result[0].ydxzy;
				if(ydxzy == "1") {
					ydxzy = "无";
				}
				if(ydxzy == "2") {
					ydxzy = "有";
				}

			}
			if(result[0].ydxzyms != "" && typeof(result[0].ydxzyms) != "undefined" && result[0].ydxzyms != null) {
				ydxzy = result[0].ydxzyms;
			}
			$("#ydxzy").html(ydxzy);

			$("#nkqt").html(result[0].qt); //内科其他
			$("#nkysqz").html(result[0].ysqz); //内科医师签字
		},
	});
}

function getSjkData(param) {
	if(typeof(param) == "undefined") {
		param = {};
	}
	var result;
	$.ajax({
		type: "get",
		url: BaseUrl + 'InquirySjk/list/1/' + +pageSize,
		data: param,
		async: true,
		success: function(data) {
			result = data.queryResult.list;
			//肌萎缩
			var jws = "";
			if(result[0].jws != "" && typeof(result[0].jws) != "undefined" && result[0].jws != null) {
				jws = result[0].jws;
			}
			if(result[0].jwsms != "" && typeof(result[0].jwsms) != "undefined" && result[0].jwsms != null) {
				jws = result[0].jwsms;
			}
			$("#jws").html(jws);
			//肌力
			var jl = "";
			if(result[0].jl != "" && typeof(result[0].jl) != "undefined" && result[0].jl != null) {
				jl = result[0].jws;
			}
			if(result[0].jlms != "" && typeof(result[0].jlms) != "undefined" && result[0].jlms != null) {
				jl = result[0].jwsms;
			}
			$("#jl").html(jl);

			//肌张力
			var jzl = "";
			if(result[0].jzl != "" && typeof(result[0].jzl) != "undefined" && result[0].jzl != null) {
				jzl = result[0].jzl;
			}

			if(result[0].jzlms != "" && typeof(result[0].jzlms) != "undefined" && result[0].jzlms != null) {
				jzl = result[0].jzlms;
			}
			$("#jzl").html(jzl);
			//三颤
			var sc = "";
			if(result[0].sc != "" && typeof(result[0].sc) != "undefined" && result[0].sc != null) {
				sc = result[0].sc;
			}
			if(result[0].scms != "" && typeof(result[0].scms) != "undefined" && result[0].scms != null) {
				sc = result[0].scms;
			}
			$("#sc").html(sc);

			//共济运动
			var gjyd = "";
			if(result[0].gjyd != "" && typeof(result[0].gjyd) != "undefined" && result[0].gjyd != null) {
				gjyd = result[0].gjyd;
			}
			if(result[0].gjydms != "" && typeof(result[0].gjydms) != "undefined" && result[0].gjydms != null) {
				gjyd = result[0].gjydms;
			}
			$("#gjyd").html(gjyd);
			//浅感觉痛觉
			var tj = "";
			if(result[0].tj != "" && typeof(result[0].tj) != "undefined" && result[0].tj != null) {
				tj = result[0].tj;
			}
			if(result[0].tjms != "" && typeof(result[0].tjms) != "undefined" && result[0].tjms != null) {
				tj = result[0].tjms;
			}
			$("#tj").html(tj);

			//触觉
			var cj = "";
			if(result[0].cj != "" && typeof(result[0].cj) != "undefined" && result[0].cj != null) {
				cj = result[0].cj;
			}
			if(result[0].cjms != "" && typeof(result[0].cjms) != "undefined" && result[0].cjms != null) {
				cj = result[0].cjms;
			}
			$("#cj").html(cj);

			//深感觉
			var sgj = "";
			if(result[0].sgj != "" && typeof(result[0].sgj) != "undefined" && result[0].sgj != null) {
				sgj = result[0].sgj;
			}
			if(result[0].sgjms != "" && typeof(result[0].sgjms) != "undefined" && result[0].sgjms != null) {
				sgj = result[0].sgjms;
			}
			$("#sgj").html(sgj);
			//腱反射
			$("#jfs").html(result[0].jfs);
			//病理反射
			$("#blfs").html(result[0].blfs);
			$("#sjkysqz").html(result[0].ysqz);
			//自主神经
			var zzsj = "";
			if(result[0].zzsj != "" && typeof(result[0].zzsj) != "undefined" && result[0].zzsj != null) {
				zzsj = result[0].zzsj;
			}
			if(result[0].zzsjms != "" && typeof(result[0].zzsjms) != "undefined" && result[0].zzsjms != null) {
				zzsj = result[0].zzsjms;
			}
			$("#zzsj").html(zzsj);
		},
	});
}

function getWkData(param) {
	if(typeof(param) == "undefined") {
		param = {};
	}
	var result;
	$.ajax({
		type: "get",
		url: BaseUrl + 'InquiryWk/list/1/' + +pageSize,
		data: param,
		async: true,
		success: function(data) {
			result = data.queryResult.list;
			//甲状腺
			var jzx = "";
			if(result[0].jzx != "" && typeof(result[0].jzx) != "undefined") {
				jzx = result[0].jzx;
				if(jzx == "1") {
					jzx = "正常";
				}
				if(jzx == "2") {
					jzx = "肿大";
				}
			}

			if(result[0].jzxms != "" && typeof(result[0].jzxms) != "undefined" && result[0].jzxms != null) {
				jzx = result[0].jzxms;
			}

			$("#jzx").html(jzx);

			var qblbj = "";
			if(result[0].qblbj != "" && typeof(result[0].qblbj) != "undefined") {
				qblbj = result[0].qblbj;
				if(qblbj == "1") {
					qblbj = "未触及";
				}
				if(qblbj == "2") {
					qblbj = "锁骨上";
				}
				if(qblbj == "3") {
					qblbj = "腋窝";
				}
			}
			if(result[0].qblbjms != "" && typeof(result[0].qblbjms) != "undefined" && result[0].qblbjms != null) {
				qblbj = result[0].qblbjms;
			}
			$("#qblbj").html(qblbj);
			$("#qt").html(result[0].qt);
			$("#wkysqz").html(result[0].ysqz);
		},
	});
}

function getHyData(param) {
	if(typeof(param) == "undefined") {
		param = {};
	}
	var result;
	$.ajax({
		type: "get",
		url: BaseUrl + 'healthLabtest/selectList',
		data: param,
		async: true,
		success: function(data) {

			result = data.queryResult.list;
			if(result.length > 0) {
				$('#granperc').html(result[0].granperc);
				$('#wbc').html(result[0].wbc);
				$('#lymphperc').html(result[0].lymphperc);
				$('#rbc').html(result[0].rbc);
				$('#hgb').html(result[0].hgb);
				$('#plt').html(result[0].plt);
				$('#npro').html(result[0].npro);
				$('#nglu').html(result[0].nglu);
				$('#nsg').html(result[0].nsg);
				$('#rbc').html(result[0].rbc);
				$('#nleu').html(result[0].nleu);
				$('#alt').html(result[0].alt);
				$('#ggt').html(result[0].ggt);
				$('#alb').html(result[0].alb);
				$('#tbil').html(result[0].tbil);
				$('#urea').html(result[0].urea);
				$('#crea').html(result[0].crea);
				$('#glo').html(result[0].glo);
				$('#glu').html(result[0].glu);
				$('#xbxp').html(result[0].xbxp);
				$('#xdt').html(result[0].xdt);
				$('#bc').html(result[0].bc);
			}

		},
	});
}

function getZybData(param) {
	if(typeof(param) == "undefined") {
		param = {};
	}
	var result;
	$.ajax({
		type: "get",
		url: BaseUrl + 'grjbxxZyb/select',
		data: param,
		async: true,
		success: function(data) {
			if(data.success) {
				result = data.queryResult.list;
				if(result.length > 0) {
					//职业病名称
					var illnessname = ""; //职业病名称
					var diagnosisdata = ""; //诊断时间
					var diagnosiscompany = ""; //单位
					var sfrecovery = ""; //是否痊愈
					for(var i = 0; i < result.length; i++) {
						illnessname += result[i].illnessname + ",";
					}
					illnessname = illnessname.substring(0, illnessname.length - 1);
					$('#illnessname').html(illnessname);
					for(var i = 0; i < result.length; i++) {
						diagnosisdata += result[i].diagnosisdata + ",";
					}
					diagnosisdata = diagnosisdata.substring(0, diagnosisdata.length - 1);
					$('#diagnosisdata').html(diagnosisdata);

					for(var i = 0; i < result.length; i++) {
						diagnosiscompany += result[i].diagnosiscompany + ",";
					}
					diagnosiscompany = diagnosiscompany.substring(0, diagnosiscompany.length - 1);
					$('#diagnosiscompany').html(diagnosiscompany);

					for(var i = 0; i < result.length; i++) {

						if(result[i].sfrecovery == '1') {
							result[i].sfrecovery = "已痊愈"
						}

						if(result[i].sfrecovery == '2') {
							result[i].sfrecovery = "未痊愈"
						}
						sfrecovery += result[i].sfrecovery + ",";
					}
					sfrecovery = sfrecovery.substring(0, sfrecovery.length - 1);
					$('#sfrecovery').html(sfrecovery);
				}

			} else {
				layer.msg(data.message);
			}

		}
	});
}