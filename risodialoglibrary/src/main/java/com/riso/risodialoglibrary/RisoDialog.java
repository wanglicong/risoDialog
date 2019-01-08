package com.riso.risodialoglibrary;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * 作用: 弹窗基类 .  是用来集成 ,, 这个 作用 就是 规范了 style
 *
 * @author: 王黎聪
 *
 * 创建时间: 2019/1/3.
 */
public abstract class RisoDialog extends DialogFragment {

    public int styleTheme = R.style.RisoDialog;
    public LinearLayout rootView;
    private boolean windowCloseOnTouchOutside = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (LinearLayout) inflater.inflate(R.layout.rd_dialog_riso, container);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击空白位置
                if (windowCloseOnTouchOutside) {
                    dismiss();
                }
            }
        });
        View contentView = getContentView(inflater);
        if (null != contentView) {
            rootView.addView(contentView);
        }
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置 Dialog 风格
        setStyle(DialogFragment.STYLE_NORMAL, styleTheme);
        setWindowCloseOnTouchOutside(windowCloseOnTouchOutside);

    }

    public abstract View getContentView(LayoutInflater inflater);


    public void show(FragmentManager manager) {
        super.showNow(manager, "risoDialog");
        Dialog dialog = getDialog();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(attributes);
    }


    public boolean isWindowCloseOnTouchOutside() {
        return windowCloseOnTouchOutside;
    }

    public void setWindowCloseOnTouchOutside(boolean windowCloseOnTouchOutside) {
        this.windowCloseOnTouchOutside = windowCloseOnTouchOutside;
    }
}
