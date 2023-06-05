<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*,digitalbd.*,AllLayout.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
<div class="signpage">
    <form class="register_form" style="max-width:400px;" action="ticketdisplay" method="post">
        <div class="rs_form_box">
            <h3 class="form_section_title">
                PURCHASE TICKET
            </h3>
            <div class="form-group">
                <label>Station From :</label>
                <select class="form-control" name="from" id="from_where_select">
                    <c:forEach var="i" items="${allStations}">																
                        <option value="${i.ID}">${i.name}</option>
                    </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label>Journey Date :</label>
                    <input  class="form-control"type="date" id="from_where_select" name="journey_date" min="${date}" required="" autofocus="">            			



                </div>

                <div class="form-group">
                    <label>Station To :</label>
                    <select class="form-control" name="to" id="from_where_select">
                        <c:forEach var="i" items="${allStations}">
                        <option value="${i.ID}">${i.name}</option>
                        </c:forEach>
                    </select>
                </div>            
            </div>
            <div class="text-center">
                <div class="rs_btn_group">
                    <button class="btn btn-default" type="submit">Search</button>
                </div>
            </div>
        </form>
    </div>
 <%@ include file='footer.jsp' %>