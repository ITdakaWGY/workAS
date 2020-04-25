$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;

        var sfz = window.location.href.split('?')[1].split('&')[0].split('=')[1];
		var data = {
			sfz: sfz,
		};

		getData(data);


		//初次请求第一页的数据，并初始化分页,如果条件查询，请传入data
function getData(data) {
	debugger
	if(typeof(data)=="undefined"){
		data={};
	}
	var urls = 'progress/tjxm/2/' + data.sfz;
	var loading = layer.msg('正在查询...', {
		icon: 16,
		shade: 0.2,
		time: false
	});
    $.ajax({
        type: "get",
        url: BaseUrl + urls,
        data: data,
        async: true,
        success: function(data) {
            setdata(data);
            layer.close(loading);
        },
        error: function(data) {
            layer.msg('程序错误!');
            layer.close(loading);
        }

    });
}

//接受结果集，往表格进行数据填充
        function setdata(data) {
            if(data.length == 0) {
                var nodata = '<td>无</td>';
                $('#tbody').html(nodata);
                return false;
            }
            var html = '';

            html += `<td>`+data+`</td>`;

            $('#tbody').html(html);
            getnull();
        }
    });

});
