package com.platdmit.simplecloudmanager.data.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class ApiManager {
    private static final String TAG = ApiManager.class.getSimpleName();
    private static final String BASE_URL = "https://api.simplecloud.ru/v3/";
    private static String API_KEY;

    private static OkHttpClient getOkHttpInstance() {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request originalRequest = chain.request();
                    Request.Builder builder = originalRequest.newBuilder().header("Authorization", "Bearer " + API_KEY);
                    Request newRequest = builder.build();
                    return chain.proceed(newRequest);
                }).addInterceptor(getOkHttpLogLevel()).build();
    }

    private static HttpLoggingInterceptor getOkHttpLogLevel(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkHttpInstance())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getApiPoint(Class rest, String api_key){
        API_KEY = api_key;
        return (T) getRetrofitInstance().create(rest);
    }
}
