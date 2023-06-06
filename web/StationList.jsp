<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<div class="box successfully_purschase_ticket">

    <div class="box_title">
        <div class="row">
            <div class="col-xs-12 col-sm-4">
                Create New Station
            </div>
            <div class="col-xs-12 col-sm-8">
                All Stations
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-4">

            <form class="register_form" action="station">
                <div class="rs_form_box" style="margin:15px; padding:0; border:0;">
                    <div class="input-group">
                        <label>ID</label>
                        <input type="text" name="id" class="form-controller">
                    </div>
                    <div class="input-group">
                        <label>Name</label>
                        <input type="text" name="name" class="form-controller">
                    </div>
                    <div class="input-group">
                        <label>Contact</label>
                        <input type="text" name="contact" class="form-controller">
                    </div>
                    <div class="input-group">
                        <label>Address</label>
                        <input type="text" name="address" class="form-controller">
                    </div>
                    <div class="text-center">
                        <div class="rs_btn_group">
                            <button class="btn btn-default pull-left" name="createStation" value="submit" type="submit">Save</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-xs-12 col-sm-8">
            <br>
            <table class="table table-bordered">
                <tr><td>ID</td>
                    <td>Name</td>
                    <td>Contact</td>
                    <td>Address</td>
                    <td>action</td>
                </tr>
                <c:forEach var="i" items="${list}">
                    <tr>
                        <td>${i.id}</td>
                        <td>${i.name}</td>
                        <td>${i.contact}</td>
                        <td>${i.address}</td>
                        <td><a href="station?delete=${i.id}" class="btn btn-sm btn-danger">Delete</a></td>
                    </tr>
                </c:forEach>




            </table>
        </div>


    </div>

</div>
<%@ include file="footer.jsp" %>