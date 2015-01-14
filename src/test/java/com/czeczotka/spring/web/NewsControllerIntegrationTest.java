package com.czeczotka.spring.web;

import junit.framework.TestCase;
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

import javax.servlet.http.HttpServletResponse;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;

@RunWith (SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration ("classpath:mvc-dispatcher-servlet.xml")
public class NewsControllerIntegrationTest {

    private static final String LATEST = "/latest";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup (context).build();
    }

    @Test public void
    getLatest() {
        given ().
                mockMvc (mockMvc).
        when ().
                get(LATEST).
        then ().
                statusCode (HttpServletResponse.SC_OK).
                contentType ("application/json").
                body ("headline", equalTo ("Latest news!")).
                body ("article", equalTo ("These are the latest news!")).
                body ("timestamp", nullValue ());
    }
}