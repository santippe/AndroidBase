package com.example.customermonitor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.content.*;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String remoteUserID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView elem = findViewById(R.id.gridview1);
        elem.setWebViewClient(new WebViewClient());
        //elem.clearCache(true);
        //elem.clearHistory();
        elem.getSettings().setJavaScriptEnabled(true);
        elem.getSettings().setDomStorageEnabled(true);
        elem.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //elem.getSettings().setDomStorageEnabled(true);
        elem.getSettings().setGeolocationEnabled(true);
        //elem.getSettings().setAllowContentAccess(true);
        elem.getSettings().setAllowUniversalAccessFromFileURLs(true);
        elem.setWebChromeClient(new WebChromeClient());
        JavaScriptInterface jsInterface = new JavaScriptInterface(this);
        elem.addJavascriptInterface(jsInterface,"JsInterface");
        elem.loadUrl("file:///android_asset/start.html");
    }

    public class JavaScriptInterface {
        private Activity activity;

        public JavaScriptInterface(Activity activity) {
            this.activity = activity;
        }

//        public  void readSomething(){
//            SQLiteOpenHelper h1 = new SQLiteOpenHelper() {
//                @Override
//                public void onCreate(SQLiteDatabase db) {
//
//                }
//
//                @Override
//                public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//                }
//            };
//        }

        @JavascriptInterface
        public void Message(String TITLE, String MESSAGE){

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(TITLE).setTitle(MESSAGE);
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        @JavascriptInterface
        public void Toast(String message){
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
        }

        @JavascriptInterface
        public void StoreUserID(String userID){
            remoteUserID = userID;
        }

        @JavascriptInterface
        public String GerLocalUserID(){
            return remoteUserID;
        }

//        @JavascriptInterface
//        public void startVideo(String videoAddress){
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setDataAndType(Uri.parse(videoAddress), "video/3gpp");
//            activity.startActivity(intent);
//        }
    }

}


