package com.example.labremotoclp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntradaAnalogicasCLPActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ENTRADAS ANALÓGICAS DO CLP");
        setContentView(R.layout.activity_entrada_analogicas_clp);

    }


    public void escrever(View view) {

        SeekBar seekBarA0 = findViewById(R.id.seekBar);
        SeekBar seekBarA1 = findViewById(R.id.seekBar2);
        int valueA0 =  seekBarA0.getProgress();
        int valueA1 =  seekBarA1.getProgress();

        Interface.atualizarInterface("A0",valueA0);
        Interface.atualizarInterface("A1",valueA1);

        Log.i("marcelo","dados para enviar servidor: " +
                Interface.converteParaJson());
        Call<String> call = RetrofitClient.getApiService()
                .escreverInterfaces(Interface.converteParaJson());

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String respostaServidor = response.body();
                    Toast.makeText(getApplicationContext(), "Dados gravados na CLP",
                            Toast.LENGTH_SHORT).show();
                    Log.d("marcelo", "O servidor respondeu: " + respostaServidor);
                } else {
                    Log.e("marcelo", "Erro no servidor. Código HTTP: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("marcelo", "Falha na conexão de rede", t);
            }
        });



    }
}