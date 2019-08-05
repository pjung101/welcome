<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link href= "https://bootswatch.com/4/slate/bootstrap.min.css" rel="stylesheet">

    <style>
    *, *:before, *:after {
    box-sizing: border-box;
    }
    html {
    overflow-y: scroll;
    }
    body {
    background: #272B30;
    font-family: 'Titillium Web', sans-serif;
    }
    a {
    text-decoration: none;
    color: #1ab188;
    transition: .5s ease;
    }
    a:hover {
    color: #179b77;
    }
    .form {
    background: rgba(19, 35, 47, 0.9);
    padding: 40px;
    max-width: 600px;
    margin: 40px auto;
    border-radius: 4px;
    box-shadow: 0 4px 10px 4px rgba(19, 35, 47, 0.3);
    }
    .tab-group {
    list-style: none;
    padding: 0;
    margin: 0 0 40px 0;
    
    }
    .tab-group:after {
    content: "";
    display: table;
    clear: both;
    }
    .tab-group li a {
    display: block;
    text-decoration: none;
    padding: 15px;
    background: rgba(160, 179, 176, 0.25);
    color: #a0b3b0;
    font-size: 20px;
    margin:auto;
    width: 50%;
    text-align: center;
    cursor: pointer;
    transition: .5s ease;
    }
    .tab-group li a:hover {
    background: #179b77;
    color: #ffffff;
    }
    .tab-group .active a {
    background: #1ab188;
    color: #ffffff;
    }
    .tab-content > div:last-child {
    display: none;
    }
    h1 {
    text-align: center;
    color: #ffffff;
    font-weight: 300;
    margin: 0 0 40px;
    }
    label {
    position: absolute;
    -webkit-transform: translateY(6px);
            transform: translateY(2px);
    left: 13px;
    color: rgba(255, 255, 255, 0.5);
    transition: all 0.25s ease;
    -webkit-backface-visibility: hidden;
    pointer-events: none;
    font-size: 22px;
    }
    label .req {
    margin: 2px;
    color: #1ab188;
    }
    label.active {
    -webkit-transform: translateY(50px);
            transform: translateY(50px);
    left: 2px;
    font-size: 14px;
    }
    label.active .req {
    opacity: 0;
    }
    label.highlight {
    color: #ffffff;
    }
    input, textarea {
    font-size: 22px;
    display: block;
    width: 100%;
    height: 100%;
    padding: 5px 10px;
    background: none;
    background-image: none;
    border: 1px solid #a0b3b0;
    color: #ffffff;
    border-radius: 0;
    transition: border-color .25s ease, box-shadow .25s ease;
    }
    input:focus, textarea:focus {
    outline: 0;
    border-color: #1ab188;
    }
    textarea {
    border: 2px solid #a0b3b0;
    resize: vertical;
    }
    .field-wrap {
    position: relative;
    margin-bottom: 40px;
    }
    .top-row:after {
    content: "";
    display: table;
    clear: both;
    }
    .top-row > div {
    float: left;
    width: 48%;
    margin-right: 4%;
    }
    .top-row > div:last-child {
    margin: 0;
    }
    .button {
    border: 0;
    outline: none;
    border-radius: 0;
    padding: 15px 0;
    font-size: 2rem;
    font-weight: 600;
    text-transform: uppercase;
    letter-spacing: .1em;
    background: #1ab188;
    color: #ffffff;
    transition: all 0.5s ease;
    -webkit-appearance: none;
    }
    .button:hover, .button:focus {
    background: #179b77;
    }
    .button-block {
    display: block;
    width: 100%;
    }
    .forgot {
    margin-top: -20px;
    text-align: right;
    }
    p{
        color:red;  
    }
    </style>
</head>
<body>

    <div class="form">
        
        <ul class="tab-group">
            <li class="tab active"><a href="#login">Log In</a></li>
        </ul>
        
        <div class="tab-content">
            <div id="login">
                <h1>Login</h1>
                <p><c:out value="${error}" /></p>
                <form method="post" action="/adminLogin">
                    <div class="field-wrap">
                        <p>
                            <label type="email" for="email">Email</label>
                            <input type="text" id="email" name="email"/>
                        </p>
                    </div>
                    <div class="field-wrap">
                        <p>
                            <label for="password">Password</label>
                            <input type="password" id="password" name="password"/>
                        </p>
                    </div>
                    <input type="submit" class = "button button-block" value="Login!"/>
                </form>
            </div>
            <div></div>
            
        </div>
    </div>
    <script>
$('.form').find('input, textarea').on('keyup blur focus', function (e) {
  
    var $this = $(this),
        label = $this.prev('label');
        if (e.type === 'keyup') {
                if ($this.val() === '') {
            label.removeClass('active highlight');
            } else {
            label.addClass('active highlight');
            }
        } else if (e.type === 'blur') {
            if( $this.val() === '' ) {
                label.removeClass('active highlight'); 
                } else {
                label.removeClass('highlight');   
                }   
        } else if (e.type === 'focus') {
        
        if( $this.val() === '' ) {
                label.removeClass('highlight'); 
                } 
        else if( $this.val() !== '' ) {
                label.addClass('highlight');
                }
        }
    });
    
    $('.tab a').on('click', function (e) {
    
        e.preventDefault();
        
        $(this).parent().addClass('active');
        $(this).parent().siblings().removeClass('active');
        
        target = $(this).attr('href');
        $('.tab-content').not(target).show();
        
        $(target).fadeIn(600);
    
    });
    </script>
</body>
</html>