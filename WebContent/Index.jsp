<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="mySQL.DBHandler"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
	<input type="radio" name="select" onclick="handleClick(0);" id="philosopher"><label for="philosopher">Philosopher</label><br>
	<input type="radio" name="select" onclick="handleClick(1);" id="country"><label for="country">Country</label><br>
	<input type="radio" name="select" onclick="handleClick(2);" id="city"><label for="city">City</label><br>
</div>

<div id="form">

</div>

</body>

<script>

<% 
	String cities = ""; 
	String countries = "";
	if (DBHandler.connectToDB()) {
		cities = DBHandler.getTableMapAsDatalist("Cities");
		countries = DBHandler.getTableMapAsDatalist("Countries");
	}
%>

var formDiv = [
	"<form action='ControllerServlet' method='post'>" + 
	"<p>Philosopher</p>" +
	"Name:<input type='text' name='philosophers'><br>" + 
	"City:<input list='cities' name='philosophers'>" + 
	"<datalist id='cities'>" + "<%= cities %>" + "</datalist><br>" + 
	"Country:<input list='countries' name='philosophers'>" + 
	"<datalist id='countries'>" + "<%= countries %>" + "</datalist><br>" +
	"Year Born:<input type='text' name='philosophers'><br>" +
	"Year Died:<input type='text' name='philosophers'><br>" +
	"Note:<input type='text' name='philosophers'><br>" + 
	"<input type='submit' value='add'>",
	
	"<form action='ControllerServlet' method='post'><p>" + 
	"Country</p>Name:<input type='text' name='countries'><br>" +
	"Capital:<input type='text' name='countries'><br>" +
	"<input type='submit' value='add'>",	
	
	"<form action='ControllerServlet' method='post'><p>" +
	"City</p>Name:<input type='text' name='cities'><br>" +
	"Country:<input list='countries' name='cities'>" +
	"<datalist id='countries'>" + "<%= countries %>" + "</datalist><br>" +
	"Note:<input type='text' name='cities'><br>" +
	"<input type='submit' value='add'>"
];


function handleClick(radio){
	document.getElementById("form").innerHTML = formDiv[radio];
}

</script>

</html>