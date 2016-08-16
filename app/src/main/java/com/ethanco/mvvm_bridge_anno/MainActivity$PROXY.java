package com.ethanco.mvvm_bridge_anno;

import com.ethanco.mvvm_bridge_anno.view.IMainView;
import com.ethanco.mvvm_bridge_anno.viewModel.MainViewModel;

/**
 * @Description proxy
 * Created by EthanCo on 2016/8/16.
 */
public class MainActivity$PROXY<T extends MainActivity> implements AbstractInjector<T> {

    @Override
    public void inject(Finder finder, T target, Object source) {
        //Finder,obj,obj
        MainViewModel mViewModel = new MainViewModel(); //创建ViewModel
        if (mViewModel != null) {
            mViewModel.attachView((IMainView) this); //View与ViewModel建立关系
        }
    }
}
