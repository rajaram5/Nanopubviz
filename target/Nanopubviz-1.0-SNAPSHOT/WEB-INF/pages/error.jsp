<%-- 
    Document   : error
    Created on : 2 Oct, 2014, 10:46:52 AM
    Author     : Rajaram kaliyaperumal
    Version    : 0.1   
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<title>Error page</title>
<!-- Copyright 1998-2014 by Northwoods Software Corporation. -->
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/pure-min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/grid.css" />">
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link href="<c:url value="/resources/css/brown/default.css" />" rel="stylesheet" type="text/css" media="all" />
<link href="<c:url value="/resources/css/brown/fonts.css" />" rel="stylesheet" type="text/css" media="all" />
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

        

<script src="<c:url value="/resources/release/go.js" />"></script>


<style> 
#head,#assertion,#provenance,#publicationInfo
{
    padding:5px;
    text-align:center;
    background-color:#e5eecc;
    border:2px solid #a1a1a1;
    border-radius:25px;
}
.graphLocal {
    padding:200px;
    border-radius:25px;
    overflow: hidden;
    border:2px solid #a1a1a1;
}
.error{    
    color: #FF0000;
}
</style>
    
</head>
<body> 
<!--<body onload="init3()">-->
    
    <div id="header" class="container">
	<div id="logo">		
            <h1><a href="#">Nanopub Viz</a></h1>
	</div>
    </div>
    
    
    <div id="page"> 
        <div class="container">	            
            <div class="title">	                
                <h2>Error visualizing nanopublication</h2>			
                <p><strong class="error">Error message : </strong> ${serverErrorMessage} </p>                
            </div>
        </div>
    </div>   
    
    
    <div id="copyright" class="container">	
        <p>&copy; Untitled. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a> | Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</p>
    </div> 
</body>
</html>

