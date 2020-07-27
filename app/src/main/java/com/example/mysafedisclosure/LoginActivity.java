package com.example.mysafedisclosure;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView textViewRegister;
    private ProgressBar loginProgressBar;
    SessionManager sessionManager;

    private static String URL_LOGIN="http://10.0.2.2/db_swe_app/login.php";//Change this

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager=new SessionManager(this);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.registerButton);
        textViewRegister = findViewById(R.id.textViewRegister);
        loginProgressBar = findViewById(R.id.loginProgressBar);

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString().trim();
                String password =passwordEditText.getText().toString().trim();

                if(username.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please insert username", Toast.LENGTH_SHORT).show();
                }else if(password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please insert password", Toast.LENGTH_SHORT).show();
                }else{
                    Login(username, password);
                }
            }
        });
    }

    private void Login(final String username, final String password) {
        loginProgressBar.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.GONE);

        StringRequest strRequest = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    try {
                        JSONObject jsonObject =new JSONObject(response);
                        String success = jsonObject.getString("success");

                        JSONArray jsonArray =jsonObject.getJSONArray("login");

                        if(success.equals("1")){//Login successful
                            String usr_id="";
                            for(int i=0; i< jsonArray.length(); i++){

                                JSONObject object = jsonArray.getJSONObject(i);

                                usr_id = object.getString("id").trim();
                            }
                            loginProgressBar.setVisibility(View.GONE);
                            loginButton.setVisibility(View.VISIBLE);

                            sessionManager.createSession(username, usr_id);
                            Intent postActivityIntent =new Intent(LoginActivity.this, PostActivity.class);
                            startActivity(postActivityIntent);
                            finish();

                        }else {//Wrong username or password
                            loginProgressBar.setVisibility(View.GONE);
                            loginButton.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this,"Wrong username or password!",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this,"Login Error!"+e.toString(),Toast.LENGTH_SHORT).show();

                        loginProgressBar.setVisibility(View.GONE);
                        loginButton.setVisibility(View.VISIBLE);
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this,"Login Error!"+error.toString(),Toast.LENGTH_SHORT).show();

                    loginProgressBar.setVisibility(View.GONE);
                    loginButton.setVisibility(View.VISIBLE);
                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params =new HashMap<>();
                    params.put("name",username);
                    params.put("password",password);
                    return params;
                }
            };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(strRequest);
    }

}
