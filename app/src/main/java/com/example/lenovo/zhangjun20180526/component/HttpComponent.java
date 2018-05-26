package com.example.lenovo.zhangjun20180526.component;

import com.example.lenovo.zhangjun20180526.MainActivity;
import com.example.lenovo.zhangjun20180526.module.HttpModule;

import dagger.Component;

@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject(MainActivity mainActivity);
}
