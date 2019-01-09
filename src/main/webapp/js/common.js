/**
 * 
 */
function followHref(id){
	var a = document.getElementById(id);
	a.click();
}

function forwardByForm(id){
	var form = document.getElementById(id);
	var submit = form.querySelector("input[type=submit]");
	submit.click();
}
