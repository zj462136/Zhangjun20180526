package com.example.lenovo.zhangjun20180526.ui.base;

public interface BaseContract {
    interface BasePresenter<T extends BaseView>{
        void attachView(T view);
        void detachView();
    }
    interface BaseView{
        void showLoading();
        void dimissLoading();
    }
}
