package com.czeczotka.spring.domain;

import java.time.LocalDateTime;

public class News {
    
    private String headline;
    private String article;
    private LocalDateTime timestamp;

    public News(String headline, String article, LocalDateTime timestamp) {
        this.headline = headline;
        this.article = article;
        this.timestamp = timestamp;
    }
       
    /*
     *   accessor methods below
     */

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
