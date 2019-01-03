package com.riso.risodialog.dialogs;

import android.content.Context;
import android.view.View;

import com.riso.risodialog.R;

/**
 * 作用:
 *
 * @author: 王黎聪
 * 创建时间: 2019/1/3.
 */
public class CenterDialogView implements DialogView {


    private View contentView;

    @Override
    public View getContentView(Context context) {
        contentView = View.inflate(context, R.layout.dialog_riso_center, null);
        return contentView;
    }
}
