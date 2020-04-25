$('#FormVerification').bootstrapValidator({
	live: 'enabled', //验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
	feedbackIcons: {
		valid: 'glyphicon glyphicon-ok',
		invalid: 'glyphicon glyphicon-remove',
		validating: 'glyphicon glyphicon-refresh'
	},
	fields: {
		"menuname": {
			validators: {
				notEmpty: { /*非空提示*/
					message: '参数不能为空'
				},
			}
		},
	}
})