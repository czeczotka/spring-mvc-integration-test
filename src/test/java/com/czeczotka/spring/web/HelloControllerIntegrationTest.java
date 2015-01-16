package com.czeczotka.spring.web;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import javax.servlet.http.HttpServletResponse;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:mvc-dispatcher-servlet.xml")
public class HelloControllerIntegrationTest {
    
    private static final String HELLO = "/hello";

    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext context;
    
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test public void 
    getHello() {
        given().
                mockMvc(mockMvc).
        when().
                get(HELLO).
        then().
                statusCode(HttpServletResponse.SC_OK).
                contentType("application/json").
                body(equalTo("Hello world!"));
    }   
    
    @Test public void 
    getHelloWithParam() {
        given().
                mockMvc(mockMvc).
                param("name", "coder").
        when().
                get(HELLO).
        then().
                statusCode(HttpServletResponse.SC_OK).
                contentType("application/json").
                body(equalTo("Hello coder!"));
    }

    @Test public void
    unsuccessfulPostRequest() {
        given().
                mockMvc(mockMvc).
        when().
                post(HELLO).
        then().
                statusCode(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}