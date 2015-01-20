package com.czeczotka.spring.web;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import javax.servlet.http.HttpServletResponse;
import static org.hamcrest.CoreMatchers.equalTo;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration ("classpath:test-mvc-dispatcher-servlet.xml")
public class HelloControllerTest {
    
    private static final String HELLO = "/hello";

    @Autowired
    private WebApplicationContext context;
    
    @Before
    public void setUp() {
        RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test public void 
    getHello() {
        given().
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
                param("name", "coder").
        when().
                get(HELLO).
        then().
                statusCode(HttpServletResponse.SC_OK).
                contentType("application/json").
                body(equalTo("Hello coder!"));
    }

    @Test public void
    getHelloWithLogging() {
        given().
                log ().all ().
        when().
                get (HELLO).
        then().
                log().all ().
                statusCode (HttpServletResponse.SC_OK);
    }

    @Test public void
    failPostToHello() {
        given().
        when().
                post(HELLO).
        then().
                statusCode(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}