package com.sopnilshinde.newsfeedapp.data.repository;

import android.content.Context;

import com.google.gson.Gson;
import com.sopnilshinde.newsfeedapp.domain.model.News;
import com.sopnilshinde.newsfeedapp.domain.model.NewsResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class NewsRepositoryImpl implements NewsDataSource {
    private final Context context;

    @Inject
    public NewsRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public List<News> getNewsFeed() throws IOException {
        try {
            // Read the JSON file from the asset folder
            String jsonString = readJsonFromAsset(context, "news.json");
            NewsResponse newsResponse = new Gson().fromJson(jsonString, NewsResponse.class);
            return newsResponse.getArticles().stream()
                    .sorted(Comparator.comparing(News::getPublishAt))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IOException("rrror reading news.json file", e);
        }
    }
    private static String readJsonFromAsset(Context context, String fileName) throws IOException {
        try (InputStream is = context.getAssets().open(fileName)) {
            int size = is.available();
            byte[] buffer = new byte[size];
            if (is.read(buffer) == -1) {
                throw new IOException("failed to read JSON file from asset folder");
            }
            return new String(buffer, StandardCharsets.UTF_8);
        }
    }

}
