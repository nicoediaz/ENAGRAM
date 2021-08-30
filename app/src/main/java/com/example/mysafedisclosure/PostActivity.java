package com.example.mysafedisclosure;

import android.Manifest;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

//EventsRecorder.EventsRecorderListener,
public class PostActivity extends AppCompatActivity implements InterventionDialog.InterventionDialogListener, GeneralCallbacks{

    private EditText postEditText;
    private ImageView mImageView;
    private Button mChooseBtn, shareBtn, clearBtn;
    private Uri imgUri;
    private SessionManager sessionManager;
    private String usrId;
    private List<Intervention> interventionList;
    private List<Integer> shownInterventions;

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

        shownInterventions = new ArrayList<Integer>();

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

        String app_language = getString(R.string.app_language); //Add as parameter in the readInterventionsTable function
        EventsRecorder.readInterventionsTable(this);

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

    public void openDialog(){ //Check the number of interventions before

        EventsRecorder.countTodaysInterventions(usrId,this);

        //InterventionDialog dialog = new InterventionDialog();
        //dialog.setCancelable(false);
        //dialog.show(getSupportFragmentManager(),"intervention dialog");
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

    private String getRealPathFromURI(Uri contentURI) {

        String thePath = "no-path-found";
        String[] filePathColumn = {MediaStore.Images.Media.DISPLAY_NAME};
        Cursor cursor = getContentResolver().query(contentURI, filePathColumn, null, null, null);
        if(cursor.moveToFirst()){
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            thePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return  thePath;
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
    public void OnPostClicked(int idMsg) { //Add this information to the database
        String imageName= getRealPathFromURI(imgUri);
        String instaCaption= postEditText.getText().toString().trim();//read the post
        EventsRecorder.recordPopupAction("publish post", usrId, instaCaption, idMsg, imageName, this);

        if(instaCaption.length()==0){//If the POST field is empty. Otherwise it may show some strange string
            instaCaption =" ";
        }
        shareFileToInstagram(imgUri, instaCaption);
    }

    @Override
    public void OnEditClicked(int idMsg) { //Add this information to the database
        String imageName= getRealPathFromURI(imgUri);
        String instaCaption= postEditText.getText().toString().trim();//read the post
        EventsRecorder.recordPopupAction("edit post", usrId, instaCaption, idMsg, imageName, this);
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

    @Override
    public void VolleyResponse(List<Intervention> intvList) {
        interventionList = intvList;
    }

    //@Override
    public void OnPopupsCount(int today_popups, int ignored_popups, int accepted_popups, int last_popup) {

        if(today_popups==0)//today_popups==0
        {
            shownInterventions.clear();
            shownInterventions = new ArrayList<Integer>();
        }

        int app_version = Integer.parseInt(getString(R.string.app_version));

        if(app_version==1){ //Here for APP 1
            NoIntervention();
        }
        else{
            if ((today_popups ==0) || (today_popups<5 && last_popup>60)) { //TODO: Change time in between (to 60 min)
                //if ((today_popups ==0) || (today_popups<5 && last_popup>10)) {//We intervene a maximum of 5 times a day. Interventions should have at least 10 min in between
                Bundle args = new Bundle();
                int warning_id = -1;
                int popup_counter = today_popups +1;
                String app_language = getString(R.string.app_language);

                if(app_language.equals("DE")){
                    args.putString("Header", "Bereit zum Teilen?");
                }
                else{
                    args.putString("Header", "Ready to share?");
                }

                if(app_version==2){// Only the "Ready to share legend is shown"
                    args.putString("Warning", "");
                }
                if(app_version==3){// Select a random warning message
                    Random rand = new Random();
                    String warning_msg="";

                    do{ //we want to make sure that the same message is not displayed twice in the same day
                        warning_id = 1 + rand.nextInt(27); //"Other users have received wake-up calls at work after posting about alcohol consumption"
                    }while(shownInterventions.contains(warning_id));

                    if(app_language.equals("DE")){
                        //warning_msg = "<p style=\"text-align:center\"><b>Fact of the day "+popup_counter+":</b><br/>"+getWarningFromList(warning_id)+"</p>";
                        warning_msg = "<b>Fakt des Tages #"+popup_counter+":</b><br/>"+getWarningFromList(warning_id);
                    }
                    else{
                        //warning_msg = "<p style=\"text-align:center\"><b>Fact of the day "+popup_counter+":</b><br/>"+getWarningFromList(warning_id)+"</p>";
                        warning_msg = "<b>Fact of the day #"+popup_counter+":</b><br/>"+getWarningFromList(warning_id);
                    }

                    args.putString("Warning", warning_msg);
                }

                args.putInt("Id",warning_id);

                InterventionDialog dialog = new InterventionDialog();
                dialog.setArguments(args);
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager(), "intervention dialog");
            } else {
                NoIntervention();
            }
        }
    }

    private String getWarningFromList(int warning_id) {

        boolean found = false;
        String message = null;

        ListIterator<Intervention> interventionListIterator = interventionList.listIterator();

        while (interventionListIterator.hasNext() && !found) {
            Intervention i = interventionListIterator.next();

            if(i.getId()==warning_id){
                found =true;
                message = i.getMessage();
            }
        }

        return message;
    }

    public void NoIntervention(){ //We do not generate an intervention but we record the event
        int warning_id = -11;
        OnPostClicked(warning_id);
    }

    /*public void NoIntervention(){
        String instaCaption= postEditText.getText().toString().trim();//read the post

        if(instaCaption.length()==0){//If the POST field is empty. Otherwise it may show some strange string
            instaCaption =" ";
        }

        shareFileToInstagram(imgUri, instaCaption);
    }*/
}