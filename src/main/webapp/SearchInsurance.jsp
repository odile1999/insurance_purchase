<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Search</title>
</head>
<body>
<div class = "container theme-showcase" role = "main">
<form action="SearchInsurance" method="post">
	<div class = "jumbotron">
	<h1>Search Insurance</h1>
	</div>
	<p>
		<label for="name" >Insurance Name</label>
		<input id="name" name="name" value="">
		<input type="submit" value="search" class="btn btn-lg btn-outline-secondary">
	</p>
	
 	<br/>
	<h1>Matching Insurances</h1>
	<h1>${messages.title}</h1>
        <table class="table table-striped">
            <thead><tr>
                <th>Insurance Id</th>
                <th>Insurance Name</th>
                <th>Premium</th>
                <th>Deductible</th>
                <th>Out Of Pocket Max</th>
                <th>Description</th>
                <th>Update</th>
                <th>Delete</th>
            </tr></thead>
            <c:forEach items="${insurances}" var="insurance" >
                <tr>
                    <td><c:out value="${insurance.getPolicyId()}" /></td>
                    <td><c:out value="${insurance.getName()}" /></td>
                    <td><c:out value="${insurance.getPremium()}" /></td>
                    <td><c:out value="${insurance.getDeductible()}" /></td>
                    <td><c:out value="${insurance.getOutOfPocketMax()}" /></td>
                    <td><c:out value="${insurance.getDescription()}" /></td>
            
                    <td><a href="UpdateInsurance?policyId=<c:out value="${insurance.getPolicyId()}"/>">Update</a></td>
                    <td><a href="DeleteInsurance?policyId=<c:out value="${insurance.getPolicyId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
    </div>
       
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
