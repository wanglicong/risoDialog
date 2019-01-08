package com.riso.risodialoglibrary;

import android.support.annotation.ColorRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 作用:
 *
 * @author: 王黎聪
 * 创建时间: 2019/1/4.
 */
public class CenterDialog extends RisoDialog implements View.OnClickListener {

    public View contentView;
    private OnCenterDialogClick onCenterDialogClick;
    private TextView tv_title;
    private TextView tv_des;
    private TextView tv_cancel;
    private TextView tv_ok;
    private EditText et_input;


    private String title;
    private String des;
    private String cancel = "取消";
    private String ok = "确定";
    private String hint;


    private int colorCancel = R.color.rdColorCancel;
    private int colorOk = R.color.rdColorOk;

    private boolean autoDismiss = true;

    @Override
    public View getContentView(LayoutInflater inflater) {
        contentView = inflater.inflate(R.layout.rd_dialog_riso_center, null);
        initView();
        return contentView;
    }


    private void initView() {
        tv_title = contentView.findViewById(R.id.tv_title);
        tv_des = contentView.findViewById(R.id.tv_des);
        tv_cancel = contentView.findViewById(R.id.tv_cancel);
        tv_ok = contentView.findViewById(R.id.tv_ok);
        et_input = contentView.findViewById(R.id.et_input);
        tv_cancel.setTextColor(getResources().getColor(colorCancel));
        tv_ok.setTextColor(getResources().getColor(colorOk));
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

        //设置描述
        if (!TextUtils.isEmpty(hint)) {
            et_input.setVisibility(View.VISIBLE);
            et_input.setHint(hint);
        }

        //设置取消
        if (TextUtils.isEmpty(cancel)) {
            tv_cancel.setVisibility(View.GONE);
            contentView.findViewById(R.id.v_center).setVisibility(View.GONE);
            tv_ok.setBackgroundResource(R.drawable.rd_selector_dialog_bottom);
        } else {
            tv_cancel.setText(cancel);
            tv_cancel.setOnClickListener(this);
        }
        tv_ok.setText(ok);
        tv_ok.setOnClickListener(this);
    }


    /**
     * 设置空 就会隐藏
     */
    public CenterDialog setTitle(String title) {
        this.title = title;
        if (null != tv_title) {
            tv_title.setText(title);
            tv_title.setVisibility(TextUtils.isEmpty(title) ? View.GONE : View.VISIBLE);
        }
        return this;
    }

    /**
     * 设置空 就会隐藏
     */
    public CenterDialog setDes(String des) {
        this.des = des;
        if (null != tv_des) {
            tv_des.setText(des);
            tv_des.setVisibility(TextUtils.isEmpty(des) ? View.GONE : View.VISIBLE);
        }
        return this;
    }

    /**
     * 默认文字 取消
     * 设置空 就会隐藏  只剩下确定按钮
     */
    public CenterDialog setCancel(String cancel) {
        this.cancel = cancel;
        if (null != tv_cancel) {
            tv_cancel.setText(cancel);
            boolean emptyCancel = TextUtils.isEmpty(cancel);
            tv_cancel.setVisibility(emptyCancel ? View.GONE : View.VISIBLE);
            contentView.findViewById(R.id.v_center).setVisibility(emptyCancel ? View.GONE : View.VISIBLE);
            tv_ok.setBackgroundResource(emptyCancel ? R.drawable.rd_selector_dialog_bottom : R.drawable.rd_selector_dialog_right);
        }
        return this;
    }


    /**
     * 默认文案  确定
     */
    public CenterDialog setOk(String ok) {
        this.ok = ok;
        if (null != tv_ok) {
            tv_ok.setText(ok);
        }
        return this;
    }

    /**
     * 默认隐藏  设置 提示文案 就展示
     */
    public CenterDialog setHint(String hint) {
        this.hint = hint;
        //设置描述
        if (null != et_input) {
            et_input.setHint(hint);
            et_input.setVisibility(TextUtils.isEmpty(hint) ? View.GONE : View.VISIBLE);
        }
        return this;
    }

    /**
     * 点击按钮 弹窗自动消失
     */
    public CenterDialog setAutoDismiss(boolean autoDismiss) {
        this.autoDismiss = autoDismiss;
        return this;
    }

    /**
     * 设置取消颜色
     */
    public CenterDialog setCancelColor(@ColorRes int colorCancel) {
        this.colorCancel = colorCancel;
        if (null != tv_cancel) {
            tv_cancel.setTextColor(getResources().getColor(colorCancel));
        }
        return this;
    }

    /**
     * 设置确定颜色
     *
     * @param colorOk
     * @return
     */
    public CenterDialog setColorOk(@ColorRes int colorOk) {
        this.colorOk = colorOk;
        if (null != tv_ok) {
            tv_ok.setTextColor(getResources().getColor(colorOk));
        }
        return this;
    }

    /**
     * 设置点击事件
     */
    public CenterDialog setOnCenterDialogClick(OnCenterDialogClick onCenterDialogClick) {
        this.onCenterDialogClick = onCenterDialogClick;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v == tv_ok) {
            if (null != onCenterDialogClick) {
                String inputText = "";
                if (null != et_input && et_input.getVisibility() == View.VISIBLE) {
                    inputText = et_input.getText().toString();
                }
                onCenterDialogClick.onBtnClick(this, true, inputText);
            }
        } else if (v == tv_cancel) {
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
        void onBtnClick(CenterDialog centerDialog, boolean clickBtn, String inputContent);
    }
}
