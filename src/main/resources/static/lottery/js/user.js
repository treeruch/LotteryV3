$(function() {
	/*$("#example1").DataTable();*/
	$('#example1').DataTable({
	      "paging": true,
	      "lengthChange": false,
	      "searching": false,
	      "ordering": true,
	      "info": true,
	      "autoWidth": false,
	      "responsive": true,
	 });
});


function confrime (id){
	 $('#confrimeDelete').val(id);
};
	 
function testExport(){
	   $.ajax({
	        type: 'GET',
	        url: '/exportTopThree',
	        contenttype: "application/json; charset=utf-8",
	        dataType: "json",
	        success:
	        window.location.href = "/exportTopThree",
	        failure: function(errMsg){
	        	alert(errMsg);
	        }
	   });
}
	