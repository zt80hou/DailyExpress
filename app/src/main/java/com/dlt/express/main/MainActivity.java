package com.dlt.express.main;


import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dlt.express.Constants;
import com.dlt.express.R;
import com.dlt.express.api.LoginBean;
import com.dlt.express.api.UserApi;
import com.dlt.express.base.AppActivity;
import com.dlt.express.base.AppCallBack;
import com.dlt.express.base.TabsPagerAdapter;
import com.dlt.express.main.fragment.HomeFragment;
import com.dlt.express.main.fragment.MineFragment;
import com.dlt.express.module.user.LoginEvent;
import com.dlt.express.util.ViewUtil;
import com.hzlh.sdk.util.SPUtils;
import com.hzlh.sdk.util.YLog;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppActivity {
    private Context context = this;
    private List<String> tabTitles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private ViewPager viewPager;
    private TextView tabHome;
    private TextView tabMine;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabTitles.add("首页");
        tabTitles.add("我的");
        fragments.add(new HomeFragment());
        fragments.add(new MineFragment());

        viewPager = findViewById(R.id.viewPager);
        tabHome = findViewById(R.id.tabHome);
        tabMine = findViewById(R.id.tabMine);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                initTabs(i);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        initTabs(0);
        tabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });
        tabMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });

        TabsPagerAdapter fragmentAdapter = new TabsPagerAdapter(getSupportFragmentManager(), tabTitles, fragments);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(fragmentAdapter);

        startRefreshTimer();
    }

    private void initTabs(int pos) {
        switch (pos) {
            case 0:
                tabHome.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                ViewUtil.setTextViewDrawableColor(context, tabHome, R.color.colorPrimary);
                tabMine.setTextColor(context.getResources().getColor(R.color.font_dark));
                ViewUtil.setTextViewDrawableColor(context, tabMine, R.color.font_dark);
                break;
            case 1:
                tabHome.setTextColor(context.getResources().getColor(R.color.font_dark));
                ViewUtil.setTextViewDrawableColor(context, tabHome, R.color.font_dark);
                tabMine.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                ViewUtil.setTextViewDrawableColor(context, tabMine, R.color.colorPrimary);
                break;
        }

    }


    @Subscribe
    public void onEvent(LoginEvent event) {
        if (event.isLogin()) {
            startRefreshTimer();
        }
    }


    /**
     * 刷新token
     */
    private void refreshToken() {
        if (Constants.isLogin()) {
            String account = SPUtils.getInstance(this).getString(Constants.SP_KEY_LOGIN_ACCOUNT);
            String pwd = SPUtils.getInstance(this).getString(Constants.SP_KEY_LOGIN_PWD);
            new UserApi().login(this, account, pwd, new AppCallBack<LoginBean>(this) {
                @Override
                public void onResultOk(LoginBean result) {
                    super.onResultOk(result);
                    Constants.loginBean = result;
                    YLog.e("======开启新一轮计时器了===========");
                    // 开启新一轮计时
                    startRefreshTimer();
                }
            });
        }
    }


    public void startRefreshTimer() {
        long refreshEndTime = 8 * 60 * 60 * 1000L;// 8小时刷新一次token
        CountDownTimer timer = new CountDownTimer(refreshEndTime, 1000) {
            @Override
            public void onTick(long l) {
//                YLog.d("刷新token倒计时剩余：" + l / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                refreshToken();
            }
        };
        timer.start();
    }
}
