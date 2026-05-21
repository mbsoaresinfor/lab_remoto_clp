package com.example.labremotoclp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {

    @GET("api")
    Call<String> lerSaidaDigitais();

    @POST("api")
    Call<String> escreverInterfaces(@Body String mensagem);
}
