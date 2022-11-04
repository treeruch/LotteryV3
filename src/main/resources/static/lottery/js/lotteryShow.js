$(document).ready(function(){
	
});

function back(){
	console.log("back");
	
	 $.ajax({
	        type: 'GET',
	        url: '/backToAdd',
	        contenttype: "application/json; charset=utf-8",
	        dataType: "json",
	        success:
		        window.location.href = "/lotteryAdd",
		        failure: function(errMsg){
		        	alert(errMsg);
		        }
	   });
};