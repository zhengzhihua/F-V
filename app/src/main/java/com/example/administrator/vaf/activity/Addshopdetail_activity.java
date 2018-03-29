package com.example.administrator.vaf.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;
import com.example.administrator.vaf.design.Getnowtime;
import com.example.administrator.vaf.design.StringAndBitmap;
import com.example.administrator.vaf.design.UUIDGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/27.
 */

public class Addshopdetail_activity extends AppCompatActivity{
    private static final String TAG="Addshopdetail_activity";


    public static final int Cut_PHOTO = 1;
    public static final int SHOW_PHOTO = 2;
    public static final int PHOTO_ALBUM = 3;
    public static final int SHOW_PHOTO_ALBUM = 4;
    private Uri imageUri;
    private Uri uri;
    private Bitmap bitmap;
    private ImageView imageViews;
    private EditText shopnametext;
    private EditText detailtext;
    private EditText pricetext,typetext,placeforproduce1text;
    private Button buttoncommit1,makeimage;
    String userid,username,shopid,shopname,shopdescribe,price,type,produceplace,images;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addshopdetail_activity);
        Intent in=getIntent();
        Bundle bun=in.getExtras();
        userid=bun.getString("userid");
        username=bun.getString("username");
        initview();
        getdata();
    }

    private void getdata() {
        StringAndBitmap stringandbitmap=new StringAndBitmap();
        images=stringandbitmap.bitmapToString(bitmap);
        shopid= UUIDGenerator.getUUID();
        shopname=shopnametext.getText().toString();
        shopdescribe=detailtext.getText().toString();
        price=pricetext.getText().toString();
        type=typetext.getText().toString();
        produceplace=placeforproduce1text.getText().toString();
    }



    private void initview() {

        imageViews= (ImageView) findViewById(R.id.imagecommit1);
        shopnametext= (EditText) findViewById(R.id.shopname1);
        detailtext= (EditText)findViewById(R.id.detailforshop1);
        pricetext= (EditText) findViewById(R.id.price1);
        typetext= (EditText) findViewById(R.id.shoptype1);
        placeforproduce1text= (EditText) findViewById(R.id.placeforproduce1);
        buttoncommit1= (Button) findViewById(R.id.buttoncommit1);

        makeimage= (Button) findViewById(R.id.btn_photo);
        makeimage.setOnClickListener(makeimagelistener);
        buttoncommit1.setOnClickListener(buttoncommit1listener);


}
    View.OnClickListener buttoncommit1listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String word="(userid,username,shopid,shopname,shopdescribe,price,type,produceplace,image)";
            String value="('"+userid+"','"+username+"','"+shopid+"','"+shopname+"',','"+shopdescribe+"'"+price+"','"+type+"','"+produceplace+"','"+images+"')";
            Httpmanager.insertdata(Addshopdetail_activity.this,shopname,username,"commodity_bank",word,value, new HttpRequestHandler<String>() {
                @Override
                public void onSuccess(String data) {

                }

                @Override
                public void onSuccesss(ArrayList<Map<String, Object>> res) {

                }

                @Override
                public void onSuccess(String data, int totalPages, int currentPage) {

                }

                @Override
                public void onFailure(String error) {

                }
            });
        }
    };
    View.OnClickListener makeimagelistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e(TAG,  Getnowtime.systemtime());
            //创建File对象,用于存储选择的照片
            File outputImage = new File(Environment.getExternalStorageDirectory(), username+Getnowtime.systemtime()+".jpg");
            try {
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                outputImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageUri = Uri.fromFile(outputImage);
            //隐式意图启动相机
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            // 启动相机程序
            startActivityForResult(intent, Cut_PHOTO);
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Cut_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    // 启动裁剪
                    startActivityForResult(intent, SHOW_PHOTO);
                }
                break;
            case SHOW_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                         bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        // 将裁剪后的照片显示出来
                        imageViews.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;

            default:
                break;
        }
    }

}
