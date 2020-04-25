	//验证插件测试-----------
	 	  $(document).ready(function() {
	 		
	 	    $('#regular').bootstrapValidator({
	 	    	live: 'enabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
	 	        message: 'This value is not valid',
	 	        feedbackIcons: {
	 	            valid: 'glyphicon glyphicon-ok',
	 	            invalid: 'glyphicon glyphicon-remove',
	 	            validating: 'glyphicon glyphicon-refresh'
	 	        },
	 	        //group: '.form-group',
	 	        fields: {/*验证*/
	 	       		   "sfz" : {
	 	                    validators : {
	 	                        notEmpty : {
	 	                            message : '身份证号码不能为空！'
	 	                        },
	 	                        regexp: {
	 	                            regexp: /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/,
	 	                            message: '身份证号码格式不正确，为15位和18位身份证号码！'
	 	                        },
	 	                        callback: {//自定义，可以在这里与其他输入项联动校验
	 	                            message: '身份证号码无效！',
	 	                            callback:function(value, validator,$field){
	 	                                //15位和18位身份证号码的正则表达式
	 	                                var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
	 	                                //如果通过该验证，说明身份证格式正确，但准确性还需计算
	 	                                var idCard = value;
	 	                                if (regIdCard.test(idCard)) {
	 	                                    if (idCard.length == 18) {
	 	                                        var idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); //将前17位加权因子保存在数组里
	 	                                        var idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
	 	                                        var idCardWiSum = 0; //用来保存前17位各自乖以加权因子后的总和
	 	                                        for (var i = 0; i < 17; i++) {
	 	                                            idCardWiSum += idCard.substring(i, i + 1) * idCardWi[i];
	 	                                        }
	 	                                        var idCardMod = idCardWiSum % 11;//计算出校验码所在数组的位置
	 	                                        var idCardLast = idCard.substring(17);//得到最后一位身份证号码
	 	                                        //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
	 	                                        if (idCardMod == 2) {
	 	                                            if (idCardLast == "X" || idCardLast == "x") {
	 	                                                return true;
	 	                                                //alert("恭喜通过验证啦！");
	 	                                            } else {
	 	                                                return false;
	 	                                                //alert("身份证号码错误！");
	 	                                            }
	 	                                        } else {
	 	                                            //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
	 	                                            if (idCardLast == idCardY[idCardMod]) {
	 	                                                //alert("恭喜通过验证啦！");
	 	                                                return true;
	 	                                            } else {
	 	                                                return false;
	 	                                                //alert("身份证号码错误！");
	 	                                            }
	 	                                        }
	 	                                    }
	 	                                } else {
	 	                                    //alert("身份证格式不正确!");
	 	                                    return false;
	 	                                }
	 	                            }
	 	                        }
	 	                    }
	 	                },
	 	        	"jhgl": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },
	 	            "gl": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },
	 	            "yzbm": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]\d{5}(?!\d)$/,
	 	        					message: '邮政编码格式不正确！！'
	 	        				}
	 	                    }
	 	                },    
	 	            "brdh": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^((((1[0-9]{1}[0-9]{1}))+\d{8})|^\s*|[0][1-9]{2,3}-[0-9]{5,10}|[1-9]{1}[0-9]{5,8})$/,
	 	        					message: '电话格式不正确！！'
	 	        				}
	 	                    }
	 	                },
	 	            "cc": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },
	 	            "jq": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },
	 	            "zq": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },
	 	            "tjnl": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },
	 	            "znnumber": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },   
	 	             "lcnumber": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },
					"zcnumber": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },	 	           
	 	        	"scnumber": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },
	 	            "yctcnumber": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },    
	 	            "xtjxnumber": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },
	 				"rxyl": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },	 
	 				"ksxynl": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },	 
	 	            "jynl": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },    
	 	            "ryjl": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },
	 	            "jjnl": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },
	 	            "ksyjnl": {/*键名和input name值对应*/
	 	                    message: '',
	 	                    validators: {
	 	        				regexp: {
	 	        					regexp: /^[0-9]*$/,
	 	        					message: '只能为数字！！'
	 	        				}
	 	                    }
	 	                },
	 	        
	 	        }
	 	    });
	 	 
	 	});