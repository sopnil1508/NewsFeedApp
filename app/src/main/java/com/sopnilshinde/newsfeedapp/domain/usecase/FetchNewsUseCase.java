package com.sopnilshinde.newsfeedapp.domain.usecase;

import com.sopnilshinde.newsfeedapp.data.repository.NewsDataSource;
import com.sopnilshinde.newsfeedapp.data.repository.NewsRepository;
import com.sopnilshinde.newsfeedapp.data.repository.NewsRepositoryImpl;
import com.sopnilshinde.newsfeedapp.data.repository.UseCase;
import com.sopnilshinde.newsfeedapp.domain.model.News;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class FetchNewsUseCase implements UseCase<List<News>> {
    private final NewsDataSource newsDataSource;

    @Inject
    public FetchNewsUseCase(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public List<News> execute() throws RuntimeException {
        try {
            return newsDataSource.getNewsFeed();
        } catch (IOException e) {
            throw new RuntimeException("Error fetching news feed", e);
        }
    }
}
