package com.example.appnumeros;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;
    private ImageView imagenPersonaje;
    private TextView txtRecord;
    private MediaPlayer music;

    int num_aleatorio = (int)(Math.random() *10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Icono en la Action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        txtNombre = findViewById(R.id.txtNombre);
        imagenPersonaje = findViewById(R.id.imagenPersonaje);
        txtRecord = findViewById(R.id.txtRecord);

        int id;
        if(num_aleatorio == 0 || num_aleatorio == 9 || num_aleatorio ==10){
            id = getResources().getIdentifier("robin","drawable",getPackageName());
            imagenPersonaje.setImageResource(id);
        } else if (num_aleatorio == 1 || num_aleatorio == 8) {
            id = getResources().getIdentifier("cyborg","drawable",getPackageName());
            imagenPersonaje.setImageResource(id);
        }
        else if (num_aleatorio == 2 || num_aleatorio == 7) {
            id = getResources().getIdentifier("chicobestia","drawable",getPackageName());
            imagenPersonaje.setImageResource(id);
        }
        else if (num_aleatorio == 3 || num_aleatorio == 6) {
            id = getResources().getIdentifier("starfire","drawable",getPackageName());
            imagenPersonaje.setImageResource(id);
        }
        else if (num_aleatorio == 4 || num_aleatorio == 5) {
            id = getResources().getIdentifier("raven","drawable",getPackageName());
            imagenPersonaje.setImageResource(id);
        }



        //Musica de fondo
        music = MediaPlayer.create(this,R.raw.backsound);
        music.start();
        music.setLooping(true);
    }

    //Metodo para el boton jugar
    public void Jugar (View view){
        String nombreStr=txtNombre.getText().toString();

        if(!nombreStr.equals("")){
            music.stop();
            music.release();

            Intent intent = new Intent(this,Main2Activity_Nivel1.class);

            intent.putExtra("jugador",nombreStr);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(this, "Primero debes escribir tu nombre", Toast.LENGTH_SHORT).show();
            //Abrir el teclado para que escriba su nombre
            txtNombre.requestFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtNombre, InputMethodManager.SHOW_IMPLICIT);

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
    protected void onPostResume() {
        super.onPostResume();

        music.start();
    }
}
