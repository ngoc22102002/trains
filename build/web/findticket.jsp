<%@page import="java.util.*,model.*,DAO.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*,DAO.*,controller.*,model.*,filter.*" %>
<%@ include file="header.jsp" %>
<head>
    <style>
        .non{
            width: 30px;
            height: 30px;
            border: 1px solid black;
            text-align: center;
            font-weight: bold;
            cursor: pointer;           
        }

        .seat-selected{
            background-color: green;
        }
        .selected{
            background-color: red;
        }
        table {
            border-collapse: collapse;
            width: 100%;
        }

        table td {
            padding: 8px;
            border: 1px solid #ddd;
            text-align: left;
            background-color: #138496;
        }

        table td + td {
            padding-left: 20px;
        }
    </style>   
</head>
<%
        String destination=request.getParameter("destination");
        String date=request.getParameter("date");       
        dao d=new dao();
        String[] a =destination.split(",");
        Destination d1=d.getDestination(a[0]);
        Destination d2=d.getDestination(a[a.length-1]);
        Stations s1=d.getStation(d1.getStation_from());
        Stations s2=d.getStation(d2.getStation_to());      
        trains tr=d.getTrains(d1.getTrain_id());     
        int num=Integer.parseInt(tr.getTotal_seat());
%>
<form class="ticket_selecting_form" action="buy" method="post">
    <input name="destination" value="<%=destination%>" style="display: none"/>
    <input name="date" value="<%=date%>" style="display: none"/>
    <h2 class="title">  <span>Train Route Showing For ::</span><%=s1.getName()%>  to  <%=s2.getName()%> <span>:: Journey Date -</span> <%=date%></h2>
    <table>
        <%for(int i=0;i<4;i++){%>
        <tr>
            <% for(int j=(i*num/4)+1;j<=((i+1)*num)/4;j++){
     if(list.contains(j)){%>
            <td class="selected"><a><%=j%></a></td>&nbsp;&nbsp;
                <%}else{%>
            <td><a href="buy?num=<%=j%>&destination=<%=destination%>&date=<%=date%>" ><%=j%></a></td>

            <%}}}%>
        </tr>

    </table>
</form>

<%@ include file='footer.jsp' %>