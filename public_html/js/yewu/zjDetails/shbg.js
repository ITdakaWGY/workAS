$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		$(':text').each(function(){
			$(this).attr('readonly',true);
			$(this).addClass('bg-hui');
		})
	});
})