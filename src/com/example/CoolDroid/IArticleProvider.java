package com.example.CoolDroid;

import  java.util.ArrayList;

public interface IArticleProvider {
    String getName();
    ArrayList<ArticleInfo> getArticles();
}
