package com.example.gagandeep.news;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    NewsAdapter mAdapter;
    News1Adapter adapter;
    RecyclerView recyclerView;
    public static final String WEB_URL = "https://content.guardianapis.com/search?api-key=0889e93a-7f87-4f71-9215-427bec507ad4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_main);

//        ArrayList<News> news = new ArrayList<>();
//        news.add(new News("Books", "", "Once upon a dream.....", "www.news.com", "2:48"));
//        news.add(new News("Music", "", "Once upon a dream.....", "www.news.com", "2:48"));
//        news.add(new News("Business", "", "Once upon a dream.....", "www.news.com", "2:48"));
//        news.add(new News("Politics", "", "Once upon a dream.....", "www.news.com", "2:48"));
//        news.add(new News("Sports", "", "Once upon a dream.....", "www.news.com", "2:48"));
//        news.add(new News("Books", "", "Once upon a dream.....", "www.news.com", "2:48"));
//        news.add(new News("Music", "", "Once upon a dream.....", "www.news.com", "2:48"));
//        mAdapter = new NewsAdapter(this, news);
       // adapter = new News1Adapter(this, news);
//        ListView listView = findViewById(R.id.list);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                News news1 = (News) mAdapter.getItem(i);
//                Uri uri = Uri.parse(news1.getUrl());
//                startActivity(new Intent(Intent.ACTION_VIEW, uri));
//            }
//        });
//        listView.setAdapter(mAdapter);
//        listView.setNestedScrollingEnabled(true);
//

        recyclerView = findViewById(R.id.recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
        NewsAsyncTask task = new NewsAsyncTask();
        task.execute(WEB_URL);

    }

    public class NewsAsyncTask extends AsyncTask<String, Void, List<News>>{


        @Override
        protected List<News> doInBackground(String... strings) {
            if (strings.length<1||strings[0]==null)
                return null;
            List<News> result = NewsUtils.fetchData(strings[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<News> news) {

            //mAdapter.clear();
            if(news!=null && !news.isEmpty()){
                //mAdapter.addAll(news);
                News1Adapter adapter1 = new News1Adapter(getBaseContext(), news);
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                recyclerView.setAdapter(adapter1);


            }
        }
    }
}
