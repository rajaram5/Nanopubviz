/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function makeGraph(nodeDataArray, linkDataArray) {
    if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this
    var $ = go.GraphObject.make;  // for conciseness in defining templates
    
    document.write(nodeDataArray);

    myDiagram =
      $(go.Diagram, "myDiagram",  // must name or refer to the DIV HTML element
        { autoScale: go.Diagram.Uniform,
          contentAlignment: go.Spot.Center });

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
    myDiagram.model = new go.GraphLinksModel(nodeDataArray, linkDataArray);

    myDiagram.layout =
      $(go.ForceDirectedLayout,
        { defaultSpringLength: 30,
          defaultElectricalCharge: 100 });
  }


