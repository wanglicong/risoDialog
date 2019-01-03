package com.riso.risodialog.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.riso.risodialog.R;

/**
 * 作用:
 *
 * @author: 王黎聪
 * 创建时间: 2019/1/3.
 */
public class RisoDialog extends DialogFragment implements View.OnClickListener {

    private int styleTheme = R.style.RisoDialog;
    private LinearLayout rootView;
    private View contentView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (LinearLayout) inflater.inflate(R.layout.dialog_riso, container);
        rootView.setOnClickListener(this);
        System.out.println("111111111111111");
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
        System.out.println("22222222");
    }

    public RisoDialog setContentView(View contentView) {
        this.contentView = contentView;
        System.out.println("3333333");
        return this;
    }


    @Override
    public void onClick(View view) {
        if (view == rootView) {
            dismiss();
        }
    }
}
