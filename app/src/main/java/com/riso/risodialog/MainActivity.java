package com.riso.risodialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.riso.risodialoglibrary.BottomDialog;
import com.riso.risodialoglibrary.CenterDialog;

import java.util.Arrays;


/**
 * @author wangl
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void btnClick(View view) {
        new CenterDialog()
                .setTitle("友情提示")
                .setDes("天冷多穿衣服,注意保暖~")
                .setOnCenterDialogClick(new CenterDialog.OnCenterDialogClick() {
                    @Override
                    public void onBtnClick(CenterDialog centerDialog, boolean clickBtn, String inputContent) {
                        Toast.makeText(MainActivity.this, clickBtn ? "点击了确定" + inputContent : "点击了取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .show(getSupportFragmentManager());
    }

    public void btnClick1(View view) {
        new BottomDialog<String>()
                .setTitle("")
                .setDes("")
                .setDataList(Arrays.asList("男", "女"))
                .setOnBottomDialogClick(new BottomDialog.OnBottomDialogClick<String>() {
                    @Override
                    public void onItemClick(BottomDialog centerDialog, int clickPosition, String itemBean) {
                        if (-1 == clickPosition) {
                            Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, itemBean, Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show(getSupportFragmentManager());

    }
}
