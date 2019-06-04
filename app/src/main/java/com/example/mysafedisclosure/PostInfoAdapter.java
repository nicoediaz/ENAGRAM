package com.example.mysafedisclosure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PostInfoAdapter extends android.widget.BaseAdapter {

    LayoutInflater mInflater;
    String[] posts; //items
    String[] sensitiveness; //sensitiveness
    String[] dates; //dates

    public PostInfoAdapter(Context c, String [] p, String [] s, String [] d){
        posts = p;
        sensitiveness = s;
        dates = d;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return posts.length;
    }

    @Override
    public Object getItem(int i) {
        return posts[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // "i" is an item of the list
        View v = mInflater.inflate(R.layout.my_listview_detail,null);

        TextView postTextView = (TextView) v.findViewById(R.id.postTextView);
        TextView sensitivenessTextView = (TextView) v.findViewById(R.id.sensitivenessTextView);
        TextView dateTextView = (TextView) v.findViewById(R.id.dateTextView);

        String post= posts[i];
        String sensitiv = sensitiveness[i];
        String date =dates[i];

        postTextView.setText(post);
        sensitivenessTextView.setText(sensitiv);
        dateTextView.setText(date);

        return v;
    }
}
