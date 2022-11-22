package com.dlt.express.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.LeadingMarginSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;

public class ViewUtil {


    /**
     * 动态设置控件的Margin
     *
     * @param view   控件
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public static void setMargins(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    private static long lastClickTime;

    /**
     * 限制控件被连续快速点击( 1s内 )
     *
     * @return true 快速点击了； false 禁止快速点击
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD > 0 && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 设置tablayout的indicator长度
     *
     * @param tabs
     * @param leftDip
     * @param rightDip //用了换肤功能以后这个方法不生效了
     */
    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
//            tabStrip = tabLayout.getDeclaredField("mTabStrip");
            // 采用了换肤框架，使用这一句tabLayout.getSuperclass()
            tabStrip = tabLayout.getSuperclass().getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        if (tabStrip != null) {
            tabStrip.setAccessible(true);
            LinearLayout llTab = null;
            try {
                llTab = (LinearLayout) tabStrip.get(tabs);
                int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
                int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

                for (int i = 0; i < llTab.getChildCount(); i++) {
                    View child = llTab.getChildAt(i);
                    child.setPadding(0, 0, 0, 0);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                    params.leftMargin = left;
                    params.rightMargin = right;
                    child.setLayoutParams(params);
                    child.invalidate();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }


    /**
     * 为图片渲染颜色
     *
     * @param drawable
     * @param colors
     * @return
     */
    public static Drawable tintDrawable(Drawable drawable, int colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(wrappedDrawable, colors);
        return wrappedDrawable;
    }

    public static Drawable tintWhite(Drawable drawable) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#FFFFFF"));
        return wrappedDrawable;
    }

    public static Drawable tintGray(Drawable drawable) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#989898"));
        return wrappedDrawable;
    }

    public static Drawable tintBrown(Drawable drawable) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#1a1a1a"));
        return wrappedDrawable;
    }

    /**
     * 渲染TextView中drawable的图片颜色
     *
     * @param context
     * @param tv
     * @param index   left:0 top:1 right:2 bottom 3
     * @param colorId 资源id ：如R.color.white
     * @attr ref android.R.styleable#TextView_drawableLeft
     * @attr ref android.R.styleable#TextView_drawableTop
     * @attr ref android.R.styleable#TextView_drawableRight
     * @attr ref android.R.styleable#TextView_drawableBottom
     */
    public static void tintTextDrawable(Context context, TextView tv, int index, int colorId) {
        Drawable[] drawables = tv.getCompoundDrawables();
        if (drawables[index] != null) {
            drawables[index].setColorFilter(ContextCompat.getColor(context, colorId), PorterDuff.Mode.SRC_ATOP);
        }
    }

    /**
     * 渲染TextView中drawable的图片颜色
     *
     * @param context
     * @param tv
     * @param index   left:0 top:1 right:2 bottom 3
     * @param colorId 资源id ：如R.color.white
     * @attr ref android.R.styleable#TextView_drawableStart
     * @attr ref android.R.styleable#TextView_drawableTop
     * @attr ref android.R.styleable#TextView_drawableEnd
     * @attr ref android.R.styleable#TextView_drawableBottom
     */
    public static void tintTextDrawableRelative(Context context, TextView tv, int index, int colorId) {
        Drawable[] drawables = tv.getCompoundDrawablesRelative();
        if (drawables[index] != null) {
            drawables[index].setColorFilter(ContextCompat.getColor(context, colorId), PorterDuff.Mode.SRC_ATOP);
        }
    }

    /**
     * 渲染ImageView颜色
     *
     * @param context
     * @param iv
     * @param colorId 资源id ：如R.color.white
     */
    public static void tintImageView(Context context, ImageView iv, int colorId) {
        iv.setColorFilter(ContextCompat.getColor(context, colorId));
    }

    /**
     * 设置TextView 的字体大小
     *
     * @param textView 目标TextView
     * @param size     字号
     */
    public static void setTextViewSize(TextView textView, int size) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
    }

    public static void setClearVisible(EditText et, ImageView ivClear) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(et.getText().toString())) {
                    ivClear.setVisibility(View.GONE);
                } else {
                    ivClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus || TextUtils.isEmpty(et.getText().toString())) {
                    ivClear.setVisibility(View.GONE);
                } else {
                    ivClear.setVisibility(View.VISIBLE);
                }

            }
        });
        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText("");
            }
        });
    }


    /**
     * TextView首行缩进
     *
     * @param text
     * @param marginFirstLine      首行缩进距离
     * @param marginRemainingLines 剩余行缩进距离
     * @return
     */
    public static SpannableString createIndentedText(String text, int marginFirstLine, int marginRemainingLines) {
        SpannableString result = new SpannableString(text);
        result.setSpan(new LeadingMarginSpan.Standard(marginFirstLine, marginRemainingLines), 0, text.length(), 0);
        return result;
    }

    public static void setTextViewDrawableColor(Context context, TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN));
            }
        }
    }

}


