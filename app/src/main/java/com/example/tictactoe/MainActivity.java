package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_MESSAGE1 = "com.example.myfirstapp.MESSAGE1";
    public void start(View view){

        EditText player1=(EditText)findViewById(R.id.player1name);
        EditText player2=(EditText)findViewById(R.id.player2name);
        String player1name=player1.getText().toString();
        String player2name=player2.getText().toString();

        if(player1name.equals("") || player2name.equals("")){
            Toast.makeText(this, "Please Enter a name", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, game.class);

            intent.putExtra(EXTRA_MESSAGE, player1name);
            intent.putExtra(EXTRA_MESSAGE1, player2name);
            startActivity(intent);
            player1.setText("");
            player2.setText("");
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

    }
}
