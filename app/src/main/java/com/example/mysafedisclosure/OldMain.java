package com.example.mysafedisclosure;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.ClipboardManager;

public class OldMain extends AppCompatActivity {

    private EditText postEditText;
    private ImageView mImageView;
    private Button mChooseBtn, shareBtn;
    private Uri imgUri;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private static final String INSTAGRAM_PACKAGE_NAME = "com.instagram.android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);//Now wih push...

        mImageView = findViewById(R.id.image_view);
        mChooseBtn = findViewById(R.id.choose_img_btn);
        shareBtn = findViewById(R.id.share_btn);
        postEditText = findViewById(R.id.postEditText);
        imgUri =null;

        mChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check runtime permission
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        //permission not granted, request it.
                        String permissions [] = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else{
                        //permission already granted
                        pickImageFromGallery();
                    }
                }
                else{
                    //system os is less than marshmallow
                    pickImageFromGallery();
                }
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String instaCaption= postEditText.getText().toString().trim();//read the post
                shareFileToInstagram(imgUri, instaCaption);

                /*String instaCaption= postEditText.getText().toString().trim();//read the post
                Intent instaIntent = createInstagramIntent(imgUri, instaCaption);

                setCaption(MainActivity.this, instaCaption);
                startActivity(Intent.createChooser(instaIntent, "Share to"));*/
            }
        });



        /*Button instagramBtn = (Button) findViewById(R.id.instagramBtn);//Opens the Instagram App
        instagramBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //copy content from text box
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
        });*/

                /*Button secondActivityBtn = (Button) findViewById(R.id.secondActivityBtn);//Reference to the second activity button
        secondActivityBtn.setOnClickListener(new View.OnClickListener() { //This listener will execute a new activity in a new view
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),SecondActivity.class); //We create an object of the class SecondActivity
                startIntent.putExtra("com.example.myselfdisclosure.MESSAGE", "HELLO WORLD!!!"); //We pas some information to the intent

                startActivity(startIntent);
            }
        });*/

        /*Button addBtn = (Button) findViewById(R.id.addBtn);//Reference to the add button
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
        });*/

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

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override //We check if the user granted the permissions
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission was granted
                    pickImageFromGallery();
                }
                else{
                    //permission was denied
                    Toast.makeText(this,"Permission denied...!",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK && requestCode==IMAGE_PICK_CODE){
            //set image to image view
            mImageView.setImageURI(data.getData());
            imgUri = data.getData();
        }
    }

    /*String type = "image/*";
    String filename = "/myPhoto.jpg";
    String mediaPath = Environment.getExternalStorageDirectory() + filename;
    createInstagramIntent(type, mediaPath);

    private void createInstagramIntent(String type, String mediaPath)
    {
        // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);

        // Set the MIME type
        share.setType(type);

        // Create the URI from the media
        File media = new File(mediaPath);
        Uri uri = Uri.fromFile(media);

        // Add the URI to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);

        //set the intent package to Instagram
        share.setPackage("com.instagram.android");

        // Broadcast the Intent.
        startActivity(Intent.createChooser(share, "Share to"));
    }*/

    /*private Intent createInstagramIntent(Uri imgUri, String caption) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
        shareIntent.putExtra(Intent.EXTRA_TEXT, caption);
        shareIntent.setPackage("com.instagram.android");
        return shareIntent;
    }*/

    //Try this other version with Stories
    private void shareFileToInstagram(Uri uri, String instaCaption) { //Tested and I works!!!!
        Intent feedIntent = new Intent(Intent.ACTION_SEND);
        feedIntent.setType("image/*");
        feedIntent.putExtra(Intent.EXTRA_STREAM, uri);
        feedIntent.putExtra(Intent.EXTRA_TEXT, instaCaption);
        feedIntent.setPackage("com.instagram.android");

        Intent storiesIntent = new Intent("com.instagram.share.ADD_TO_STORY");
        storiesIntent.setDataAndType(uri, "jpg");
        storiesIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        storiesIntent.putExtra(Intent.EXTRA_TEXT, instaCaption);
        storiesIntent.setPackage("com.instagram.android");

        setCaption(OldMain.this, instaCaption);

        OldMain.this.grantUriPermission(
                "com.instagram.android", uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Intent chooserIntent = Intent.createChooser(feedIntent, "Share on Instagram!");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {storiesIntent});
        startActivity(chooserIntent);
    }

    public static void setCaption(Context context, String caption) { //Set caption in the Image
        if (!TextUtils.isEmpty(caption)) {
            ClipboardManager clipboard =
                    (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard != null) {
                clipboard.setPrimaryClip(ClipData.newPlainText("Caption", caption));
            }
        }
    }
}
