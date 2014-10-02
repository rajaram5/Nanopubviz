package nl.biosemantics.nanopubviz.converter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.nanopub.MalformedNanopubException;
import org.openrdf.OpenRDFException;
import org.openrdf.rio.RDFFormat;

/**
 *
 * @author Rajaram kaliyaperumal
 * @since 02-10-2014
 * @version 0.1
 */
public class NaopubToJSONTest {
    
    public NaopubToJSONTest() {
    }
    
    @Test
    public void validNanopubURI() throws MalformedNanopubException, IOException, OpenRDFException {
        String npURI = "http://rdf.biosemantics.org/nanopubs/cpm/gene_disease_associations/000001"; 
        
        NanopubToJSON test = new NanopubToJSON(npURI, RDFFormat.NQUADS);
        
        assertNotNull(test);
    }
    
    @Test(expected=MalformedNanopubException.class)
    public void invalidNanopub() throws Exception {
        String npURI = "http://rdf.biosemantics.org/nanopubs/cpm/protein_protein_associations/000001"; 
        
        NanopubToJSON test = new NanopubToJSON(npURI, RDFFormat.NQUADS);
    }
    
    @Test(expected=IOException.class)
    public void invalidNanopubURL() throws Exception {
        String npURI = "http://rdf.biosemantics.org/nanopubs/000001-invalid"; 
        
        NanopubToJSON test = new NanopubToJSON(npURI, RDFFormat.NQUADS);
    }
}