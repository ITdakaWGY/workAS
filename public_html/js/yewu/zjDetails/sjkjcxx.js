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
		
		function getCode(params){
			var urls = 'InquirySjk/list/1/' + pageSize;
			$.ajax({
				type: "get",
				url: BaseUrl + urls,
				async: true,
				data: params,
				success:function(data){
					if(data.success) {
						debugger;
						var list = data.queryResult.list;
						if(list.length > 0) {
							var data = list[0];
							$('#personalid').val(data.personalid);
			            	$('#xm').val(data.xm);
			            	$('#tmh').val(data.tmh);
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
						}else{
							layer.msg('暂未查询到相关数据');
						}
					}else{
						layer.msg(data.message);
					}
				}
			});
			
		}
	});
})