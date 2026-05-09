package com.example.labremotoclp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EntradaAnalogicasCLPActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ENTRADAS ANALÓGICAS DO CLP");
        setContentView(R.layout.activity_entrada_analogicas_clp);
     //   ler(null);
    }


    public void escrever(View view) {

        Toast.makeText(getApplicationContext(), "Dados enviados para a CLP",
                Toast.LENGTH_SHORT).show();
    }
}