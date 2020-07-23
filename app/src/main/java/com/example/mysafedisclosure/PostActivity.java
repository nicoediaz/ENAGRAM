package com.example.mysafedisclosure;

import android.Manifest;
import android.app.Activity;
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

public class PostActivity extends AppCompatActivity implements InterventionDialog.InterventionDialogListener {

    private EditText postEditText;
    private ImageView mImageView;
    private Button mChooseBtn, shareBtn, clearBtn;
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
                //String instaCaption= postEditText.getText().toString().trim();//read the post
                openDialog();
                //shareFileToInstagram(imgUri, instaCaption);
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

    private void shareFileToInstagram(Uri uri, String instaCaption) {
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
    public void OnPostClicked() {
        String instaCaption= postEditText.getText().toString().trim();//read the post
        shareFileToInstagram(imgUri, instaCaption);
    }
}
