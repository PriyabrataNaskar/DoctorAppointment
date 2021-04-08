package com.blogspot.priyabratanaskar.firebaselogin;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Priyabrata Naskar on 08-04-2021.
 */
public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    //Base URL for Google books API
    private static final String NEWS_BASE_URL = "http://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=84a9ce0f3b9040bf8edf144d10a95708";
    //Param for search string
    private static final String QUERY_PARAM = "q";
    //Param that limits search result
    private static final String MAX_RESULTS = "maxResults";
    //Param to filter print type
    private static final String PRINT_TYPE = "printType";

    static String getStoryInfo(String queryString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String newsJSONString = null;
        try {

            //Create URI using the base url
            Uri builtURI = Uri.parse(NEWS_BASE_URL).buildUpon()
                    //.appendQueryParameter(QUERY_PARAM, queryString)
                    //.appendQueryParameter(MAX_RESULTS, "10")
                    //.appendQueryParameter(PRINT_TYPE, "books")
                    .build();
            //convert URI into URL
            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Get the input sream
            InputStream inputStream = urlConnection.getInputStream();

            //create bufferedReader using inputStream
            reader = new BufferedReader(new InputStreamReader(inputStream));

            //Use string builder to hold responses
            StringBuilder builder = new StringBuilder();

            //It will read from reader and will be used to append with StringBuilder
            String line;

            while ( (line=reader.readLine() ) != null){
                builder.append(line);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }
            newsJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection!=null){
                urlConnection.disconnect();
            }
            if (reader!=null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        Log.e(LOG_TAG,newsJSONString);
        return newsJSONString;
    }
}