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