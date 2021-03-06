<%-- 
    Document   : nanoPubTabs
    Created on : 2 Oct, 2014, 2:47:06 PM
    Author     : rajaram
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<title>Nanopublication</title>
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


<script id="drawGraphLocal">
    
    function init2(nodeJSON, linkJSON, div) {
        
        var $ = go.GraphObject.make;  // for conciseness in defining templates
        myDiagram =
          $(go.Diagram, div,  // must name or refer to the DIV HTML element
          
            { 
                // start everything in the middle of the viewport
                initialContentAlignment: go.Spot.Center,
                // have mouse wheel events zoom in and out instead of scroll up and down
                "toolManager.mouseWheelBehavior": go.ToolManager.WheelZoom,
                // enable undo & redo
                "undoManager.isEnabled": true,
                //"isReadOnly": true, // for the delete command
                "allowDelete": false, // for the delete command
            }
                  
                );

        var radgrad = $(go.Brush, go.Brush.Radial, { 0: "rgb(240, 240, 240)", 0.3: "rgb(240, 240, 240)", 1: "rgba(240, 240, 240, 0)" });
        var yellowgrad = $(go.Brush, go.Brush.Linear, { 0: "rgb(254, 201, 0)", 1: "rgb(254, 162, 0)" });

        // define the Node template for non-terminal nodes
        myDiagram.nodeTemplate =
          $(go.Node, "Auto",  // the whole node panel
            // define the node's outer shape, which will surround the TextBlock
            $(go.Shape, "Rectangle",
              { fill: yellowgrad,
                stroke: "black" }),
            $(go.TextBlock,
              { font: "bold 10pt helvetica, bold arial, sans-serif",
                margin: 4 },
              new go.Binding("text", "text"))
          );

        // replace the default Link template in the linkTemplateMap
        myDiagram.linkTemplate =
          $(go.Link,  // the whole link panel
            $(go.Shape,  // the link shape
              { isPanelMain: true,
                stroke: "black" }),
            $(go.Shape,  // the arrowhead
              { toArrow: "standard",
                stroke: null }),
            $(go.Panel, "Auto",
              $(go.Shape,  // the link shape
                { fill: radgrad, stroke: null }),
              $(go.TextBlock,  // the label
                { textAlign: "center",
                  font: "10pt helvetica, arial, sans-serif",
                  stroke: "#919191",
                  margin: 4 },
                new go.Binding("text", "text"))
            )
          );

        // create the model for the concept map
        var nodeDataArray = nodeJSON;
        var linkDataArray = linkJSON;
        
        myDiagram.model = new go.GraphLinksModel(nodeDataArray, linkDataArray);

        myDiagram.layout =
          $(go.ForceDirectedLayout,
            { defaultSpringLength: 30,
              defaultElectricalCharge: 100 });   

        }

    </script>


    <script>

    function init3() {                    
        init2(${npJSON.headNodesJSON}, ${npJSON.headLinksJSON}, "headGraph");
        init2(${npJSON.assertionNodesJSON}, ${npJSON.assertionLinksJSON}, "assertionGraph");            
        init2(${npJSON.provenanceNodesJSON}, ${npJSON.provenanceLinksJSON}, "provenanceGraph");            
        init2(${npJSON.publicationInfoNodesJSON}, ${npJSON.publicationInfoLinksJSON}, "publicationInfoGraph");
        
        $(document).ready(function(){
            $("#headGraph").hide();
            $("#assertionGraph").hide();
            $("#provenanceGraph").hide()
            $("#publicationInfoGraph").hide()
            
        });
        
       
    }
    
</script>

<script> 
$(document).ready(function(){    
    
  $("#head").click(function(){
      
      
      $("#head").css("background","#9DC45F")
      $("#assertion").css("background","#4C4532")
      $("#provenance").css("background","#4C4532")
      $("#publicationInfo").css("background","#4C4532")
      
    $("#assertionGraph").hide();
    $("#provenanceGraph").hide()
    $("#publicationInfoGraph").hide()
    
    $("#headGraph").show();
  });
  
  $("#assertion").click(function(){
      
      $("#head").css("background","#4C4532")
      $("#assertion").css("background","#9DC45F")
      $("#provenance").css("background","#4C4532")
      $("#publicationInfo").css("background","#4C4532")
      
    $("#headGraph").hide();
    $("#provenanceGraph").hide()
    $("#publicationInfoGraph").hide()
      
    $("#assertionGraph").show();
  });
  
  $("#provenance").click(function(){
      
      $("#head").css("background","#4C4532")
      $("#assertion").css("background","#4C4532")
      $("#provenance").css("background","#9DC45F")
      $("#publicationInfo").css("background","#4C4532")
      
    $("#headGraph").hide();
    $("#assertionGraph").hide()
    $("#publicationInfoGraph").hide()
    
    $("#provenanceGraph").show();
  });
  
  $("#publicationInfo").click(function(){
      
      $("#head").css("background","#4C4532")
      $("#assertion").css("background","#4C4532")
      $("#provenance").css("background","#4C4532")
      $("#publicationInfo").css("background","#9DC45F")
      
    $("#headGraph").hide();
    $("#assertionGraph").hide()
    $("#provenanceGraph").hide()
    
    $("#publicationInfoGraph").show();
  }); 
  
});


</script>


<!--<script> 
$(document).ready(function(){  
  $("#assertion").click(function(){
    $("#assertionGraph").slideToggle("slow");
  });
});
</script>-->

<style> 
.selected
{
/*padding:5px;
text-align:center;
background-color: #9DC45F;
border:2px solid #a1a1a1;
border-radius:25px;
display: inline-block*/

display: inline-block;
padding:5px;
background: #9DC45F;
letter-spacing: 0.20em;
text-decoration: none;
text-transform: uppercase;
font-weight: 400;
font-size: 0.90em;
color: #FFF;
}

.unselected
{
display: inline-block;
padding:5px;
background: #4C4532;
letter-spacing: 0.20em;
text-decoration: none;
text-transform: uppercase;
font-weight: 400;
font-size: 0.90em;
color: #FFF;
}

.graphLocal {
  padding:200px;
  border-radius:25px;
  overflow: hidden;
  border:2px solid #a1a1a1;
}

#tabs .ui-tabs-active {
  background: yellow;
}
</style>
    
</head>
<body onload="init3()"> 
<!--<body onload="init3()">-->
    
    <div id="header" class="container">
	<div id="logo">		
            <h1><a href="#">Nanopub Viz</a></h1>
	</div>
    </div>
    
    
    <div id="page"> 
        <div class="container">	            
            <div class="title">	                
                <h2>Visualize nanopublication</h2>			
                <p><strong>This</strong> page help you to visualize the contents of the <strong>nanopublication</strong>. All four graph's(Head, Assertion, Provenance and PublicationInfo) content will be shown as a graph in the container shown below. The <a href="http://www.gojs.net/latest/index.html" rel="nofollow">GoJS</a> java script library is used to draw these graphs.</p>
                <!--<p><strong>(Note):</strong> At this moment the application won't check the validity of nanopublication. </p>-->
            </div>
            

            
            <div align="center">
                
                <div class="unselected" id="head">HEAD GRAPH</div>
                <div class="unselected" id="assertion">ASSERTION GRAPH</div>
                <div class="unselected" id="provenance">PROVENANCE GRAPH</div>
                <div class="unselected" id="publicationInfo">PUBLICATIONINFO GRAPH</div>
                
                
            </div>
           
           
           <div class="graphLocal" id="headGraph"> </div>          
           
           <div class="graphLocal" id="assertionGraph"> </div>               
           
           <div class="graphLocal" id="provenanceGraph"> </div>                     
            
           <div class="graphLocal" id="publicationInfoGraph" > </div>
            
            
        </div>
    </div>   
    
    
    <div id="copyright" class="container">	
       <p>&copy; Copyright 2014 - <a href="http://biosemantics.org/">Biosemantics Group</a>.</p></div> 
</body>
</html>

