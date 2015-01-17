package com.czeczotka.spring.web;

import com.czeczotka.spring.domain.News;
import com.czeczotka.spring.service.NewsService;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith (SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration ("classpath:test-mvc-dispatcher-servlet.xml")
public class NewsControllerIntegrationTest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    private static final String LATEST = "/latest";

    private MockMvc mockMvc;

    private NewsService newsService;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        newsService = (NewsService) context.getBean ("newsService");
        mockMvc = MockMvcBuilders.webAppContextSetup (context).build ();
    }

    @Test public void
    getLatestNews() {
        LocalDateTime dateTime = LocalDateTime.now ();
        String now = formatter.format (dateTime);
        org.mockito.Mockito.
                when (newsService.getLatestNews ()).
                thenReturn (new News ("Some news!", "These are some news!", dateTime));

        given ().
                mockMvc (mockMvc).
        when ().
                get (LATEST).
        then ().
                statusCode (HttpServletResponse.SC_OK).
                contentType ("application/json").
                body ("headline",  equalTo ("Some news!")).
                body ("article",   equalTo ("These are some news!")).
                body ("timestamp", equalTo (now));
    }

    @Test public void
    getLatestNewsWithPlural () {
        int number = 3;
        LocalDateTime dateTime = LocalDateTime.now ();
        String now = formatter.format (dateTime);
        org.mockito.Mockito.
                when (newsService.getLatestNews (number)).
                thenReturn (Arrays.asList (
                        new News ("Some news! 0", "These are some news! 0", dateTime),
                        new News ("Some news! 1", "These are some news! 1", dateTime),
                        new News ("Some news! 2", "These are some news! 2", dateTime)
                ));

        given ().
                mockMvc (mockMvc).
        when ().
                get (LATEST + "/" + number).
        then ().
                statusCode (HttpServletResponse.SC_OK).
                contentType ("application/json").
                body ("size()", equalTo (number)).
                body ("[0].headline",  equalTo ("Some news! 0")).
                body ("[0].article",   equalTo ("These are some news! 0")).
                body ("[0].timestamp", equalTo (now)).
                body ("[1].headline",  equalTo ("Some news! 1")).
                body ("[1].article",   equalTo ("These are some news! 1")).
                body ("[1].timestamp", equalTo (now)).
                body ("[2].headline",  equalTo ("Some news! 2")).
                body ("[2].article",   equalTo ("These are some news! 2")).
                body ("[2].timestamp", equalTo (now));
    }

    @Test public void
    unsuccessfulLatestWithNumber() {
        given ().
                mockMvc (mockMvc).
        when ().
                get (LATEST + "/abc").
        then ().
                statusCode (HttpServletResponse.SC_BAD_REQUEST);
    }
}