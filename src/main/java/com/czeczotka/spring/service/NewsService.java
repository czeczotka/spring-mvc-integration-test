package com.czeczotka.spring.service;

import com.czeczotka.spring.domain.News;

import java.util.List;

public interface NewsService {

    News getLatestNews ();

    List<News> getLatestNews (int number);
}
