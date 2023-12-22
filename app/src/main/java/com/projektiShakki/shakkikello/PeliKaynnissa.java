package com.projektiShakki.shakkikello;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class PeliKaynnissa extends AppCompatActivity {

    private CountDownTimer ajastinPelaaja1;
    private CountDownTimer ajastinPelaaja2;
    private CountDownTimer ajastinPikkuKello1;
    private CountDownTimer ajastinPikkuKello2;
    private long aikaPelaaja1;
    private long aikaPelaaja2;
    private TextView tekstiAjastinP1;
    private TextView tekstiAjastinP2;
    private TextView pikkukello1;
    private TextView pikkukello2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peli_ikkuna);
        tekstiAjastinP1 = findViewById(R.id.ajastinP1);
        tekstiAjastinP2 = findViewById(R.id.ajastinP2);
        pikkukello1 = findViewById(R.id.pikkukello1);
        pikkukello2 = findViewById(R.id.pikkukello2);

        aikaPelaaja1 = getIntent().getLongExtra("PELIN_KESTO", 900000);
        aikaPelaaja2 = getIntent().getLongExtra("PELIN_KESTO", 900000);
        paivitaAjastinTeksti(tekstiAjastinP1, aikaPelaaja1);
        paivitaAjastinTeksti(tekstiAjastinP2, aikaPelaaja2);
        paivitaAjastinTeksti(pikkukello1, aikaPelaaja2);
        paivitaAjastinTeksti(pikkukello2, aikaPelaaja1);
        aikaPelaaja2 = aikaPelaaja1;
        
        alustaAjastimet();
    }

    private void alustaAjastimet() {
        ajastinPelaaja1 = luoUusiAjastin(aikaPelaaja1, tekstiAjastinP1);
        ajastinPelaaja2 = luoUusiAjastin(aikaPelaaja2, tekstiAjastinP2);

        ajastinPikkuKello1 = luoUusiAjastin(aikaPelaaja2, pikkukello1);
        ajastinPikkuKello2 = luoUusiAjastin(aikaPelaaja1, pikkukello2);
    }

    private CountDownTimer luoUusiAjastin(long aika, TextView ajastimenTeksti) {
        return new CountDownTimer(aika, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (ajastimenTeksti == tekstiAjastinP1 || ajastimenTeksti == pikkukello1) {
                    aikaPelaaja1 = millisUntilFinished;
                } else if (ajastimenTeksti == tekstiAjastinP2 || ajastimenTeksti == pikkukello2) {
                    aikaPelaaja2 = millisUntilFinished;
                }
                paivitaAjastinTeksti(ajastimenTeksti, millisUntilFinished);
            }
            @Override
            public void onFinish() {
                ajastimenTeksti.setText("Aika loppui!");
            }
        };
    }

    private void paivitaAjastinTeksti(TextView ajastimenTeksti, long aikaMillis) {
        int minuutit = (int) (aikaMillis / 1000) / 60;
        int sekunnit = (int) (aikaMillis / 1000) % 60;
        String aikaMuotoiltuna = String.format(Locale.getDefault(), "%02d:%02d", minuutit, sekunnit);
        ajastimenTeksti.setText(aikaMuotoiltuna);
    }

    public void pelaaja1Painike(View view) {
        if (ajastinPelaaja1 != null) {
            ajastinPelaaja1.cancel();
        }
        if (ajastinPikkuKello1 != null) {
            ajastinPikkuKello1.cancel();
        }
        ajastinPelaaja2 = luoUusiAjastin(aikaPelaaja2, tekstiAjastinP2);
        ajastinPikkuKello2 = luoUusiAjastin(aikaPelaaja2, pikkukello2);
        ajastinPelaaja2.start();
        ajastinPikkuKello2.start();

    }

    public void pelaaja2Painike(View view) {
        if (ajastinPelaaja2 != null) {
            ajastinPelaaja2.cancel();
        }

        if (ajastinPikkuKello2 != null) {
            ajastinPikkuKello2.cancel();
        }
        ajastinPelaaja1 = luoUusiAjastin(aikaPelaaja1, tekstiAjastinP1);
        ajastinPikkuKello1 = luoUusiAjastin(aikaPelaaja1, pikkukello1);
        ajastinPelaaja1.start();
        ajastinPikkuKello1.start();
    }

    public void handlePysayta(View view) {

        if (ajastinPelaaja1 != null) {
            ajastinPelaaja1.cancel();
        }
        if (ajastinPikkuKello1 != null) {
            ajastinPikkuKello1.cancel();
        }
        if (ajastinPelaaja2 != null) {
            ajastinPelaaja2.cancel();
        }
        if (ajastinPikkuKello2 != null) {
            ajastinPikkuKello2.cancel();
        }
    }

    public void handleAlkuun(View view) {
        if (ajastinPelaaja1 != null) {
            ajastinPelaaja1.cancel();
        }
        if (ajastinPelaaja2 != null) {
            ajastinPelaaja2.cancel();
        }
        aikaPelaaja1 = getIntent().getLongExtra("PELIN_KESTO", 900000);
        aikaPelaaja2 = aikaPelaaja1;

        alustaAjastimet();
        paivitaAjastinTeksti(tekstiAjastinP1, aikaPelaaja1);
        paivitaAjastinTeksti(tekstiAjastinP2, aikaPelaaja2);
        paivitaAjastinTeksti(pikkukello1, aikaPelaaja2);
        paivitaAjastinTeksti(pikkukello2, aikaPelaaja1);
    }

    public void handlePoistu(View view) {
        finish();
    }

}
