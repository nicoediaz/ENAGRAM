package com.example.mysafedisclosure;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//Testcommit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//Now wih push...


        Button secondActivityBtn = (Button) findViewById(R.id.secondActivityBtn);//Reference to the second activity button
        secondActivityBtn.setOnClickListener(new View.OnClickListener() { //This listener will execute a new activity in a new view
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),SecondActivity.class); //We create an object of the class SecondActivity
                startIntent.putExtra("com.example.myselfdisclosure.MESSAGE", "HELLO WORLD!!!"); //We pas some information to the intent

                startActivity(startIntent);
            }
        });

        Button addBtn = (Button) findViewById(R.id.addBtn);//Reference to the add button
        addBtn.setOnClickListener(new View.OnClickListener() { //This activity will display the sum of two numbers in a TextView
            @Override
            public void onClick(View view) {
                EditText firstNumEditText = (EditText) findViewById(R.id.firstNumEditText);
                EditText secondNumEditText = (EditText) findViewById(R.id.secondNumEditText);
                TextView resultTestView = (TextView) findViewById(R.id.resultTextView);

                int num1 = Integer.parseInt(firstNumEditText.getText().toString());
                int num2 = Integer.parseInt(secondNumEditText.getText().toString());
                int result = num1 + num2;

                resultTestView.setText(result + " ");
            }
        });

        Button instagramBtn = (Button) findViewById(R.id.instagramBtn);//Reference to the Google button
        instagramBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://instagram.com/");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/")));
                }
            }
        });

        /*googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String google ="http://www.googl.com";
                Uri webAddress= Uri.parse(google);

                Intent goToGoogle= new Intent(Intent.ACTION_VIEW, webAddress);
                if(goToGoogle.resolveActivity(getPackageManager())!=null){
                    startActivity(goToGoogle);
                }
            }
        });*/

        /*Button postsBtn = (Button) findViewById(R.id.postsBtn);//Reference post list activity
        postsBtn.setOnClickListener(new View.OnClickListener() { //This listener will execute a new activity in a new view
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),PostList.class); //We create an object of the class PostList
                startActivity(startIntent);
            }
        });*/

    }
}
