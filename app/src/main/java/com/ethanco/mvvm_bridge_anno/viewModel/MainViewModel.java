package com.ethanco.mvvm_bridge_anno.viewModel;

import com.ethanco.mvvm_bridge_anno.view.IMainView;
import com.ethanco.mvvm_bridge_anno.viewModel.abs.BaseViewModel;

/**
 * Created by EthanCo on 2016/8/16.
 */
public class MainViewModel extends BaseViewModel<IMainView> {
    public void show() {
        getView().toast();
    }
}
