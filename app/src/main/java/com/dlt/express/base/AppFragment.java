package com.dlt.express.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.hzlh.sdk.ui.BaseFragment;
import com.hzlh.sdk.util.YLog;



public class AppFragment extends BaseFragment {

    public AppFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        YLog.i("fragment >>> " + this.getClass().getSimpleName());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 启动一个新的Activity
     *
     * @param clazz
     */
    protected void startActivity(Class<?> clazz) {
        startActivity(new Intent(getActivity(), clazz));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
