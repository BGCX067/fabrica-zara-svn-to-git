function initDataTable(table){
	$(table).dataTable( {
		"sPaginationType": "full_numbers",
        "bLengthChange": false,
        "bDestroy": true,
        "bFilter": false,
        "bInfo": false,
        "bAutoWidth": false,
        "iDisplayLength": 10} );
}