package nl.biosemantics.nanopubviz.test;


import java.io.IOException;
import java.net.URL;
import nl.biosemantics.nanopubviz.converter.NanopubToJSON;
import org.nanopub.MalformedNanopubException;
import org.openrdf.OpenRDFException;
import org.openrdf.rio.RDFFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rajaram
 */
public class Driver2 {
    
    public static void main(String args[]) throws MalformedNanopubException, OpenRDFException, IOException {      
        
        
        
        String npURI = "http://rdf.biosemantics.org/nanopubs/cpm/gene_disease_associations/000001"; 
        URL url = new URL(npURI);      
        NanopubToJSON npjson = new NanopubToJSON(npURI, RDFFormat.NQUADS);
        
        //System.out.println(npjson.getAssertionLinksJSON());
        
        
        
        
    }
    
}
