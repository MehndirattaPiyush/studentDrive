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

import org.json.JSONArray;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class TimeTable extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter listAdapter;

    JSONArray feeds ;

    List<String> listDataHeader;

    HashMap<String, List<String>> listDataChild;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID      = "id";
    private static final String TAG_MON     = "mon";
    private static final String TAG_TUE     = "tue";
    private static final String TAG_WED     = "wed";
    private static final String TAG_THU     = "thu";
    private static final String TAG_FRI     = "fri";
    private static final String TAG_ATT     = "att";



    List<String> mon = new ArrayList<String>();
    List<String> tue = new ArrayList<String>();
    List<String> wed = new ArrayList<String>();
    List<String> thu = new ArrayList<String>();
    List<String> fri = new ArrayList<String>();
    List<String> att = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        expandableListView = (ExpandableListView)findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeader, listDataChild,att);

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


        String mond = "IT-201@Mr. Piyush Kumar@10:00-11:00am@D-314&IT-211@Mr. Piyush Kumar@11:00-12:00am@D-314";
        String tues = "IT-201@Mr. Piyush Kumar@10:00-11:00am@D-314&IT-211@Mr. Piyush Kumar@11:00-12:00am@D-314";
        String wedn = "IT-201@Mr. Piyush Kumar@10:00-11:00am@D-314&IT-211@Mr. Piyush Kumar@11:00-12:00am@D-314";
        String thur = "IT-201@Mr. Piyush Kumar@10:00-11:00am@D-314&IT-211@Mr. Piyush Kumar@11:00-12:00am@D-314";
        String frid = "IT-201@Mr. Piyush Kumar@10:00-11:00am@D-314&IT-211@Mr. Piyush Kumar@11:00-12:00am@D-314";

        String atte = "IT-201@1/10&IT-211@2/3";
        String[] att = atte.split("&");
        int i = 0;
        for (String child: mond.split("&")
             ) {

            child=child+"@"+att[i] ;
            i++;
            mon.add(child);
        }

        //Collections.addAll(mon, mond.split("&"));
        Collections.addAll(tue, tues.split("&"));
        Collections.addAll(wed, wedn.split("&"));
        Collections.addAll(thu, thur.split("&"));
        Collections.addAll(fri, frid.split("&"));
       // Collections.addAll(att, atte.split("&"));

//        BackgroundWorker backgroundWorker = new BackgroundWorker(getApplicationContext());
//        backgroundWorker.execute();

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("IT-201@Mr. Piyush Kumar@7/7@10:00-11:00am");
        top250.add("IT-201@Mr. Piyush Kumar@7/7@10:00-11:00am");
        top250.add("IT-201@Mr. Piyush Kumar@7/7@10:00-11:00am");
        top250.add("IT-201@Mr. Piyush Kumar@7/7@10:00-11:00am");
        List<String> nowShowing = new ArrayList<String>();

        List<String> comingSoon = new ArrayList<String>();


        listDataChild.put(listDataHeader.get(0), mon); // Header, Child data
        listDataChild.put(listDataHeader.get(1), tue); // Header, Child data
        listDataChild.put(listDataHeader.get(2), wed); // Header, Child data
        listDataChild.put(listDataHeader.get(3), thu); // Header, Child data
        listDataChild.put(listDataHeader.get(4), fri); // Header, Child data

//        listDataChild.put(listDataHeader.get(1), nowShowing);
//        listDataChild.put(listDataHeader.get(2), comingSoon);
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
                             URLEncoder.encode("enroll", "UTF-8")+"="+URLEncoder.encode(params[0], "UTF-8")
                        +"&"+URLEncoder.encode("branch", "UTF-8")+"="+URLEncoder.encode(params[1], "UTF-8")
                        +"&"+URLEncoder.encode("sem"   , "UTF-8")+"="+URLEncoder.encode(params[2], "UTF-8")

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
                jsonObj = new JSONObject(result);
                feeds = jsonObj.getJSONArray(TAG_RESULTS);

                for(int i=0;i<feeds.length();i++){
                    JSONObject c  = feeds.getJSONObject(i);
                    String id     = c.getString(TAG_ID);
                    String mond   = c.getString(TAG_MON);
                    String tues   = c.getString(TAG_TUE);
                    String wedn   = c.getString(TAG_WED);
                    String thur   = c.getString(TAG_THU);
                    String frid   = c.getString(TAG_FRI);

                    mond = "IT-201@Mr. Piyush Kumar@7/7@10:00-11:00am&IT-211@Mr. Piyush Kumar@7/10@11:00-12:00am";

                    Collections.addAll(mon, mond.split("&"));
                    Collections.addAll(tue, tues.split("&"));
                    Collections.addAll(wed, wedn.split("&"));
                    Collections.addAll(thu, thur.split("&"));
                    Collections.addAll(fri, frid.split("&"));

                    listDataChild.put(listDataHeader.get(0), mon);
                    listDataChild.put(listDataHeader.get(1), tue);
                    listDataChild.put(listDataHeader.get(2), wed);
                    listDataChild.put(listDataHeader.get(3), thu);
                    listDataChild.put(listDataHeader.get(4), fri);

                }
            } catch (JSONException e) {
                e.printStackTrace();
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
