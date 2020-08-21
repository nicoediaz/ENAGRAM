package com.example.mysafedisclosure;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public abstract class EventsRecorder {

    private static String URL_ACTIVITY_RECORD="http://10.0.2.2/db_swe_app/activity_record.php";
    //private static String URL_ACTIVITY_RECORD="https://www.uni-due.de/~adf978l/db_swe_app/activity_record.php";

    public static void recordPopupAction(final String action, final String usrId, final String caption, final Context cntx)
    {
        final String hashedCaption =new PostHash(caption).getHashedPost();//Hashed version of the post
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
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(cntx);
        requestQueue.add(strRequest);

    }
}
