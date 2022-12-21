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
<title>Delete a Insurance</title>
</head>
<body>
<div class = "container theme-showcase" role = "main">

	<form action="DeleteInsurance" method="post">
	<div class = "jumbotron">
	<h1>${messages.title}</h1>
	
		</div>
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label for="policyId">Policy Id</label>
				<input id="policyId" name="policyId" value="${fn:escapeXml(param.policyId)}">
			</div>
		</p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit" value="Delete" class="btn btn-lg btn-outline-secondary">
			</span>
			
		</p>
		<p><a href="SearchInsurance" class="btn btn-lg btn-outline-secondary"/>Back</a></p>
	</form>
	<br/><br/>
	</div>
	
</body>
</html>