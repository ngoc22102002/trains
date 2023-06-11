<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>
<div class="signpage">
    <c:if test="${message!=null}">
        <div class="alert alert-danger"><p>${message}</p></div>
            </c:if>
    <form class="register_form" action="register" method="post">
        <div class="row">
            <div class="col-xs-12 col-sm-6">
                <div class="rs_form_box">
                    <h3 class="form_section_title">
                        Personal Information
                    </h3>
                    <div class="input-group">
                        <label>Passenger Name*</label>
                        <input type="text" name="name" class="form-controller" required="" autofocus="">
                    </div>
                    <div class="input-group">
                        <label>Email Address*</label>
                        <input type="email" name="email" class="form-controller" required="" autofocus="">
                    </div>

                    <div class="input-group">
                        <label>Password*</label>
                        <input type="password" name="password" class="form-controller" required="" autofocus="">
                    </div>

                    <div class="input-group">
                        <label>Re-Enter Password*</label>
                        <input type="password" name="password_con" class="form-controller" required="" autofocus="">
                    </div>
                    <div class="input-group">
                        <label>Date of birth</label>
                        <input type="date" name="dob" class="form-controller" required="" autofocus="">
                    </div>

                </div>

            </div>
            <div class="col-xs-12 col-sm-6">
                <div class="rs_form_box">
                    <h3 class="form_section_title">
                        Extra information
                    </h3>
                    <div class="input-group">
                        <label>Address</label>
                        <textarea name="address" class="form-controller"></textarea>
                    </div>
                    <div class="input-group">
                        <label>Phone Number</label>
                        <input type="textt" name="phone" class="form-controller" required="" autofocus="">
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-12 text-center">
                <div class="rs_btn_group">
                    <button class="btn btn-default pull-left" name="submit" type="submit">Register</button>
                    <a href="login" class="btn btn-default">Login here.</a>
                </div>
            </div>
        </div>
    </form>
</div>
<%@ include file="footer.jsp" %>