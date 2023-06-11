<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="DAO.dao,model.*,java.sql.ResultSet" %>
<%@ include file="header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="dashboard">
    <div class="box personal_info">
        <h2 class="box_title">Personal Information</h2>
        <table class="table table-bordered">                 
            <tr>
                <td><strong>Full Name</strong></td>
                <td>${sessionScope.acc.name}</td>
            </tr>
            <tr>
                <td><strong>Email Address</strong></td>
                <td>${sessionScope.acc.email}</td>
            </tr>
            <tr>
                <td><strong>Cell Phone Number</strong></td>
                <td>${sessionScope.acc.phone}</td>
            </tr>
            <tr>
                <td><strong>Address</strong></td>
                <td>${sessionScope.acc.address}</td>
            </tr>
        </table>
    </div>
    <div class="box successfully_purschase_ticket">
        <h2 class="box_title">Successful Purchase Information</h2>
        <table class="table table-bordered">
            <tr>
                <td>Train Name</td>
                <td>Train code</td>
                <td>Purchase Date</td>
                <td>Journey Date</td>
                <td>destination</td>
                <td>Seat</td>
                <td>Option</td>
            </tr>
            <c:forEach var="i" items="${list}">
               
                <tr>
                    <td>${i.get("name")}</td>
                    <td>${i.get("code")}</td>
                    <td>${i.get("booking_date")}</td>
                    <td>${i.get("journey_date")}</td>
                    <td>${i.get("from")}-${i.get("to")}</td>
                    <td>${i.get("num")}</td>
                    <td><a class="btn btn-success btn-sm">Print</a></td>

                </tr>							
            </c:forEach>
        </table>
    </div>
</div>
<%@ include file="footer.jsp" %>