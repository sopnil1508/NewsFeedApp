package com.sopnilshinde.newsfeedapp.data.repository;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.sopnilshinde.newsfeedapp.domain.model.News;
import com.sopnilshinde.newsfeedapp.domain.model.NewsResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        AssetManager assetManager = context.getAssets();
        if (assetManager == null) {
            throw new IOException("AssetManager is null");
        }

        try {
            // Read the JSON file from the asset folder
            String jsonString = readJsonFromAsset("news.json", assetManager);
            NewsResponse newsResponse = new Gson().fromJson(jsonString, NewsResponse.class);
            return newsResponse.getArticles().stream()
                    .sorted(Comparator.comparing(News::getPublishAt))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IOException("Error reading news.json file", e);
        }
    }

    private static String readJsonFromAsset(String fileName, AssetManager assetManager) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream is = assetManager.open(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(is);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }
}
