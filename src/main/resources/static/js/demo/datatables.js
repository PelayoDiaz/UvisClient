// Call the dataTables jQuery plugin
$(document).ready(function() {
	value = $("#prueba").text();
	if (value === 'Es') {
		$('#dataTable').dataTable( {
	        "language": {
	        	"url": "/js/demo/dataTable_spanish.json"
	        }
	    } );
	} else {
		$('#dataTable').DataTable();
	} 
} );