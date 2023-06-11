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
                        <label>Name</label>
                        <input type="text" name="name" class="form-controller">
                    </div>
                    <div class="input-group">
                        <label>Add</label>
                        <input type="text" name="add" class="form-controller">
                    </div>
                    <div class="input-group">
                        <label>Map</label>
                        <input type="text" name="map" class="form-controller">
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
                    <td>Add</td>
                    <td>Map</td>
                    <td>action</td>
                </tr>
                <c:forEach var="i" items="${list}">
                    <tr>
                    <div style="display: none"id="myDIV${i.ID}">${i.map}</div> 
                        <td>${i.ID}</td>
                        <td>${i.name}</td>
                        <td>${i.add}</td>
                        <td><button class="tab-button" onclick="myFunction(${i.ID})">map</button></td>                      
                        <td><a href="station?delete=${i.ID}" class="btn btn-sm btn-danger">Delete</a></td>   
                        
                    </tr>
                                   
                </c:forEach>

            </table>
        </div>  


    </div>
</div>
<script type="text/javascript">
  function myFunction(a) {
    var x = document.getElementById('myDIV'+a);
    if (x.style.display === 'none') {
        x.style.display = 'block';
    } else {
        x.style.display = 'none';
    }
  }
</script>
<%@ include file="footer.jsp" %>