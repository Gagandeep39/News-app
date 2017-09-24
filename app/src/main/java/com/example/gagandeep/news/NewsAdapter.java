package com.example.gagandeep.news;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gagandeep on 9/24/17.
 */

public class NewsAdapter extends ArrayAdapter {
    private static final String TTT = "T";
    public NewsAdapter(@NonNull Context context,  @NonNull List <News> news) {
        super(context, 0, news);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView==null){
            listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list, parent, false);

            TextView head = listItemView.findViewById(R.id.head);
            //TextView body = listItemView.findViewById(R.id.body);
            TextView web = listItemView.findViewById(R.id.web);
            TextView time = listItemView.findViewById(R.id.time);

            News currentNews = (News) getItem(position);
            String t = currentNews.getTime();
            String part1, part2;
            if(t.contains(TTT)){
                String parts[] = t.split(TTT);
                part1 = parts[0];
                part2 = parts[1];
            }
            else part1 = t;
            String webText = currentNews.getWeb();
            webText = webText + "...";
            //GradientDrawable drawable = (GradientDrawable)head.getBackground();
            int color = getMagnitudeColor(currentNews.getHead());
            head.setTextColor(color);


            head.setText(currentNews.getHead());
            //body.setText(currentNews.getBody());
            web.setText(webText);
            time.setText(part1);
        }



        return listItemView;
    }

    private int getMagnitudeColor(String head) {
        int magnitudeColorID ;
        switch (head){
            case "Music" :{
                magnitudeColorID = R.color.magnitude9;
                break;
            }
            case "Travel" :{
                magnitudeColorID = R.color.magnitude8;
                break;
            }
            case "Business" :{
                magnitudeColorID = R.color.magnitude7;
                break;
            }
            case "Politics" :{
                magnitudeColorID = R.color.magnitude6;
                break;
            }
            case "Books" :{
                magnitudeColorID = R.color.magnitude5;
                break;
            }
            case "Sports" :{
                magnitudeColorID = R.color.magnitude4;
                break;
            }
            case "World news" :{
                magnitudeColorID = R.color.magnitude3;
                break;
            }

            default:{
                magnitudeColorID = R.color.magnitude1;
                break;
            }

        }
        return ContextCompat.getColor(getContext(), magnitudeColorID);
    }
}
