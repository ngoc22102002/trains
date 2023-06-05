<%@page import="java.util.*,model.*,DAO.*" %>
<%
    dao d=new dao();
ArrayList<HashMap<String,String>> trains = new ArrayList<HashMap<String,String>>();
trains trnObj = new trains();
String stationFrom = (String) request.getParameter("from");
String stationTo = (String) request.getParameter("to");
String sCoach = (String) request.getParameter("coach");
String journey_date = (String) request.getParameter("journey_date");
Stations stationFromObj = new Stations();
Stations stationToObj = new Stations();
if(stationTo != null || stationFrom != null){
        trains = d.SearchTrainFromTo(stationFrom, stationTo,sCoach);
        Stations tempStationsObj = new Stations();
        stationFromObj = d.getStation(stationFrom);
        stationToObj = d.getStation(stationTo);
}

%>

<%@ include file="header.jsp" %>
<form class="ticket_selecting_form" action="FindTicket.jsp">
    <h2 class="title">  <span>Train Route Showing For ::</span> <%= stationFromObj.getName() %> to  <%= stationToObj.getName() %> <span>:: Journey Date -</span> <%= journey_date %> </h2>
    <table class="table rs_shadow">
        <tr>
            <th>Serial</th>
            <th>Train No</th>
            <th>Train Name</th>
            <th>Class</th>
            <th>Departure Time</th>
            <th>Unit Fare</th>
            <th>Number Of Seat</th>
            <th>Selection</th>
        </tr>
        <% for(int i =0; i<trains.size(); i++){ 
                HashMap<String,String> tempTrain= trains.get(i);
		
        %>
        <tr>
            <td><%= i+1 %></td>
            <td><%= tempTrain.get("code") %></td>
            <td><%= tempTrain.get("name") %></td>
            <td><%= tempTrain.get("coach") %></td>
            <td><%= tempTrain.get("time") %></td>
            <td><%= tempTrain.get("fare")%></td>
            <td>
                <select name="total_seat"  class="form-control total_seat_select">
                    <option value="1">01</option>
                    <option value="2">02</option>
                    <option value="3">03</option>
                    <option value="4">04</option>
                </select>
            </td>
            <td>
                <a href="javascript:;" class="btn btn-success rs_search_ticket" data-date="<%= journey_date %>" data-destination="<%= tempTrain.get("destination_id") %>">Search Ticket</a>
            </td>
        </tr>
        <% } %>
    </table>
</form>
<div id="rs_ticket_result">

</div>
<%@ include file='footer.jsp' %>