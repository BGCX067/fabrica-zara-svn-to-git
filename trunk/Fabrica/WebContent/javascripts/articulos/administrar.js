
$(document).ready(function() {

	$("tbody").click(function(event) {
		$(oTable.fnSettings().aoData).each(function() {
			$(this.nTr).removeClass('row-selected');
		});
		$(event.target.parentNode).addClass('row-selected');
	});

	$('#articulosTable tbody tr').click(function() {

		var aData = oTable.fnGetData(this);

		$.get("/fabrica-zara-web/EspecificacionArticulo", {
			idArticulo : aData[0]
		}, function(data) {
				$("#especificacion").html(data);
				initDataTable($('#especificacionTable'));
		});

	});

	oTable = $('#articulosTable').dataTable({
		"sPaginationType" : "full_numbers",
		"bLengthChange" : false,
		"bDestroy" : true,
		"bFilter" : false,
		"bInfo" : false,
		"bAutoWidth" : false,
		"iDisplayLength" : 10,
		"aoColumnDefs" : [

		{
			"bVisible" : false,
			"aTargets" : [ 0 ]
		} ]
	});
	
	$("#guardar").live('click', function() {
		$.post("/fabrica-zara-web/GuardarTiempo", {
			idArticulo : $("#idArticulo").val(), tiempo: $("#tiempo").val()
		}, function() {
				
		},function(){alert("Error. Contacte a su administrador.");});
	});
	
	$("#agregarMateria").live('click', function() {
		$.post("/fabrica-zara-web/AgregarMateria", {
			idArticulo : $("#idArticulo").val(), referencia: $("#selectMateria option:selected").val(), cantidad: $("#cantidad").val() 
		}, function() {
			$("#cantidad").val("");
			
			$.get("/fabrica-zara-web/EspecificacionArticulo", {
				idArticulo : $("#idArticulo").val()
			}, function(data) {
					$("#especificacion").html(data);
					initDataTable($('#especificacionTable'));
			});

			
		},function(){alert("Error. Contacte a su administrador.");});
	});

});