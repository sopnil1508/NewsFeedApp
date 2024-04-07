package com.sopnilshinde.newsfeedapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import android.content.Context;

import com.sopnilshinde.newsfeedapp.data.repository.NewsRepositoryImpl;
import com.sopnilshinde.newsfeedapp.domain.model.News;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsRepositoryTest {

    @Mock
    private Context context;

    private NewsRepositoryImpl newsRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        newsRepository = new NewsRepositoryImpl(context);
    }

    @Test
    public void testGetNewsFeed_success() throws IOException {
        List<News> expectedNewsList = createNewsList();

        assertEquals(expectedNewsList.size(),2);
        assertEquals(expectedNewsList.get(0).getPublishAt(), "2024-01-10T22:41:25Z");
        assertEquals(expectedNewsList.get(0).getTitle(), "Title 1");
        assertEquals(expectedNewsList.get(0).getUrl(), "URL 1");
        assertEquals(expectedNewsList.get(0).getImageUrl(), "ImageURL 1");
    }

    @Test(expected = IOException.class)
    public void testGetNewsFeed_failure() throws IOException {
        // Stub the behavior of the AssetManager to return null
        when(context.getAssets()).thenReturn(null);

        // Call the method to get news feed
        newsRepository.getNewsFeed();
    }


    public static List<News> createNewsList() {
        List<News> newsList = new ArrayList<>();
        News n1 = new News();
        n1.setPublishAt("2024-01-10T22:41:25Z");
        n1.setTitle("Title 1");
        n1.setUrl("URL 1");
        n1.setImageUrl("ImageURL 1");

        newsList.add(n1);

        News n2 = new News();
        n2.setPublishAt("2024-01-11T18:18:13Z");
        n2.setTitle("Title 2");
        n2.setUrl("URL 2");
        n2.setImageUrl("ImageURL 2");

        newsList.add(n2);
        return newsList;
    }
}
