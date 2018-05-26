//package com.example.lenovo.zhangjun20180526.ui.music.presenter;
//
//import com.example.lenovo.zhangjun20180526.bean.BaseBean;
//import com.example.lenovo.zhangjun20180526.net.MainApi;
//import com.example.lenovo.zhangjun20180526.ui.base.BasePresenter;
//import com.example.lenovo.zhangjun20180526.ui.main.MainContract;
//import com.example.lenovo.zhangjun20180526.ui.music.contract.MusicContract;
//
//import javax.inject.Inject;
//
//import io.reactivex.Observer;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.schedulers.Schedulers;
//
//public class MusicPresenter extends BasePresenter<MainContract.View> implements MusicContract.Presenter {
//    MusicApi musicApi;
//    @Inject
//    public MusicPresenter(MusicApi musicApi) {
//        this.musicApi = musicApi;
//    }
//
//    @Override
//    public void onSuccess(String msg) {
//        musicApi.music(msg)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<BaseBean>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                        }
//
//                        @Override
//                        public void onNext(BaseBean baseBean) {
//                            mView.onSuccess(baseBean);
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    });
//        }
//}
