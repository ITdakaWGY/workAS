function readIDCard() {
	
	alert("读取身份证信息");
	var CertCtl = document.getElementById("CertCtl");
	var result = CertCtl.readCert();
	var resultObj = toJson(result);
	//resultObj.resultFlag == 0时代表读取身份证信息成功
	if(resultObj.resultFlag == 0) {
		var certNumber = resultObj.resultContent.certNumber; //身份证号
		var pName = resultObj.resultContent.partyName; //姓名 
		var pGender = resultObj.resultContent.gender; //性别
		var pNation = resultObj.resultContent.nation; //民族
		var pBirthdate = resultObj.resultContent.bornDay; //出生日期
		var pIdAddress = resultObj.resultContent.certAddress; //地址
		var pIdNumber = resultObj.resultContent.certNumber; //身份证号
		var pPolice = resultObj.resultContent.certOrg; //所属公安局
		var pIdValidityStartdate = resultObj.resultContent.effDate; //身份证有效期（起） 
		var pIdValidityEnddate = resultObj.resultContent.expDate; //身份证有效期（止）
		var pPhotoBuffer = resultObj.resultContent.identityPic; //照片编码（data:image/jpeg;base64,）
		var pPhotoPath = "data:image/jpeg;base64," + resultObj.resultContent.identityPic; //照片本地路径
	} else {
		alert("读卡器读取身份证信息失败");
	}
}

function toJson(str) {
	return eval('(' + str + ')');
}


