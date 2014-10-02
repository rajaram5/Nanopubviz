/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.biosemantics.nanopubviz.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import static org.junit.Assert.assertEquals;
import org.junit.Ignore;

/**
 *
 * @author Rajaram kaliyaperumal    
 * @since 02-10-2014
 * @version 0.1
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@ContextConfiguration(locations = "classpath:/dispatcher-servlet.xml")
@ContextConfiguration("classpath:/test-applicationContext.xml")
@Ignore
public class NanopublicationControllerIntegrationTest {
    
    @Autowired
    private NanopublicationController controller;
    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;
    
    @DirtiesContext
    @Test
    public void testValidNanopubURI() throws Exception {
        
        String uri = "http://rdf.biosemantics.org/nanopubs/cpm/gene_disease_associations/000001";
    
        MockHttpServletRequest request = new MockHttpServletRequest();    
        request.setMethod("GET");    
        request.setRequestURI("/nanopublication");    
        request.addParameter("uri", uri);
        MockHttpServletResponse response = new MockHttpServletResponse();    
        Object handler = handlerMapping.getHandler(request).getHandler();    
        handlerAdapter.handle(request, response, handler);    
        //assertEquals("[]", response.getContentAsString());
    } 
}
