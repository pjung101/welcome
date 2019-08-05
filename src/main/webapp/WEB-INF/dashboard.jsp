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
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">


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
        .side-bar{
            display:inline-block;
            width:200px;
                        vertical-align:top;

        }
        .table{
            color: black;
            text-align: center;
        }
        .dtabler{
            display:inline-block;
            vertical-align:top;
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
        .nav-link{
            color:white;
        }
        img{
            width:100px;
            height:100px;
        }
        .makeSmaller{
            width:101px;
        }
        a{
            color:white;
        }
        h2{
            color:#1AB188;
        }
        a:hover{
            color:#1AB188;
        }
        a:focus{
            color:#1AB188;
        }
        ul{
            display:none;
        }
        h2{
            border:2px solid #1AB188;
            padding:10px;
            border-radius:5px;
        }
        .shazam{
            border:2px solid #1AB188;
            padding:10px;
            border-radius:5px;
            background-color:transparent;
            color:#1AB188;
            transition-property:background-color color border;
            transition-duration:2s;
        }
        .shazam:hover{
            border:2px solid white;
            padding:10px;
            border-radius:5px;
            background-color:#1AB188;
            color:white;
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
    
    <div class='container side-bar'>
        <h2 class="shazam">Categories</h2>
        <ul class="blackAtom">
            <c:forEach items="${categories}" var="category">
                <li><a href="#" class="filter">${category.name}</a></li>
            </c:forEach>
        </ul>
    </div>
    <div class='container dtabler'>
        <table id="table_id" class="display">
            <thead>
                <tr>
                    <th>Image</th>
                    <th>Product Name</th>
                    <th>Description</th>
                    <th>Category</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${products}" var = "product">
                    <tr>
                        <td class="makeSmaller">
                            <a href="/user/${product.id}/details">
                                <img src = "${product.imageHash}">
                            </a>
                        </td>
                        <td><p>${product.name}</p></td>
                        <td><p>${product.description}</p></td>
                        <td><p>${product.category.name}</p></td>
                        <td><p>$ ${String.format("%.2f", product.price)}</p></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    
    <script>
        $(document).ready( function () {
            var table = $('#table_id').DataTable();
            $('.filter').click(function(){
                table
                    .column(3)
                    .data()
                    .search($(this).text())
                    .draw();
            })
            $('.nav-link').hover(function(){
                $(this).css("color","white");
            });
            $('.navbar-brand').hover(function(){
                $(this).css("color","white");
            });
            $('.shazam').click(function(){
                $('.blackAtom').slideToggle("slow");
            });
            

        } );

    </script>
</body>
</html>