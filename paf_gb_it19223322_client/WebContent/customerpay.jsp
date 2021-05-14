<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.customerpay"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="view/bootstrap.min.css">

<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/customerpay.js"></script>

</head>



<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h2>Customer Payments Management </h2>
<form id="formCustomer" name="formCustomer">
 Card Number: 
 <input id="Card_No" name="Card_No" type="text" 
 class="form-control form-control-sm">
 <br> Name on the card: 
 <input id="Name_on_card" name="Name_on_card" type="text" 
 class="form-control form-control-sm">
 <br> Expiration date:
 <input id="Exp_date" name="Exp_date" type="text" 
 class="form-control form-control-sm">
 <br>CVV: 
 <input id="Cvv" name="Cvv" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 customerpay cusObj = new customerpay(); 
 out.print(cusObj.viewCustomerpay()); 
 %>
</div>
</div> </div> </div> 




</body>



</html>