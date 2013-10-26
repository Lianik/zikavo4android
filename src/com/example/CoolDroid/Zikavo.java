package com.example.CoolDroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Zikavo extends Activity {
    /**
     * Called when the activity is first created.
     */
    private int InternalRequestCode;
    private TextView message;
    private TextView textViewDownloaded;
    private ImageView droid;
    private View.OnClickListener droidTapListener;
    private int counter;
    private String downloadedLabel = String.valueOf('s');

    public Zikavo() {
        InternalRequestCode = 1234;
        downloadedLabel = "";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        initializeApp();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("counter", counter);
    }

    private void initializeApp() {
        message = (TextView) findViewById(R.id.message);
        textViewDownloaded = (TextView) findViewById(R.id.textViewDownloaded);
        droid = (ImageView) findViewById(R.id.imageView);

        droidTapListener = new View.OnClickListener() {
            public void onClick(View v) {
                goGetFeed();
            }
        };

        droid.setOnClickListener(droidTapListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode != InternalRequestCode)
            return;

        Bundle extras = intent.getExtras();
        if (extras != null) {
            updateDownloadedLabel(extras.getInt("ArticlesCount"));
//            Toast.makeText(this, "Got: " + returned, Toast.LENGTH_LONG).show();
        }

    }

    private void goGetFeed() {
        Intent i = new Intent(Zikavo.this, FeedList.class);

        // Rss feed link as parameter
        i.putExtra("url", "http://zikavo.com/rss.xml");

//        startActivity(i);
        startActivityForResult(i, InternalRequestCode);
    }


    private void updateDownloadedLabel(int count) {
        if (count >= 0) {
            downloadedLabel = String.format("%s article(s) found.", count);
        }

        textViewDownloaded.setText(downloadedLabel);
    }

}
