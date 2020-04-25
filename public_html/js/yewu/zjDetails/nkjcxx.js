$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		
		var tmh = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var params = {
				tmh: tmh,
		}
		getData(params);	
		
		  function getData(data) {
            if(typeof(data)=="undefined"){
                data={};
            }
            var urls = 'InquiryNk/list/1/1';
            var loading = layer.msg('正在查询...', {
                icon: 16,
                shade: 0.2,
                time: false
            });
            var total;
            var result;
            $.ajax({
                type: "get",
                url: BaseUrl + urls,
                data: data,
                async: true,
                dataType: "json"   ,//请求页面返回的数据类型
                success: function(data) {
                    if(data.success) {
                        result = data.queryResult.list;
                        total = data.queryResult.total;
                        setdata(result);
                    } else {
                        layer.msg(data.message);
                    }
                    layer.close(loading);
                },
                error: function(data) {
                    layer.msg(data.message);
                    layer.close(loading);
                }

            });
        }
		  
		  
		  
		  //接受结果集，往表格进行数据填充
        function setdata(result) {
            if(result.length == 0) {
                var nodata = '<div class="nodata">暂无数据!</div>';
                $('#tbody').html(nodata);
                return false;
            }else {
            	$('#personalid').val(result[0].personalid);
            	$('#xm').val(result[0].xm);
            	$('#tmh').val(result[0].tmh);
            	$('#res1').val(result[0].res1);
            	
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
            }

            getnull();
        }
       
		
	});
})