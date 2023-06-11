<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*,DAO.*,model.*,filter.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <style>
        .containe {
            display: flex;
            justify-content: space-evenly;
        }

        .box {
            width: 200px;
            height: 200px;
            background-color: #333;
            color: #FFF;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            font-size: 20px;
            cursor: pointer;
            transition: all 0.2s ease-in-out;
        }

        .box:hover {
            transform: scale(1.05);
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
        }
        .train {
            display: flex;
            flex-wrap: nowrap;
            justify-content: center;
            align-items: center;
        }

        .carriage {
            width: 100px;
            height: 50px;
            background-color: #ccc;
            margin: 10px;
        }
        .toa{
            display: none;
        }
        .carriage:hover {
            transform: scale(1.05);
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
        }
        .ghe{
            display: none;
        }



    </style>
</head>
</head>
<%@ include file="header.jsp" %>
<body>
    <h3>${ta.get(0).ID}</h3>
    <div class="signpage">
        <div class="containe">
            <c:set var="a" value="0"/>
            <c:forEach var="i" items="${list}">
                <div style="display: flex"class="button box" >
                    <c:set var="a" value="${a+1}"/>
                    <h3>${i.ID}</h3>
                    <p>${i.time_come.get(0)} - ${i.time_go.get(t.time_go.size())}</p>
                </div>
            </c:forEach>

        </div>

        <c:forEach var="i" items="${list}">
            <div class="train toa" style="display: none">
                <div class="">${i.ID} </div>
                <c:set value="0" var="x"/>
                <c:forEach var="j" items="${i.toa}">    
                    <c:set value="${x+1}" var="x"/>
                    <div class="carriage">
                        <h3>${x}</h3>
                    </div>
                </c:forEach>    
            </div>
        </c:forEach>
        <c:forEach var="i" items="${list}">
            
            <c:forEach var="j" items="${i.toa}"> 
                <div class="ghe" style="display: none">
                    <h3>${i.ID}</h3>
                    <c:set value="0" var="y"/>                   
                    <c:forEach var="k" items="${j.ghe}">     
                        <c:set value="${y+1}" var="y"/>                        
                            <h3>${y}</h3>
                    </c:forEach> 
                </div> 
            </c:forEach>               
        </c:forEach>




    </div>
    <script>
        const buttons = document.querySelectorAll('.button');
        const boxes = document.querySelectorAll('.toa');
        const buttons1 = document.querySelectorAll('.carriage');
        const boxess = document.querySelectorAll('.ghe');
        buttons.forEach((button, index) => {
            button.addEventListener('click', () => {

                boxes.forEach((toa) => {
                    toa.style.display = 'none';
                });
                boxess.forEach((ghe) => {
                    ghe.style.display = 'none';
                });
                


                boxes[index].style.display = 'flex';
            });
        });
        buttons1.forEach((carriage, index) => {
            carriage.addEventListener('click', () => {

                boxess.forEach((ghe) => {
                    ghe.style.display = 'none';
                });


                boxess[index].style.display = 'flex';
            });
        });
    </script>

</body>
<%@ include file='footer.jsp' %>