$(document).ready(function() { /* 文档加载，执行一个函数*/
	$('#FormVerification').bootstrapValidator({
		live: 'enabled', //验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: { /*验证*/
			"iteamName": { /*键名username和input name值对应*/
				validators: {
					notEmpty: { /*非空提示*/
						message: '参数不能为空'
					},
				}
			},
			"price": { /*键名username和input name值对应*/
				validators: {
					notEmpty: { /*非空提示*/
						message: '不能为空'
					},
					digits: {
						message: '只能输入数字'
					},
				}
			},

		}
	})
});