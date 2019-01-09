/**
 * 
 */
function forwardLoginPage(){
	var formData = new FormData();

	formData.append("command", "FORWARD_TO_LOGIN");

	var request = new XMLHttpRequest();
	request.open("POST", "ParkingServlet");
	request.send(formData);
}
