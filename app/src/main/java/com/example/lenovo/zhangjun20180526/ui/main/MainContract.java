package com.example.lenovo.zhangjun20180526.ui.main;

import com.example.lenovo.zhangjun20180526.bean.BaseBean;
import com.example.lenovo.zhangjun20180526.ui.base.BaseContract;

public interface MainContract {
    interface View extends BaseContract.BaseView{
        void onSuccess(BaseBean baseBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void onSuccess(int type,int page);
    }

}
