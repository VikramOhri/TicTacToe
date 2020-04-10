package com.example.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class game extends AppCompatActivity {

    int x=1,c=0,flag=0;

    int[] blocks={0,0,0,0,0,0,0,0,0};
    int[][] winings={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};

    public void myFunc(View view)
    {
        Intent intent=getIntent();
        ImageView counter=(ImageView)  view;
        TextView textView = (TextView) findViewById(R.id.textView);
        String pl1=intent.getStringExtra(MainActivity.EXTRA_MESSAGE1);
        String pl2=intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        MediaPlayer media=MediaPlayer.create(this,R.raw.sound);
        media.start();

        textView.setVisibility(View.VISIBLE);

        int tag=Integer.parseInt(counter.getTag().toString());
        if(blocks[tag]==0 && flag==0) {
            counter.setTranslationY(-1500);
            blocks[tag]=x;

            if (x == 1) {
                textView.setText(pl1 + "'s Turn");

                x = 2;
                c++;
                counter.setImageResource(R.drawable.cross);
                counter.animate().translationYBy(1500).setDuration(300);
            }
            else if (x == 2) {
                textView.setText(pl2 + "'s Turn");
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
                    if (x == 1) {
                        win +=pl1;
                        win += " has won";
                    } else if (x == 2) {
                        win +=pl2;
                        win += " has won";
                    } else {
                        win += "Something went wrong";
                    }
                    flag = 1;
                    Button playAgain = (Button) findViewById(R.id.playAgain);
                    Button mainMenu = (Button) findViewById(R.id.mainMenu);
                    textView.setText(win);
                    playAgain.setVisibility(View.VISIBLE);
                    mainMenu.setVisibility(View.VISIBLE);
                }
                else if(c==9){
                    Button playAgain = (Button) findViewById(R.id.playAgain);
                    Button mainMenu = (Button) findViewById(R.id.mainMenu);

                    textView.setText("Match Draw");
                    playAgain.setVisibility(View.VISIBLE);
                    mainMenu.setVisibility(View.VISIBLE);
                }
            }
        }
        else{
            Toast.makeText(this, "Already taken!!", Toast.LENGTH_SHORT).show();
        }
    }
    public void mainMenu(View view){

        finish();

    }
    public void playAgain(View view){
        MediaPlayer media=MediaPlayer.create(this,R.raw.sound);
        media.start();
        Button playAgain = (Button) findViewById(R.id.playAgain);
        Button mainMenu=(Button) findViewById(R.id.mainMenu);
        TextView textView = (TextView) findViewById(R.id.textView);
        playAgain.setVisibility(View.INVISIBLE);
        mainMenu.setVisibility(View.INVISIBLE);
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
        Intent intent=getIntent();
        String pl1=intent.getStringExtra(MainActivity.EXTRA_MESSAGE1);
        String pl2=intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        if(x==1)
            textView.setText(pl2 + "'s Turn");
        else if(x==2)
            textView.setText(pl1 + "'s Turn");
        textView.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView textView = (TextView) findViewById(R.id.textView);
        Intent intent=getIntent();
        String pl2=intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        textView.setText(pl2 + "'s Turn");
        textView.setVisibility(View.VISIBLE);
    }
}
