package com.example.mysafedisclosure;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText, cnfPasswordEditText;
    private Button registerButton;
    private TextView textViewLogin;
    private ProgressBar registerProgressBar;

    private static String URL_REGIST="http://10.0.2.2/db_swe_app/register.php";//Change this

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerProgressBar = findViewById(R.id.registerProgressBar);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        cnfPasswordEditText = (EditText) findViewById(R.id.cnfPasswordEditText);
        registerButton = (Button) findViewById(R.id.registerButton);
        textViewLogin = (TextView) findViewById(R.id.textViewLogin);

        textViewLogin.setOnClickListener(new View.OnClickListener() { //Takes you to the Login activity
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        /*Register button*/
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Regist();
            }
        });
    }

    private void Regist(){
        registerProgressBar.setVisibility(View.VISIBLE);
        registerButton.setVisibility(View.GONE);

        final String username= this.usernameEditText.getText().toString();
        final String password= this.passwordEditText.getText().toString();
        final String cnfPassword= this.cnfPasswordEditText.getText().toString();

        if(!password.equals(cnfPassword))
        {
            registerProgressBar.setVisibility(View.GONE);
            registerButton.setVisibility(View.VISIBLE);
            Toast.makeText(RegisterActivity.this,"Password does not match!",Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest strRequest =new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject= new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if(success.equals("1")){
                                Toast.makeText(RegisterActivity.this,"Register Success!",Toast.LENGTH_SHORT).show();
                                registerProgressBar.setVisibility(View.GONE);
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this,"Register Error!"+e.toString(),Toast.LENGTH_SHORT).show();

                            registerProgressBar.setVisibility(View.GONE);
                            registerButton.setVisibility(View.VISIBLE);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this,"Register Error!"+error.toString(),Toast.LENGTH_SHORT).show();

                        registerProgressBar.setVisibility(View.GONE);
                        registerButton.setVisibility(View.VISIBLE);
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
