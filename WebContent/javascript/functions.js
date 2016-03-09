function addAttribute(id){
	document.getElementById(id).setAttribute("class", "active");
}

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