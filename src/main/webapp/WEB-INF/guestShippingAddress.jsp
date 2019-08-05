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
        @import url(https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900);
        .contact-form {
        margin-top: 30px;
      
        }
        .contact-form .input-block {
        background-color: rgba(255, 255, 255, 0.8);
        border: solid 2px #7851a9;
        width: 100%;
        height: 60px;
        padding: 25px;
        position: relative;
        margin-bottom: 20px;
        -moz-transition: all 0.3s ease-out;
        -o-transition: all 0.3s ease-out;
        -webkit-transition: all 0.3s ease-out;
        transition: all 0.3s ease-out;
        }
        .contact-form .input-block.focus {
        background-color: #fff;
        border: solid 1px #5C6A68;
        }
        .contact-form .input-block.textarea {
        height: auto;
        }
        .contact-form .input-block.textarea .form-control {
        height: auto;
        resize: none;
        }
        .contact-form .input-block label {
        position: absolute;
        left: 25px;
        top: 25px;
        display: block;
        margin: 0;
        font-weight: 300;
        z-index: 1;
        color: #333;
        font-size: 18px;
        line-height: 10px;
        }
        .contact-form .input-block .form-control {
        background-color: transparent;
        padding: 0;
        border: none;
        -moz-border-radius: 0;
        -webkit-border-radius: 0;
        border-radius: 0;
        -moz-box-shadow: none;
        -webkit-box-shadow: none;
        box-shadow: none;
        height: auto;
        position: relative;
        z-index: 2;
        font-size: 18px;
        color: #333;
        }
        .contact-form .input-block .form-control:focus label {
        top: 0;
        }
        .contact-form .square-button {
        background-color: #5C6A68;
        color: #3E007F;
        font-size: 26px;
        text-transform: uppercase;
        font-weight: 700;
        text-align: center;
        -moz-border-radius: 2px;
        -webkit-border-radius: 2px;
        border-radius: 2px;
        -moz-transition: all 0.3s ease;
        -o-transition: all 0.3s ease;
        -webkit-transition: all 0.3s ease;
        transition: all 0.3s ease;
        padding: 0 60px;
        height: 60px;
        border: none;
        width: 100%;
        }
        .contact-form .square-button:hover, .contact-form .square-button:focus {
        background-color: black;
        }

        @media (min-width: 768px) {
        .contact-wrap {
            width: 60%;
            margin: auto;
        }
        }
        /*----page styles---*/
        body {
        background-image: url('data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4gPHN2ZyB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+PGRlZnM+PGxpbmVhckdyYWRpZW50IGlkPSJncmFkIiBncmFkaWVudFVuaXRzPSJvYmplY3RCb3VuZGluZ0JveCIgeDE9IjAuMCIgeTE9IjAuNSIgeDI9IjEuMCIgeTI9IjAuNSI+PHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmNTEyZiIvPjxzdG9wIG9mZnNldD0iMTAwJSIgc3RvcC1jb2xvcj0iI2RkMjQ3NiIvPjwvbGluZWFyR3JhZGllbnQ+PC9kZWZzPjxyZWN0IHg9IjAiIHk9IjAiIHdpZHRoPSIxMDAlIiBoZWlnaHQ9IjEwMCUiIGZpbGw9InVybCgjZ3JhZCkiIC8+PC9zdmc+IA==');
        background-size: 100%;
        background-image: -webkit-gradient(linear, 0% 50%, 100% 50%, color-stop(0%, #23262A), color-stop(100%, #23262A));
        background-image: -moz-linear-gradient(left, #23262A, #23262A);
        background-image: -webkit-linear-gradient(left, #23262A, #23262A);
        background-image: linear-gradient(to right, #23262A, #23262A);
        font-family: 'Roboto', sans-serif;
        }
        select{
            margin-top:-15px;
            margin-left:-10px;
        }
        option{
                        font-family:'Roboto', sans-serif;
            font-weight:300;

        }
        .contact-wrap {
        padding: 15px;
        }

        h1 {
        background-color: black;
        color: #3E007F;
        padding: 40px;
        margin: 0 0 50px;
        font-size: 30px;
        text-transform: uppercase;
        font-weight: 700;
        text-align: center;
        }
        h1 small {
        font-size: 18px;
        display: block;
        text-transform: none;
        font-weight: 300;
        margin-top: 10px;
        color: #ff7c62;
        }

        .made-with-love {
        margin-top: 40px;
        padding: 10px;
        clear: left;
        text-align: center;
        font-size: 10px;
        font-family: arial;
        color: #fff;
        }
        .made-with-love i {
        font-style: normal;
        color: #F50057;
        font-size: 14px;
        position: relative;
        top: 2px;
        }
        .made-with-love a {
        color: #fff;
        text-decoration: none;
        }
        .made-with-love a:hover {
        text-decoration: underline;
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
    <section class="contact-wrap">
        <h1>Shipping Address</h1>        
            <form:errors path="guestShippingAddress.*"/>
            <form:form method="POST" action="/guest/guestShippingAddress" modelAttribute="guestShippingAddress" class="contact-form">
                <div class="col-sm-12">
                    <div class="input-block">
                        <p>
                            <form:label path="firstName">First Name:</form:label>
                            <form:input path="firstName" class="form-control"/>
                        </p>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="input-block">
                        <p>
                            <form:label path="lastName">Last Name:</form:label>
                            <form:input path="lastName" class="form-control"/>
                        </p>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="input-block">
                        <p>
                            <form:label path="address">Address:</form:label>
                            <form:input path="address" class="form-control"/>
                        </p>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="input-block">
                        <p>
                            <form:label path="city">City:</form:label>
                            <form:input path="city" class="form-control"/>
                        </p>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="input-block">
                        <p>
                            <form:label path="state">State:</form:label>
                            <form:input path="state" class="form-control"/>
                        </p>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="input-block">
                        <p>
                            <form:label path="zipcode">Zip Code:</form:label>
                            <form:input path="zipcode" class="form-control"/>
                        </p>
                    </div>
                </div>
                <div class="col-sm-12">
                    <button class="square-button" type="submit">Add</button>
                </div>
            </form:form>
        </section>
    <script>
        $('.contact-form').find('.form-control').each(function() {
        var targetItem = $(this).parent();
        if ($(this).val()) {
            $(targetItem).find('label').css({
            'top': '10px',
            'fontSize': '14px'
            });
        }
        })
        $('.contact-form').find('.form-control').focus(function() {
        $(this).parent('.input-block').addClass('focus');
        $(this).parent().find('label').animate({
            'top': '10px',
            'fontSize': '14px'
        }, 300);
        })
        $('.contact-form').find('.form-control').blur(function() {
        if ($(this).val().length == 0) {
            $(this).parent('.input-block').removeClass('focus');
            $(this).parent().find('label').animate({
            'top': '25px',
            'fontSize': '18px'
            }, 300);
        }
        });
    $('.nav-link').hover(function(){
        $(this).css("color","white");
    });
    $('.navbar-brand').hover(function(){
        $(this).css("color","white");
    });
    </script>
</body>
</html>
