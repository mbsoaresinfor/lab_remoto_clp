package com.example.labremotoclp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SaidaDigitaisCLTActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SAÍDAS DIGITAIS DO CLP");
        setContentView(R.layout.activity_saida_digitais_clp);
     //   ler(null);
    }

    public void ler(View view) {
        ImageView[] imageViews = {findViewById(R.id.imageView1),findViewById(R.id.imageView2),findViewById(R.id.imageView3),findViewById(R.id.imageView4),findViewById(R.id.imageView5),
                findViewById(R.id.imageView6),findViewById(R.id.imageView7),findViewById(R.id.imageView8),findViewById(R.id.imageView9),findViewById(R.id.imageView10)};


        String dados = buscarDadosCLP();
        String[] dadosInterfaces = dados.split(",");
        for(int i=0; i < dadosInterfaces.length;i++){
            String[] dadoInterface = dadosInterfaces[i].split(":");
            int statusInterface = Integer.parseInt(dadoInterface[1]);
            ImageView imageView = imageViews[i];
            if(StatusInterface.ATIVADO == statusInterface){
                imageView.setImageResource(R.drawable.ic_launcher_background);
            }else if(StatusInterface.DESABILITADO == statusInterface){
                imageView.setImageResource(R.drawable.ic_launcher_foreground);
            }else{
                Log.i("marcelo","Status desconhecido");
            }
        }
    }


    private String buscarDadosCLP(){

       return "\"OUT1\":0,\"OUT2\":0,\"OUT3\":0,\"OUT4\":0,\"OUT5\":0,\"OUT6\":0\"OUT7\":0,\"OUT8\":0,\"OUT9\":1,\"OUT10\":0\\";
    }
}