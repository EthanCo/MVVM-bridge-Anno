package com.ethanco.mvvm_bridge_anno;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

@MVVM(View = String.class, ViewModel = String.class)
public class MainActivity extends AppCompatActivity {

    @MVVM(View = MainActivity.class, ViewModel = MainActivity.class)
    public void say() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TransInjector.inject(this);
    }
}
