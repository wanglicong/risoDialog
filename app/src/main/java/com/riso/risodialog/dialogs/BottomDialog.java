package com.riso.risodialog.dialogs;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.riso.risodialog.R;

/**
 * 作用:
 *
 * @author: 王黎聪
 * 创建时间: 2019/1/4.
 */
public class BottomDialog extends RisoDialog implements View.OnClickListener {

    public View contentView;
    private OnCenterDialogClick onCenterDialogClick;
    private TextView tv_title;
    private TextView tv_des;
    private TextView tv_cancel;


    private String title;
    private String des;
    private String cancel = "取消";

    private boolean autoDismiss = true;
    public ListView lv_list;

    @Override
    public View getContentView(LayoutInflater inflater) {
        contentView = inflater.inflate(R.layout.dialog_riso_center, null);
        initView();
        return contentView;
    }


    private void initView() {
        tv_title = contentView.findViewById(R.id.tv_title);
        tv_des = contentView.findViewById(R.id.tv_des);
        tv_cancel = contentView.findViewById(R.id.tv_cancel);
        lv_list = contentView.findViewById(R.id.lv_list);
        //设置标题
        if (TextUtils.isEmpty(title)) {
            tv_title.setVisibility(View.GONE);
        } else {
            tv_title.setText(title);
        }
        //设置描述
        if (TextUtils.isEmpty(des)) {
            tv_des.setVisibility(View.GONE);
        } else {
            tv_des.setText(des);
        }

        if (tv_title.getVisibility() == View.GONE && tv_des.getVisibility() == View.GONE) {
            contentView.findViewById(R.id.v_line).setVisibility(View.GONE);
        }
        //设置取消
        if (TextUtils.isEmpty(cancel)) {
            tv_cancel.setVisibility(View.GONE);
        } else {
            tv_cancel.setText(cancel);
            tv_cancel.setOnClickListener(this);
        }
    }


    /**
     * 设置标题  , 空 就会隐藏
     */
    public BottomDialog setTitle(String title) {
        this.title = title;
        if (null != tv_title) {
            tv_title.setText(title);
            tv_title.setVisibility(TextUtils.isEmpty(title) ? View.GONE : View.VISIBLE);
            if (tv_title.getVisibility() == View.GONE && tv_des.getVisibility() == View.GONE) {
                contentView.findViewById(R.id.v_line).setVisibility(View.GONE);
            }
        }
        return this;
    }

    /**
     * 设置描述, 空 就会隐藏
     */
    public BottomDialog setDes(String des) {
        this.des = des;
        if (null != tv_des) {
            tv_des.setText(des);
            tv_des.setVisibility(TextUtils.isEmpty(des) ? View.GONE : View.VISIBLE);
            if (tv_title.getVisibility() == View.GONE && tv_des.getVisibility() == View.GONE) {
                contentView.findViewById(R.id.v_line).setVisibility(View.GONE);
            }
        }
        return this;
    }

    /**
     * 默认文字 取消
     * 设置空 就会隐藏  只剩下确定按钮
     */
    public BottomDialog setCancel(String cancel) {
        this.cancel = cancel;
        if (null != tv_cancel) {
            tv_cancel.setText(cancel);
            boolean emptyCancel = TextUtils.isEmpty(cancel);
            tv_cancel.setVisibility(emptyCancel ? View.GONE : View.VISIBLE);
            contentView.findViewById(R.id.v_center).setVisibility(emptyCancel ? View.GONE : View.VISIBLE);
        }
        return this;
    }


    /**
     * 点击按钮 弹窗自动消失
     */
    public BottomDialog setAutoDismiss(boolean autoDismiss) {
        this.autoDismiss = autoDismiss;
        return this;
    }

    /**
     * 设置点击事件
     */
    public BottomDialog setOnCenterDialogClick(OnCenterDialogClick onCenterDialogClick) {
        this.onCenterDialogClick = onCenterDialogClick;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v == tv_cancel) {
            if (null != onCenterDialogClick) {
                onCenterDialogClick.onBtnClick(this, false, "");
            }
        }
        if (autoDismiss && !isRemoving()) {
            dismiss();
        }
    }


    /**
     * 接口回调
     */
    public interface OnCenterDialogClick {
        /**
         * @param centerDialog 弹框本身
         * @param clickBtn     true=确认 ; false=取消
         * @param inputContent 输入框内容
         */
        void onBtnClick(BottomDialog centerDialog, boolean clickBtn, String inputContent);
    }
}
