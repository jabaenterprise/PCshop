/*used*/
function setCurrentCategory(id){
	if(id!="none"){
		document.getElementById(id).setAttribute("class", "active");
	}
}

/*not used*/
function setDisplay(className, isLogged){
	var elements = document.getElementsByClassName(className);
	for(var i = 0; i<elements.length; i++){
		if(isLogged){
			elements[i].style.display="block";
		}else{
			elements[i].style.display="none";
		}
	}
}
/*not used*/
function startRedirectTimer(){
	var second = 5;
	var counter = setInterval(function(){
	document.getElementById("timer").innerHTML=second;
	second = second-1;
	if(second<=0){
		clearInterval(counter);
		window.location.href = "index.jsp";
		return;
	}
	}, 1000);
}

/*used*/
function getSelectedSortType(selectId, category){
	var sortType = document.getElementById(selectId).value;
	window.location.href="index.jsp?category="+category+"&sortType="+sortType;
	return;
}
/*used*/
function setSelectedSortType(option){
	/*$("select option[value='"+option+"']").attr("selected","selected");*/
	var select = document.getElementsByTagName("option");
	if(option==="priceDescending"){
		select[1].setAttribute("selected", true);
	}else{
		if(option==="producer"){
			select[2].setAttribute("selected", true);
		}else{
			select[0].setAttribute("selected", true);
		}
	}
}

