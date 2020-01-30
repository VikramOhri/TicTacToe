package com.example.tictactoe;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int x=1,c=0,flag=0;

    int[] blocks={0,0,0,0,0,0,0,0,0};
    int[][] winings={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    public void myFunc(View view)
    {
        ImageView counter=(ImageView)  view;

        MediaPlayer media=MediaPlayer.create(this,R.raw.sound);
        media.start();
        int tag=Integer.parseInt(counter.getTag().toString());
        if(blocks[tag]==0 && flag==0) {
            counter.setTranslationY(-1500);
          blocks[tag]=x;
          if (x == 1) {
              x = 2;
              c++;
              counter.setImageResource(R.drawable.cross);
              counter.animate().translationYBy(1500).setDuration(300);
          }
          else if (x == 2) {
              x = 1;
              c++;
              counter.setImageResource(R.drawable.zero);
              counter.animate().translationYBy(1500).setDuration(300);
          } else {
              Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
          }

          for(int[] wining : winings){
              if(blocks[wining[0]] == blocks[wining[1]] && blocks[wining[1]] == blocks[wining[2]] && blocks[wining[0]]!=0) {
                  Log.i("info", Integer.toString(blocks[wining[2]]));
                  String win = "";
                  String color= "";
                  if (x == 1) {
                      win += "Blue Circle has won";
                  } else if (x == 2) {
                      win += "Red Cross has won";
                      color +="Color.Red";
                  } else {
                      win += "Something went wrong";
                      color +="Color.Blue";
                  }
                  flag = 1;
                  Button playAgain = (Button) findViewById(R.id.playAgain);
                  TextView textView = (TextView) findViewById(R.id.textView);
                  textView.setText(win);
                  playAgain.setVisibility(View.VISIBLE);
                  textView.setVisibility(View.VISIBLE);
              }
              else if(c==9){
                  Button playAgain = (Button) findViewById(R.id.playAgain);
                  TextView textView = (TextView) findViewById(R.id.textView);
                  textView.setText("Match Draw");
                  playAgain.setVisibility(View.VISIBLE);
                  textView.setVisibility(View.VISIBLE);
              }
          }
        }
        else{
            Toast.makeText(this, "Already taken!!", Toast.LENGTH_SHORT).show();
        }
    }
    public void playAgain(View view){
        MediaPlayer media=MediaPlayer.create(this,R.raw.sound);
        media.start();
        Button playAgain = (Button) findViewById(R.id.playAgain);
        TextView textView = (TextView) findViewById(R.id.textView);
        playAgain.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        android.support.v7.widget.GridLayout gridLayout=findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView image=(ImageView) gridLayout.getChildAt(i);
            image.setImageDrawable(null);
        }
        for(int i=0;i<9;i++){
            blocks[i]=0;
        }
        flag=0;
        c=0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }
}
