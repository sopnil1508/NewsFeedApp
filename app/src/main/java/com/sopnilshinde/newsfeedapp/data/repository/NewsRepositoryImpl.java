package com.sopnilshinde.newsfeedapp.data.repository;

import com.sopnilshinde.newsfeedapp.data.network.NewsService;
import com.sopnilshinde.newsfeedapp.domain.model.News;

import java.util.List;

public class NewsRepositoryImpl implements NewsRepository{

    private NewsService apiService;

    public NewsRepositoryImpl(NewsService apiService) {
        this.apiService = apiService;
    }

    @Override
    public List<News> fetchNews() {
        return null;
    }
}
