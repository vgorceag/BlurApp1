package com.example.blurapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;
//import android.support.v4.app.FragmentActivity;

public class Main3Activity extends AppCompatActivity {


    Uri videoUri;
    Button butonvideo;
    VideoView videoView;
    Button play;
    Button replay;
    Button pause;
    private static final int PICK_VIDEO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        butonvideo = (Button) findViewById(R.id.switch2);
        butonvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentswitch2 = new Intent(Main3Activity.this, Main2Activity.class);
                startActivity(intentswitch2);
            }
        });

        videoView = (VideoView) findViewById(R.id.videoView3);
        butonvideo = (Button) findViewById(R.id.selectvideo);
        butonvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }


    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_VIDEO)
        {
            try {
                videoUri = data.getData();
                videoView.setVideoURI(videoUri);

                porneste();
                stopeaza();
                reporneste();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void openGallery ( ) {

            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, PICK_VIDEO);
        }

        @SuppressLint("ClickableViewAccessibility")
        private void pause() {
            videoView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (videoView.isPlaying() && event.getAction() == MotionEvent.ACTION_DOWN) {
                        videoView.pause();

                        return true;
                    }

                    return false;
                }
            });
        }
        private void porneste(){
        play = (Button) findViewById(R.id.playbuton);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();

            }
        });

        }
        private void stopeaza(){

        pause = (Button) findViewById(R.id.pausebuton);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.pause();
            }
        });
        }
        private void reporneste(){
        replay = (Button) findViewById(R.id.replaybuton);
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.resume();
            }
        });
        }


}












