var currentFocusId = "posDiv_1_0";
var posList;//全部的推荐位数据
/**
 * 加载推荐位数据
 */
function loadPositionData(){
	var Ajax;
	if (window.XMLHttpRequest) { //非IE
		Ajax = new XMLHttpRequest();
	} else if (window.ActiveXObject) { //IE
		Ajax = new ActiveXObject("Microsoft.XMLHTTP");
	}
	Ajax.open("get", "../html/recommend.json", false);
	Ajax.send()
	if (Ajax.readyState == 4) {
		if (Ajax.status >= 200 && Ajax.status < 300 || Ajax.status == 304) {
			if(Ajax.responseText!=null&&Ajax.responseText!=""&&Ajax.responseText!="null"&&Ajax.responseText!="undefined"){
				var result = eval('(' + Ajax.responseText + ')');
				if(result.positionList!=null&&result.positionList.length>0){
					posList = result.positionList.sort(compare('itemSize'));//按itemSize排序
					convertData(posList);//转数据格式(给vod类型的推荐位增加vodJson属性)
					generaHtml(posList);//生成html页面
					switchFcs(currentFocusId);//初始化焦点
				}
			}
		} else {
			console.log(Ajax.status)
		}
	}
}

/**
 * 排序
 * @param property
 * @returns {Function}
 */
function compare(property){
    return function(a,b){
        var value1 = a[property];
        var value2 = b[property];
        return value1 - value2;
    }
}

/**
 * 做数据转换
 * @param posList
 */
function convertData(posList){
	for(var i=0;i<posList.length;i++){
		var posItem = posList[i];
		if(posItem.objType=="vod"){
			var vodJson = '{';
			vodJson += '"status":"200",';
			vodJson += '"vod":{';
			vodJson += '"code":"'+posItem.objValue+'",';
			vodJson += '"title":"'+posItem.title+'",';
			vodJson += '"externalCode":"'+posItem.objValue+'"';
			vodJson += '},';
			vodJson += '"mediaList":'+JSON.stringify(posItem.mediaList);
			vodJson += '}';
			posList[i].vodJson = vodJson;
		}else{
			posList[i].vodJson = "";
		}
	}
}


/**
 * 生成html页面
 */
function generaHtml(posList){
	var indexHtml = "";
	for(var i=0;i<posList.length;i++){
		var tmpobj = posList[i];
		var posImg = tmpobj.icon;
		if(posImg==null||posImg==""){
			posImg = tmpobj.icon1;
		}
		if(posImg==null||posImg==""){
			posImg = tmpobj.icon2;
		}
		if(posImg==null||posImg==""){
			posImg = tmpobj.icon3;
		}
		if(i<=2){
			indexHtml += "<div id=\"posDiv_1_"+i+"\" style=\"position:absolute;left:"+(70+i*(364+24))+"px;top:112px;width:364px;height:204px;\">";
			indexHtml += "<img id=\"posImg_1_"+i+"\" style=\"position:absolute;border-radius:8px;\" width=\"364px\" height=\"204px\" src=\"../html"+posImg+"\"/>";//图片
			indexHtml += "</div>";
		}else if(i>2&&i<=6){
			indexHtml += "<div id=\"posDiv_2_"+(i-3)+"\" style=\"position:absolute;left:"+(70+(i-3)*(266+26))+"px;top:340px;width:266px;height:148px;\">";
			indexHtml += "<img id=\"posImg_2_"+(i-3)+"\" style=\"position:absolute;border-radius:8px;\" width=\"266px\" height=\"148px\" src=\"../html"+posImg+"\"/>";//图片
			indexHtml += "</div>";
		}else if(i>6&&i<=10){
			indexHtml += "<div id=\"posDiv_3_"+(i-7)+"\" style=\"position:absolute;left:"+(70+(i-7)*(266+26))+"px;top:512px;width:266px;height:148px;\">";
			indexHtml += "<img id=\"posImg_3_"+(i-7)+"\" style=\"position:absolute;border-radius:8px;\" width=\"266px\" height=\"148px\" src=\"../html"+posImg+"\"/>";//图片
			indexHtml += "</div>";
		}else{}
	}
	document.getElementById("mainHtml").innerHTML = indexHtml;
}


document.onkeypress = function(ev){
	 keycode = ev.which;
	switch (keycode) {
		case 13:
			onclickOk();
			return false;
		case 37:
		case 108:
			var leftId = findNextFcs("left");
			switchFcs(leftId);
			return false;
		case 39:
		case 114:
			var rightId = findNextFcs("right");
			switchFcs(rightId);
			return false;
		case 38:
		case 117:
			var upId = findNextFcs("up");
			switchFcs(upId);
			return false;
		case 40:
		case 100:
			var downId = findNextFcs("down");
			switchFcs(downId);
			return false;
	}
	return false;
};


/**
 * 选择焦点
 * @param id
 */
function switchFcs(id){
	if(id==null||id==""||id=="null"||id=="undefined"){
		return;
	}
	//先将所有的焦点样式清除
	for(var i=0;i<posList.length;i++){
		if(i<=2){
			document.getElementById("posDiv_1_"+i).className = "";
		}else if(i>2&&i<=6){
			document.getElementById("posDiv_2_"+(i-3)).className = "";
		}else if(i>6&&i<=10){
			document.getElementById("posDiv_3_"+(i-7)).className = "";
		}else{}
	}
	document.getElementById(id).className = "posFocus";
	currentFocusId = id;
}



/**
 * 查找焦点
 * @param nextDirection
 * @returns
 */
function findNextFcs(nextDirection){
	var currentFcsArr = currentFocusId.split("_");
	if(nextDirection=="left"){//向左查找
		if(currentFcsArr[2]==0){
			return;
		}else{
			return "posDiv_"+currentFcsArr[1]+"_"+(parseInt(currentFcsArr[2])-1);
		}
	}else if(nextDirection=="right"){//向右查找
		if(currentFcsArr[1]==1&&currentFcsArr[2]==2){
			return;
		}else if((currentFcsArr[1]==2||currentFcsArr[1]==3)&&currentFcsArr[2]==3){
			return;
		}else{
			return "posDiv_"+currentFcsArr[1]+"_"+(parseInt(currentFcsArr[2])+1);
		}
	}else if(nextDirection=="up"){//向上查找
		if(currentFcsArr[1]==1){
			return;
		}else if(currentFcsArr[1]==2){
			if(currentFocusId=="posDiv_2_0"){
				return "posDiv_1_0"
			}else if(currentFocusId=="posDiv_2_1"||currentFocusId=="posDiv_2_2"){
				return "posDiv_1_1"
			}else if(currentFocusId=="posDiv_2_3"){
				return "posDiv_1_2"
			}
		}else{
			return "posDiv_"+(parseInt(currentFcsArr[1])-1)+"_"+currentFcsArr[2];
		}
	}else if(nextDirection=="down"){//向下查找
		if(currentFcsArr[1]==1){
			if(currentFocusId=="posDiv_1_0"){
				return "posDiv_2_0"
			}else if(currentFocusId=="posDiv_1_1"){
				return "posDiv_2_1"
			}else if(currentFocusId=="posDiv_1_2"){
				return "posDiv_2_3"
			}
		}else if(currentFcsArr[1]==3){
			return;
		}else{
			return "posDiv_"+(parseInt(currentFcsArr[1])+1)+"_"+currentFcsArr[2];
		}
	}
}

/**
 * 查找当前焦点所在链接的数据
 */
function findCurrentLinkData(){
	var currentFcsArr = currentFocusId.split("_");
	var rowIdx = parseInt(currentFcsArr[1]);
	var colIdx = parseInt(currentFcsArr[2]);
	var dataIdx = 0;
	if(rowIdx==1){
		dataIdx = colIdx;
	}else if(rowIdx==2){
		dataIdx = colIdx+3;
	}else if(rowIdx==3){
		dataIdx = colIdx+7;
	}else{}
	return posList[dataIdx];
}


/**
 * 点击确定
 */
function onclickOk(){
	var posData = findCurrentLinkData();
	if(typeof(ottService) !="undefined"){
		if(posData.objType=="vod"){
			ottService. openEmergencyPage("emergency_vod","vod",posData.objValue,posData.vodJson);
		}else if(posData.objType=="channel"){
			ottService. openEmergencyPage("emergency_live","live",posData.objValue,"");
		}
	}else{
		console.log("not found ottService object!");
	}
}