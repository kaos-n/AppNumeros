package com.example.appnumeros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity_Nivel1 extends AppCompatActivity {

    private TextView txtjugador, txtpuntos;
    private ImageView imguno, imgdos, imgvidas;
    private EditText etirespuesta;
    private MediaPlayer music, mpbien, mpmal;

    int puntaje, numAleatorio_uno, numAleatorio_dos, resultado, vidas = 3;
    String nombrejugador, strpuntaje, strvidas;

    String numero [] = {"cero","uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2__nivel1);

        //Icono en la Action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        txtjugador = findViewById(R.id.txtNombre);
        txtpuntos = findViewById(R.id.txtPuntos);
        imgvidas = findViewById(R.id.imagen_vidas);
        imguno = findViewById(R.id.imagenPrimerNum);
        imgdos = findViewById(R.id.imagenSegundoNum);
        etirespuesta = findViewById(R.id.txtRespuesta);

        nombrejugador = getIntent().getStringExtra("jugador");
        txtjugador.setText("Jugador: " + nombrejugador);

        Toast.makeText(this,"Nivel 1 - Sumas Básicas", Toast.LENGTH_SHORT).show();

        music = MediaPlayer.create(this,R.raw.goats);
        music.start();
        music.setLooping(true);

        mpbien = MediaPlayer.create(this,R.raw.ganar);
        mpmal = MediaPlayer.create(this,R.raw.perder);

        NumAleatorio();
    }

    //boton para revisar respuestas
    public void Comparar (View view){
        String respuesta = etirespuesta.getText().toString();

        if(!respuesta.equals("")){

            int respuesta_jugador = Integer.parseInt(respuesta);
            if(resultado==respuesta_jugador){

                mpbien.start();
                puntaje++;
                txtpuntos.setText("Puntos: "+puntaje);
                etirespuesta.setText("");

            } else {
                mpmal.start();
                vidas--;

                switch (vidas){
                    case 3:
                        imgvidas.setImageResource(R.drawable.tresvidas);
                        break;
                    case 2:
                        Toast.makeText(this,"Te quedan dos oportunidades", Toast.LENGTH_SHORT).show();
                        imgvidas.setImageResource(R.drawable.dosvidas);
                        break;
                    case 1:
                        Toast.makeText(this,"Te quedan una oportunidad", Toast.LENGTH_SHORT).show();
                        imgvidas.setImageResource(R.drawable.unavida);
                        break;
                    case 0:
                        Toast.makeText(this,"Perdiste todas tus oportunidades", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }

                etirespuesta.setText("");
            }
            NumAleatorio();

        } else {
            Toast.makeText(this, "Escribe tu respuesta", Toast.LENGTH_SHORT).show();

        }

    }

    //numeros aleatorios para las sumas
    public void NumAleatorio(){
        if(puntaje<=9){

            numAleatorio_uno = (int)(Math.random()*10);
            numAleatorio_dos = (int)(Math.random()*10);

            resultado = numAleatorio_uno+numAleatorio_dos;

            if(resultado <=10){
                for(int i = 0; i <numero.length; i++){
                    int id = getResources().getIdentifier(numero[i], "drawable", getPackageName());
                    if (numAleatorio_uno == i){
                        imguno.setImageResource(id);
                    } if (numAleatorio_dos==i){
                        imgdos.setImageResource(id);
                    }
                }
            } else {
                NumAleatorio();
            }
        } else {
            Intent intent = new Intent(this, Main2Activity_Nivel2.class);

            strpuntaje = String.valueOf(puntaje);
            strvidas = String.valueOf(vidas);
            intent.putExtra("jugador", nombrejugador);
            intent.putExtra("puntaje",strpuntaje);
            intent.putExtra("vidas",strvidas);

            startActivity(intent);
            finish();
        }
    }

    //Para controlar el boton back
    @Override
    public void onBackPressed(){

    }


    @Override
    protected void onPause() {
        super.onPause();

        music.pause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        music.stop();
        music.release();
    }

    @Override
    protected void onResume() {
        super.onResume();

        music.start();
    }
}
