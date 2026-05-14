package com.example.labremotoclp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("/lerSaidaDigitais")
    Call<String> lerSaidaDigitais();

    @POST("/gravarInterfaces")
    Call<String> escreverInterfaces(@Body String mensagem);
}
