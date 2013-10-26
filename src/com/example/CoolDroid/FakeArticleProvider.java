package com.example.CoolDroid;


import java.util.ArrayList;

public class FakeArticleProvider implements IArticleProvider {
    public String getName() {
        return "Sample Data";
    }

    public ArrayList<ArticleInfo> getArticles() {
        ArrayList<ArticleInfo> articles = new ArrayList();

        ArticleInfo a1 = new ArticleInfo();
        a1.Title = "Test content. First Item";
        a1.Abstract = "Abstract of first content item";
        articles.add(a1);

        ArticleInfo a2 = new ArticleInfo();
        a2.Title = "Second Article";
        a2.Abstract = "Test content. Second Item";
        articles.add(a2);

        return articles;
    }
}
