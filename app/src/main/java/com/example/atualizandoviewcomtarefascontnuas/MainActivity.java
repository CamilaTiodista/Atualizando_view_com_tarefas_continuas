package com.example.atualizandoviewcomtarefascontnuas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://i.pinimg.com/originals/25/d6/7e/25d67e1ab8761a9fce706554245b2bdb.png";
    private ProgressBar progress;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress=(ProgressBar) findViewById(R.id.progress);
        progress.setVisibility(View.VISIBLE);
        downloadImagem(URL);
    }

    //Faz o download da Imagem em uma nova thread
    private void downloadImagem(final String urlImg){

        new Thread(){

            @SuppressLint("LongLogTag")
            @Override
            public void run () {
                try {
                    //Faz o download da imagem
                    final Bitmap imagem = Download.downloadBitmap(URL);
                    atualizaImagem(imagem);
                    //agenda o download novamente (daqui a 5 segundos)
                    handler.postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            downloadImagem(URL);
                            Log.i("Tag","ok");
                            Toast.makeText(getBaseContext(), "Atualizando", Toast.LENGTH_SHORT).show();
                        }
                    }, 5000);
                } catch (IOException e){
                    Log.e("Erro ao fazer o download: ", e.getMessage(), e);
                }
            }
        }.start();
    }

    private void atualizaImagem(final Bitmap imagem){
        runOnUiThread(new Runnable() { //atualiza a view na ui thread
            @Override
            public void run() {
                //esconde progress
                progress.setVisibility(View.INVISIBLE);
                //atualiza a imagem
                ImageView imgView= (ImageView) findViewById(R.id.img);
                imgView.setImageBitmap(imagem);
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //cancela o runnable a sair da activity
        handler.removeCallbacks(null);
    }
}