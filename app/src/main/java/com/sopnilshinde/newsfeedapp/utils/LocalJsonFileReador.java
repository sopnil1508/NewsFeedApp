package com.sopnilshinde.newsfeedapp.utils;

import android.content.Context;

import com.sopnilshinde.newsfeedapp.domain.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LocalJsonFileReador {

    public static List<News> readJsonFromAssets(Context context) {
        List<News> newsList = new ArrayList<>();

        try {
            // Read JSON data from the assets folder
            InputStream inputStream = context.getAssets().open("news.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String jsonStr = stringBuilder.toString();

            // Parse JSON data
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray articlesArray = jsonObject.getJSONArray("articles");

            // Iterate through the articles array and create News objects
            for (int i = 0; i < articlesArray.length(); i++) {
                JSONObject articleObject = articlesArray.getJSONObject(i);
                String publishedAt = articleObject.getString("publishedAt");
                String title = articleObject.getString("title");
                String url = articleObject.getString("url");
                String urlToImage = articleObject.getString("urlToImage");

                // Create News object and add it to the list
                News news = new News();
                news.setPublishAt(publishedAt);
                news.setTitle(title);
                news.setUrl(url);
                news.setImageUrl(urlToImage);
                newsList.add(news);
            }

            // Close the input stream
            inputStream.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            // Handle exception
        }

        return newsList;
    }
}
