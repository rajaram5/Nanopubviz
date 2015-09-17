/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.biosemantics.nanopubviz.converter;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import nl.biosemantics.nanopubviz.pojo.Link;
import nl.biosemantics.nanopubviz.pojo.NanopubJSON;
import nl.biosemantics.nanopubviz.pojo.Node;
import org.nanopub.MalformedNanopubException;
import org.nanopub.Nanopub;
import org.nanopub.NanopubImpl;
import org.openrdf.OpenRDFException;
import org.openrdf.model.Literal;
import org.openrdf.model.URI;
import org.openrdf.model.Statement;
import org.openrdf.rio.RDFFormat;

/**
 * <p>
 * Class contains methods to convert nanopub object to nanopublication JSON
 * format. 
 * </p>
 * 
 * @author Rajaram kaliyaperumal
 * @version 0.1
 * @since 29-09-2014
 */
public class NanopubToJSON {
    
    private NanopubJSON nanopubJSON = null;   
    
    /**
     * <p>
     * Get nanopublication from the given URL and convert into GoJs 
     * (nodes and links) JSON format (e.g nodes and link json 
     * https://github.com/NorthwoodsSoftware/GoJS/blob/master/samples/conceptMap.html)
     * </p>
     * @param url   Nanopublication url
     * @param format    Nanopublication RDF format     
     */
    public NanopubToJSON (String uriStr, RDFFormat format) throws MalformedNanopubException, IOException, OpenRDFException {
        
        Nanopub np; 
        Map<String, String> jsons;
        this.nanopubJSON = new NanopubJSON();
        
        
        URL url =  new URL(uriStr);        
        np = new NanopubImpl(url, format);           
            
        jsons =  convertToJSON(np.getHead());
        nanopubJSON.setHeadNodesJSON(jsons.get("node"));
        nanopubJSON.setHeadLinksJSON(jsons.get("link"));
            
        jsons =  convertToJSON(np.getAssertion());
        nanopubJSON.setAssertionNodesJSON(jsons.get("node"));
        nanopubJSON.setAssertionLinksJSON(jsons.get("link"));
            
        jsons =  convertToJSON(np.getProvenance());
        nanopubJSON.setProvenanceNodesJSON(jsons.get("node"));
        nanopubJSON.setProvenanceLinksJSON(jsons.get("link"));
            
        jsons =  convertToJSON(np.getPubinfo());
        nanopubJSON.setPublicationInfoNodesJSON(jsons.get("node"));
        nanopubJSON.setPublicationInfoLinksJSON(jsons.get("link"));
        
        
    }
    
    /**
     * <p>
     * Convert RDF Set<statement> to GoJS format.
     * </p>
     * @param statements Set of statements
     * @return Map with key "node" contains nodesJSON and key "link" contains 
     * linksJSON
     */
    private Map<String, String> convertToJSON (Set<Statement> statements) {
        
        Map<String, String> jsons  = new HashMap<> ();
        Map<Integer, String> nodesMap = new HashMap<> ();
        Set<Node> nodeList = new HashSet<>();
        Set<Link> linkList = new HashSet<>();
        int key = 1;
        
        for (Statement statement : statements) {
            String subject = getHumanReadableName(statement.getSubject());
            String predicate = getHumanReadableName(statement.getPredicate());
            String object = getHumanReadableName(statement.getObject());
            
            if (!nodesMap.containsValue(subject)){
                nodesMap.put(key, subject);                
                Node node = new Node();
                node.setKey(key);
                node.setText(subject);
                nodeList.add(node);
                key++;
            }
            
            if (!nodesMap.containsValue(object)){
                nodesMap.put(key, object);                
                Node node = new Node();
                node.setKey(key);
                node.setText(object);
                nodeList.add(node);
                key++;
            }
            
            boolean isSubjectKeyFound = false;
            boolean isObjectKeyFound = false;
            int subjectKey = 0;
            int objectKey = 0;
            
            for(Map.Entry<Integer, String> entry : nodesMap.entrySet()){
                
                if(subject.equals(entry.getValue())){
                    subjectKey = entry.getKey();
                    isSubjectKeyFound = true;            
                }
                
                if(object.equals(entry.getValue())){
                    objectKey = entry.getKey();
                    isObjectKeyFound = true;            
                }
                
                if (isSubjectKeyFound  && isObjectKeyFound) {                   
                    
                    Link link = new Link();
                    link.setFrom(subjectKey);
                    link.setTo(objectKey);
                    link.setText(predicate);
                    linkList.add(link);
                    break;
                    
                }
            }
        }
        
        String jsonNode = new Gson().toJson(nodeList );
            
        String jsonLink = new Gson().toJson(linkList );
        
        jsons.put("node", jsonNode);
        jsons.put("link", jsonLink);
        
        return jsons;
        
        
    }
    
    /**
     * <p>
     * Convert object into human readable string. If object is RDF literal return
     * label, if object is URI return local name of URI, if local name of URI is
     * empty return full URI.
     * </p>
     * 
     * @param rs Object either URI or RDF Literal
     * @return Human readable string
     */
    private String getHumanReadableName (Object rs) {
        
        String name = "";
        
        if (rs.getClass().getName().contains("org.openrdf.model.impl.URIImpl")) {
            
            URI uri = (URI) rs;  
            name = uri.getLocalName();
            if (name.isEmpty()) { 
                name = uri.toString();
            }  
            else {            
                name = ":" + name;
            }
        }            
        else {
            Literal literal = (Literal) rs;
            name = literal.getLabel();
            //name = name.concat("\t[Literal]");
        }
            
        return name;
        
    }

    /**
     * @return the nanopubJSON
     */
    public NanopubJSON getNanopubJSON() {
        return nanopubJSON;
    }
    
}
