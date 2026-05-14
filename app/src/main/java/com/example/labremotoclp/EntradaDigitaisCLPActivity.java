package com.example.labremotoclp;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntradaDigitaisCLPActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ENTRADAS DIGITAIS DO CLP");
        setContentView(R.layout.activity_entrada_digitais_clp);

        for(int i=0; i < getSwitchs().length; i++){
            Switch sw = getSwitchs()[i];
            TextView tv = getTextViews()[i];
            sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        sw.setText("on");
                    } else {
                        sw.setText("off");
                    }
                }
            });
        }
    }

    private Switch[] getSwitchs(){
        return new Switch[] {findViewById(R.id.switch1),findViewById(R.id.switch2),
                findViewById(R.id.switch3),
                findViewById(R.id.switch4),findViewById(R.id.switch5),findViewById(R.id.switch6),
                findViewById(R.id.switch7),findViewById(R.id.switch8),findViewById(R.id.switch9),
                findViewById(R.id.switch10)};
    }

    private TextView[] getTextViews(){
        return new TextView[] {findViewById(R.id.textView1),findViewById(R.id.textView2),
                findViewById(R.id.textView3),
                findViewById(R.id.textView4),findViewById(R.id.textView5),findViewById(R.id.textView6),
                findViewById(R.id.textView7),findViewById(R.id.textView8),findViewById(R.id.textView9),
                findViewById(R.id.textView10)};
    }


    public void escrever(View view) {
        Switch[] sws =  getSwitchs();
        for(int i = 0; i < sws.length; i++){
            Switch sw = sws[i];
            if(sw.isChecked()){
                Interface.atualizarInterface("IN"+(i+1), Interface.STATUS_ATIVADO);
            }else{
                Interface.atualizarInterface("IN"+(i+1), Interface.STATUS_DESABILITADO);
            }
        }

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