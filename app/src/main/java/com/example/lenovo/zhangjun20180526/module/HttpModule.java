package com.example.lenovo.zhangjun20180526.module;

import com.example.lenovo.zhangjun20180526.net.MainApi;
import com.example.lenovo.zhangjun20180526.net.ApiService;
import com.example.lenovo.zhangjun20180526.util.Constant;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {
    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS);
    }
    @Provides
    MainApi providemainApi(OkHttpClient.Builder builder){
        ApiService apiService = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build()
                .create(ApiService.class);
        return MainApi.getMainApi(apiService);
    }
}
