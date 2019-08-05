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
            display:block;
            vertical-align:middle;
            margin-right:10px;
        }
        .table{
            text-align:center;
        }
        img{
            width:100px;
            height:100px;
        }
        .righter{
            display:flex;
            justify-content:flex-end;

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
            <c:if test="${user.firstName != null || vendor != null}">
            <li class="nav-item">
                <a class="nav-link" href="/logout">Log out</a>
            </li>
            </c:if>
            
            
            </ul>
        </div>
    </nav>
    <div class='container'>
        <c:choose>
            <c:when test="${user.firstName != null}">
                <table class="table table-dark">
                    <thead>
                        <tr>
                            <th scope="col">Image</th>
                            <th scope="col">Product</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${products}" var="product" varStatus = "loop">
                            <tr>
                                <td><img src = "${product.imageHash}"></td>
                                <td>${product.name}</td>
                                <td>$ ${product.price}</td>
                                <%-- ADD EDIT QUANTITY --%>
                                <td>${counts.get(loop.index)}</td>
                                <td>
                                    <%-- CHANGE TO DELETE FROM PRODUCT LIST --%>
                                    <a class="red" href="/user/${product.id}/delete">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class='righter'>
                <p>Subtotal: $ ${subtotal}</p>
                <p>Total Quantity: ${supersum}</p>
                <form action="/checkout">
                    <button class = "btn btn-secondary btn-sm" type="submit">Checkout</button>
                </form>
                </div>
            </c:when>
            <c:otherwise>
                <table class="table table-dark">
                <thead>
                    <tr>
                        <th scope="col">Image</th>
                        <th scope="col">Product</th>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${products}" var="product" varStatus = "loop">
                        <tr>
                            <td><img src = "${product.imageHash}"></td>
                            <td>${product.name}</td>
                            <td>$ ${product.price}</td>
                            <%-- ADD EDIT QUANTITY --%>
                            <td>${counts.get(loop.index)}</td>
                            <td>
                                <%-- CHANGE TO DELETE FROM PRODUCT LIST --%>
                                <a class="red" href="/guest/${product.id}/delete">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                </table>
                <div class='righter'>
                <p>Subtotal: $ ${subtotal}</p>
                
                <p>Total Quantity: ${supersum}</p>
                
                <%-- NEED TO CHANGE FORM ROUTE TO GUEST SHIPPING & THEN BILLING --%>
                <form action="/guest/guestShippingAddress">
                    <button class = "btn btn-secondary btn-sm" type="submit">Checkout</button>
                </form>
                </div>
            </c:otherwise>
        </c:choose>  
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