<%@page import="model.Inquiry" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inquiry Management</title>
<link rel="stylesheet" href="Views/bootstrap.css">

</head>
<body>
	<div class="container">
		<div class="row">
		<div class= "col-3"></div>
			<div class="col-6">
				<h1 style="margin-left: 70px;">Inquiry Management</h1><br>
				<form id="formItem" name="formItem">
					Account num: <input id="accountNum"
						name="accountNum" type="text" class="form-control form-control-sm"> <br>
					Customer name: <input id="Name" name="Name"
						type="text" class="form-control form-control-sm"> <br> Contact
					num: <input id="contactNum" name="contactNum"
						type="text" class="form-control form-control-sm"> <br> Email:
					<input id="email" name="email" type="text"
						class="form-control form-control-sm"> <br> Inquiry: <input
						id="inquiryDet" name="inquiryDet" type="text"
						class="form-control form-control-sm"> <br> <input id="btnSave"
						name="btnSave" type="button" value="Save" class="btn btn-primary btn-lg"> <input type="hidden" id="hidInquiryIDSave" name="hidInquiryIDSave"
						value="">
				</form>
				<br>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				
			</div>
		</div>
		
				<div id="divItemsGrid">
				<%
				Inquiry inquiryObj = new Inquiry();
 					out.print(inquiryObj.readInquiries());
 					%>
				
	
				</div>
	</div>
</body>
<script src="Components/jquery.min.js" type="text/javascript"></script>
<script src="Components/inquiries.js" type="text/javascript"></script>
</html>