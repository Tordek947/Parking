/**
 * 
 */

function onNewUsersLoaded(){
	
	pagerButtons = document.querySelectorAll("ul.pager>li");
	if (!pagerButtons[0].classList.contains("disabled")) {
		pagerButtons[0].onclick=sendPrevPage;
	}
	if (!pagerButtons[1].classList.contains("disabled")){
		pagerButtons[1].onclick=sendNextPage;
	}
	
	var clkRows = document.querySelectorAll(".clkRow[beanId]");
	//clkRows.forEach(item.onclick=chooseItem(item.getAttribute("beanId")));
	var hidden = document.querySelectorAll("[hidden]");
	document.addEventListener('click', function(event) {
		var isClickInsideHidden = false;
		for(var i = 0;i<hidden.length;i++){
			if (hidden[i].contains(event.target)){
				isClickInsideHidden = true;
				break;
			}
		}
		var isClickedInsideButtons = false;
		var buttons = document.querySelectorAll(".navbar-fixed-bottom button");
		for(var j = 0;j<buttons.length;j++){
			if (buttons[j].contains(event.target)){
				sendDefineNewUserCommand(buttons[j]);
				isClickedInsideButtons = true;
				break;
			}
		}
		
		if (!isClickInsideHidden && !isClickedInsideButtons) {
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
		}
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


function sendDefineNewUserCommand(button){
	if (button.classList.contains("disabled")){
		return;
	}
	var command = button.getAttribute("command");
	var commandInput = document.querySelector("#defineNewUser input[name=command]");
	commandInput.setAttribute("value",command);
	var form = document.getElementById("defineNewUser");
	var submit = form.querySelector("input[type=submit]");
	//console.log(command);
	submit.click();
}

function sendNextPage(){
	var clkRows = document.querySelectorAll(".clkRow[beanId]");
	if (clkRows == null){
		return;
	}
	var minBeanId = Number(clkRows[0].getAttribute("beanId"));
	for(var i = 0;i<clkRows.length;i++){
		if (Number(clkRows[i].getAttribute("beanId")) < minBeanId){
			minBeanId = Number(clkRows[i].getAttribute("beanId"));
		}
	}
	var form = document.getElementById("newUserPaging");
	var fromIndexInput = form.querySelector("input[name=fromIndex]");
	fromIndexInput.setAttribute("value",minBeanId-1);
	var forwardInput = form.querySelector("input[name=forward]");
	forwardInput.setAttribute("value",true);
	var submit = form.querySelector("input[type=submit]");
	submit.click();
}


function sendPrevPage(){
	var clkRows = document.querySelectorAll(".clkRow[beanId]");
	if (clkRows == null){
		return;
	}
	var maxBeanId = Number(clkRows[0].getAttribute("beanId"));
	for(var i = 0;i<clkRows.length;i++) {
		if (Number(clkRows[i].getAttribute("beanId")) > maxBeanId){
			maxBeanId = Number(clkRows[i].getAttribute("beanId"));
		}
	}
	var form = document.getElementById("newUserPaging");
	var fromIndexInput = form.querySelector("input[name=fromIndex]");
	fromIndexInput.setAttribute("value",maxBeanId+1);
	var forwardInput = form.querySelector("input[name=forward]");
	forwardInput.setAttribute("value",false);
	var submit = form.querySelector("input[type=submit]");
	submit.click();
}