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
        .container{
            display:inline-block;
        }
        .scontainer{
            width:40%;
            margin-top:20px;
        }
        .xcontainer{
            display:block;
            margin:auto;
            margin-top:20px;
        }
        .navbar{
            margin-bottom:20px;
        }
        button{
            margin-top:20px;
            margin-bottom:20px;
            display:block;
            vertical-align:middle;
            margin-left:14px;
            
        }
        .checkout a{
            display:block;
            margin-bottom:10px;
        }
        input{
            display:inline-block;
        }
        p{
            display:inline-block;
            vertical-align:middle;
            margin-right:10px;
        }
        .radiospace{
            margin: 0px 10px 0px 0px;
            vertical-align:middle;
        }
        .radiospace1{
            margin: 0px 10px 0px 10px;
            vertical-align:middle;

        }
        h1{
            color:#1AB188;
        }
        h3{
            color:#1AB188;
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
            <c:if test="${(user.adminLevel != 9 && user.adminLevel != 1) || (user.adminLevel == 9 && user.firstName == null)}">
            <li class="nav-item">
                <a class="nav-link" href="/signupUser">Log in as User</a>
            </li>
            </c:if>
            <c:if test="${(user.adminLevel == 9 && user.firstName != null) || user.adminLevel == 1}">
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
        <%-- ADD COUNT OF ITEMS IN CART --%>
        <h1>Checkout</h1>
        
        <div class="checkout">
            <form:errors path="order.*"/>
            <form:form method="POST" action="/checkout" modelAttribute="order">
            <div class="xcontainer">
                <h3>Select Shipping Speed</h3>
                <p>
                    <form:radiobutton class="radiospace" path="shippingSpeed" value="ground" checked = "checked"/>GroundShipping
                    <form:radiobutton class="radiospace1" path="shippingSpeed" value="express"/>Express Shipping
                </p>
            </div>
            <div class='container scontainer'>
                <h3>Select Shipping</h3>
                <p>
                <%-- CHECK PATH NAMES --%>
                    <form:select path="shippingAddress">
                        <c:forEach items="${user.shippingAddresses}" var = "shippingAddress">
                            <form:option value="${shippingAddress}">${shippingAddress.address}</form:option>
                        </c:forEach>
                    </form:select>
                </p>
                <c:if test="${user.firstName != null || vendor != null}">
                    <a href="/user/qAddShippingAddress">Add Shipping Address</a>
                </c:if>
            </div>
            <div class='container scontainer'>
                <h3>Select Billing</h3>
                <p>
                    <%-- CHECK PATH NAMES --%>
                    <form:select path="billing">
                        <c:forEach items="${user.billings}" var = "billing">
                            <form:option value="${billing}">${billing.address}</form:option>
                        </c:forEach>
                    </form:select>
                </p>
                <c:if test="${user.firstName != null || vendor != null}">
                    <a href="/user/quasiAddBilling">Add Billing Information</a>
                </c:if>
            </div>
            <br>
            <button class = "btn btn-secondary btn-sm" type="submit">Complete Order</button>

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