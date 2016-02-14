function checkAuthCookie() {
	var cookieVal = $.cookie('fullTestAuth');
	return (cookieVal && cookieVal == 'A1B2C3');
}

function getCookie() {
	$.ajax({
		url:"/auth/", 
		type:"POST", 
		data: {
			username:"user", 
			password:"pass"
		},
		success:function() {
			console.log("got cookie");
		}
	});
}