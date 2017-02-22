package com.example.harshjha.studentdrive;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TimeTable extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter listAdapter;

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    List<String> mon = new ArrayList<String>();
    List<String> tue = new ArrayList<String>();
    List<String> wed = new ArrayList<String>();
    List<String> thu = new ArrayList<String>();
    List<String> fri = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        expandableListView = (ExpandableListView)findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeader, listDataChild);

        // setting list adapter
        expandableListView.setAdapter(listAdapter);


           }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Monday");
        listDataHeader.add("Tuesday");
        listDataHeader.add("Wednesday");
        listDataHeader.add("Thursday");
        listDataHeader.add("Friday");

        BackgroundWorker backgroundWorker = new BackgroundWorker(getApplicationContext());
        backgroundWorker.execute();

        // Adding child data
        List<String> top250 = new ArrayList<String>();


        List<String> nowShowing = new ArrayList<String>();


        List<String> comingSoon = new ArrayList<String>();


        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }

    private class BackgroundWorker extends AsyncTask<String, Void, String> {

        Context context;
        String type = "";
        String result;


        private BackgroundWorker(Context context) {
            this.context = context;
        }

        private ProgressDialog dialog = new ProgressDialog(TimeTable.this);


        @Override
        protected String doInBackground(String... params) {
            String get_sub = "http://digitalprolearn.com/app_work/php/get_topic.php";

            try {
                Log.d("zxc", "doInBackground: "+params[0]);
                URL url = new URL(get_sub);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =
                        //URLEncoder.encode("book_name", "UTF-8")+"="+URLEncoder.encode("Prachin Etihas", "UTF-8")
                         URLEncoder.encode("book_name", "UTF-8")+"="+URLEncoder.encode(params[0], "UTF-8")
                        +"&"+URLEncoder.encode("book_name", "UTF-8")+"="+URLEncoder.encode(params[0], "UTF-8")
                        +"&"+URLEncoder.encode("book_name", "UTF-8")+"="+URLEncoder.encode(params[0], "UTF-8")

                        ;
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                try {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Log.d("encode", "encoded");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    Log.d("encode", "encoded");
                    result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            //  alertDialog = new AlertDialog.Builder(context).create();
            //alertDialog.setTitle("Login Status");
            super.onPreExecute();

//            dialog.setMessage("Please wait..");
//            dialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

//            if(dialog.isShowing()){
//                dialog.dismiss();
//            }
//            fl.setVisibility(View.GONE);

            Log.d("topics", "onPostExecute: "+result);

            JSONObject jsonObj = null;
            try {
//                jsonObj = new JSONObject(result);
//                feeds = jsonObj.getJSONArray(TAG_RESULTS);
//
//
//
//                for(int i=0;i<feeds.length();i++){
//                    JSONObject c = feeds.getJSONObject(i);
//                    String book_name = c.getString(TAG_BOOK_NAME);
//                    String chapter   = c.getString(TAG_CHAPTER);
//                    String tname     = c.getString(TAG_TNAME);
//                    String tuid      = c.getString(TAG_TUID);
//                    String nfn       = c.getString(TAG_NFN);
//                    String nfa       = c.getString(TAG_NFA);
//                    String vfn       = c.getString(TAG_VFN);
//                    String vfa       = c.getString(TAG_VFA);
//                    String video_pic = c.getString(TAG_VIDEO_PIC);
//                    String date      = c.getString(TAG_PUBDT);
//
//                    vfam.add(vfa);
//                    vfnm.add(vfn);
//                    nfam.add(nfa);
//                    nfnm.add(nfn);



//                    titles.add(name);
//                    desc.add(desc1);
//                    text_con.add(text_con1);
//                    picname.add(picname1);
//                    videoname.add(videoname1);
//                    a_date.add(date);
//
//
//                    HashMap<String,String> feed = new HashMap<String,String>();
//
//
//                    feed.put(TAG_ID,id);
//                    feed.put("auth_name",auth_name);
//                    feed.put(TAG_AC,ac_type);
//                    feed.put(TAG_NAME,name);
//                    feed.put(TAG_DESC,desc1);
//                    feed.put(TAG_TEXT_CON,text_con1);
//                    feed.put(TAG_PICNAME,picname1);
//                    feed.put(TAG_VIDEONAME,videoname1);
//
//                    feed.put(TAG_DATE,date);
//
//                    feedList.add(feed);
//
//                    FeedData feedData = new FeedData();
//
//                    switch (contxt) {
//                        case "syll":
//
//                            feedData.setTitle(chapter);
//                            feedData.setDesc(book_name);
//                            feedData.setImage(video_pic);
//                            feedData.setAuth(tname);
//                            feedData.setType(date);
//                            data.add(feedData);
//
//                            break;
//                        case "rc":
//
//                            if(vfa.equals("active")){
//                                feedData.setTitle(chapter);
//                                feedData.setDesc(book_name);
//                                feedData.setImage(video_pic);
//                                feedData.setAuth(tname);
//                                feedData.setType(date);
//                                data.add(feedData);
//                            }
//
//                            break;
//                        case "cn":
//
//                            if(nfa.equals("active")){
//                                feedData.setTitle(chapter);
//                                feedData.setDesc(book_name);
//                                feedData.setImage(video_pic);
//                                feedData.setAuth(tname);
//                                feedData.setType(date);
//                                data.add(feedData);
//                            }
//
//                            break;
//                    }

//                    feedData.setTitle(chapter);
//                    feedData.setDesc(book_name);
//                    feedData.setImage(video_pic);
//                    feedData.setAuth(tname);
//                    feedData.setType(date);
//                    data.add(feedData);


//                    ListAdapter adapter = new SimpleAdapter(
//                            NewsFeed.this, feedList, R.layout.feed_item,
//                            new String[]{TAG_NAME,TAG_DESC,"auth_name",TAG_AC},
//                            new int[]{R.id.tv_feed_top, R.id.tv_feed_desc,R.id.tv_feed_auth,R.id.tv_feed_auth_post}
//                    );

//                    NewsFeedAdapter adapter = new NewsFeedAdapter(data,context);
//                    feed_list.setAdapter(adapter);

//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
            } catch (NullPointerException e){

            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            ProgressDialog pd = new ProgressDialog(context);
            pd.setMessage("Please Wait...");
            pd.show();
        }

    }

}
