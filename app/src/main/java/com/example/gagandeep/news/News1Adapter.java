package com.example.gagandeep.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gagandeep on 10/5/17.
 */

public class News1Adapter extends RecyclerView.Adapter<News1Adapter.ViewHolder> {

    private static final String TTT = "T";
    @Override
    public News1Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list, parent, false);
        return new ViewHolder(listItemView);
    }

    public News1Adapter(Context context, List<News> list) {
        this.context = context;
        this.list = list;
    }

    private Context context;
    private List<News> list;

    @Override
    public void onBindViewHolder(News1Adapter.ViewHolder holder, final int position) {
        final News current = list.get(position);
        String t = current.getTime();
        String part1, part2;
        if(t.contains(TTT)){
            String parts[] = t.split(TTT);
            part1 = parts[0];
            part2 = parts[1];
        }
        else part1 = t;
        String webText = current.getWeb();
        webText = webText + "...";
        //GradientDrawable drawable = (GradientDrawable)head.getBackground();


        holder.head.setText(current.getHead());
        //body.setText(currentNews.getBody());
        holder.web.setText(webText);
        holder.time.setText(part1);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //News news1 = list.get(position);
                Uri uri = Uri.parse(current.getUrl());
                context.startActivity(new Intent(Intent.ACTION_VIEW, uri).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });


        int color = getMagnitudeColor(current.getHead());
        holder.head.setTextColor(color);


        holder.head.setText(current.getHead());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView head, web, time;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            head = itemView.findViewById(R.id.head);
            //TextView body = listItemView.findViewById(R.id.body);
            web = itemView.findViewById(R.id.web);
            time = itemView.findViewById(R.id.time);
            linearLayout = itemView.findViewById(R.id.linear);
        }
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
        return ContextCompat.getColor(context, magnitudeColorID);
    }


}
