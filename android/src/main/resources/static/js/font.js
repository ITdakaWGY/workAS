//750是设计尺寸，根据需求来。
(function(doc,win){
	var docEl = doc.documentElement,resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
		recalc = function(){
			var clientWidth = docEl.clientWidth;
			if(!clientWidth) return;
			if(clientWidth>800){
				clientWidth=800;
				docEl.style.fontSize = '100px'; //1rem  = 100px
			}
			else{
				docEl.style.fontSize = 100 * (clientWidth / 800) + 'px';
			}
		};
		if (!doc.addEventListener) return;
        win.addEventListener(resizeEvt, recalc, false);
        doc.addEventListener('DOMContentLoaded', recalc, false);
})(document,window);

