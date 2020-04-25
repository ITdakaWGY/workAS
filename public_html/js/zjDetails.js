$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		laydate.render({
			elem: '#starttime' //指定元素
		});
		var id = window.location.href.split('?')[1].split('&')[1].split('=')[1];
		var tmh =window.location.href.split('?')[1].split('&')[0].split('=')[1];
		$('.dt').each(function() {
			var dataHref = $(this).attr('data-href');
			dataHref = dataHref + '?tmh='+tmh+'&id='+id;
			$(this).attr('data-href', dataHref);
		})
		$('.xqIframe').attr('src',$('.xqIframe').attr('src')+ '?tmh='+tmh+'&id='+id);

		/*$(".leftsidebar_box dt").css({
			"background-color": "#3992d0"
		});*/
		$(".leftsidebar_box dt img").attr("src", "../../images/select_xl01.png");
		$(".leftsidebar_box dd").hide();
		$(".leftsidebar_box dt").click(function() {
			/*$(".leftsidebar_box dt").css({
				"background-color": "#3992d0"
			})
			$(this).css({
				"background-color": "#317eb4"
			});*/
			$(this).parent().find('dd').removeClass("menu_chioce");
			$(".leftsidebar_box dt img").attr("src", "../../images/select_xl01.png");
			$(this).parent().find('img').attr("src", "../../images/select_xl.png");
			$(".menu_chioce").slideUp();
			$(this).parent().find('dd').slideToggle();
			$(this).parent().find('dd').addClass("menu_chioce");
		});
		
		$('.dt,.dm').click(function(){
			$('.dt,.dm').removeClass('actives')
			$(this).addClass('actives');
			var href=$(this).attr('data-href');
			$('.xqIframe').attr('src',href);
		})

	});
})