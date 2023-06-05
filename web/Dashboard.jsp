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
                <td>Purchase Date</td>
                <td>Journey Date</td>
                <td>destination</td>
                <td>Total Seat</td>
                <td>Option</td>
            </tr>
            <c:forEach var="i" items="${list}">
               
                <tr>
                    <td>${i.train_id}</td>
                    <td>${i.booking_date}</td>
                    <td>${i.journey_date}></td>
                    <td>${i.destination_id}</td>
                    <td>${i.number_of_seat}</td>
                    <td><a class="btn btn-success btn-sm">Print</a></td>

                </tr>							
            </c:forEach>
        </table>
    </div>
</div>
<%@ include file="footer.jsp" %>