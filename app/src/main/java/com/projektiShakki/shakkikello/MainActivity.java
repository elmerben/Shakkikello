package com.projektiShakki.shakkikello;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button UusiPeli = findViewById(R.id.handleUusiPeli);
        UusiPeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aloitaUusiPeli();
            }
        });
    }


    public void aloitaUusiPeli() {
        AlertDialog.Builder aloittaja = new AlertDialog.Builder(this);
        aloittaja.setTitle("Aseta pelin kesto minuutteina");

        final EditText syote = new EditText(this);
        syote.setInputType(InputType.TYPE_CLASS_NUMBER);
        syote.setMaxWidth(50);
        syote.setMinHeight(200);
        aloittaja.setView(syote);

        aloittaja.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int kesto = 0;
                try {
                    kesto = Integer.parseInt(syote.getText().toString()) * 60 * 1000;
                    Intent inteetti = new Intent(MainActivity.this, PeliKaynnissa.class);
                    inteetti.putExtra("PELIN_KESTO", (long) kesto);
                    startActivity(inteetti);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Virhe keston asettamisessa", Toast.LENGTH_LONG).show();                }
            }
        });
        aloittaja.setNegativeButton("Peruuta", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialogi = aloittaja.create();
        dialogi.show();
    }




}

