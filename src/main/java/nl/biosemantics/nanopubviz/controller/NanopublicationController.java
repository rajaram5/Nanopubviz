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
 * <p>
 * Controller class to handle REST call's directed to /nanopublication path. 
 * </p>
 * @author Rajaram kaliyaperumal
 * @since 29-09-2014
 * @version 0.1
 */
@Controller
@RequestMapping(value = "/nanopublication")
public class NanopublicationController {    
    
    /**
     * <p>
     * Method to handle the GET request with nanopublication URI. Returns 
     * nanopulication in JSON format which can be visualized using GoJS 
     * java script. 
     * </p>
     * @param uri   Nanopublication URI
     * @return Nanopublication in JSON format.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showNanopub(@RequestParam(value="uri") String uri) {
        
        NanopubToJSON npJSON = null;
        String serverErrorMessage = "";
            
        ModelAndView mav = new ModelAndView();
        try {
            npJSON = new NanopubToJSON(uri, RDFFormat.NQUADS); 
            mav.addObject("npJSON", npJSON.getNanopubJSON());
            mav.setViewName("nanopubGraphsTabs");
        }
        catch (MalformedNanopubException | OpenRDFException | IOException e) {
            serverErrorMessage = e.getMessage();
            mav.addObject("serverErrorMessage", serverErrorMessage);
            mav.setViewName("error");
            Logger.getLogger(NanopublicationController.class.getName()).
                    log(Level.SEVERE, null, e);
        }
        return mav;
    }
    
}
