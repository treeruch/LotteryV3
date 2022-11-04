
function addInputText() {
	jQuery("#groupRevenue").append('<div class="form-group"><div class="row"><div class="col-md-6 col-xs-6 no-padding-right"><input type="text" id="accRevenue" name="accRevenue"class="form-control"></div><div><div class="input-group-prepend"><span class="input-group-text">$</span></div></div><div><input type="text" id="accRevenueAmount" class="form-control"></div><div><div class="input-group-append"><span class="input-group-text">.00</span></div></div><div class="col-md-2 col-xs-6 no-padding-right"><input type="text" id="noteRevenue" class="form-control"></div></div></div>');
}

function addInputTextExpenditure() {
	jQuery("#addInputTextExpenditure").append('<div class="form-group"><div class="row"><div class="col-md-6 col-xs-6 no-padding-right"><input type="text" id="accExpenditure" name="accRevenue"class="form-control"></div><div><div class="input-group-prepend"><span class="input-group-text">$</span></div></div><div><input type="text" id="accExpenditureAmount" class="form-control"></div><div><div class="input-group-append"><span class="input-group-text">.00</span></div></div><div class="col-md-2 col-xs-6 no-padding-right"><input type="text" id="noteExpenditure" class="form-control"></div></div></div>');
}

function save(){
	//AccRevenue
	var accRevenue = document.querySelectorAll('#accRevenue');
	var arrAccRevenue = [];
	for (var i = 0 ; i <= accRevenue.length-1 ; i++){
		arrAccRevenue.push(accRevenue[i].value);
	}
	
	var accRevenueAmount = document.querySelectorAll('#accRevenueAmount');
	var arrRevenueAmount = [];
	for (var i = 0 ; i <= accRevenueAmount.length-1 ; i++){
		arrRevenueAmount.push(accRevenueAmount[i].value);
	}
	
	var noteRevenue = document.querySelectorAll('#noteRevenue');
	var arrNoteRevenue = [];
	for (var i = 0 ; i <= noteRevenue.length-1 ; i++){
		arrNoteRevenue.push(noteRevenue[i].value);
	}
	
	//AccExpenditure
	var accExpenditure = document.querySelectorAll('#accExpenditure');
	var arrAccExpenditure = [];
	for (var i = 0 ; i <= accExpenditure.length-1 ; i++){
		arrAccExpenditure.push(accExpenditure[i].value);
	}
	
	var accExpenditureAmount = document.querySelectorAll('#accExpenditureAmount');
	var arrExpenditureAmount = [];
	for (var i = 0 ; i <= accExpenditureAmount.length-1 ; i++){
		arrExpenditureAmount.push(accExpenditureAmount[i].value);
	}
	
	var noteExpenditure = document.querySelectorAll('#noteExpenditure');
	var arrNoteExpenditure = [];
	for (var i = 0 ; i <= noteExpenditure.length-1 ; i++){
		arrNoteExpenditure.push(noteExpenditure[i].value);
	}
	
	$.ajax({
		type:"POST",
		url: "saveActivity",
		data: {arrayAccRevenue : arrAccRevenue,
			   arrayRevenueAmount : arrRevenueAmount,
			   arrayNoteRevenue : arrNoteRevenue,
			   arrayAccExpenditure : arrAccExpenditure,
			   arrayExpenditureAmount : arrExpenditureAmount,
			   arrayNoteExpenditure : arrNoteExpenditure
			  }
	});
	
}

