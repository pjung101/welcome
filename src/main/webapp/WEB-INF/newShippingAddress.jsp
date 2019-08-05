<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.0/css/bootstrap.css">
    <link href= "https://bootswatch.com/4/slate/bootstrap.min.css" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>

    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
    
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <style>
        
        .red{
            color:red;
        }
        .black{
            color:black;
        }
        .navbar-light .navbar-brand, .navbar-light .navbar-nav .nav-link{
            color: white;
        }
        .navbar{
            margin-bottom:20px;
        }
        button{
            margin-bottom:20px;
            display:inline-block;
                        vertical-align:middle;
            
        }
        input{
            display:inline-block;
        }
        p{
            display:inline-block;
            vertical-align:middle;
            margin-right:10px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
        <a class="navbar-brand" href="/">Welcome!</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
            <c:if test="${user.adminLevel != 9 && user.adminLevel != 1}">
            <li class="nav-item">
                <a class="nav-link" href="/signupUser">Log in as User</a>
            </li>
            </c:if>
            <c:if test="${user.adminLevel == 9 || user.adminLevel == 1}">
            <li class="nav-item">
                <a class="nav-link" href="/editUser">Edit Account Settings</a>
            </li>
            
            </c:if>
            <c:if test="${user.adminLevel != null || user.adminLevel == 9 || user == null && vendor == null}">
            <li class="nav-item">
                <a class="nav-link" href="/signupVendor">Log in as Vendor</a>
            </li>
            </c:if>
            <c:if test="${user.adminLevel != 9 && user.adminLevel != 1 && vendor != null}">
            <li class="nav-item">
                <a class="nav-link" href="/vendor/editVendorAccount">Edit Account Settings</a>
            </li>
            </c:if>
            
            <li class="nav-item">
                <a class="nav-link" href="/cart">Go to Cart</a>
            </li>
            <c:if test="${user.firstName != null || vendor != null}">
            <li class="nav-item">
                <a class="nav-link" href="/logout">Log out</a>
            </li>
            </c:if>
            </ul>
        </div>
    </nav>
    <div class = "container">
        <h1>New Shipping Address</h1>        
        <div class="shipping">
            <form:errors path="newShippingAddress.*"/>
            <form:form method="POST" action="/user/newShippingAddress" modelAttribute="newShippingAddress">
                <p>
                    <form:label path="firstName">First Name:</form:label>
                    <form:input path="firstName"/>
                </p>
                <p>
                    <form:label path="lastName">Last Name:</form:label>
                    <form:input path="lastName"/>
                </p>
                <p>
                    <form:label path="address">Address:</form:label>
                    <form:input path="address"/>
                </p>
                <p>
                    <form:label path="city">City:</form:label>
                    <form:input path="city"/>
                </p>
                <p>
                    <form:label path="state">State:</form:label>
                    <form:input path="state"/>
                </p>
                <p>
                    <form:label path="zipcode">Zip Code:</form:label>
                    <form:input path="zipcode"/>
                </p>
                
                <input type="submit" value="Add"/>
            </form:form>
        </div>
        
    </div>
    <script>
    $('.nav-link').hover(function(){
        $(this).css("color","white");
    });
    $('.navbar-brand').hover(function(){
        $(this).css("color","white");
    });
    </script>
</body>
</html>