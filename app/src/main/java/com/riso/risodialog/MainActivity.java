package com.riso.risodialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.riso.risodialog.dialogs.CenterDialog;

import java.util.Calendar;
import java.util.TimeZone;

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


        System.out.println(Calendar.getInstance(TimeZone.getTimeZone("GMT+8")).getTime().getTime()+"==111==");
        System.out.println(System.currentTimeMillis()+"==2222==");
    }
}
