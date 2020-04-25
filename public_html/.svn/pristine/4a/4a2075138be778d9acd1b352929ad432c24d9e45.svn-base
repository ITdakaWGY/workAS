$(document).ready(function() {/* 文档加载，执行一个函数*/
 $('#FormVerification').bootstrapValidator({ 
 	live: 'enabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
	feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {/*验证*/
     "orgname": {/*键名username和input name值对应*/
      validators: { 
       notEmpty: {/*非空提示*/
        message: '参数不能为空'
       	}, 
    	}
     },
     "phone": {
		validators: {
        regexp: {
            regexp: /^((((1[0-9]{1}[0-9]{1}))+\d{8})|^\s*|[0][1-9]{2,3}-[0-9]{5,10}|[1-9]{1}[0-9]{5,8})$/,
            message: '请输入正确的手机号码或座机!'
        },
	}
	},
	"address": {/*键名username和input name值对应*/
      validators: { 
       notEmpty: {/*非空提示*/
        message: '不能为空'
       	}, 
    	}
     },
     
    }
  }) 
 });