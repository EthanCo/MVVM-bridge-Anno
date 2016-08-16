package com.ethanco.mvvm_bridge_anno.viewModel;

import android.util.Log;

import com.ethanco.mvvm_bridge_anno.view.abs.ToolbarActivity;
import com.ethanco.mvvm_bridge_anno.viewModel.abs.BaseViewModel;

/**
 * Created by EthanCo on 2016/8/16.
 */
public abstract class BBActivity<M extends BaseViewModel> extends ToolbarActivity {
    private M mViewModel;
    private boolean isPrintLifeCycle = false; //是否打印Activity生命周期
    private static final String TAG = "Z-BaseActivity";

    @Override
    protected void onStart() {
        super.onStart();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "onStart : ");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "onResume : ");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "onPause : ");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "onStop : ");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "onDestroy : ");
        }
        if (mViewModel != null) {
            mViewModel.detachView();
        }
    }
}
