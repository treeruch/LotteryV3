$(document).ready(function(){
	$('#addLotteryThree').val(0);
	$('#addLotteryTwo').val(0);
	 
	document.getElementById("buttonSave").disabled = true;
	
	 // กด กลับมาจากยืนยัน
	 var userHidden = $('#userHidden').val();
	 let user = parseInt(userHidden);
	 if('' != userHidden){
		 console.log('กดกลับมาแก้ไข เซ็ต User ที่เลือก : '+userHidden);
		 $('#userId').val(user); 
			 
		 // 3 ตัวบน
		 var listThree = $('#listThree').val();
		 const threeArr = listThree.split(":");
		 
		 var listThreePrice = $('#listThreePrice').val();
		 const threePriceArr = listThreePrice.split(":");
		 
		 var listTodPrice = $('#listTodPrice').val();
		 const todPriceArr = listTodPrice.split(":");
		 
		 var listTwo = $('#listTwo').val();
		 const twoArr = listTwo.split(":");
		 
		 var listTopTwoPrice = $('#listTopTwoPrice').val();
		 const topTwoPriceArr = listTopTwoPrice.split(":");
		 
		 var listUnderTwoPrice = $('#listUnderTwoPrice').val();
		 const underTwoPriceArr = listUnderTwoPrice.split(":");
		 
		 for(let i = 0 ; i < threeArr.length ; i++){
			   addThreeRow();
			   let countRow = $('#addLotteryThree').val();
			   let nextRow = parseInt(countRow);
			   $('#threeArray'+nextRow).val(threeArr[i]);
			   $('#topThreePriceArray'+nextRow).val(threePriceArr[i]);
			   $('#todPriceArray'+nextRow).val(todPriceArr[i]);
			  
		 }
		 
		 for(let i = 0 ; i < twoArr.length ; i++){
			   addTwoRow();
			   let countRow = $('#addLotteryTwo').val();
			   let nextRow = parseInt(countRow);
			   $('#twoArray'+nextRow).val(twoArr[i]);
			   $('#topTwoPriceArray'+nextRow).val(topTwoPriceArr[i]);
			   $('#underTwoPriceArray'+nextRow).val(underTwoPriceArr[i]);
		 }   
		 
		 document.getElementById("addLotteryThree").disabled = false;
		 document.getElementById("addLotteryTwo").disabled = false;
		 document.getElementById("buttonSave").disabled = false;
	 } else {
		 
		 // เข้าซื้อหวยครั้งแรก
		 // เพิ่ม Row TopThree ครั้งแรก
		 addThreeRow();
		 
		 // เพิ่ม Row Two ครั้งแรก
		 addTwoRow();
	 }  

});


// กด เพิ่มหวย Top Three
$('#addLotteryThree').click(function(){
	addThreeRow();
})
  
// กด เพิ่มหวย Two
$('#addLotteryTwo').click(function(){
	addTwoRow();
})

  
  function addThreeRow(){
		 // Get เลข Row ล่าสุด มา +1
	     let countRow = $('#addLotteryThree').val();
	   	 let x = 1;
	   	 let nextRow = parseInt(countRow) + x;
	   	 $('#addLotteryThree').val(nextRow);
	   	  
	   	 // ปุ่ม Delete แต่ละ Row 
	   	 $('#deleteLotteryThree').val(nextRow);	 
	   	 // เพิ่ม Row
	     $('#addThree').append($('#addThreeHidden').eq(0).html());
	     
	     // ปุ่ม Delete แต่ละ Row  เปลี่ยน Id +1
	     document.getElementById("deleteLotteryThree").setAttribute("id", "deleteLotteryThree"+nextRow);
	     
	     if(nextRow == 1){
	    	 $('#deleteLotteryThree'+nextRow).css('display','none');
	     }
	         
	     // เปลี่ยน id Row
	     document.getElementById("addThreeHiddenRow").setAttribute("id", "addThreeHiddenRow"+nextRow);
	     
	     // เปลี่ยน id ข้อความ กรอกข้อมูลให้ถูกต้อง
	     var messg = "addThreeMessg"+nextRow;
	     document.getElementById("addThreeMessg").setAttribute("id", messg);
	     document.getElementById(messg).style.display = "none";
	     
	     // เลข 3 ตัว เปลี่ยน Id three
	     document.getElementById("threeArray").setAttribute("id", "threeArray"+nextRow);
	     
	     // เลข 3 ตัว เปลี่ยน Id topThreePrice
	     document.getElementById("topThreePriceArray").setAttribute("id", "topThreePriceArray"+nextRow);
	     
	     // เลข 3 ตัว เปลี่ยน Id todPrice
	     document.getElementById("todPriceArray").setAttribute("id", "todPriceArray"+nextRow);
	     
	     // ปิดปุ่ม เพิ่มหวย
	     document.getElementById("addLotteryThree").disabled = true;
	     
	     // validate disable button
	     $('#validateAddThree').val(nextRow);	 
	     
	     document.getElementById("buttonSave").disabled = true;
	     
	     $('#countDupThree').val(nextRow);
	     
   };
   
   function addTwoRow(){
	   let countRow = $('#addLotteryTwo').val();
	   	 let x = 1;
	   	 let nextRow = parseInt(countRow) + x;
	   	 $('#addLotteryTwo').val(nextRow);
	 	   	  
	   	 $('#deleteLotteryTwo').val(nextRow);	   	
	     $('#addTwo').append($('#addTwoHidden').eq(0).html());
	     
	     document.getElementById("deleteLotteryTwo").setAttribute("id", "deleteLotteryTwo"+nextRow);
	     
	     if(nextRow == 1){
	    	 $('#deleteLotteryTwo'+nextRow).css('display','none');
	     }
	     
	     document.getElementById("addTwoHiddenRow").setAttribute("id", "addTwoHiddenRow"+nextRow);
	     
	     var messg = "addTwoMessg"+nextRow;
	     document.getElementById("addTwoMessg").setAttribute("id", messg);
	     document.getElementById(messg).style.display = "none";
	     
	     document.getElementById("twoArray").setAttribute("id", "twoArray"+nextRow);
	     
	      // เลข 2 ตัว บน
	     document.getElementById("topTwoPriceArray").setAttribute("id", "topTwoPriceArray"+nextRow);
	     
	     // เลข 2 ตัว ล่าง
	     document.getElementById("underTwoPriceArray").setAttribute("id", "underTwoPriceArray"+nextRow);
	     
	     
	     document.getElementById("addLotteryTwo").disabled = true;
	     
	     // validate disable button
	     $('#validateAddTwo').val(nextRow);	  
	     
	     document.getElementById("buttonSave").disabled = true;
	     
	     $('#countDupTwo').val(nextRow);
   };
  
  
  function deleteRowThree (event){
	    event = event || window.event; 
	    var target = event.target || event.srcElement; 
	    var value = target.value;
	    var rowDelete = "addThreeHiddenRow"+value;
	    const element = document.getElementById(rowDelete);
	    element.remove();
	    
	    var disableButton = $('#deleteLotteryThree').val();	
	    var dis = parseInt(disableButton);
	    console.log("value : "+value + " ; dis : "+dis);
	    if(value == dis){
	    	 document.getElementById("addLotteryThree").disabled = false;
	    	document.getElementById("buttonSave").disabled = false;
	    }
  };
  
  function deleteRowTwo (event){
	    event = event || window.event;
	    var target = event.target || event.srcElement; 
	    var value = target.value;
	    var rowDelete = "addTwoHiddenRow"+value;
	    const element = document.getElementById(rowDelete);
	    element.remove();
	    
	    var disableButton = $('#deleteLotteryTwo').val();	
	    var dis = parseInt(disableButton);
	    console.log("value : "+value + " ; dis : "+dis);
	    if(value == dis){
	    	 document.getElementById("addLotteryTwo").disabled = false;
	    	 document.getElementById("buttonSave").disabled = false;
	    }
	   
};
  
function checkValueThree (event){
	console.log("----------------------------");
	console.log("Start เช็ค 3 ตัวบน เลข 3 ตัว ...");
	var target = event.target || event.srcElement; 
    var id = target.id;
    var value = target.value;
    let length = parseInt(value.length);

    var replace = id.replace("threeArray", "");
    console.log('เลข '+ value + ' : '+ 'length = '+ length);
    
    var topThreePrice = $('#topThreePriceArray'+replace).val();
    let topThreeLength = parseInt(topThreePrice);
    
    var todThreePrice = $('#todPriceArray'+replace).val();
    let todThreeLength = parseInt(todThreePrice);
    	
	 if(length != 3){
		 console.log("เข้าเคส length ไม่เท่ากับ 3 ");
		 console.log(" -> เปิดเตือน x ");
		 console.log(" -> ปิดปุ่ม เพิ่มหวย 3 ตัว ");
		 console.log(" -> ปิดปุ่ม บันทึก ");
		 document.getElementById("addThreeMessg"+replace).style.display = "block";
		 document.getElementById("addLotteryThree").disabled = true; 
		 document.getElementById("buttonSave").disabled = true;
	 } else {
		 console.log("เข้าเคส length เท่ากับ 3");
		 document.getElementById("addThreeMessg"+replace).style.display = "none";
		 
		 var checkDup = false;
		 var countDupThree =  $('#countDupThree').val();
		 
		 console.log("# เริ่ม เช็คเลขซ้ำ ...");
		 for(let i = 1 ; i <= countDupThree ; i++){
			   var loopThree = $('#threeArray'+i).val();
			   console.log("- เลข 3 ตัว ตัวที่" +i+ " : "+loopThree);
			   
				 if( replace != i && value == loopThree){
					 var checkDup = true;
				 }
		 }
		 console.log("# จบ เช็คเลขซ้ำ .");
		 if(checkDup){
			 console.log("เคส1 เลข 3 ตัวซ้ำ");
			 document.getElementById("addThreeMessg"+replace).style.display = "block";
			 $('#modal-sm').css('display','block');
			 
			 $('#'+id).val('');
			 $('#numberDup').val(value);
			 document.getElementById("buttonSave").disabled = true;
		 } else if(topThreeLength > 0 || todThreeLength > 0){
			 console.log("เคส2 จำนวนเงิน น้อยกว่า 0 ");
			 console.log(" -> ปิดปุ่ม เพิ่มหวย");
			 console.log(" -> ปิดปุ่ม บันทึก");
			 document.getElementById("addLotteryThree").disabled = false;
			 document.getElementById("buttonSave").disabled = false;
		 } else {
			 console.log("เคส3");
			 console.log(" -> เปิดเตือน x");
			 console.log(" -> ปิดปุ่ม เพิ่มหวย");
			 console.log(" -> ปิดปุ่ม บันทึก");
			 document.getElementById("addThreeMessg"+replace).style.display = "block";
			 document.getElementById("addLotteryThree").disabled = true;
			 document.getElementById("buttonSave").disabled = true;
			 
		 }
		 
	 }
    
	 
};

function checkValueTopThree (event){
	var target = event.target || event.srcElement; 
    var id = target.id;
    var value = target.value;
    let length = parseInt(value.length);

    var replace = id.replace("topThreePriceArray", "");
    console.log(" replace : "+replace);
    console.log('checkValue id : '+ id +' ; '+'value = '+ value + ' ; '+ 'length = '+ length);
    	
    var three = $('#threeArray'+replace).val();
    let threeLength = three.length;
    console.log("threeLength : "+threeLength);
    
    var topThreePrice = $('#topThreePriceArray'+replace).val();
    let topThreeLength = parseInt(topThreePrice);
    console.log("topThreeLength : "+topThreeLength);
    
    var todThreePrice = $('#todPriceArray'+replace).val();
    let todThreeLength = parseInt(todThreePrice);
    console.log("todThreeLength : "+todThreeLength);
    
    
	 if(threeLength != 3){
		 console.log("length != 3");
		 console.log(" length : " +length  + " : addThreeMessg"+value);
		 document.getElementById("addThreeMessg"+replace).style.display = "block";
		 document.getElementById("addLotteryThree").disabled = true; 
		 document.getElementById("buttonSave").disabled = true;
	 } else {
		 console.log(" else != 3 ");
		 document.getElementById("addThreeMessg"+replace).style.display = "none";
		 
		 console.log(topThreeLength + " >= 1   || " + todThreeLength + " >= 1");
		 if(topThreeLength > 0 || todThreeLength > 1){
			 console.log("topThreePrice > 0 || todThreePrice > 0");
			 document.getElementById("addLotteryThree").disabled = false;
			 document.getElementById("buttonSave").disabled = false;
		 } else {
			 document.getElementById("addThreeMessg"+replace).style.display = "block";
			 document.getElementById("addLotteryThree").disabled = true;
			 document.getElementById("buttonSave").disabled = true;
		 }
	 }
    
};

function checkValueTodThree (event){
	var target = event.target || event.srcElement; 
    var id = target.id;
    var value = target.value;
    let length = parseInt(value.length);

    var replace = id.replace("todPriceArray", "");
    console.log(" replace : "+replace);
    console.log('checkValue id : '+ id +' ; '+'value = '+ value + ' ; '+ 'length = '+ length);
    	
    var three = $('#threeArray'+replace).val();
    let threeLength = three.length;
    console.log("threeLength : "+threeLength);
    
    var topThreePrice = $('#topThreePriceArray'+replace).val();
    let topThreeLength = parseInt(topThreePrice);
    console.log("topThreeLength : "+topThreeLength);
    
    var todThreePrice = $('#todPriceArray'+replace).val();
    let todThreeLength = parseInt(todThreePrice);
    console.log("todThreeLength : "+todThreeLength);
    
    
	 if(threeLength != 3){
		 console.log("length != 3");
		 console.log(" length : " +length  + " : addThreeMessg"+value);
		 document.getElementById("addThreeMessg"+replace).style.display = "block";
		 document.getElementById("addLotteryThree").disabled = true; 
		 document.getElementById("buttonSave").disabled = true;
	 } else {
		 console.log(" else  != 3 ");
		 document.getElementById("addThreeMessg"+replace).style.display = "none";
		 
		 if(topThreeLength > 0 || todThreeLength > 0){
			 console.log("topThreePrice > 0 || todThreePrice > 0");
			 document.getElementById("addLotteryThree").disabled = false;
			 document.getElementById("buttonSave").disabled = false;
		 } else {
			 document.getElementById("addThreeMessg"+replace).style.display = "block";
			 document.getElementById("addLotteryThree").disabled = true;
			 document.getElementById("buttonSave").disabled = true;
		 }
	 }
    
};
  
function checkValueTwo(event){
	var target = event.target || event.srcElement; 
    var id = target.id;
    var value = target.value;
    let length = parseInt(value.length);
    
    var replace = id.replace("twoArray", "");
    console.log(" replace : "+replace);
    console.log('checkValue id : '+ id +' ; '+'value = '+ value + ' ; '+ 'length = '+ length);
    
    var topTwoPrice = $('#topTwoPriceArray'+replace).val();
    let topTwoLength = parseInt(topTwoPrice);
    console.log("topTwoLength : "+topTwoLength);
    
    var underTwoPrice = $('#underTwoPriceArray'+replace).val();
    let underTwoLength = parseInt(underTwoPrice);
    console.log("underTwoLength : "+underTwoLength);
    	
	 if (length != 2){
		 console.log(" length : " +length);
		 document.getElementById("addTwoMessg"+replace).style.display = "block";
		 document.getElementById("addLotteryTwo").disabled = true;
		 document.getElementById("buttonSave").disabled = true;
	 } else {
		 console.log(" else == 2  ");
		 document.getElementById("addTwoMessg"+replace).style.display = "none";
		 
		 var checkDup = false;
		 var countDupTwo =  $('#countDupTwo').val();
		 console.log("countDupTwo : "+countDupTwo);
		 
		 for(let i = 1 ; i <= countDupTwo ; i++){
			   var loopTwo = $('#twoArray'+i).val();
			   console.log("loopTwo " +i+ " : "+loopTwo);
			   
				 if(replace != i && value == loopTwo){
					 var checkDup = true;
					 console.log("value : " +value+ " =  loopThree : "+loopTwo);
				 }
		 }
		 
		 if(checkDup){
			 document.getElementById("addTwoMessg"+replace).style.display = "block";
			 $('#modal-sm').css('display','block');
			 
			 $('#'+id).val('');
			 $('#numberDup').val(value);
			 document.getElementById("buttonSave").disabled = true;
			
		 } else if(topTwoLength > 0 || underTwoLength > 0){
			 console.log("topTwoLength > 0 || underTwoLength > 0");
			 document.getElementById("addLotteryTwo").disabled = false;
			 document.getElementById("buttonSave").disabled = false;
		 } else {
			 document.getElementById("addTwoMessg"+replace).style.display = "block";
			 document.getElementById("addLotteryTwo").disabled = true;
			 document.getElementById("buttonSave").disabled = true;
			 
			 // add list check dup
			 listCheckDup = listCheckDup +":"+value;
			 $('#valueDupTwoArr').val(listCheckDup);
			 console.log("valueDupTwoArr : " + $('#valueDupTwoArr').val());

		 }
		 
	 }
    
};

function checkValueTopTwo(event){
	var target = event.target || event.srcElement; 
    var id = target.id;
    var value = target.value;
    let length = parseInt(value.length);
    
    var replace = id.replace("topTwoPriceArray", "");
    console.log(" replace : "+replace);
    console.log('checkValue id : '+ id +' ; '+'value = '+ value + ' ; '+ 'length = '+ length);
    
    var two = $('#twoArray'+replace).val();
    let twoLength = two.length;
    console.log("twoLength : "+twoLength);
    
    var topTwoPrice = $('#topTwoPriceArray'+replace).val();
    let topTwoLength = parseInt(topTwoPrice);
    console.log("topTwoLength : "+topTwoLength);
    
    var underTwoPrice = $('#underTwoPriceArray'+replace).val();
    let underTwoLength = parseInt(underTwoPrice);
    console.log("underTwoLength : "+underTwoLength);
    	
	 if(twoLength != 2){
		 console.log(" length : " +length);
		 document.getElementById("addTwoMessg"+replace).style.display = "block";
		 document.getElementById("addLotteryTwo").disabled = true;
		 document.getElementById("buttonSave").disabled = true;
	 } else {
		 console.log(" else != 2  ");
		 document.getElementById("addTwoMessg"+replace).style.display = "none";
		 
		 if(topTwoLength > 0 || underTwoLength > 0){
			 console.log("topTwoLength > 0 || underTwoLength > 0");
			 document.getElementById("addLotteryTwo").disabled = false;
			 document.getElementById("buttonSave").disabled = false;
		 } else {
			 document.getElementById("addTwoMessg"+replace).style.display = "block";
			 document.getElementById("addLotteryTwo").disabled = true;
			 document.getElementById("buttonSave").disabled = true;
		 }

	 }
    
};

function checkValueUnderTwo(event){
	var target = event.target || event.srcElement; 
    var id = target.id;
    var value = target.value;
    let length = parseInt(value.length);
    
    var replace = id.replace("underTwoPriceArray", "");
    console.log(" replace : "+replace);
    console.log('checkValue id : '+ id +' ; '+'value = '+ value + ' ; '+ 'length = '+ length);
    
    var two = $('#twoArray'+replace).val();
    let twoLength = two.length;
    console.log("twoLength : "+twoLength);
    
    var topTwoPrice = $('#topTwoPriceArray'+replace).val();
    let topTwoLength = parseInt(topTwoPrice);
    console.log("topTwoLength : "+topTwoLength);
    
    var underTwoPrice = $('#underTwoPriceArray'+replace).val();
    let underTwoLength = parseInt(underTwoPrice);
    console.log("underTwoLength : "+underTwoLength);
    	
	 if(twoLength != 2){
		 console.log(" length : " +length);
		 document.getElementById("addTwoMessg"+replace).style.display = "block";
		 document.getElementById("addLotteryTwo").disabled = true;
		 document.getElementById("buttonSave").disabled = true;
	 } else {
		 console.log(" else != 2  ");
		 document.getElementById("addTwoMessg"+replace).style.display = "none";
		 
		 if(topTwoLength > 0 || underTwoLength > 0){
			 console.log("topTwoLength > 0 || underTwoLength > 0");
			 document.getElementById("addLotteryTwo").disabled = false;
			 document.getElementById("buttonSave").disabled = false;
		 } else {
			 document.getElementById("addTwoMessg"+replace).style.display = "block";
			 document.getElementById("addLotteryTwo").disabled = true;
			 document.getElementById("buttonSave").disabled = true;
		 }

	 }
    
};




function  closePupupUser(){
	$('#modal-sm').css('display','none');
};


function clickTabCheckLottery(){
	var userId = $('#userIdSelect').val();
	if(userId == 0){
		$('#modal-sm').css('display','block');
	} else {
		$('#modal-sm').css('display','none');
		 console.log('userId : '+userId);
		$('#userId').val(userId);

		
		document.getElementById("tabAddLotterry").style.display = "none";
		document.getElementById("tabCheckLottery").style.display = "block";
	}
};

function tabAddLotterry(){
	
	document.getElementById("tabAddLotterry").style.display = "block";
	document.getElementById("tabCheckLottery").style.display = "none";
};

function closeDup(){
	$('#modal-sm').css('display','none');
}
