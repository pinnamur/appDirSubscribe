<%--
Document : index
Created on : Nov 5, 2012, 6:06:23 PM
Author	 : mano
--%>

<%@page import="java.util.List"%>
<%@page import="com.subscription.dao.EventsDAO"%>
<%@page import="java.util.Date"%>
<%@page import="com.subscription.orm.common.EventSchema"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <link rel="stylesheet" type="text/css" href="style.css"/>
	 <title>Home Page</title>	
</head>
<body>
	 <div id="mystyle">
		 <h1>Welcome! Number of Orders for <a href="https://www.appdirect.com/apps/41352">AppDirect service</a></h1>
		 <p>
			 Last updated: <%=new Date()%>
		 </p>

		 <%
		 	EventsDAO events = new EventsDAO();
			 List<EventSchema> list = events.getEvents();
			 if (list == null || list.size() == 0) {
		 %>
		  <p>
			 No Orders available.
		 </p>
		 <%} else {%>
		 

		 <table border="1">
			 <thead>
				 <tr>
					 <th>User Name</th>
					 <th>Email</th>
					 <th>Edition Code</th>
				 </tr>
			 </thead>
			 <tbody>
				 <%
					 for (EventSchema u : list) {
				 %>
				 <tr>
				 	<td><%=u.getFullName()%></td>
					 <td><%=u.getEmail()%></td>
					 <td><%=u.getEditionCode()%></td>
				 </tr>
				 <%}%>
			 <tbody>
		 </table>		
		 <%}%>
	 </div>

</body>
</html>