package com.dlt.express.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.dlt.express.base.AppFragment;
import com.dlt.express.R;
import com.dlt.express.main.PutOffActivity;
import com.dlt.express.main.PutOnActivity;

/**
 * 描述：服务端
 * 作者：Zhout
 * 日期：2020/8/9 10:45
 */
public class ServerFragment extends AppFragment implements View.OnClickListener {
    private Context context;
    private TextView tvPutOn;
    private TextView tvPutOff;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(context, R.layout.fragment_server, null);
        tvPutOn = (TextView) view.findViewById(R.id.tvPutOn);
        tvPutOff = (TextView) view.findViewById(R.id.tvPutOff);
        tvPutOn.setOnClickListener(this);
        tvPutOff.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvPutOn:
                startActivity(new Intent(context, PutOnActivity.class));
                break;
            case R.id.tvPutOff:
                startActivity(new Intent(context, PutOffActivity.class));
                break;
        }
    }
}
