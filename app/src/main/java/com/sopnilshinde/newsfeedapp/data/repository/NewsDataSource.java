package com.sopnilshinde.newsfeedapp.data.repository;

import com.sopnilshinde.newsfeedapp.domain.model.News;

import java.io.IOException;
import java.util.List;

public interface NewsDataSource {
    List<News> getNewsFeed() throws IOException;
}