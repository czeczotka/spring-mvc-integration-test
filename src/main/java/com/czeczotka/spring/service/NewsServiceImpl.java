package com.czeczotka.spring.service;

import com.czeczotka.spring.domain.News;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Override
    public News getLatestNews () {
        return getNews ("");
    }

    @Override
    public List<News> getLatestNews (int number) {
        List<News> newsList = new LinkedList<> ();
        for (int i = 0; i < number; i++) {
            newsList.add (getNews (i + ""));
        }
        return newsList;
    }

    private News getNews (String suffix) {
        return new News ("Latest news!" + suffix, "These are the latest news!" + suffix, LocalDateTime.now ());
    }
}