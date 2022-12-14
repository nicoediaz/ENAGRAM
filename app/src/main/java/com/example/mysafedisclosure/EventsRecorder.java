package com.example.mysafedisclosure;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EventsRecorder {

    /*private static String URL_ACTIVITY_RECORD="http://10.0.2.2/db_swe_app/activity_record.php";
    private static String URL_INTERVENTIONS="http://10.0.2.2/db_swe_app/interventions.php";
    private static String URL_POPUPSCOUNT="http://10.0.2.2/db_swe_app/popups_count.php";
    private static String URL_INTERVENTIONS_DE="http://10.0.2.2/db_swe_app/interventions_de.php";*/

    private static String URL_ACTIVITY_RECORD="https://www.uni-due.de/~adf978l/db_swe_app/activity_record.php";
    private static String URL_INTERVENTIONS="https://www.uni-due.de/~adf978l/db_swe_app/interventions.php";
    private static String URL_POPUPSCOUNT="https://www.uni-due.de/~adf978l/db_swe_app/popups_count.php";
    private static String URL_INTERVENTIONS_DE="https://www.uni-due.de/~adf978l/db_swe_app/interventions_de.php";


    public static void recordPopupAction(final String action, final String usrId, final String caption, final int idMsg, final String imageName, final Context cntx)
    {
        final String hashedCaption =new PostHash(caption).getHashedPost();//Hashed version of the post
        final String hashedImage =new PostHash(imageName).getHashedPost();//Hashed version of the post

        StringRequest strRequest = new StringRequest(Request.Method.POST, URL_ACTIVITY_RECORD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("1")) {//Activity successful recorded
                        //Toast.makeText(PostActivity.this,"Activity successfully recorded :-)",Toast.LENGTH_SHORT).show();
                    }else{//Failed recording
                        //Toast.makeText(PostActivity.this,"Failed on recording activity :-(",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(cntx,"Query Error!"+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(cntx,"Query Error!"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params =new HashMap<>();
                params.put("id",usrId);
                params.put("popup_action",action);
                params.put("post_lenght",Integer.toString(caption.length()));
                params.put("post_hash", hashedCaption);
                params.put("msg_id", Integer.toString(idMsg));
                params.put("image_hash",hashedImage);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(cntx);
        requestQueue.add(strRequest);

    }

    public static void readInterventionsTable(final Context cntx) //Read all the interventions table and keep it locally
    {
        final String app_language = cntx.getString(R.string.app_language);
        final String url_interventions;

        if(app_language.equals("DE")){
            url_interventions=URL_INTERVENTIONS_DE;
        }
        else{
            url_interventions=URL_INTERVENTIONS;
        }

        StringRequest strRequest = new StringRequest(Request.Method.POST, url_interventions, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("1")) {//Activity successful recorded
                        JSONArray dataArray =jsonObject.getJSONArray("data");

                        String intv_id, intv_cat, intv_msg, intv_rsk;
                        List<Intervention> intvList = new ArrayList<Intervention>();

                        for(int i=0; i< dataArray.length(); i++) {
                            JSONObject object = dataArray.getJSONObject(i);

                            intv_id = object.getString("id").trim();
                            intv_cat = object.getString("category").trim();
                            intv_msg = object.getString("message").trim();
                            intv_rsk = object.getString("risk").trim();

                            Intervention intervention = new Intervention(Integer.parseInt(intv_id), intv_cat, intv_msg, Float.parseFloat(intv_rsk));
                            intvList.add(intervention);
                        }
                        ((GeneralCallbacks)cntx).VolleyResponse(intvList);
                    }else{//Failed recording
                        //Toast.makeText(PostActivity.this,"Failed on recording activity :-(",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(cntx,"Query Error!"+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(cntx,"Query Error!"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(cntx);
        requestQueue.add(strRequest);

    }

    public static void countTodaysInterventions(final String usrId, final Context cntx) //CHANGE TO READ EVENTS!!!
    {
        StringRequest strRequest = new StringRequest(Request.Method.POST, URL_POPUPSCOUNT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("1")) {
                        JSONArray dataArray =jsonObject.getJSONArray("data");

                        String today_popups, ignored_popups, accepted_popups, last_popup;

                        for(int i=0; i< dataArray.length(); i++) {
                            JSONObject object = dataArray.getJSONObject(i);

                            today_popups = object.getString("today_popups").trim();
                            ignored_popups = object.getString("ignored_popups").trim();
                            accepted_popups = object.getString("accepted_popups").trim();
                            last_popup = object.getString("last_popup").trim();

                            PostActivity postActivity= (PostActivity) cntx;

                            postActivity.OnPopupsCount(Integer.parseInt(today_popups), Integer.parseInt(ignored_popups), Integer.parseInt(accepted_popups), Integer.parseInt(last_popup));

                        }

                        //Activity successful recorded
                        //Toast.makeText(PostActivity.this,"Activity successfully recorded :-)",Toast.LENGTH_SHORT).show();
                    }else{//Failed recording
                        //Toast.makeText(PostActivity.this,"Failed on recording activity :-(",Toast.LENGTH_SHORT).show();
                    }
                    //PostActivity postActivity= (PostActivity) cntx;

                    //postActivity.OnPopupsCount(10);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(cntx,"Query Error!"+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(cntx,"Query Error!"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params =new HashMap<>();
                params.put("id",usrId);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(cntx);
        requestQueue.add(strRequest);
    }

}
