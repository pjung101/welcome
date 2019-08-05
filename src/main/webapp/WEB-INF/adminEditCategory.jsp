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
        <a class="navbar-brand" href="/admin/dashboard">Admin</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
            
            <li class="nav-item">
                <a class="nav-link" href="/admin/addCategory">Add Category</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout">Log out</a>
            </li>
            </ul>
        </div>
    </nav>
    <div class = "container">
        <h1>Edit ${originalCategory.name}</h1>
        <form:errors path="category.*"/>
        <p class="red">${error}</p>
        <form:form method="POST" action="/admin/${originalCategory.id}/edit" modelAttribute="category">
                <p>
                    <form:label path="name">Name:</form:label>
                    <form:input type="text" path="name"/>
                </p>
                                                <button class = "btn btn-secondary btn-sm" type="submit">Edit</button>

        </form:form>
        
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