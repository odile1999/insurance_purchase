<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register for a Insurance</title>
</head>
<body>
	<h1>Register for a Insurance</h1>
	<form action="InsuranceRegister" method="post">
		<p>
			<label for="customerId">Customer Id</label>
			<input id="customerId" name="customerId" value="${fn:escapeXml(param.customerId)}">
		</p>
		<p>
			<label for="policyId">Policy Id</label>
			<input id="policyId" name="policyId" value="${fn:escapeXml(param.policyId)}">
		</p>

		<p>
			<input type="submit" value="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>

</body>
</html>