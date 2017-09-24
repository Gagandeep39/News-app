package com.example.gagandeep.news;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gagandeep on 9/24/17.
 */

public class NewsUtils {
    public static List<News> fetchData(String string) {
        URL url = createUrl(string);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        }catch (IOException e){
            Log.e("LOLOL xD", "Problem with hTTp", e);
        }
        List<News> news = extractFeatureFromJson(jsonResponse);

        return news;
    }

    private static List<News> extractFeatureFromJson(String jsonResponse) {
        ArrayList<News> news = new ArrayList<>();
        if (TextUtils.isEmpty(jsonResponse))
            return null;
        try {

            JSONObject base = new JSONObject(jsonResponse);
            JSONObject response = base.getJSONObject("response");
            JSONArray array = response.getJSONArray("results");
            for (int i=0; i<array.length(); i++){
                JSONObject current = array.getJSONObject(i);
                String sectionName = current.getString("sectionName");
                String sectionId = current.getString("sectionId");
                String time = current.getString("webPublicationDate");
                String web = current.getString("webTitle");
                //String web = current.getString("apiUrl");
                String url = current.getString("webUrl");

                News news1 = new News(sectionName, sectionId, web, url, time);
                news.add(news1);
            }
        }catch (JSONException e){
            Log.e("LOLOL", "JSON error");
        }





        return news;
    }

    private static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";
        if(url == null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();
            if (urlConnection.getResponseCode()==200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);

            }
            else {
                Log.e("LOLOOL", "Error response code" + urlConnection.getResponseCode() );

            }
        }catch (IOException e){
            Log.e("LOLOLOL", "problem retrieving JSON results, e");

        }finally {
            if (urlConnection!=null){
                urlConnection.disconnect();
            }
            if(inputStream!=null){
                inputStream.close();
            }
            return jsonResponse;
        }
    }

    private static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();
        if (inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line!=null){
                output.append(line);
                line =  reader.readLine();
            }
        }

        return  output.toString();
    }

    private static URL createUrl(String string) {
        URL url = null;
        try {
            url = new URL(string);
        }catch (MalformedURLException e){
            Log.e("LOLOL xD", "Problem while creating URL");
        }

        return url;
    }
}
