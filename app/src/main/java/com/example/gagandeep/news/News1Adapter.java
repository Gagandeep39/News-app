package com.example.gagandeep.news;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public void onBindViewHolder(News1Adapter.ViewHolder holder, int position) {
        News current = list.get(position);
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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView head, web, time;

        public ViewHolder(View itemView) {
            super(itemView);
            head = itemView.findViewById(R.id.head);
            //TextView body = listItemView.findViewById(R.id.body);
            web = itemView.findViewById(R.id.web);
            time = itemView.findViewById(R.id.time);
        }
    }


}
