<%@page import="java.util.*,model.*,DAO.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="header.jsp" %>
<form class="ticket_selecting_form" action="findticket">
    <h2 class="title">  <span>Train Route Showing For ::</span> ${stationFromObj.name} to  ${stationToObj.name} <span>:: Journey Date -</span> ${journey_date}</h2>
    <table class="table rs_shadow">
        <tr>
            <th>Serial</th>
            <th>Train No</th>
            <th>Train Name</th>
            <th>Class</th>
            <th>Departure Time</th>
            <th>Unit Fare</th>
            <th>Selection</th>
        </tr>
        <c:set var="i" value="${0}"/>
        <c:forEach var="t" items="${trains}">
        <tr>
            <c:set var="i" value="${i+1}"/>
            <td>${i}</td>
            <td>${t.get("code")}</td>
            <td>${t.get("name")}</td>
            <td>${t.get("coach")}</td>
            <td>${t.get("time")}</td>
            <td>${t.get("fare")}</td>
            <td>
             <a href="findticket.jsp?date=${journey_date}&destination=${t.get("destination_id")}" class="btn btn-success">Search Ticket</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</form>
<div id="rs_ticket_result">

</div>
<%@ include file='footer.jsp' %>