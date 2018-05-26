package com.example.lenovo.zhangjun20180526.ui.main;

import com.example.lenovo.zhangjun20180526.bean.BaseBean;
import com.example.lenovo.zhangjun20180526.net.MainApi;
import com.example.lenovo.zhangjun20180526.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    MainApi mainApi;
    @Inject
    public MainPresenter(MainApi mainApi) {
        this.mainApi = mainApi;
    }

    @Override
    public void onSuccess(int type, int page) {
        mainApi.main(type,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        mView.onSuccess(baseBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
