package com.blogspot.priyabratanaskar.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<News> mNewsData;
    private NewsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Log.d("News","Oncreate");

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ArrayList that will contain the data.
        mNewsData = new ArrayList<>();

        /**
        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new NewsAdapter(mNewsData,this);
        mRecyclerView.setAdapter(mAdapter);
**/
        initializeData();
        Toast.makeText(this,"Executing",Toast.LENGTH_SHORT).show();
        // Get the data.
        mAdapter = new NewsAdapter(mNewsData,NewsActivity.this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initializeData() {
        Log.e("Main","Fetching");
        mNewsData.add(new News(null,
                "Blood cancer risk higher than expected in kids with Down syndrome - Morung Express",
                "<p><strong>India News, Nagaland News, Latest News Update - The Morung Express provides the latest news, &nbsp;Northeast, business, sports, world, naga, &nbsp;entertainment&nbsp;</strong></p>",
                "https://www.morungexpress.com/public/front/images/morung_logo2017.png",
                "The Morung Express\r\nThe Morung Express is a people-oriented alternative newspaper based in Nagaland that was conceived from the Naga peoples historical realities and is guided by their voices and exp… [+277 chars]",
                "2021-04-07T13:53:28Z",
                "Morungexpress.com"));

        mNewsData.add(new News("Dr Ramakanta Panda",
                "A look at the changing profile of disease, post Covid-19 - BusinessLine",
                "Covid infection could change the country’s heart-health profile, besides causing long-term damage to the lungs, kidney and pancreas among other organs",
                "https://www.thehindubusinessline.com/specials/pulse/ngeip2/article34265151.ece/ALTERNATES/LANDSCAPE_615/Dr-Ramakanta-Panda-leading-cardiovascular-thoracic-surgeon-VC-Asian-Heart-In",
                "At the turn of the year, social media was gung-ho heralding the end of Covid-19. New cases had fallen dramatically across the country since the highs of mid-September.\r\nIn March 2021 came the second … [+4312 chars]",
                "2021-04-07T13:46:13Z",
                "BusinessLine"));

        mNewsData.add(new News("DNA Video Team",
                "Certain high BP medications may alter heart risk in people with HIV: Study - DNA India",
                "A new research has found that when people with human immunodeficiency virus (HIV) develop high blood pressure, the type of medication chosen for their initial treatment may influence their risk of heart disease, stroke and heart failure. With current anti-ret…",
                "https://cdn.dnaindia.com/sites/default/files/2021/04/07/968561-00000004.jpg",
                "©2021 Diligent Media Corporation Ltd.",
                "2021-04-07T13:35:09Z",
                "DNA India"));

        mNewsData.add(new News("Arushi Bidhuri",
                "World Health Day: Lifestyle diseases can impact your productive years; Here's how to mitigate the risk - TheHealthSite",
                "On this world health day a survey revealed that the most common lifestyle diseases that can negatively affect your productive years.",
                "https://st1.thehealthsite.com/wp-content/uploads/2021/04/words-655x353.jpg",
                "This year, the theme of World Health Day is to build a fairer, healthier world. One of the most important things to keep in mind when you want to build a healthy future is to safeguard yourself from … [+3526 chars]",
                "2021-04-07T13:23:07Z",
                "Thehealthsite.com"));

        mNewsData.add(new News("WSJ",
                "New York City’s deadliest day from Covid-19 hit one year ago - Livemint",
                "On April 7, 2020, 815 residents succumbed to the disease, the city’s highest daily toll in the pandemic",
                "https://images.livemint.com/img/2021/04/07/600x338/1311135669_1617799401828_1617799533227.jpg",
                "In early March of last year, Jorge Morales was keeping customers caffeinated at Mikes Coffee Express, a popular roadside food truck near LaGuardia Airport in Queens that he ran for more than 30 years… [+5981 chars]",
                "2021-04-07T13:12:00Z",
                "Livemint"));

        mNewsData.add(new News(null,
                "COVID-19: Study Says Masks, Good Ventilation Prevent Spread Better than Social Distancing in Closed Spaces - NewsClick",
                "'These results highlight that with masks, transmission probability does not decrease with increased physical distancing, which emphasises how mask mandates may be key to increasing capacity in schools and other places.'",
                "https://www.newsclick.in/sites/default/files/2021-04/mask3.jpg",
        "At a time when universities and schools across the world are looking to reopen, a recent study says that masks and good ventilation systems can be better and more important in containing COVID-19 spr… [+3672 chars]",
                "2021-04-07T12:56:10Z",
                "Newsclick.in"));

        mNewsData.add(new News("Bloomberg",
                "Covid boosts risks for mental, neurological disorders in study - Hindustan Times",
                "A third of Covid-19 survivors were diagnosed with a neurological or psychiatric condition in the six months after being infected, according to the first large-scale research to compare the risks to other illnesses, including influenza.",
                "https://images.hindustantimes.com/img/2021/04/07/1600x900/test-tube-5065426_1920_1617799771585_1617799777280.jpg",
                "A third of Covid-19 survivors were diagnosed with a neurological or psychiatric condition in the six months after being infected, according to the first large-scale research to compare the risks to o… [+3363 chars]",
                "2021-04-07T12:50:14Z",
                "Hindustan Times"));

        mNewsData.add(new News("Pinkvilla Desk",
                "World Health Day: India’s leading nutritionist Rujuta Diwekar shares EFFECTIVE tips for a healthy lifestyle - PINKVILLA",
                "The COVID-19 pandemic disrupted various aspects of our lives taking a toll on physical health and mental well-being for many. April 7th, celebrated as World Health Day, is a good time to get back on the health bandwagon.",
                "https://www.pinkvilla.com/files/styles/fbimagesection/public/rujuta_social.jpg?itok=ClA5yJwN",
                null,
                "2021-04-07T12:40:21Z",
                "PINKVILLA"));

        mNewsData.add(new News("Tripti Shahi",
                "Children Asymptomatic To COVID-19 Suffer From Inflammatory Syndrome: Study - SheThePeople",
                "MIS-C: According to a study by Ermias D. Belay, Joseph Abrams, and Matthew E. Oster, published in JAMA Pediatrics on Tuesday, April 6, a case of Multisystem Inflammatory Syndrome (MIS-C) can be seen in young patients previously infected with COVID-19. MIS-C i…",
                "https://www.shethepeople.tv/wp-content/uploads/2021/04/COVID-Inflammatory-disease.jpg",
                "MIS-C: According to a study by Ermias D. Belay, Joseph Abrams, and Matthew E. Oster, published in JAMA Pediatrics on Tuesday, April 6, a case of Multisystem Inflammatory Syndrome (MIS-C) can be seen … [+2389 chars]",
                "2021-04-07T11:33:53Z",
                "Shethepeople.tv"));

        mNewsData.add(new News("DTSrija",
                "India Gets FREE National Autism Helpline - 9100 181 181 - DTNext",
                "Autism is spreading at an epidemic rate with more than 3 crore autistic children/people out there currently, as per WHO.",
                "https://img.dtnext.in/Articles/2021/Apr/202104071617557188_India-Gets-FREE-National-Autism-Helpline--9100-181-181_SECVPF.gif",
                "Autism is spreading at an epidemic rate with more than 3 crore autistic children/people out there currently, as per WHO.\r\nHyderabad:Autism Spectrum Disorder is a life-long Neurological and Psychologi… [+2791 chars]",
                "2021-04-07T11:05:32Z",
                "Dtnext.in"));
    }


    private class FetchNews extends AsyncTask<String,Void,String> {
        //private WeakReference<TextView> mTitleText;
        //private WeakReference<TextView> mAuthorText;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            //mNewsData.clear();

            Log.d("NewsActivity",  "Inside Do in background");
            String result = null;
            try {
                URL url = new URL("https://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=84a9ce0f3b9040bf8edf144d10a95708");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String temp;

                    while ((temp = reader.readLine()) != null) {
                        stringBuilder.append(temp);
                    }
                    result = stringBuilder.toString();
                }else  {
                    result = "error";
                }

            } catch (Exception  e) {
                e.printStackTrace();
            }

            return result;

//            Log.d("din back", strings[0]);
//            return NetworkUtils.getStoryInfo(strings[0]);
        }

//        public FetchNews(TextView mTitleText, TextView mAuthorText) {
//            this.mTitleText = new WeakReference<>(mTitleText);
//            this.mAuthorText = new WeakReference<>(mAuthorText);
//        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(NewsActivity.this,"S is " + s,Toast.LENGTH_SHORT).show();

            try {
                //Convert response into JSONObject
                JSONObject jsonObject = new JSONObject(s);

                Toast.makeText(NewsActivity.this, "JSON Object S", Toast.LENGTH_SHORT).show();

                //Get book-item array from jsonObject
                JSONArray jsonArray = jsonObject.getJSONArray("articles");

                Log.e("NewsActivity", "Array Length" + jsonArray.length());
                //Use index to work with jsonArray
                int index = 0;
                String title = null;
                String authors = null;

                Toast.makeText(NewsActivity.this, "Article", Toast.LENGTH_SHORT).show();
                while (index < jsonArray.length() && (title == null && authors == null)) {
                    // Get the current item information.
                    JSONObject news = jsonArray.getJSONObject(index);
                    String newsAuthorName = news.getString("author");
                    String newsTitle = news.getString("title");
                    String newsDescription = news.getString("description");
                    String newsImageResource = news.getString("urlToImage");
                    String newsContent;
                    String newsPublishTime;
                    String sourceName;

                    News currentNews = new News(newsAuthorName,newsTitle,newsDescription,newsImageResource);

                    mNewsData.add(currentNews);
                    //JSONObject volumeInfo = news.getString("volumeInfo");

                    // Try to get the author and title from the current item,
                    // catch if either field is empty and move on.
//                    try {
//                        title = volumeInfo.getString("title");
//
//                        JSONArray authorArray = volumeInfo.getJSONArray("authors");
//
//                        int authorIndex = 0;
//                        while (authorIndex < authorArray.length()) {
//                            if (authors == null) {
//                                authors = authorArray.getString(authorIndex);
//                            } else {
//                                authors = authors + "\n" + authorArray.getString(authorIndex);
//                            }
//                            authorIndex++;
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                    index++;
                }

//                if (title != null && authors != null) {
//                    mTitleText.get().setText(title);
//                    mAuthorText.get().setText(authors);
//                } else {
//                    // If both are null, update the UI to show failed results.
//                    mTitleText.get().setText(R.string.no_results);
//                    mAuthorText.get().setText("");
//                }

            } catch (JSONException e) {
                /**
                 * If onPostExecute does not receive a proper JSON string, update the UI to show failed results.
                 */
//                mTitleText.get().setText(R.string.no_results);
//                mAuthorText.get().setText("");
                Toast.makeText(NewsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            mAdapter = new NewsAdapter(mNewsData,NewsActivity.this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private class FetchNewsFromNetwork extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            String result = null;
            try {
                URL url = new URL("https://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=84a9ce0f3b9040bf8edf144d10a95708");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String temp;

                    while ((temp = reader.readLine()) != null) {
                        stringBuilder.append(temp);
                    }
                    result = stringBuilder.toString();
                }else  {
                    result = "error";
                }

            } catch (Exception  e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("onPostExecute",s);
            Toast.makeText(NewsActivity.this,s,Toast.LENGTH_SHORT).show();
        }
    }
}