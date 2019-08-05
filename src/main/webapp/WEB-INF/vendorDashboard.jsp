<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
    
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.0/css/bootstrap.css">
    <link href= "https://bootswatch.com/4/slate/bootstrap.min.css" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>

    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
    
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <style>
        .dataTables_wrapper .dataTables_paginate .paginate_button {
            background: linear-gradient(to bottom, #a2a2a2 0%, #5a5a5a 100%);
        }
        .red{
            color:red;
        }
        .black{
            color:black;
        }
        .navbar-light .navbar-brand, .navbar-light .navbar-nav .nav-link{
            color: white;
        }

        .table{
            color: black;
            text-align: center;
        }
        .hover{
            background-color:darkgrey;
        }
        .dataTables_wrapper .dataTables_length, .dataTables_wrapper .dataTables_filter, .dataTables_wrapper .dataTables_info, .dataTables_wrapper .dataTables_processing, .dataTables_wrapper .dataTables_paginate{
            color:white;
        }
        .navbar{
            margin-bottom:20px;
        }
        img{
            width:100px;
            height:100px;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-primary">
        <a class="navbar-brand" href="/vendor/dashboard">${vendor.companyName}</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/vendor/editVendorAccount">Edit Account<span class="sr-only"></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/signupUser">Log In as User</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/vendor/analytics">Analytics</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/vendor/addProduct">New Product</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout">Log out</a>
            </li>
             
             
            </ul>
        </div>
    </nav>
            

    
    
    
    
    <div class='container'>
        <table id="example" class="display">
            <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Category</th>
                    <th scope="col">Unit Price</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Image</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${vendor.products}" var = "product">
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>${product.category.name}</td>
                        <td>$ ${String.format("%.2f",product.price)}</td>
                        <td>${product.quantity}</td>
                        <td><img src = "${product.imageHash}"></td>
                        <td>
                            <a class = "black" href="/vendor/${product.id}/edit">Edit</a> | 
                            <a class="red" href="/vendor/${product.id}/delete">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script>
    $(document).ready( function () {
                    $('#example').DataTable();
    $('.nav-link').hover(function(){
        $(this).css("color","white");
    });
    $('.navbar-brand').hover(function(){
        $(this).css("color","white");
    });
                        
    });
    
    </script>
</body>
</html>