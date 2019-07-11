// Call the dataTables jQuery plugin
$(document).ready(function() {
	value = $("#prueba").text();
	if (value === 'Es') {
		$('#pending, #received, #sent').dataTable( {
	        "language": {
	        	"url": "/js/demo/dataTable_spanish.json"
	        }
	    } );
	} else {
		$('#pending, #received, #sent').DataTable();
	} 
} );