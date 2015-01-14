package com.czeczotka.spring.service;

import com.czeczotka.spring.domain.News;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    @Override
    public News getLatestNews () {
        return new News("Latest news!", "These are the latest news!", null);
    }
}
