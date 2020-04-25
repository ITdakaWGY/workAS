$(document).ready(function() {
	$('#nkwz').bootstrapValidator({
		live: 'enabled', //验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
		message: 'This value is not valid',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		//group: '.form-group',
		fields: { /*验证*/
			"xlnumber": { /*键名和input name值对应*/
				message: 'The username is not valid',
				validators: {
					digits: { /*非空提示*/
						message: '只能输入数字'
					}
				
				}
			}
			

		}

	});

});