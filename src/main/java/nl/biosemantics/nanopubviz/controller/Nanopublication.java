/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.biosemantics.nanopubviz.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.biosemantics.nanopubviz.converter.NanopubToJSON;
import org.nanopub.MalformedNanopubException;
import org.openrdf.OpenRDFException;
import org.openrdf.rio.RDFFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rajaram
 */
@Controller
@RequestMapping(value = "/nanopublication")
public class Nanopublication {   
    
    
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showNanopub(@RequestParam(value="uri") String uri) {
        
        NanopubToJSON npJSON = null;
        String serverErrorMessage = "no error";
            
        ModelAndView mav = new ModelAndView();
        try {
            npJSON = new NanopubToJSON(uri, RDFFormat.NQUADS); 
            //System.out.println("URI = "+uri);
        }
        catch (MalformedNanopubException | OpenRDFException | IOException e) {
            serverErrorMessage = e.getMessage();
            Logger.getLogger(Nanopublication.class.getName()).
                    log(Level.SEVERE, null, e);
        }      
        
        System.out.println("Message "+npJSON.getHeadNodesJSON());
        
        mav.addObject("npJSON", npJSON);
        mav.addObject("serverErrorMessage", serverErrorMessage);
        mav.setViewName("nanopubGraphsTabs");
        return mav;
    }
    
}
