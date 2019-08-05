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
            display:block;
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
            display:block;
            vertical-align:middle;
        }
        hr{
            background-color:white;
        }
        select{
            display:block;
            margin-bottom:20px;
        }
        input{
            display:inline-block;
        }
        p{
            display:block;
            vertical-align:middle;
            margin-right:10px;
            margin-left:20px;
        }
        img{
            margin-bottom:10px;
            width: 80%;

        }
        textarea{
            display:block;
            margin-bottom:20px;
        }
        h1{
            color:#1AB188;
            margin-right:500px;
        }
        h2{
            color:#1AB188;
        }
        .right{
            display:inline-block;
            width:40%;
            vertical-align:middle;
        }
        .left{
            display:inline-block;
            width:40%;
            vertical-align:middle;
        }
        .green{
            color:#1AB188;  
        }
        a, h1{
            display:inline-block;
        }
        h5{
            color:#a742f4;
        }
        
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light">
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
    <div class = "container header">
        <h1>${product.name}</h1>
        <a href="/">Go Back</a>
    </div>
    <div class="container">
        <div class='container left'>
            <img src = "${product.imageHash}">
        </div>
        <div class = 'container right'>
            <label class="green">Price:</label>
            <label>$ ${product.price}</label>
            <form method="POST" action="/product/${product.id}/addToCart" >
            <label class="green">Quantity:</label>
                <select name="quantity">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
                <button class = "btn btn-secondary btn-sm" type="submit">Add To Cart</button>

            </form>
            <p>${success}</p>
            <label class="green">Product Description:</label>
            <br>
            <label>${product.description}</label>

        </div>
    </div>
    
    <div class = 'container' id = "reviewer">
        <h2>Reviews</h2>
        <c:forEach items="${product.reviews}" var = "review">
        <hr>
            <h5>${review.author.firstName} ${review.author.lastName} said:</h5>
            <p>${review.text}</p>
        </c:forEach>
        <p class="red">${error}</p>
        <form id = "reviewForm" action="/products/${product.id}/addReview" method = "post">
            <textarea name = "reviewText"></textarea>
            <button class = "btn btn-secondary btn-sm" type="submit">Add Review</button>
        </form>
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