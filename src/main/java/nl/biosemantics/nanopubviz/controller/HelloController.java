package nl.biosemantics.nanopubviz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Rajaram kaliyaperumal
 * @since 29-09-2014
 * @version 0.1
 */

@Controller
//@RequestMapping(value = "/Nanopubviz/")
public class HelloController {
    
    private final String msg="Hello";

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public ModelAndView getdata() {
//
//		ModelAndView model = new ModelAndView("hello");
//	
//		return model;
//
//	}
        
        @RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getIndex() {

		ModelAndView model = new ModelAndView("index");
	
		return model;

	}

}