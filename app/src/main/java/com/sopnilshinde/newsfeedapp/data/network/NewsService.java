package com.sopnilshinde.newsfeedapp.data.network;

import com.sopnilshinde.newsfeedapp.domain.model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {

    @GET("news.json")
    Call<List<News>> getNews();
}
