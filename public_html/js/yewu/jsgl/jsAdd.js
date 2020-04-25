$(function() {
	//一般直接写在一个js文件中
	layui.use(['layer', 'form', 'laydate'], function() {
		var layer = layui.layer,
			form = layui.form;
		var laydate = layui.laydate;
		
		 
		var frameId = window.location.href.split('?')[1].split('=')[1];
		
			var num = "";   //定义用户编号
			for (var i = 0; i < 4; i++) //4位随机数，用以加在时间戳后面。
			{
			    num += Math.floor(Math.random() * 10);
			}
			num = new Date().getTime() + num;  //时间戳，用来生成用户编号。
			$('#rolecode').val(num);
		
		/*新增保存*/
		$(".saveTijian").click(function(){
		//角色名称
		var  rolename =	$("#rolename").val();
		//角色编码
		var rolecode = $("#rolecode").val();
		//角色状态
		var rolestate = $("#rolestate").val();
		//角色说明
		var roleexplain = $("#roleexplain").val();
		
		var params = {
			rolename: rolename,
			rolecode: rolecode,
			rolestate: rolestate,
			roleexplain: roleexplain,
		}
			
			$.ajax({
				type:"post",
				url:BaseUrl+"role/add",
				async:true,
				data: JSON.stringify(params),
				processData: false,
				contentType: "application/json",
				success:function(data){
					if(data.success) {
						layer.alert('保存成功', {
							skin: 'layui-layer-lan',
							closeBtn: 0,
							anim: 4 //动画类型
						}, function(tc, layero) {
							parent.layer.closeAll();
						var dom=$(top.window.document.getElementsByName(frameId)[0].contentWindow.document.getElementById('intSearch'));
     							 var newpage=parseInt(dom.attr('data-page'));
     							 top.window.document.getElementsByName(frameId)[0].contentWindow.getPageData(newpage);
						});
					} else {
						layer.msg(data.message);
					}
					
				}
			});//ajax end
			
			
			
			
		})//新增保存 end
		
		
		
		
		
		
		
		
		
		
		
	});//layui end
})//就绪 end



