$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		$(':text').each(function(){
			$(this).attr('readonly',true);
			$(this).addClass('bg-hui');
		})
		
		var tmh = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var params = {
				tmh: tmh,
		}
		getCode(params);	
		
		
	function getCode(param){
		var urls = 'symptominfo/list/1/' + pageSize;
					$.ajax({
						type: "get",
						url: BaseUrl + urls,
						async: true,
						data: params,
						success: function(data) {
							if(data.success) {
								var result = data.queryResult.list;
								if(result.length > 0) {
									$('#personalid').val(result[0].personalid);
									$('#tmh').val(result[0].tmh);
									$('#name').val(result[0].name);
									$('#sfz').val(result[0].sfz);
									$('#tjrq').val(result[0].tjrq);
									$('#res1').val(result[0].res1);
									
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
									zhikong('layui-table');
									form.render();
								}
							} else {
								layer.msg(data.message);
							}
						}
					});
	}
	});
})

