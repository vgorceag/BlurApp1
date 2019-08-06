package com.example.blurapp;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v8.renderscript.*;
import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static org.opencv.imgproc.Imgproc.GaussianBlur;

public class Main2Activity extends AppCompatActivity {

    Button buton1;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    ImageView imageView;
    Button butonchange;
    Button butoncrop;
    final int PIC_CROP = 3;
    //private static final float BITMAP_SCALE = 0.4f;
    //private static final float BLUR_RADIUS = 7.5f;
    ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        butonchange = findViewById(R.id.switch1);
        butonchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentswitch1 = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intentswitch1);
            }
        });

        imageView = findViewById(R.id.imageView);
        buton1 = findViewById(R.id.selectphoto);
        buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();


            }
        });
        cropepicture();
    }

    private void openGallery() {

        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
                Uri imageUri = data.getData();
                imageView.setImageURI(imageUri);
                //performCrop();
                   // if(imageView.getDrawable() != null){
                       // cropepicture();
                   //  }
                //cropepicture();


                //performCrop();
                //Bitmap bitmap = BitmapFactory.decodeResource(getResources(),imageUri);
                //imageView.setImageBitmap(bitmap);
                // Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
                // InputStream inputStream = getContentResolver().openInputStream(imageUri);
                // bitmap = BitmapFactory.decodeStream(inputStream);
                // GaussianBlur(bitmap);


            }
        } catch (ActivityNotFoundException e) {
            String eroare = "Whoops - your device doesn't support the crop action!";
            Toast.makeText(this, eroare, Toast.LENGTH_SHORT).show();


        }// catch (FileNotFoundException e) {
        // e.printStackTrace();
        //String mesaj = "File not found!";
        //Toast.makeText(this, mesaj, Toast.LENGTH_SHORT).show();
    } //catch (IOException e) {
    // e.printStackTrace();
    //}
    //}

    private void cropepicture() {

            butoncrop = findViewById(R.id.cropbuton);
            butoncrop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    performCrop();
                }
            });
        }

        public void performCrop(){
                try {
                    //call the standard crop action intent (the user device may not support it)
                    Intent cropIntent = new Intent(" ");
                    //indicate image type and Uri
                    cropIntent.setDataAndType(imageUri, "image/*");
                    //set crop properties
                    cropIntent.putExtra("crop", "true");
                    //indicate aspect of desired crop
                    cropIntent.putExtra("aspectX", 1);
                    cropIntent.putExtra("aspectY", 1);
                    //indicate output X and Y
                    cropIntent.putExtra("outputX", 256);
                    cropIntent.putExtra("outputY", 256);
                    //retrieve data on return
                    cropIntent.putExtra("return-data", true);
                    //start the activity - we handle returning in onActivityResult
                    startActivityForResult(cropIntent, PIC_CROP);
                }
                catch (ActivityNotFoundException anfe){
                    //display an error message
                    String errorMessage = "Whoops - your device doesn't support the crop action!";
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();

                }


            }

            public void GaussianBlur(Bitmap bitmap){
                bitmap=bitmap.copy(Bitmap.Config.ARGB_8888, true);
                Mat src= new Mat(bitmap.getHeight(), bitmap.getWidth(), CvType.CV_8UC4);
                Mat dest= new Mat(bitmap.getHeight(), bitmap.getWidth(), CvType.CV_8UC4);
                Utils.bitmapToMat(bitmap,src);
                Imgproc.GaussianBlur(src,dest,new Size( 21,21),0);
                Bitmap processImg= Bitmap.createBitmap(dest.cols(),dest.rows(),Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(dest,processImg);
                iv = findViewById(R.id.blurimage);
                iv.setImageBitmap(processImg);
               }
            /*
        public void blurcropedseq(){


                if(requestCode == PIC_CROP){
                try {
                    Bundle extras =  data.getExtras();
                    Bitmap cropedseq = extras.getParcelable("data");
                    Bitmap result = BlurBuilder.blur(this, BitmapFactory.decodeResource(getResources(), PIC_CROP));
                    imageView.setImageBitmap(result);
                }
                catch(ActivityNotFoundException suck) {
                    String error = "Whoops - your device doesn't support the crop action!";
                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                }
                }
                */


             }




