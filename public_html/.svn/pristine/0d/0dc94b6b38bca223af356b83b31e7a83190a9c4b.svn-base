layui.use(['layer', 'form','element','carousel'], function(){
  var layer = layui.layer
  ,form = layui.form;
   var element = layui.element;
    var carousel = layui.carousel;
    carousel.render({
    elem: '#test1'
    ,width: '100%' //设置容器宽度
    ,arrow: 'always' //始终显示箭头
    //,anim: 'updown' //切换动画方式
  });
  
  $('#welcomeNav').on('click','.info',function(){
  	var type=$(this).attr('data-type');
  	window.location.href="index.html?urlType="+type;
  })
});