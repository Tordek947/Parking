/**
 * 
 */

function declineChoosenUser(){
	sendDefineNewUserCommand("DECLINE_NEW_USER");
}

function confirmChoosenUser(){
	sendDefineNewUserCommand("CONFIRM_NEW_USER");
}

function sendDefineNewUserCommand(command){
	var commandInput = document.querySelector("#defineNewUser input[name=command]");
	commandInput.setAttribute("value",command);
	var form = document.getElementById("defineNewUser");
	var submit = form.querySelector("input[type=submit]");
	submit.click();
}