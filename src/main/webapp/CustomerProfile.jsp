<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Profile</title>
</head>
<body>

	<h1>${messages.title}</h1>
		<h2>This is customer ${messages.customerId}'s profile</h2>
        <table border="1">
            <tr>
                <th>PolicyId</th>
                <th>PolicyName</th>
                <th>Drop</th>
            </tr>
            <c:forEach items="${registrations}" var="registration" >
                <tr>
                    <td><c:out value="${registration.getPolicyId()}" /></td>
                    <td><c:out value="${registration.getInsuranceName()}" /></td>
                    <td><a href="DropInsurance?record=<c:out value="${registration.getRegistrationId()}"/>">Drop</a></td>
                    
                </tr>
            </c:forEach>
            
       </table>
       <a href="InsuranceRegister?customerId=<c:out value="${fn:escapeXml(param.customerId)}"/>">Register More</a>

</body>
</html>