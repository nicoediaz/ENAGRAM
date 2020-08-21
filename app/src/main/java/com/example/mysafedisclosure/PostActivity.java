package com.example.mysafedisclosure;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.PendingIntent;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ComponentCallbacks2;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.ClipboardManager;

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
import java.util.List;
import java.util.Map;



public class PostActivity extends AppCompatActivity implements InterventionDialog.InterventionDialogListener{

    private EditText postEditText;
    private ImageView mImageView;
    private Button mChooseBtn, shareBtn, clearBtn;
    private Uri imgUri;
    private SessionManager sessionManager;
    private String usrId;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private static final String INSTAGRAM_PACKAGE_NAME = "com.instagram.android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);//Now wih push...

        sessionManager = new SessionManager(this);
        sessionManager.checkLoggin();//If not Logged in, it will redirect to the Login page

        HashMap<String,String> user_detail = sessionManager.getUserDetail();
        usrId = user_detail.get(SessionManager.ID);

        mImageView = findViewById(R.id.image_view);
        mChooseBtn = findViewById(R.id.choose_img_btn);
        shareBtn = findViewById(R.id.share_btn);
        clearBtn = findViewById(R.id.clear_btn);
        postEditText = findViewById(R.id.postEditText);
        imgUri =null;

        shareBtn.setClickable(false);
        shareBtn.setEnabled(false);

        clearBtn.setClickable(false);
        clearBtn.setEnabled(false);

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
                openDialog();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImageView.setImageResource(R.drawable.ic_baseline_image_24);
                postEditText.getText().clear();

                clearBtn.setEnabled(false);
                clearBtn.setClickable(false);

                shareBtn.setEnabled(false);
                shareBtn.setClickable(false);
                shareBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.instagram_ico_gray, 0, 0, 0);
            }
        });
    }

    public void openDialog(){
        InterventionDialog dialog = new InterventionDialog();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(),"intervention dialog");
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

            //if(mImageView.getDrawable()!=null)
            shareBtn.setClickable(true);
            shareBtn.setEnabled(true);
            shareBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.instagram_ico, 0, 0, 0);

            clearBtn.setClickable(true);
            clearBtn.setEnabled(true);
        }
    }

    public void amKillProcess(String process)
    {
        ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        am.killBackgroundProcesses(process);
    }

    private void shareFileToInstagram(Uri uri, String instaCaption) {

        amKillProcess("com.instagram.android");//we kill the Instagram process in case it is already running

        Intent feedIntent = new Intent(Intent.ACTION_SEND);
        feedIntent.setType("image/*");
        feedIntent.putExtra(Intent.EXTRA_STREAM, uri);
        feedIntent.putExtra(Intent.EXTRA_TEXT, instaCaption);
        feedIntent.setPackage("com.instagram.android");
        feedIntent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);//added

        Intent storiesIntent = new Intent("com.instagram.share.ADD_TO_STORY");
        storiesIntent.setDataAndType(uri, "jpg");
        storiesIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        storiesIntent.putExtra(Intent.EXTRA_TEXT, instaCaption);
        storiesIntent.setPackage("com.instagram.android");
        storiesIntent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);//added

        setCaption(PostActivity.this, instaCaption);

        PostActivity.this.grantUriPermission(
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

    @Override
    public void OnPostClicked() { //Add this information to the database
        String instaCaption= postEditText.getText().toString().trim();//read the post
        EventsRecorder.recordPopupAction("publish post", usrId, instaCaption, this);

        if(instaCaption.length()==0){//If the POST field is empty. Otherwise it may show some strange string
            instaCaption =" ";
        }
        shareFileToInstagram(imgUri, instaCaption);
    }

    @Override
    public void OnEditClicked() { //Add this information to the database
        String instaCaption= postEditText.getText().toString().trim();//read the post
        EventsRecorder.recordPopupAction("edit post", usrId, instaCaption, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.post_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout_item:{
                sessionManager.logout();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
