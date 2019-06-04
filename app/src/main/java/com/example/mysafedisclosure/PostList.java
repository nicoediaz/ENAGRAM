package com.example.mysafedisclosure;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class PostList extends AppCompatActivity {

    ListView myListView;
    String[] posts; //items
    String[] sensitiveness; //sensitiveness
    String[] dates; //dates

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        Resources res = getResources();
        myListView= (ListView) findViewById(R.id.myListView);
        posts = res.getStringArray(R.array.posts);
        sensitiveness = res.getStringArray(R.array.sensitiveness);
        dates = res.getStringArray(R.array.dates);

        PostInfoAdapter postInfoAdapter = new PostInfoAdapter(this, posts, sensitiveness, dates);
        myListView.setAdapter(postInfoAdapter);
    }
}
