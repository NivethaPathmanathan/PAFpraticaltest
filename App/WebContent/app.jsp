<%@ page import="com.App"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
 //Insert item----------------------------------
if (request.getParameter("user_id") != null)
 {
 	App AppObj = new App();
 	String stsMsg = AppObj.addApp
 	(
 		request.getParameter("user_id"),
 		request.getParameter("app_no"),
 		request.getParameter("doctor_name"),
 		request.getParameter("payment")
 	);
 	session.setAttribute("statusMsg", stsMsg);
 }
%>

<%
 //delete item----------------------------------
if (request.getParameter("app_no") == null)
 {
	App AppObj = new App();
 	String stsMsg = AppObj.deleteApp
 	(
 		request.getParameter("app_no")
 		
 	);
 	session.setAttribute("statusMsg", stsMsg);
 }
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>App</title>
</head>
<body>

	<h1>App</h1>
	
	<form method="post" action="Appointment_2.jsp">
		User ID: <input name="user_id" type="text"><br> 
		Appointment ID: <input name="app_no" type="text"><br> 
		Doctor name:<input name="doctor_name" type="text"><br>
 		Payment Amount: <input name="payment" type="text"><br> 
 		<input name="btnSubmit" type="submit" value="Save">
	</form>
	
	<%
	 out.print(session.getAttribute("statusMsg"));
	%>
	<br>
	<%
		App AppObj = new App();
	 	out.print( AppObj.readApp());
	%>
	


</body>
</html>