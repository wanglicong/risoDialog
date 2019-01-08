package com.riso.risodialoglibrary;

import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作用: 从下面 弹出的 dialog
 *
 * @author: 王黎聪
 * 创建时间: 2019/1/4.
 */
public class BottomDialog<T> extends RisoDialog implements View.OnClickListener {

    public View contentView;
    private OnBottomDialogClick onBottomDialogClick;
    private TextView tv_title;
    private TextView tv_des;
    private TextView tv_cancel;
    private List<T> dataList = new ArrayList<>();


    private String title;
    private String des;
    private String cancel = "取消";
    private Typeface typeface = null;

    private boolean autoDismiss = true;
    public ListView lv_list;
    private LayoutInflater inflater;
    private BottomDialogAdapter bottomDialogAdapter;

    private int colorCancel = R.color.rdColorCancel;

    public BottomDialog() {
        //设置从下弹出的 动画
        styleTheme = R.style.RisoDialogBottom;
    }

    @Override
    public View getContentView(LayoutInflater inflater) {
        this.inflater = inflater;
        contentView = inflater.inflate(R.layout.rd_dialog_riso_bottom, null);
        initView();
        contentView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return contentView;
    }


    private void initView() {
        tv_title = contentView.findViewById(R.id.tv_title);
        tv_des = contentView.findViewById(R.id.tv_des);
        tv_cancel = contentView.findViewById(R.id.tv_cancel);
        lv_list = contentView.findViewById(R.id.lv_list);
        tv_cancel.setTextColor(getResources().getColor(colorCancel));
        if (null != typeface) {
            tv_cancel.setTypeface(typeface);
        }

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
            contentView.findViewById(R.id.ll_list).setPadding(0, 0, 0, 0);
        }
        //设置取消
        if (TextUtils.isEmpty(cancel)) {
            tv_cancel.setVisibility(View.GONE);
        } else {
            tv_cancel.setText(cancel);
            tv_cancel.setOnClickListener(this);
        }
        //设置适配器
        bottomDialogAdapter = new BottomDialogAdapter();
        lv_list.setAdapter(bottomDialogAdapter);
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (null != onBottomDialogClick) {
                    T itemData = null;
                    if (dataList.size() > position) {
                        itemData = dataList.get(position);
                    }
                    onBottomDialogClick.onItemClick(BottomDialog.this, position, itemData);
                }
            }
        });
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
                contentView.findViewById(R.id.ll_list).setPadding(0, 0, 0, 0);
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
                contentView.findViewById(R.id.ll_list).setPadding(0, 0, 0, 0);
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
     * 设置 取消字体颜色
     */
    public BottomDialog setCancelColor(@ColorRes int colorCancel) {
        this.colorCancel = colorCancel;
        if (null != tv_cancel) {
            tv_cancel.setTextColor(getResources().getColor(colorCancel));
        }
        return this;
    }

    /**
     * 设置取消 加粗
     */
    public BottomDialog setCancelFont(Typeface typeface) {
        this.typeface = typeface;
        if (null != tv_cancel) {
            tv_cancel.setTypeface(typeface);
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
    public BottomDialog setOnBottomDialogClick(OnBottomDialogClick<T> onBottomDialogClick) {
        this.onBottomDialogClick = onBottomDialogClick;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v == tv_cancel) {
            if (null != onBottomDialogClick) {
                onBottomDialogClick.onItemClick(this, -1, null);
            }
        }
        if (autoDismiss && !isRemoving()) {
            dismiss();
        }
    }

    /**
     * 设置展示数据
     *
     * @param dataList 展示数据 , 对象一定要 重写 toString方法
     */
    public BottomDialog setDataList(List<T> dataList) {
        this.dataList = dataList;
        if (null != bottomDialogAdapter) {
            bottomDialogAdapter.notifyDataSetChanged();
        }
        return this;
    }

    public class BottomDialogAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = inflater.inflate(R.layout.rd_dialog_bottom_item, null);
            }
            TextView tv = (TextView) convertView;
            tv.setText(dataList.get(position).toString());
            return convertView;
        }
    }


    /**
     * 接口回调
     */
    public interface OnBottomDialogClick<T> {
        /**
         * @param centerDialog  弹框本身
         * @param clickPosition -1====取消
         */
        void onItemClick(BottomDialog centerDialog, int clickPosition, T itemBean);
    }

}
