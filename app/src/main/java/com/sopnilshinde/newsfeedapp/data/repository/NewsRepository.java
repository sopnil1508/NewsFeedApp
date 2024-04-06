package com.sopnilshinde.newsfeedapp.data.repository;

import com.sopnilshinde.newsfeedapp.domain.model.News;

import java.util.List;

public interface NewsRepository {

    List<News> fetchNews();
}
