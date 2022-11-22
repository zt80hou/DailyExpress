package com.dlt.express.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.dlt.express.R;
import com.dlt.express.base.AppFragment;
import com.dlt.express.main.StorageInActivity;

import org.jetbrains.annotations.NotNull;

/**
 * 描述：首页
 * 作者：Zhout
 * 日期：2022/11/19 11:45
 */
public class HomeFragment extends AppFragment implements View.OnClickListener {
    private Context context;
    private LinearLayout layoutIn;
    private LinearLayout layoutOn;
    private LinearLayout layoutPd;
    private LinearLayout layoutOff;
    private LinearLayout layoutOut;

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(context, R.layout.fragment_home, null);
        layoutIn = view.findViewById(R.id.layout_in);
        layoutOn = view.findViewById(R.id.layout_on);
        layoutPd = view.findViewById(R.id.layout_pd);
        layoutOff = view.findViewById(R.id.layout_off);
        layoutOut = view.findViewById(R.id.layout_out);

        layoutIn.setOnClickListener(this);
        layoutOn.setOnClickListener(this);
        layoutPd.setOnClickListener(this);
        layoutOff.setOnClickListener(this);
        layoutOut.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == layoutIn) {//入库
            startActivity(StorageInActivity.class);
        } else if (view == layoutOn) {//上架

        } else if (view == layoutPd) {//盘点

        } else if (view == layoutOff) {//下架

        } else if (view == layoutOut) {//出库

        }
    }

}
