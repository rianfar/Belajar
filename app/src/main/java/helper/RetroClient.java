package helper;

import connection.API;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    /********
     * URLS
     *******/

    private static final String ROOT_URL = "http://dev.edi-indonesia.co.id/";

    private static API service;
    private static RetroClient retroClient;
    public static Retrofit retrofit;
    private RetroClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(API.class);
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static RetroClient getInstance() {
        if (retroClient == null) {
            retroClient = new RetroClient();
        }
        return retroClient;
    }
}
