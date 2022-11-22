package com.dlt.express.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.List;

/**
 * 说明 : 输入法工具类
 */

public class InputMethodUtils {
    private static InputMethodManager imm;

    /**
     * 显示输入法
     *
     * @param context
     * @param focusView
     */
    public static void show(Context context, View focusView) {
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(focusView, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 隐藏输入法
     *
     * @param context
     */
    public static void hide(Context context) {
        View view = ((Activity) context).getWindow().peekDecorView();
        if (view != null && view.getWindowToken() != null) {
            imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 隐藏对话框中的输入法
     * @param context
     * @param dialog
     */
    public static void hideDialogKeyboard(Context context, Dialog dialog) {
        if (dialog != null && dialog.getCurrentFocus() != null) {
            InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(dialog.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 调用该方法；键盘若显示则隐藏; 隐藏则显示
     *
     * @param context
     */
    public static void toggle(Context context) {
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 判断InputMethod的当前状态
     *
     * @param context
     * @param focusView
     * @return
     */
    public static boolean isShow(Context context, View focusView) {
        Object obj = context.getSystemService(Context.INPUT_METHOD_SERVICE);
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean bool = imm.isActive(focusView);
        List<InputMethodInfo> mInputMethodProperties = imm.getEnabledInputMethodList();

        final int N = mInputMethodProperties.size();
        for (int i = 0; i < N; i++) {
            InputMethodInfo imi = mInputMethodProperties.get(i);
            if (imi.getId().equals(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD))) {
                //imi contains the information about the keyboard you are using
                break;
            }
        }
        return bool;
    }

    public static void formatMoneyEdt(final EditText editText) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0, s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

