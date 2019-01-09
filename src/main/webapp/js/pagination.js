/**
 * 
 */
function onNewUsersLoaded(){
	var clkRows = document.querySelectorAll(".clkRow[beanId]");
	//clkRows.forEach(item.onclick=chooseItem(item.getAttribute("beanId")));
	document.addEventListener('click', function(event) {
		clkRows.forEach(function(item, i, arr){
		    var isClickInside = item.contains(event.target);
		    if (isClickInside) {
		    	if (!item.classList.contains("choosen")){
		    		item.classList.add("choosen");
		    	}
		    }
		    else {
		    	item.classList.remove("choosen");
		    }
		});
		checkChoosenBean(document.querySelector(".choosen[beanId]"));
	});
}

function checkChoosenBean(beanRow){
	var input = document.querySelector("#defineNewUser input[name=choosenBeanId]");
	var buttons = document.querySelectorAll(".navbar-fixed-bottom button");
	if (beanRow == null){
		input.setAttribute("value","");
		buttons.forEach(function(item, i, arr) {item.classList.add("disabled");});
	} else {
		input.setAttribute("value",beanRow.getAttribute("beanId"));
		buttons.forEach(function(item, i, arr) {item.classList.remove("disabled");});
	}
}

