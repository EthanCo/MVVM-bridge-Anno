package com.ethanco.mvvm_bridge_anno;

import android.os.Bundle;

import com.ethanco.mvvm_bridge_anno.viewModel.BBActivity;
import com.ethanco.mvvm_bridge_anno.viewModel.MainViewModel;

@MVVM(View = Integer.class, ViewModel = Integer.class)
public class MainActivity extends BBActivity<MainViewModel> {

    @MVVM(View = String.class, ViewModel = String.class)
    public void say() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TransInjector.inject(this);
    }
}
