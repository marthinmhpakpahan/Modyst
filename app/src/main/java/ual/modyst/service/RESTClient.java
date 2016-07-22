package ual.modyst.service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RESTClient {

    public static RESTInterface REST_CLIENT;
    public static String URL = "http://192.168.43.56/wsmodyst/vendor/slim/slim/";

    static {
        setRestClient();
    }

    private RESTClient(){}
    public static RESTInterface getRestClient(){return REST_CLIENT; }

    private static void setRestClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        REST_CLIENT = retrofit.create(RESTInterface.class);
    }
}
