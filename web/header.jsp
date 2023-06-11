<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="css/assets/owl.carousel.min.css"/>
        <link rel="stylesheet" href="css/assets/owl.theme.default.css"/>
        <link rel="stylesheet" href="css/main.css"/>
    </head>
    <body>
        <div class="header">
            <div class="container">
                <div class="header_content ">
                    Ticketing System
                </div>
                <div class="main_nav">
                    <ul class="nav nav-pills pull-left">                                   
                        <c:if test="${sessionScope.acc!=null}">
                            <c:if test="${sessionScope.acc.rule=='2'}">
                                <li  class="nav-item"><a href="dashboard" class="nav-link">profile</a></li>
                                <li  class="nav-item"><a href="purchaseticket" class="nav-link">Purses Ticket</a></li>
                                <li  class="nav-item"><a href="train" class="nav-link">Trains</a></li>
                                <li  class="nav-item"><a href="station" class="nav-link">Stations</a></li>
                                <li  class="nav-item"><a href="destinations" class="nav-link">Destinations</a></li>
                                <li class="nav-item"><a href="logout" class="nav-link">Logout</a></li>
                                </c:if>
                                <c:if test="${sessionScope.acc.rule=='1'}">     
                                <li  class="nav-item"><a href="dashboard" class="nav-link">profile</a></li>
                                <li  class="nav-item"><a href="purchaseticket" class="nav-link">Purses Ticket</a></li>
                                <li class="nav-item"><a href="logout" class="nav-link">Logout</a></li>
                                </c:if>
                            </c:if>
                            <c:if test="${sessionScope.acc==null}">
                             <li  class="nav-item"><a href="purchaseticket" class="nav-link">Purses Ticket</a></li>
                            <li class="nav-item"><a href="register" class="nav-link">Register</a></li>
                            <li class="nav-item"><a href="login" class="nav-link">Login</a></li>
                            </c:if>
                    </ul>
                </div>
            </div>
        </div>
        <section class="main_contents">
            <div class="container">
                <div class="main_contents_inner" >
