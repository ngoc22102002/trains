<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>
<div class="signpage">
    <form action="destinations" method="post">
        <c:if test="${message!=null}">
        <div class="alert alert-danger"><p>${message}</p></div>
        </c:if>
        <div class="register_form" >

            <div class="row">
                <div class="col-xs-12 col-sm-6 offset-sm-3">
                    <div class="rs_form_box">
                        <h3 class="form_section_title">
                            Information
                        </h3>
                        <div class="input-group">
                            <label>Select Train</label>
                            <select name="dst_train" class="form-control" style="width:auto;">  
                                <option value="">any train</option>                            
                                <c:forEach var="i" items="${trainlist}">
                                    <option value="${i.id}">${i.name}(${i.code}:${i.type})</option>
                                </c:forEach>
                                    
                            </select>
                        </div>

                        <div class="input-group">
                            <label>Station From</label>
                            <select name="station_from" class="form-control" style="width:auto;">
                                <c:forEach var="i" items="${stationList}">
                                    <option value="${i.id}">${i.name}</option>
                                </c:forEach> 
                            </select>                    
                        </div>

                    </div>

                </div>
                <div class="col-xs-12 col-sm-12 text-center">
                    <div class="rs_btn_group">
                        <button class="btn btn-default pull-left" name="search" value="1" type="submit">Search</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="rs_box" style="overflow:auto;">
            <div class="ticket_selecting_form" name="frm_deslist">
                <h2 class="title"> Train Name: ${dst_train} <br>Station From: ${station_from} </h2>
                <table class="table table_des_seat" id="table_des_list">
                    <tr>
                        <th>Station To</th>
                        <th>Time</th>
                        <th>Unit Fare</th>
                        <th width="50" align="center">Actions</th>
                    </tr>
                    <c:forEach var="i" items="${list}">                       
                        <tr>
                            <td>
                                <div class="input-group">
                                    <label>${i.get("2").name}</label>
                                </div>

                            </td>
                            <td>
                                <div class="input-group">
                                    <label>${i.get("1").time}</label>
                                </div>
                            </td>
                            <td>
                                <div class="input-group">
                                    <label>${i.get("1").fare}</label>
                                </div>
                            </td>
                            <td><a href="?delete=${i.get("1").id}" class="btn btn-danger btn-xs rv_destination" type="button">X</button></td>
                        </tr>
                    </c:forEach>



                </table>
                <div class="text-center">
                    <div class="rs_btn_group">
                        <button class="btn btn-success pull-left" name="save_all" type="submit" value="1">Save All</button>
                        <button id="btn_add_new_item" class="btn btn-info pull-left" name="search" type="button">Add New Item</button>
                    </div>
                </div>

                <table id="data_list_item" style="display:none;">


                    <tr>
                        <td>
                            <div class="input-group">
                                <select name="station_to[]" class="form-control">
                                    <c:forEach var="i" items="${stationList}">
                                        <option value="${i.id}">${i.name}</option>

                                    </c:forEach>
                                </select>
                            </div>

                        </td>
                        <td>
                            <div class="input-group">
                                <input class="form-contoller" name="jurny_time[]">
                            </div>
                        </td>
                        <td>
                            <div class="input-group">
                                <input class="form-contoller" name="fare[]">
                            </div>
                        </td>    
                        <td><button class="btn btn-danger btn-xs rv_destination" type="button">X</button></td>
                    </tr>
                </table>
            </div>
        </div>  
    </form>
</div>
<%@ include file="footer.jsp" %>