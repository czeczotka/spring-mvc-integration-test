package com.czeczotka.spring.web;

import com.czeczotka.spring.domain.News;
import com.czeczotka.spring.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewsController extends AbstractController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/latest", method = RequestMethod.GET, produces = CONTENT_TYPE)
	public @ResponseBody News getLatestNews() {
		return newsService.getLatestNews();
	}

    /*
     *   accessor methods below
     */
    public void setNewsService (NewsService newsService) {
        this.newsService = newsService;
    }

}
