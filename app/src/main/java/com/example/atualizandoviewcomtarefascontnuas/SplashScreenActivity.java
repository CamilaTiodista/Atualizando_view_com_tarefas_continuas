package com.example.atualizandoviewcomtarefascontnuas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class SplashScreenActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //delay
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                //Inicia a mainActivity
                startActivity(new Intent(getBaseContext(),MainActivity.class));
                //fecha a activity do splash
                finish();
            }
        },2000);

    }
}
