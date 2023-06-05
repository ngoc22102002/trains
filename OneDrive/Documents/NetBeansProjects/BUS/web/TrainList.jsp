<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
<div class="text-right">
	<a class="btn btn-success" href="Add.jsp">Create Train</a>
</div>
<br>
<div class="box successfully_purschase_ticket">
	<h2 class="box_title">All Train List</h2>
	<table class="table table-bordered">
		<tr>
			<td wide="50">Train Code</td>
			<td>Name</td>
                        <td>Type</td>
			<td>Total Seat</td>
			<td>Actions</td>
		</tr>
                <c:forEach var="i" items="${list}">
			<tr>
				<td>${i.code}</td>
				<td>${i.name}</td>
                                <td>${i.type}</td>
				<td>${i.total_seat}</td>
				<td><a href="train?delete=${i.id}" class="btn btn-sm btn-danger">Delete</a></td>
			</tr>
                </c:forEach>
		
	</table>
</div>
<%@ include file="footer.jsp" %>