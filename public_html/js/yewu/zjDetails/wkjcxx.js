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
		
		 //初次请求第一页的数据，并初始化分页,如果条件查询，请传入data
        function getData(data) {
            if(typeof(data)=="undefined"){
                data={};
            }
            var urls = 'InquiryWk/list/1/1';
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
				var jzx = result[0].jzx;
				$('input[name="jzx"]').each(function() {
					if($(this).val() == jzx) {
						$(this).prop('checked', true);
					}
				});
				var qblbj = result[0].qblbj;
				$('input[name="qblbj"]').each(function() {
					if($(this).val() == qblbj) {
						$(this).prop('checked', true);
					}
				});
				$('input[name="id"]').val(result[0].id);
				$('input[name="jzxms"]').val(result[0].jzxms);
				$('input[name="jxzbz"]').val(result[0].jxzbz);
				$('input[name="qblbjms"]').val(result[0].qblbjms);
				$('input[name="qblbjbz"]').val(result[0].qblbjbz);
				$('input[name="qt"]').val(result[0].qt);
				$('input[name="ysqz"]').val(result[0].ysqz);
				$('input[name="res1"]').val(result[0].res1);
                form.render(); //更新渲染
            }

            getnull();
        }
	});
})