$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
});



$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
 
 //Form validation-------------------
var status = validatePayForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
 
 
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 

 $.ajax( 
 { 
 url : "customerpayApi", 
 type : type, 
 data : $("#formCustomer").serialize(), 
 dataType : decodeURIComponent("text"), 
 complete : function(response, status) 
 { 
 onPaySaveComplete(response.responseText, status); 
 } 
 }); 
});

$(document).on("click", ".btnUpdate", function(event)
{ 
$("#hidItemIDSave").val($(this).data("cardid")); 
 $("#Card_No").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#Name_on_card").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#Exp_date").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#Cvv").val($(this).closest("tr").find('td:eq(4)').text()); 
});

$(document).on("click", ".btnRemove", function(event)
{ 
 $.ajax( 
 { 
 url : "customerpayApi", 
 type : "DELETE", 
 data : "Card_Id=" + $(this).data("cardid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onPayDeleteComplete(response.responseText, status); 
 } 
 }); 
});




function onPaySaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
 $("#hidItemIDSave").val(""); 
 $("#formCustomer")[0].reset(); 
}







function onPayDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


function validatePayForm() {

	// CODE
if ($("#Card_No").val().trim() == "") 
 { 
 return "Insert Card Number."; 
 } 
// NAME
if ($("#Name_on_card").val().trim() == "") 
 { 
 return "Insert Name on the card."; 
 } 9
// PRICE-------------------------------
if ($("#Exp_date").val().trim() == "") 
 { 
 return "Insert Expiration date."; 
 } 
// is numerical value
 if ($("#Cvv").val().trim() == "") 
 { 
 return "Insert Cvv."; 
 } 

// convert to decimal price

// DESCRIPTION------------------------

return true; 
}
