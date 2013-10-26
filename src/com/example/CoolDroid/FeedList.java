package com.example.CoolDroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FeedList extends ListActivity {
    private String url;
    private TextView textViewUrl;
    private Intent currentIntent;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.feedlist);

        currentIntent = getIntent();
        textViewUrl = (TextView) findViewById(R.id.rssUrl);

        Bundle extras = currentIntent.getExtras();
        if (extras != null)  {
            url = extras.getString("url");
            textViewUrl.setText(url);
        }

        int count = loadArticles();



        currentIntent.putExtra("ArticlesCount", count);
        setResult(ListActivity.RESULT_OK, currentIntent);

    }

    private int loadArticles() {
        if (url == null || url == "")
            return 0;

        IArticleProvider provider = new FakeArticleProvider();
//        IArticleProvider provider = new ZikavoArticleProvider();
        ArrayList<ArticleInfo> articles = provider.getArticles();
        Bind(articles);
        return articles.size();
    }

    private void Bind(ArrayList<ArticleInfo> articles) {
        ArrayAdapter<ArticleInfo> adapter = new ArticleAdapter(this, articles);
        setListAdapter(adapter);
    }

}
