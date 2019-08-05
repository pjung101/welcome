<!-- chart.jsp-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.0/css/bootstrap.css">
    <link href= "https://bootswatch.com/4/slate/bootstrap.min.css" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>

    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
window.onload = function() {
var dps = [[]];
var chart = new CanvasJS.Chart("chartContainer", {
	theme: "dark1", // "light1", "light2", "dark1", "dark2"
	exportEnabled: true,
	animationEnabled: true,
	title: {
		text: "Product Category Breakdown"
	},
	data: [{
		type: "pie",
		startAngle: 25,
		toolTipContent: "<b>{label}</b>: {y}%",
		showInLegend: "true",
		legendText: "{label}",
		indexLabelFontSize: 16,
		indexLabel: "{label} - {y}",
		dataPoints: dps[[0]]
    }]
});
var yValue;
var label;
 
<c:forEach items="${categories}" var="category" varStatus = "loop" >	
	<c:if test="${numList.get(loop.index) != 0}">		
		yValue = ${numList.get(loop.index)};
		label = "${category.name}";
		dps[0].push({
			label : label,
			y : yValue,
		});			
	</c:if>
</c:forEach>
chart.render();

}
</script>
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

        .table{
            color: black;
            text-align: center;
            background-color: #21B5A5;
        }
        table.dataTable tbody tr{
            background-color: #21B5A5;
        }
        .dataTables_wrapper .dataTables_length, .dataTables_wrapper .dataTables_filter, .dataTables_wrapper .dataTables_info, .dataTables_wrapper .dataTables_processing, .dataTables_wrapper .dataTables_paginate{
            color:white;
        }
        .navbar{
            margin-bottom:20px;
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

<div id="chartContainer" style="margin:auto; height: 300px; width: 45%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
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