package com.sopnilshinde.newsfeedapp.domain.model;

import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("publishedAt")
    private String publishAt;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    @SerializedName("urlToImage")
    private String imageUrl;

}
