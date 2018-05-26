package com.example.lenovo.zhangjun20180526.net;

import com.example.lenovo.zhangjun20180526.bean.BaseBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

public class MainApi {
    private static MainApi mainApi;
    private ApiService apiService;

    public MainApi(ApiService apiService) {
        this.apiService = apiService;
    }
    public static MainApi getMainApi(ApiService apiService){
        if (mainApi==null){
            mainApi=new MainApi(apiService);
        }
        return mainApi;
    }
    public Observable<BaseBean> main(int type,int page){
        Map<String, String> params = new HashMap<>();
        params.put("type", String.valueOf(type));
        params.put("page", String.valueOf(page));
        return apiService.doGet(params);
    }
}
