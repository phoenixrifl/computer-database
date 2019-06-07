/**
 * 
 */

function errorMessage(msg){
	$("#error").html(msg);
	$("#error").show();
}

function insertion() {
	$("#insertion").html("Insertion réussie !");
	$("#insertion").show();
}

function checkName(name){
	if(name.trim() == ""){
		errorMessage("entrez un nom de pc");
		return 0;
	}
	return 1;
}

function checkDate(introduced, discontinued){
	if(introduced.trim() =="" && discontinued.trim() != ""){
		errorMessage("Vous devez rentrer une date de debut d'activite si vous rentrer une date de fin d'activite");
		return 0;
	}
	if(introduced.trim()!="" && discontinued.trim()!=""){
		if((new Date(introduced).getTime() > new Date(discontinued).getTime())){
			errorMessage("Date de mise en activite supérieure à date de fin d'activite !");
			return 0;
		}
	}
	return 1;
}

(function($){
	$("#submit").click(function(){
		var name = $("#computerName").val();
		var introduced = $("#introduced").val();
		var discontinued = $("#discontinued").val();
		if(!checkName(name) || !checkDate(introduced, discontinued))
			return false;
		else
			insertion();
	});
}(jQuery))